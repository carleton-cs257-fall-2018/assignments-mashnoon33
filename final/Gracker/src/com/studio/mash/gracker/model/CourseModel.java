package com.studio.mash.gracker.model;

import java.util.ArrayList;

public class CourseModel {

    public Float average;
    public Float gpa;
    public String letterGrade;
    public ArrayList<Course> courses;

    public void calculate(ArrayList grades) {
        /**
         * Gathers all the grades, and then calls 3 helper methods to calculate in differnt formats
         */
        this.average=calculate_average(grades);
        this.gpa=calculate_gpa(grades);
        this.letterGrade=calculateLetterGrade(grades);
    }

    public Float calculate_gpa(ArrayList grades){
        return 0.00f;
    }
    public Float calculate_average(ArrayList grades){
        return 0.00f;

    }
    public String calculateLetterGrade(ArrayList grades){
        return "";

    }

    public void createClass() {

    }
    public void addGrade() {

    }
    public void modifyClass() {

    }
    public void modifyGrade() {

    }



}
