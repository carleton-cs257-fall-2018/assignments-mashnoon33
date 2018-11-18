package com.studio.mash.gracker.view;

import com.studio.mash.gracker.model.Assignment;
import com.studio.mash.gracker.model.AssignmentType;
import com.studio.mash.gracker.model.Course;
import com.studio.mash.gracker.model.CourseModel;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.studio.mash.gracker.controller.CourseController;
import javafx.util.Callback;

import java.util.function.UnaryOperator;


public class CourseView extends BaseView {

    private CourseController controller;
    private CourseModel model;
    private Course course;
    private final TableView<Assignment> table = new TableView<Assignment>();
    public Text avg_header;
    public Text lg_header;
    public Text courseName;

    public CourseView(Stage stage, Course course, CourseModel model){
        /**
         * Utilizes tableview to display the assignments and respective grades, and textfield and buttons to add more stuffs
         * or manitpulate
         */

        super(stage);
        this.controller=new CourseController(stage,model,this);
        this.model=model;
        this.course=course;
    }

    public void parseCourse() {

    }

    public void gradeSummary(){
        avg_header.setText(course.getAverage().toString());
        lg_header.setText(course.getLetterGrade().toString());
    }

    public  void  updateCourseName() {
        this.courseName.setText(course.getName());
    }

    @Override
    public Scene getScene() {
        //TODO update coursname post update
        courseName = new Text(course.getName());
        courseName.setFont(Font.font("Arial", 30));
        VBox header = new VBox();
        HBox row1 = new HBox();
        row1.setPadding(new Insets(10));
        row1.getChildren().add(courseName);

        HBox row2 = new HBox();
        Text pre_avg = new Text("Course Average : ");
        Text pre_lg = new Text("GPA : ");
        pre_avg.setFont(Font.font("Arial", 15));
        pre_lg.setFont(Font.font("Arial", 15));
        pre_avg.setFill(Color.web("636363"));
        pre_lg.setFill(Color.web("636363"));

        avg_header = new Text("93.44%");
        avg_header.setFont(Font.font("Arial", 15));
        lg_header = new Text("A-");
        lg_header.setFont(Font.font("Arial", 15));
        row2.setSpacing(10);
        row2.setPadding(new Insets(0,0,0,10));
        row2.getChildren().addAll(pre_avg,avg_header,pre_lg,lg_header);
        header.getChildren().addAll(row1,row2);
        gradeSummary();

        root.setTop(header);
        parseCourse();

        table.setItems(course.assignments);

//        table.setOnMousePressed(e -> this.controller.handleRowPress(e));
        table.setColumnResizePolicy( TableView.CONSTRAINED_RESIZE_POLICY );
        TableColumn assignmentColumn = new TableColumn("Assignment");
        assignmentColumn.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        assignmentColumn.setMaxWidth( 1f * Integer.MAX_VALUE * 30 );
        TableColumn typeColumn = new TableColumn("Type");
        typeColumn.setCellValueFactory(
                new PropertyValueFactory<>("type")
        );
        typeColumn.setMaxWidth( 1f * Integer.MAX_VALUE * 20 );

        TableColumn avgGradeColumn = new TableColumn("Grade");
        avgGradeColumn.setCellValueFactory(
                new PropertyValueFactory<>("grade")
        );
        avgGradeColumn.setMaxWidth( 1f * Integer.MAX_VALUE * 10 );
        TableColumn letterGradeColumn = new TableColumn("Letter Grade");
        letterGradeColumn.setCellValueFactory(
                new PropertyValueFactory<>("lg")
        );
        letterGradeColumn.setMaxWidth( 1f * Integer.MAX_VALUE * 20 );

        // Make table cells editable, well certain columns

        table.setRowFactory(new Callback<TableView<Assignment>, TableRow<Assignment>>() {
            @Override
            public TableRow<Assignment> call(TableView<Assignment> tableView) {
                final TableRow<Assignment> row = new TableRow<>();
                final ContextMenu contextMenu = new ContextMenu();
                final MenuItem removeMenuItem = new MenuItem("Remove Grade");
                removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        table.getItems().remove(row.getItem());
                        course.getAverage();
                        gradeSummary();
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



        table.getColumns().addAll(assignmentColumn, typeColumn, avgGradeColumn, letterGradeColumn);
        BorderPane tablePane = new BorderPane();
        tablePane.setPadding(new Insets(10));
        tablePane.setCenter(table);

        final TextField assignmentName = new TextField();
        assignmentName.setPromptText("Assignment");
        assignmentName.setMaxWidth(assignmentColumn.getPrefWidth());
        final ComboBox typeComboBox =  new ComboBox();
        typeComboBox.setItems(course.typeList);
        typeComboBox.setMaxWidth(typeColumn.getPrefWidth());
        typeComboBox.setPromptText("Type");

        final TextField grade = new TextField();

        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();
            if (text.matches("[0-9]*")) {
                return change;
            }
            return null;
        };

        TextFormatter<String> textFormatter1 = new TextFormatter<>(filter);
        grade.setTextFormatter(textFormatter1);


        grade.setMaxWidth(avgGradeColumn.getPrefWidth());
        grade.setPromptText("Grade");

        final Button addButton = new Button("Add");
        addButton.setOnAction( e -> this.controller.handleNewGradeAdded(e,this,this.course, assignmentName.getText(),(AssignmentType)typeComboBox.getSelectionModel().getSelectedItem(),Integer.parseInt(grade.getText())));
        HBox hb = new HBox();
        hb.getChildren().addAll(assignmentName, typeComboBox, grade, addButton);
        hb.setSpacing(3);
        tablePane.setBottom(hb);

        root.setCenter(tablePane);

        Button backButton = new Button("Back");
        backButton.setOnMousePressed( e -> this.controller.handleBackPress(e));

        Button editButton = new Button("Edit");
        editButton.setOnMousePressed( e -> this.controller.handleEditPress(e, this.course));

        Button deleteButton = new Button("Delete");
        deleteButton.setOnMousePressed( e -> this.controller.handleDeletePress(e, this.course));

        ButtonBar bbar = new ButtonBar();
        bbar.setPadding(new Insets(10, 10, 10, 10));

        bbar.getButtons().addAll(backButton, editButton, deleteButton);
        root.setBottom(bbar);

        Scene scene =  new Scene(root,  340, 600);
        scene.getStylesheets().add("styles.css");

        return scene;


    }




}
