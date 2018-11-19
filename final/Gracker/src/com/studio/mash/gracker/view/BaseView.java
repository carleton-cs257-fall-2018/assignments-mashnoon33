package com.studio.mash.gracker.view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BaseView {
    /**
     * Not necessary as the app evolved, will refactor it out, if time permits
     */
    public Stage stage;
    public BorderPane root;

    /**
     * Implements the top portion of the app that is present in both CourseView and DashBoard. It shows the GPA, AVG and Letter grade
     * @param stage
     */
    public BaseView(Stage stage) {
        if (stage == null) {
            throw new IllegalArgumentException("Stage cannot be null");
        }
//
        this.stage = stage;
        root = new BorderPane();
    }

    public Scene getScene() {
        return new Scene(root,  340, 600);
    }


}
