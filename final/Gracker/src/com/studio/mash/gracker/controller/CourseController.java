package com.studio.mash.gracker.controller;

import javafx.event.Event;
import javafx.stage.Stage;

public class CourseController {
    private Stage stage;

    public CourseController(Stage stage) {
        if (stage == null) {
            throw new IllegalArgumentException("Stage cannot be null");
        }
        this.stage = stage;
    }

    public void handleMousePress(Event event) {
        /**
         * Handles back button press, should name it better
         */

    }
    public void handleNewGradeAdded(Event event){
        /**
         * Handles submit button press, retrives data from textfields
         */

    }
    public void handleGradeUpdate(Event event){
        /**
         * Handles tableview cell updates
         */

    }
    public void calculate() {
        /**
         * Calls calculate in the model everytime something significant changes
         */
    }
}