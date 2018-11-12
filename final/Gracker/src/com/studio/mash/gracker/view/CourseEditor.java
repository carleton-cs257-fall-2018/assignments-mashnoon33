package com.studio.mash.gracker.view;

import com.studio.mash.gracker.controller.CourseEditorController;
import javafx.stage.Stage;

public class CourseEditor extends BaseView {
    public CourseEditor(Stage stage) {
        /**
         * Adds ViewSpecific elements to the BaseView, for this view, it'll have multiple form elements including text fields
         * and buttons
         */

        super(stage, e -> new CourseEditorController(stage).handleMousePress(e));
    }

}
