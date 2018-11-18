/**
 * Gracker
 * by Mash Ibtesum
 * CS 257
 * Nov 11, 2018
 */
package com.studio.mash.gracker;

import com.studio.mash.gracker.model.CourseModel;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import com.studio.mash.gracker.view.DashboardView;
import javafx.stage.WindowEvent;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;

public class Main extends Application {
    public CourseModel model;

    @Override
    public void start(Stage stage) throws Exception{
        model = new CourseModel();

        stage.setScene(new DashboardView(stage, model).getScene());
        stage.setTitle("Gracker");
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                System.out.println("Stage is closing");
                ObjectMapper mapper = new ObjectMapper();
//                try {
//                    mapper.writeValue(new File("/Users/mashny/Movies/cat.json"), model);
//                }
//                catch (Exception e) {
//                    System.out.println(e);
//                }
                stage.close();
            }
        });


    }

    public static void main(String[] args) {
        launch(args);
    }
}
