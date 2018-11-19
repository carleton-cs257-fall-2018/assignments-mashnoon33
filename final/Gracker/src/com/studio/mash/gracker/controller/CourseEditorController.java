package com.studio.mash.gracker.controller;

import com.studio.mash.gracker.model.AssignmentType;
import com.studio.mash.gracker.model.Course;
import com.studio.mash.gracker.model.CourseModel;
import com.studio.mash.gracker.view.CourseView;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.control.TextField;
import javafx.util.Pair;

import java.util.Collections;

public class CourseEditorController {
    private CourseModel model;
    private Course course;
    private ObservableList<AssignmentType> typeList;
    private CourseView view;

    /**
     * Gets called, when intiailized from DashView
     * @param model
     * @param typeList
     */
    public CourseEditorController(CourseModel model,  ObservableList<AssignmentType> typeList) {
        this.model=model;
        this.typeList=typeList;
    }

    /**
     * Gets called when initlalized from CourseView
     * @param course
     * @param view
     * @param typeList
     */
    public CourseEditorController(Course course,CourseView view,  ObservableList<AssignmentType> typeList) {
        this.course=course;
        this.view=view;
        this.typeList=typeList;
    }

    /**
     * Handles add button press for Coure type adder textfields. Highlights the empty textfield in red if submitted with
     * empty textfield
     * @param event
     * @param typeTextField
     * @param weightTextField
     */
    public void handleAddTypePress(Event event, TextField typeTextField, TextField weightTextField) {
        boolean a = typeTextField.getText().trim().isEmpty();
        boolean b = weightTextField.getText().trim().isEmpty();
        removeRed(typeTextField);
        removeRed(weightTextField);
        if (a) {
            ObservableList<String> styleClass = typeTextField.getStyleClass();
            if(!styleClass.contains("tferror")) {
                styleClass.add("tferror");
            }
        }
        if (b) {
            ObservableList<String> styleClass = weightTextField.getStyleClass();
            if(!styleClass.contains("tferror")) {
                styleClass.add("tferror");
            }
        }
        if(!a && !b) {
            // If something is inputted, submits
            typeList.add(new AssignmentType(typeTextField.getText(), Integer.parseInt(weightTextField.getText())));
            typeTextField.clear();
            weightTextField.clear();
        }
    }

    /**
     * Handles addCourse/update press for the dialogue. Adds a new course
     * @param p
     * @param update
     * @param courseName
     */
    public void handleAddCoursePress(Pair<String, String> p, Boolean update, TextField courseName) {
        if (!update) {
            model.courses.add(new Course(p.getKey(), this.typeList));
        }
        else  {
            this.course.setName(courseName.getText());
            this.course.setTypeList(this.typeList);
            view.updateCourseName();
        }
    }

    /**
     * Assists with styling the textfields, removes the red border
     * @param tf the Textfield that is to be stripped of its css class
     */
    private void removeRed(TextField tf) {
        ObservableList<String> styleClass = tf.getStyleClass();
        styleClass.removeAll(Collections.singleton("tferror"));
    }



}
