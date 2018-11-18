package com.studio.mash.gracker.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static java.lang.Double.NaN;

public class Course extends Gradable {
    /**
     * A course has multiple assignment types and grades expressed in different formats. This Class also performs calcylations
     */

    public ObservableList<Assignment> assignments;
    public ObservableList<AssignmentType> typeList;
    public String name;
    public Float average;
    public String letterGrade;

    public Course(String name, ObservableList<AssignmentType> typeList) {
        this.name = name;
        this.letterGrade = "F";
        this.average = 0f;
        this.typeList = typeList;
        this.assignments =  FXCollections.observableArrayList();
    }

    public void addAssignment(String name, Float grade, AssignmentType type){
        assignments.add(new Assignment(name, grade, type));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getAverage() {
        ArrayList<Float> listOfAvgByType = new ArrayList<>();
        for (AssignmentType type : typeList) {
            Float total = 0f;
            for (Assignment assignment:type.grades) {
                total+=assignment.grade;
            }
            total = total/type.grades.size();
            total = total*type.weight/100;
            if (!Double.isNaN(total)) {
                listOfAvgByType.add(total);
            }
        }

        double sum = listOfAvgByType.stream()
                .mapToDouble(a -> a)
                .sum();
        this.average = (float) sum;

        if (!Double.isNaN(sum)) {
            return (float) sum;
        }
        return 0f;
    }

    public void purgeType(AssignmentType type) {
        //TODO java.util.ConcurrentModificationException
        for (Assignment assignment: assignments) {
            if (assignment.type == type ) {
                assignments.remove(assignment);
            }

        }
    }


    public String getLetterGrade() {
        return calc_lg((float)Math.round(this.average));
    }

    public void setAverage(Float average) {
        this.average = average;
    }


    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }


    public ObservableList<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(ObservableList<Assignment> assignments) {
        this.assignments = assignments;
    }


    public ObservableList<AssignmentType> getTypeList() {
        return typeList;
    }

    public void setTypeList(ObservableList<AssignmentType> typeList) {
        this.typeList = typeList;
    }

}


