package com.studio.mash.gracker.controller;

import com.studio.mash.gracker.model.Course;
import com.studio.mash.gracker.model.CourseModel;
import com.studio.mash.gracker.view.CourseEditorView;
import com.studio.mash.gracker.view.CourseView;
import com.studio.mash.gracker.view.XTableView;
import javafx.event.Event;
import javafx.stage.Stage;


public class DashController {
    private Stage stage;
    private CourseModel model;
    private XTableView table;

    /**
     * Inits the controller
     * @param stage
     * @param model
     * @param table
     */
    public DashController(Stage stage, CourseModel model, XTableView table) {
        if (stage == null) {
            throw new IllegalArgumentException("Stage cannot be null");
        }
        this.stage = stage;
        this.model = model;
        this.table = table;
    }

    /**
     * Handles addbutton press
     * @param event
     */
    public void handleAddPress(Event event) {
        new CourseEditorView(model);
    }

    /**
     * Handle table row click, which would show course details
     * @param event
     */
    public void handleRowPress(Event event) {

        if (table.getSelectionModel().getSelectedItem()!=null) {
            Course clicker = (Course) table.getSelectionModel().getSelectedItem();
            this.stage.setScene(new CourseView(this.stage, clicker, this.model).getScene());
        }
    }
}