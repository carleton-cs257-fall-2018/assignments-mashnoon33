/**
 * Gracker
 * by Mash Ibtesum
 * CS 257
 * Nov 11, 2018
 */
package com.studio.mash.gracker;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.studio.mash.gracker.model.Assignment;
import com.studio.mash.gracker.model.AssignmentType;
import com.studio.mash.gracker.model.Course;
import com.studio.mash.gracker.model.CourseModel;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import com.studio.mash.gracker.view.DashboardView;
import javafx.stage.WindowEvent;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main extends Application {
    public CourseModel model;

    @Override
    public void start(Stage stage) throws Exception{


        // Creates App Support dir if doesnt exist, where the app stores all its data
        // Currently only hardcoded for mac, I do not have a windows/linux machine to check
        ObjectMapper mapper =new  ObjectMapper();
        String base = System.getProperty("user.home") + "/Library/Application "  + "Support/Gracker/";
        Path d = Paths.get(base);
        if (!Files.exists(d)) {
           Files.createDirectory(d);
        }
        File f = new File(base +"Data.json");



        // If data already exists, loads that
        if(f.exists() && !f.isDirectory()) {

            CollectionType typeReference =
                    TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, Course.class);

            model = mapper.readValue(f, CourseModel.class); // restores model

            ArrayList<Course> gg= mapper.readValue(new File( base+"Course_List.json"), typeReference);
            model.setCourses(FXCollections.observableArrayList(gg)); // restores Coures list

            for (Course c : model.getCourses()) { // restores types and assignments for each course
                AssignmentType[] at = mapper.readValue(new File(base+c.getId()+"/types.json"), AssignmentType[].class);
                Assignment[] a = mapper.readValue(new File(base+c.getId()+"/assignments.json"), Assignment[].class);
                c.setTypeList(FXCollections.observableArrayList(at));
                c.setAssignments(FXCollections.observableArrayList(a));
            }
        }
        else{
            // Initilizaes a new model if no app data exists
            model = new CourseModel();
        }



        stage.setScene(new DashboardView(stage, model).getScene());
        stage.setTitle("Gracker");
        stage.show();



        // Saves the data on Close request
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                try {
                    mapper.writeValue(new File(base+"Data.json"), model); // backups everything but the ObservableLists in the model
                    ArrayList<Course> temp = new ArrayList<Course>(model.getCourses()); // Jackson cannot deserialize ObsLists, so we are converting to ArrayLists
                    mapper.writeValue(new File(base+"Course_List.json"), temp);
                    for (Course c: temp) { // There are some nested ObsLists that needs to backed up too!

                        Path dir = Paths.get(base);
                        Path dirToCreate = dir.resolve(c.getId());
                        if (!Files.exists(dirToCreate)) { //Creates directory for each course
                            Path directory = Files.createDirectory(dirToCreate);
                        }

                        ArrayList<AssignmentType> at = new ArrayList<>(c.getTypeList());
                        ArrayList<Assignment> a = new ArrayList<>(c.getAssignments());

                        mapper.writeValue(new File(base+c.getId()+"/types.json"), at);
                        mapper.writeValue(new File(base+c.getId()+"/assignments.json"), a);
                    }
                }
                catch (Exception e) {
                    System.out.println(e);
                }
                stage.close();
            }
        });

    }

    public static void main(String[] args) {
        launch(args);
    }
}
