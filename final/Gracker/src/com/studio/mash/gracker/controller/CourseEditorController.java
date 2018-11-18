package com.studio.mash.gracker.controller;

import com.studio.mash.gracker.model.CourseModel;
import javafx.event.Event;
import javafx.stage.Stage;

public class CourseEditorController {
    private Stage stage;

    public CourseEditorController(Stage stage, CourseModel model) {
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
