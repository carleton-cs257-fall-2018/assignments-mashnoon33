/**
 * Gracker
 * by Mash Ibtesum
 * CS 257
 * Nov 11, 2018
 */
package com.studio.mash.gracker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import com.studio.mash.gracker.view.Dashboard;
import com.studio.mash.gracker.view.CourseView;
import com.studio.mash.gracker.view.CourseEditor;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        stage.setScene(new Dashboard(stage).getScene());
        stage.setTitle("Gracker");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
