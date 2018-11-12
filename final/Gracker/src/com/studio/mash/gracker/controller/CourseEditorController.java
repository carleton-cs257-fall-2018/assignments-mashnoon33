package com.studio.mash.gracker.controller;

import javafx.event.Event;
import javafx.stage.Stage;

public class CourseEditorController {
    private Stage stage;

    public CourseEditorController(Stage stage) {
        if (stage == null) {
            throw new IllegalArgumentException("Stage cannot be null");
        }
        this.stage = stage;
    }

    public void handleMousePress(Event event) {
        /**
         * This and other to be addled handlers would handle all the button presses
         */

    }


}
