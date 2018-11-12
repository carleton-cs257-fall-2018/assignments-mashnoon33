package com.studio.mash.gracker.view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class BaseView {
    private Stage stage;
    private EventHandler<? super MouseEvent> handler;

    /**
     * Implements the top portion of the app that is present in both CourseView and DashBoard. It shows the GPA, AVG and Letter grade
     */
    public BaseView(Stage stage, EventHandler handler) {
        if (stage == null) {
            throw new IllegalArgumentException("Stage cannot be null");
        }
        if (handler == null) {
            throw new IllegalArgumentException("Handler cannot be null");
        }
        this.stage = stage;
        this.handler = handler; // there would be multiple event handler in a fleshed out app, maybe utilize an array later (?)

    }

    public Scene getScene() {
        /**
         *  Serves as the base for all views. Generates common elements for all the views
         */
        BorderPane root = new BorderPane();
        return new Scene(root,  400, 600);
    }


}
