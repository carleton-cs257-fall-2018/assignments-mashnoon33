package com.studio.mash.gracker.model;

public class AssignmentType {
    /**
     * AssignmentType would refer to different types of assignments, e.g: Essays, Quiz, Exams
     * This class stores their weights, name, and grades for the respective type
     */
    public String name;
    public Integer weight;
    public Integer numOfAssignment;

    /**
     * Creates an assignment object
     * @param name Name of the type of the assignment
     * @param weight Weight in precentage, not decimal
     */
    public AssignmentType(String name, Integer weight){
        this.name = name;
        this.weight = weight;
        numOfAssignment=0;

    }

    public  AssignmentType() {}

    public Integer getNumOfAssignment() {
        return numOfAssignment;
    }

    public void setNumOfAssignment(Integer numOfAssignment) {
        this.numOfAssignment = numOfAssignment;
    }

    /**
     * Increases the number of assignment by one for the respective catagory
     */
    public void addAss() {
        this.numOfAssignment ++;
    }

    /**
     * Decreases the number of assignment by one for the respective catagory
     */
    public void remAss() {
        this.numOfAssignment --;
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

    @Override
    public String toString() {
        return this.name;
    }
}
