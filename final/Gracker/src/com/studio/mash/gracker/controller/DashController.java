package com.studio.mash.gracker.controller;

import javafx.event.Event;
import javafx.stage.Stage;

public class DashController {
    private Stage stage;

    public DashController(Stage stage) {
        if (stage == null) {
            throw new IllegalArgumentException("Stage cannot be null");
        }
        this.stage = stage;
    }

    public void handleMousePress(Event event) {
        /**
         * Handles addbutton press
         */
    }
    public void handleRowPress(Event event) {
        /**
         * Handle table row click, which would show course details
         */
    }

    public void update() {
        /**
         * Called everytime the dashboard is called to update the average
         */
    }
}