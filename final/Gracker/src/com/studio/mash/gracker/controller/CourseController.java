package com.studio.mash.gracker.controller;

import com.studio.mash.gracker.model.AssignmentType;
import com.studio.mash.gracker.model.Course;
import com.studio.mash.gracker.model.CourseModel;
import com.studio.mash.gracker.view.CourseEditorView;
import com.studio.mash.gracker.view.DashboardView;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.studio.mash.gracker.view.CourseView;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;


public class CourseController {
    private Stage stage;
    private CourseModel model;
    private CourseView view;

    /**
     * Inits the controller
     * @param stage
     * @param model
     * @param view
     */
    public CourseController(Stage stage, CourseModel model, CourseView view) {
        if (stage == null) {
            throw new IllegalArgumentException("Stage cannot be null");
        }
        this.stage = stage;
        this.model=model;
        this.view=view;
    }

    /**
     * Passes the state to dashboardView
     * @param event
     */
    public void handleBackPress(Event event) {
        /**
         * Handles back button press, should name it better
         */

        stage.setScene(new DashboardView(stage,model).getScene());
    }

    /**
     * Creates a dialog to allow editing the course
     * @param event
     * @param course
     */
    public void handleEditPress(Event event, Course course) {
        new CourseEditorView(course, view, false);
    }

    /**
     *  Handles submit button press, retrives the name, number and types from the textfiend and comboboxes
     * @param event
     * @param view
     * @param course
     * @param assignmentName
     * @param typeComboBox
     * @param grade
     */
    public void handleNewGradeAdded(Event event, CourseView view, Course course, TextField assignmentName, ComboBox typeComboBox, TextField grade){

        boolean a = assignmentName.getText().trim().isEmpty();
        boolean b = typeComboBox.getSelectionModel().isEmpty();
        boolean c = grade.getText().trim().isEmpty();
        removeRed(assignmentName);
        removeRed(typeComboBox);
        removeRed(grade);

        if (a) {
            ObservableList<String> styleClass = assignmentName.getStyleClass();
            if(!styleClass.contains("tferror")) {
                styleClass.add("tferror");
            }
        }
        if (b) {
            ObservableList<String> styleClass = typeComboBox.getStyleClass();
            if(!styleClass.contains("tferror")) {
                styleClass.add("tferror");
            }
            if(course.getTypeList().isEmpty()) {
                new CourseEditorView(course, view, true);
            }
        }
        if (c) {
            ObservableList<String> styleClass = grade.getStyleClass();
            if (!styleClass.contains("tferror")) {
                styleClass.add("tferror");
            }
        }

        if (!a && !b && !c) {
            course.addAssignment(assignmentName.getText(),(float)Integer.parseInt(grade.getText()),(AssignmentType)typeComboBox.getSelectionModel().getSelectedItem());
            assignmentName.clear();
            typeComboBox.getSelectionModel().clearSelection();
            typeComboBox.setPromptText("Select");
            grade.clear();
            view.gradeSummary();
        }

    }

    /**
     * Removes read border from any of the control elements (Textfield / Combo Box)
     * @param tf
     */
    private void removeRed(Control tf) {

        ObservableList<String> styleClass = tf.getStyleClass();
        styleClass.removeAll(Collections.singleton("tferror"));
    }

    /**
     * Deletes the directory of the deleted course
     * @param event
     * @param course
     */
    public void handleDeletePress(Event event, Course course){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Course");
        alert.setHeaderText("Are you sure that you want to delete " + course.getName()+ " ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            this.model.courses.remove(course);
            stage.setScene(new DashboardView(stage,model).getScene());
            try {
                delete(new File("/Users/mashny/Movies/" + course.getId() + "/"));
            }
            catch (Exception e) {
                System.out.println(e);
            }
        } else {

        }


    }

    /**
     * Delete a file or a directory and its children.
     * @param file The directory to delete.
     * @throws IOException Exception when problem occurs during deleting the directory.
     */
    private static void delete(File file) throws IOException {

        for (File childFile : file.listFiles()) {

            if (childFile.isDirectory()) {
                delete(childFile);
            } else {
                if (!childFile.delete()) {
                    throw new IOException();
                }
            }
        }

        if (!file.delete()) {
            throw new IOException();
        }
    }

}
