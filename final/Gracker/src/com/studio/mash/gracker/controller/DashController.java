package com.studio.mash.gracker.controller;

import com.studio.mash.gracker.Main;
import com.studio.mash.gracker.model.Course;
import com.studio.mash.gracker.model.CourseModel;
import com.studio.mash.gracker.view.CourseEditorView;
import com.studio.mash.gracker.view.CourseView;
import com.studio.mash.gracker.view.XTableView;
import javafx.event.Event;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.Optional;

public class DashController {
    private Stage stage;
    private CourseModel model;
    private XTableView table;

    public DashController(Stage stage, CourseModel model, XTableView table) {
        if (stage == null) {
            throw new IllegalArgumentException("Stage cannot be null");
        }
        this.stage = stage;
        this.model = model;
        this.table = table;
    }

    public void handleMousePress(Event event) {
        /**
         * Handles addbutton press
         */
        new CourseEditorView(model);
    }
    public void handleRowPress(Event event) {
        /**
         * Handle table row click, which would show course details
         */
        if (table.getSelectionModel().getSelectedItem()!=null) {
            Course clicker = (Course) table.getSelectionModel().getSelectedItem();
            this.stage.setScene(new CourseView(this.stage, clicker, this.model).getScene());
        }

        // Import an array of stuffs that then we can manipulate
    }

    public void update() {
        /**
         * Called everytime the dashboard is called to update the average
         */
    }
}