package com.studio.mash.gracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.UUID;


@JsonIgnoreProperties({ "tags" })
public class Course extends Gradable  {
    @JsonIgnore
    public ObservableList<Assignment> assignments;
    @JsonIgnore public ObservableList<AssignmentType> typeList;
    public String name;
    public Float average;
    public String letterGrade;
    private String id;

    public Course() {
        /**
         * Default constructuor to be used by Jackson to restore
         */
    }

    /**
     * Creates a course object with some placeholder properties that get updated later through methods
     * @param name
     * @param typeList
     */
    public Course(String name, ObservableList<AssignmentType> typeList) {
        this.name = name;
        this.letterGrade = "F"; // Failing by default :(
        this.average = 0f;
        this.typeList = typeList;
        this.assignments =  FXCollections.observableArrayList();
        this.id=UUID.randomUUID().toString(); // UUID to create folders, in the case there are two courses with the same name
        // Also helpful incase the app crashed while deleting, Implement some sort of periodica cleanup ?
    }

    /**
     * Adds an assignment with the name, grade and type property
     * @param name
     * @param grade
     * @param type
     */
    public void addAssignment(String name, Float grade, AssignmentType type){
        assignments.add(new Assignment(name, grade, type));
        type.addAss(); // increases the number of assignment by 1
    }

    /**
     * Calculates the average grade considering all assignments
     * @return averge grade
     */
    public Float getAverage() {
        Float sum = 0f;
        for (Assignment assignment : assignments) {
            sum+= assignment.retriveWeightedAvgGrade(); // weighted avg does the division before hand, neat (!?)
            // ^ WeightedAverage might be a potentially confusing name though :(
        }
        return sum;
    }

    /**
     * Once a course type is deleted, purge would would pure all the assighnments of that type. But
     * it is not functioning correctly as of now
     * @param type
     */
    public void purgeType(AssignmentType type) {
        ArrayList<Assignment> toDelete = new ArrayList<>();
        int count = 0;
        while (assignments.size()>count) {
            Assignment ass= assignments.get(count);
            if (ass.getType().equals(type)) { //
                toDelete.add(ass); // for some reason, only half the objects get added in spite of if statement getting triggered 100% of the time
            }
            count++;
        }

        for (Assignment a: toDelete) {
            assignments.remove(a);
        }
    }

    public String getName() {
        return name;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getLetterGrade() {
        return calc_lg((float)Math.round(getAverage()));
    }

    public void setAverage(Float average) {
        this.average = average;
    }

    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }

    @JsonIgnore public ObservableList<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(ObservableList<Assignment> assignments) {
        this.assignments = assignments;
    }

    @JsonIgnore public ObservableList<AssignmentType> getTypeList() {
        return typeList;
    }

    public void setTypeList(ObservableList<AssignmentType> typeList) {
        this.typeList = typeList;
    }

}


