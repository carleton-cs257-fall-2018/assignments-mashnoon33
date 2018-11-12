package com.studio.mash.gracker.view;

import javafx.stage.Stage;
import com.studio.mash.gracker.controller.CourseController;


public class CourseView extends BaseView {

    public CourseView(Stage stage){
        /**
         * Utilizes tableview to display the assignments and respective grades, and textfield and buttons to add more stuffs
         * or manitpulate
         */

        super(stage, e -> new CourseController(stage).handleMousePress(e));
    }




}
