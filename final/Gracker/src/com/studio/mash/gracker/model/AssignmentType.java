package com.studio.mash.gracker.model;

import java.util.ArrayList;

public class AssignmentType {
    /**
     * AssignmentType would refer to different types of assignments, e.g: Essays, Quiz, Exams
     * This class stores their weights, name, and grades for the respective type
     */
    public String name;
    public Integer weight;
    public ArrayList<Assignment> grades;

    public ArrayList<Assignment> getGrades() {
        return grades;
    }

    public String getName() {
        return name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
    public  void addGrade(String name, Integer grade) {
        /**
         * Adds grades ...
         */

    }
}