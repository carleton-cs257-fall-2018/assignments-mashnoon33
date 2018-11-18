package com.studio.mash.gracker.view;

import com.studio.mash.gracker.model.Course;
import com.studio.mash.gracker.model.CourseModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import com.studio.mash.gracker.controller.DashController;

public class DashboardView extends BaseView {

    private EventHandler<? super MouseEvent> handler;
    private final XTableView table = new XTableView();
    private CourseModel model;
    private DashController controller;

    public DashboardView(Stage stage, CourseModel model)
    {
        /**
         * Utilizes tableview to display the courses and respective averages, and a singular button to handle adding new courses.
         * table rows are clickable as well
         * or manitpulate
         */

        super(stage);
        this.model =model;
        this.controller = new DashController(stage, model, table);
    }



    @Override
    public Scene getScene() {
        /**
         *  Serves as the base for all views. Generates common elements for all the views
         */
        final Label label = new Label("Summary");
        label.setFont(new Font("Arial", 20));

        table.setOnMousePressed(e -> this.controller.handleRowPress(e));
        table.setColumnResizePolicy( TableView.CONSTRAINED_RESIZE_POLICY );

        //TODO put text in vbox and put titles
        //TODO fix header texts when there is no tests

        TableColumn nameColumn = new TableColumn("Course");
        nameColumn.setMaxWidth( 1f * Integer.MAX_VALUE * 80 );
        TableColumn avgColumn = new TableColumn("Average");
        avgColumn.setMaxWidth( 1f * Integer.MAX_VALUE * 10 );
        TableColumn lgColumn = new TableColumn("Letter Grade");
        lgColumn.setMaxWidth( 1f * Integer.MAX_VALUE * 10 );
        table.getColumns().addAll(nameColumn, avgColumn, lgColumn);

        table.setItems(model.courses);

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        avgColumn.setCellValueFactory(
                new PropertyValueFactory<>("average")
        );
        lgColumn.setCellValueFactory(
                new PropertyValueFactory<>("letterGrade")
        );



        table.setMinWidth(320);
//        table.setStyle("-fx-control-inner-background-alt: white ;   -fx-text-fill: red;  -fx-background-color: transparent;    -fx-table-cell-border-color : white; -fx-selection-bar: red; -fx-selection-bar-non-focused: white;");


        final BorderPane left = new BorderPane();
        left.setPadding(new Insets(10, 10, 10, 10));
        left.setTop(label);
        left.setCenter(table);

        Button backButton = new Button("Add Course");
        backButton.setOnMousePressed( e -> this.controller.handleMousePress(e));

        ButtonBar bbar = new ButtonBar();
        bbar.setPadding(new Insets(10, 0, 10, 10));

        bbar.getButtons().addAll(backButton);
        left.setBottom(bbar);

        root.setCenter(left);

        final HBox top = new HBox();
        top.setSpacing(20);
        top.setPadding(new Insets(0,0, 5,0));

        Separator separator1 = new Separator();
        Separator separator2 = new Separator();
        separator1.setOrientation(Orientation.VERTICAL);
        separator2.setOrientation(Orientation.VERTICAL);
        Text gpa_top = new Text("GPA");
        gpa_top.setFont(Font.font("Arial", 15));
        gpa_top.setTextAlignment(TextAlignment.CENTER);
        Text gpa = new Text(model.getGpa().toString());
        Text avg_top = new Text("Average");
        avg_top.setFont(Font.font("Arial", 15));
        Text lg_top = new Text("Letter Grade");
        lg_top.setFont(Font.font("Arial", 15));
        gpa.setFont(Font.font("Arial", 40));
        Text avg = new Text(model.getAverage().toString());
        avg.setFont(Font.font("Arial", 40));
        Text lg = new Text(model.getLetterGrade().toString());
        lg.setFont(Font.font("Arial", 40));
        VBox gpa_v = new VBox();
        VBox avg_v = new VBox();
        VBox lg_v = new VBox();
        gpa_v.getChildren().addAll(gpa_top,gpa);
        avg_v.getChildren().addAll(avg_top,avg);
        lg_v.getChildren().addAll(lg_top,lg);
        top.getChildren().addAll(gpa_v,separator1,avg_v, separator2,lg_v);

        left.setTop(top);

//        root.setStyle("-fx-background-color: white;");

        Scene scene =  new Scene(root,  340, 600);
        scene.getStylesheets().add("styles.css");
        return scene;

    }





}
