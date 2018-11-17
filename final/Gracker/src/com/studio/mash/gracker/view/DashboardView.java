package com.studio.mash.gracker.view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
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

    @Override
    public Scene getScene() {
        /**
         *  Serves as the base for all views. Generates common elements for all the views
         */
        Label label = new Label("BaseView");
        label.setFont(new Font(32));
        root.setCenter(label);
        System.out.println("Dash");
        return new Scene(root,  400, 600);
    }



}
