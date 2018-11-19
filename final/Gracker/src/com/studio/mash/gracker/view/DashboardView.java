package com.studio.mash.gracker.view;

import com.studio.mash.gracker.model.CourseModel;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import com.studio.mash.gracker.controller.DashController;

import java.text.DecimalFormat;

public class DashboardView extends BaseView {
    /**
     * An extension of baseview. Shows the Grades on top, and the courses in a tableview form in the center.
     */

    private EventHandler<? super MouseEvent> handler;
    private final XTableView table = new XTableView();
    private CourseModel model;
    private DashController controller;

    /**
     * Utilizes tableview to display the courses and respective averages, and a singular button to handle adding new courses.
     * table rows are clickable as well
     * or manitpulate
     * @param stage
     * @param model
     */
    public DashboardView(Stage stage, CourseModel model) {
        super(stage);
        this.model =model;
        this.controller = new DashController(stage, model, table);
    }

    /**
     *  Generates all the elements and returns the scene
     * @return  a renderd scene
     */
    @Override
    public Scene getScene() {
        //
        // Generate the tableView
        //
        table.setOnMousePressed(e -> this.controller.handleRowPress(e));
        table.setColumnResizePolicy( TableView.CONSTRAINED_RESIZE_POLICY );
        table.setItems(model.courses);
        table.setMinWidth(320);
        table.setPlaceholder(new Label("Add a Course to get started"));

        TableColumn nameColumn = new TableColumn("Course");
        nameColumn.setMaxWidth( 1f * Integer.MAX_VALUE * 70 );
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn avgColumn = new TableColumn("Average");
        avgColumn.setMaxWidth( 1f * Integer.MAX_VALUE * 20 );
        avgColumn.setCellValueFactory(new PropertyValueFactory<>("average"));

        TableColumn lgColumn = new TableColumn("Letter Grade");
        lgColumn.setMaxWidth( 1f * Integer.MAX_VALUE * 10 );
        lgColumn.setCellValueFactory(new PropertyValueFactory<>("letterGrade"));

        table.getColumns().addAll(nameColumn, avgColumn, lgColumn);

        final BorderPane mainPane = new BorderPane();
        mainPane.setPadding(new Insets(10, 10, 10, 10));
        mainPane.setCenter(table);

        //
        // Generates the buttons and the button bar
        //
        Button backButton = new Button("Add Course");
        backButton.setOnMousePressed( e -> this.controller.handleAddPress(e));

        ButtonBar bbar = new ButtonBar();
        bbar.setPadding(new Insets(10, 0, 10, 10));
        bbar.getButtons().addAll(backButton);

        mainPane.setBottom(bbar);
        root.setCenter(mainPane);

        //
        // Generate the dashboard top information view
        //
        //TODO show only 2 decimal points
        final HBox top = new HBox();
        top.setSpacing(13);
        top.setPadding(new Insets(0,0, 5,0));

        Separator separator1 = new Separator();
        Separator separator2 = new Separator();
        separator1.setOrientation(Orientation.VERTICAL);
        separator2.setOrientation(Orientation.VERTICAL);
        Text gpa_top = new Text("GPA");
        gpa_top.setFont(Font.font("Arial", 15));
        gpa_top.setTextAlignment(TextAlignment.CENTER);
        Text gpa = new Text("0.00");
        Text avg = new Text("00.00");
        Text lg = new Text("F -");
        Text avg_top = new Text("Average");
        avg_top.setFont(Font.font("Arial", 15));
        Text lg_top = new Text("Letter Grade");
        lg_top.setFont(Font.font("Arial", 15));
        gpa.setFont(Font.font("Arial", 40));
        avg.setFont(Font.font("Arial", 40));
        lg.setFont(Font.font("Arial", 40));

        //Formats the decimals to 2 decimal points
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        // Grays out if there are no courses
        if (Float.isNaN(model.getGpa()) || Float.isNaN(model.getAverage()) || model.getGpa()==null){
            gpa.setFill(Color.web("c1c1c1"));
            avg.setFill(Color.web("c1c1c1"));
            lg.setFill(Color.web("c1c1c1"));
        }
        else {
            gpa.setText(df.format(model.getGpa()));
            avg.setText(df.format(model.getAverage()));
            lg.setText(model.getLetterGrade().toString());
        }

        // Puts all the texts togather nicely
        VBox gpa_v = new VBox();
        VBox avg_v = new VBox();
        VBox lg_v = new VBox();
        gpa_v.getChildren().addAll(gpa_top,gpa);
        avg_v.getChildren().addAll(avg_top,avg);
        lg_v.getChildren().addAll(lg_top,lg);
        top.getChildren().addAll(gpa_v,separator1,avg_v, separator2,lg_v);

        mainPane.setTop(top);

        Scene scene =  new Scene(root,  350, 600);
        scene.getStylesheets().add("styles.css");
        return scene;

    }





}
