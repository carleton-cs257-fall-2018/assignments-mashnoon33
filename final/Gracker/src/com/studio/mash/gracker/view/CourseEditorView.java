package com.studio.mash.gracker.view;

import com.studio.mash.gracker.controller.CourseEditorController;
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

import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.Pair;

import java.util.Optional;
import java.util.function.UnaryOperator;

public class CourseEditorView  {
    private final TableView table = new TableView();
    private CourseModel model=this.model;
    private  ObservableList<AssignmentType> typeList;
    private String name;
    private Course course;
    private CourseView view;
    private ButtonType addCourse;
    private boolean a = true;
    private boolean b = true;
    private CourseEditorController controller;

    /**
     * Gnereates a dialogue with option to add a course, and assignment types and their respective weights
     * @param model
     */
    public CourseEditorView(CourseModel model) {
        // Create the custom dialog.
        this.name= "";
        this.model=model;
        this.typeList =  FXCollections.observableArrayList();
        this.controller= new CourseEditorController(model,typeList);
        renderDialogue(false, false);
    }

    /**
     * Generates an dialogue to update an existing course
     * @param course
     * @param view
     * @param noType
     */
    public CourseEditorView(Course course, CourseView view, Boolean noType) {
        this.typeList=course.getTypeList();
        this.name=course.getName();
        this.course=course;
        this.view=view;
        this.course=course;
        this.controller= new CourseEditorController(course,view,typeList);
        renderDialogue(true, noType);
    }

    /**
     * Renders a dialogue
     * @param update Whether its an update call or adding a new course
     * @param notype Boolean indicating wheter the course has any types or not
     */
    public void renderDialogue(Boolean update, Boolean notype) {
        //
        // Initiate the dialogue iteself
        //
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Add Course");
        if (notype) {
            dialog.setHeaderText("Please add atleast one Assignment Type before adding an Assignment");
            dialog.getDialogPane().getStylesheets().add("redHeader.css");

        }
        else {
            dialog.setHeaderText("Use the form below to add the different types of Assignments and their weights\n" +
                    "E.g :  Final Project -- 20% \n");
            dialog.getDialogPane().getStylesheets().add("styles.css");
        }

// Set the button types, depending on the context
        if (!update) {
            addCourse = new ButtonType("Add Course", ButtonBar.ButtonData.OK_DONE);}
        else {
            addCourse = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
            }
        dialog.getDialogPane().getButtonTypes().addAll(addCourse, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        //
        // Sets up the tableview
        //

        //Creates tablecolumns
        TableColumn typeColumn = new TableColumn("Type");
        typeColumn.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        TableColumn weightColumn = new TableColumn("%");
        weightColumn.setCellValueFactory(
                new PropertyValueFactory<>("weight")
        );
        table.getColumns().addAll(typeColumn,weightColumn);

        table.setItems(typeList);
        table.setMinWidth(300);
        table.setMaxHeight(230);
        table.setPlaceholder(new Text("No types added"));
        table.setColumnResizePolicy( TableView.CONSTRAINED_RESIZE_POLICY );
        typeColumn.setMaxWidth( 1f * Integer.MAX_VALUE * 70 );
        weightColumn.setMaxWidth( 1f * Integer.MAX_VALUE * 30 );
        // Sets up right click on tablview
        table.setRowFactory(new Callback<TableView, TableRow>() { // Stays in the view instead of controller due to tableview dependencies
            @Override
            public TableRow call(TableView tableView) {
                final TableRow row = new TableRow();
                final ContextMenu contextMenu = new ContextMenu();
                final MenuItem removeMenuItem = new MenuItem("Remove Type");
                removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
//                        if (update) {
//                            course.purgeType((AssignmentType) row.getItem());
                            // Commenting out purge as it doesn't work very well
                            // For now, if you're moving a assignment type, you'd need to manually delete all of that type
//                        }
                        table.getItems().remove(row.getItem());
                        if (update) {
                            view.gradeSummary();
                        }
                        //TODO FIX PURGE --> I think there some java issues, as the == or .equals don't always function as expected to

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

        //
        // Text entry forms
        //
        TextField courseNameTextField = new TextField(this.name);
        courseNameTextField.setPromptText("Course Name");
        grid.add(new Label("Course Name:"), 0, 0);
        grid.add(courseNameTextField, 1, 0);

// Some form validation (using the Java 8 lambda syntax), to enable and disable the add/update button
        if (!update) {
            Node loginButton = dialog.getDialogPane().lookupButton(addCourse);
            loginButton.setDisable(true);
            courseNameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                loginButton.setDisable(newValue.trim().isEmpty());
            });
        }

        TextField typeTextField = new TextField();
        typeTextField.setPromptText("Type");
        typeTextField.setMaxWidth(130);

        TextField weightTextField = new TextField();
        weightTextField.setPromptText("%");
        // Prevents non numerical inputs, unfortunately prevents decimals as well
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();
            if (text.matches("[0-9]*")) {
                return change;
            }
            return null;
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        weightTextField.setTextFormatter(textFormatter);
        weightTextField.setMaxWidth(50);

        Button addType = new Button("Add");
        addType.setOnAction( e -> controller.handleAddTypePress(e,typeTextField,weightTextField));

        // Bunch of Hboxes and Gridpanes to nicely organize all the UI elements
        HBox adder = new HBox();
        adder.getChildren().addAll(typeTextField,weightTextField, addType);
        adder.setSpacing(20);

        GridPane papaGrid = new GridPane();
        GridPane brotherGrid = new GridPane();
        papaGrid.add(grid,0,0);
        papaGrid.add(brotherGrid,0,1);
        brotherGrid.add(new Label("Assignment Type:"),0,0);
        brotherGrid.add(table,1,1);
        brotherGrid.setHgap(10);
        brotherGrid.setVgap(10);
        brotherGrid.add(adder, 1,0);
        brotherGrid.setHalignment(table, HPos.CENTER);
        dialog.getDialogPane().setContent(papaGrid);

        // Request focus on the username field by default.
        Platform.runLater(() -> courseNameTextField.requestFocus());

        // Convert the result to a key-val pair when the add button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addCourse) {
                return new Pair<>(courseNameTextField.getText().toString(), "");
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        // Retrives the key and adds/ or updates regarding the context
        result.ifPresent(p-> this.controller.handleAddCoursePress(p, update, courseNameTextField));
    }



}
