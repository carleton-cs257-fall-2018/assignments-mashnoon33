package com.studio.mash.gracker.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.codehaus.jackson.map.ObjectMapper;


import java.io.File;
import java.util.ArrayList;

public class CourseModel extends Gradable {

    public Float average;
    public Float gpa;
    public String letterGrade;
    public ObservableList<Course> courses;

    public CourseModel() {
        ObservableList<AssignmentType> typeList1 = FXCollections.observableArrayList(
                new AssignmentType("Essay",40),
                new AssignmentType("Project",50)
        );
//        ObservableList<AssignmentType> typeList2 = FXCollections.observableArrayList(
//                new AssignmentType("Essay",40),
//                new AssignmentType("Project",50)
//        );
        this.courses = FXCollections.observableArrayList(
                new Course("Software Design",typeList1 )
//                ,
//                new Course("Experimental Photo", typeList2)
        );
        this.average=0f;
        this.gpa=0f;
        this.letterGrade="";



    }

    public ObservableList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ObservableList<Course> courses) {
        this.courses = courses;
    }

    public void calculate(ArrayList grades) {
        /**
         * Gathers all the grades, and then calls 3 helper methods to calculate in differnt formats
         */
        this.average=calculate_average();
        this.gpa=calculate_gpa(grades);
        this.letterGrade=calculateLetterGrade(grades);
    }

    public Float calculate_gpa(ArrayList grades){
        return 0.00f;
    }
    public Float calculate_average(){
        Float sum = 0f;
        for (Course course: courses) {
            sum += course.getAverage();
        }
        sum/=courses.size();
        return sum;

    }
    public String calculateLetterGrade(ArrayList grades){
        return "";

    }

    public void createClass() {

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

    public void addGrade() {

    }
    public void modifyClass() {

    }
    public void modifyGrade() {

    }



}
