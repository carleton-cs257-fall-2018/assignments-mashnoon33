package com.studio.mash.gracker.view;

import javafx.stage.Stage;
import com.studio.mash.gracker.controller.DashController;

public class Dashboard extends BaseView {
    public Dashboard(Stage stage)
    {
        /**
         * Utilizes tableview to display the courses and respective averages, and a singular button to handle adding new courses.
         * table rows are clickable as well
         * or manitpulate
         */

        super(stage, e -> new DashController(stage).handleMousePress(e));
    }



}
