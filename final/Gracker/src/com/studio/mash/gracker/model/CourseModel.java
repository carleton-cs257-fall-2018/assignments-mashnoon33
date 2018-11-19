package com.studio.mash.gracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

@JsonIgnoreProperties({ "tags" })
public class CourseModel extends Gradable {
    public Float average;
    public Float gpa;
    public String letterGrade;
    @JsonIgnore
    public ObservableList<Course> courses;

    /**
     * Takes no params
     */
    public CourseModel() {
        this.courses = FXCollections.observableArrayList();
        this.average=0f;
        this.gpa=0f;
        this.letterGrade="";
    }


    /**
     * Calculates the term average considering all courses
     * @return term average grade
     */
    public Float calculate_average(){
        Float sum = 0f;
        for (Course course: courses) {
            sum += course.getAverage();
        }
        sum/=courses.size();
        return sum;
    }

    @JsonIgnore public ObservableList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ObservableList<Course> courses) {
        this.courses = courses;
    }

    public Float getAverage() {
        return calculate_average();
    }

    public void setAverage(Float average) {
        this.average = average;
    }

    public Float getGpa() {
        return calc_gpa(getAverage());
    }

    public void setGpa(Float gpa) {
        this.gpa = gpa;
    }

    public String getLetterGrade() {
        return calc_lg(calculate_average());
    }

    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }

}
