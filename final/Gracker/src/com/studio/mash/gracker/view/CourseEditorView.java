package com.studio.mash.gracker.view;

import com.studio.mash.gracker.model.Assignment;
import com.studio.mash.gracker.model.AssignmentType;
import com.studio.mash.gracker.model.Course;
import com.studio.mash.gracker.model.CourseModel;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.util.Pair;

import java.util.Optional;

public class CourseEditorView  {
    private final TableView table = new TableView();
    private CourseModel model=this.model;
    private  ObservableList<AssignmentType> typeList;
    private String name;
    private Course course;
    private CourseView view;

    public CourseEditorView(CourseModel model) {
        /**
         * Adds ViewSpecific elements to the BaseView, for this view, it'll have multiple form elements including text fields
         * and buttons
         */
        // Create the custom dialog.
        this.name= "";
        this.model=model;
        this.typeList =  FXCollections.observableArrayList();
        renderDialogue(false);
    }

    public CourseEditorView(Course course, CourseView view) {
        this.typeList=course.getTypeList();
        this.name=course.getName();
        this.typeList=course.getTypeList();
        this.course=course;
        this.view=view;
        renderDialogue(true);


    }

    public void renderDialogue(Boolean update) {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.getDialogPane().getStylesheets().add("styles.css");
        dialog.setTitle("Add Course");
        dialog.setHeaderText("Add the course you wanna add");

// Set the button types.
        ButtonType addCourse;
        if (!update) {
         addCourse = new ButtonType("Add Course", ButtonBar.ButtonData.OK_DONE);}

        else {
            addCourse = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);}


        dialog.getDialogPane().getButtonTypes().addAll(addCourse, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TableColumn typeColumn = new TableColumn("Type");
        typeColumn.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        TableColumn weightColumn = new TableColumn("%");
        weightColumn.setCellValueFactory(
                new PropertyValueFactory<>("weight")
        );
        table.getColumns().addAll(typeColumn,weightColumn);
        table.setMinWidth(300);

        table.setRowFactory(new Callback<TableView, TableRow>() {
            @Override
            public TableRow call(TableView tableView) {
                final TableRow row = new TableRow();
                final ContextMenu contextMenu = new ContextMenu();
                final MenuItem removeMenuItem = new MenuItem("Remove Type");
                removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        course.purgeType((AssignmentType) row.getItem());
                        table.getItems().remove(row.getItem());
//                        course.getAverage();
                        view.gradeSummary();
                        //TODO add a prompt that says purge all the courses with that type?


                    }
                });
                contextMenu.getItems().add(removeMenuItem);
                // Set context menu on row, but use a binding to make it only show for non-empty rows:
                row.contextMenuProperty().bind(
                        Bindings.when(row.emptyProperty())
                                .then((ContextMenu)null)
                                .otherwise(contextMenu)
                );
                return row ;
            }
        });



        TextField courseName = new TextField(this.name);
        courseName.setPromptText("Course Name");



        TextField textField[] = new TextField[15];




        grid.add(new Label("Course Name:"), 0, 0);
        grid.add(courseName, 1, 0);

// Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(addCourse);

// Do some validation (using the Java 8 lambda syntax).
        if (!update) {
            courseName.textProperty().addListener((observable, oldValue, newValue) -> {
                loginButton.setDisable(newValue.trim().isEmpty());
            });
        }

        TextField type = new TextField();
        type.setPromptText("Assignment Name");
        TextField weight = new TextField();
        weight.setPromptText("%");
        type.setMaxWidth(130);
        weight.setMaxWidth(50);
        Button addType = new Button("Add");


        HBox adder = new HBox();
        adder.getChildren().addAll(type,weight, addType);
        adder.setSpacing(20);
        table.setItems(typeList);
        addType.setOnAction( e -> {
            this.typeList.add(new AssignmentType(type.getText(), Integer.parseInt(weight.getText())));
        });


        table.setMaxHeight(230);
        table.setColumnResizePolicy( TableView.CONSTRAINED_RESIZE_POLICY );
        typeColumn.setMaxWidth( 1f * Integer.MAX_VALUE * 70 );
        weightColumn.setMaxWidth( 1f * Integer.MAX_VALUE * 30 );


        GridPane papaGrid = new GridPane();
        GridPane brotherGrid = new GridPane();
        papaGrid.add(grid,0,0);
        papaGrid.add(brotherGrid,0,1);

        brotherGrid.add(new Label("Assignment Types:"),0,0);
        brotherGrid.add(table,1,1);
        brotherGrid.setHgap(10);

        brotherGrid.add(adder, 1,0);

        brotherGrid.setHalignment(table, HPos.CENTER);

        dialog.getDialogPane().setContent(papaGrid);

// Request focus on the username field by default.
        Platform.runLater(() -> courseName.requestFocus());

// Convert the result to a username-detail-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addCourse) {
                return new Pair<>(courseName.getText(), type.getText());
            }
            return null;
        });



        Optional<Pair<String, String>> result = dialog.showAndWait();
        result.ifPresent(usernamePassword -> {
            if (!update) {
                model.courses.add(new Course(usernamePassword.getKey(), this.typeList));
            }
            else  {
                this.course.setName(courseName.getText());
                this.course.setTypeList(this.typeList);
                view.updateCourseName();
            }
        });
    }



}
