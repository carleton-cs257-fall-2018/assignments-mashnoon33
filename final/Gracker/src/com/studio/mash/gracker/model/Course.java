package com.studio.mash.gracker.model;

import java.util.ArrayList;

public class Course {
    /**
     * A course has multiple assignment types and grades expressed in different formats. This Class also performs calcylations
     */

    public ArrayList<AssignmentType> breakdown;
    public Integer average;
    public Float gpa;
    public String letterGrade;

    public Course() {
    }

    public Integer getAverage() {
        return average;
    }

    public Float getGpa() {
        return gpa;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public void setAverage(Integer average) {
        this.average = average;
    }

    public void setGpa(Float gpa) {
        this.gpa = gpa;
    }

    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }
}


