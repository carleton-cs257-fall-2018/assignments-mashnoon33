package com.studio.mash.gracker.controller;

import com.studio.mash.gracker.model.AssignmentType;
import com.studio.mash.gracker.model.Course;
import com.studio.mash.gracker.model.CourseModel;
import com.studio.mash.gracker.view.CourseEditorView;
import com.studio.mash.gracker.view.DashboardView;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import com.studio.mash.gracker.view.CourseView;

import java.util.Optional;


public class CourseController {
    private Stage stage;
    private CourseModel model;
    private CourseView view;

    public CourseController(Stage stage, CourseModel model, CourseView view) {
        if (stage == null) {
            throw new IllegalArgumentException("Stage cannot be null");
        }
        this.stage = stage;
        this.model=model;
        this.view=view;
    }

    public void handleBackPress(Event event) {
        /**
         * Handles back button press, should name it better
         */

        stage.setScene(new DashboardView(stage,model).getScene());
    }

    public void handleEditPress(Event event, Course course) {
        System.out.println("Smash");
        new CourseEditorView(course, view);
    }
    public void handleNewGradeAdded(Event event,CourseView view,Course course, String name, AssignmentType type, int grade){
        /**
         * Handles submit button press, retrives courses from textfields
         */
        course.addAssignment(name,(float)grade,type);
        view.gradeSummary();
    }
    public void handleDeletePress(Event event, Course course){
        /**
         * Handles deleting courses
         */
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Course");
        alert.setHeaderText("Are you sure that you want to delete " + course.getName()+ " ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            this.model.courses.remove(course);
            stage.setScene(new DashboardView(stage,model).getScene());
        } else {

        }


    }
    public void calculate() {
        /**
         * Calls calculate in the model everytime something significant changes
         */
    }
}