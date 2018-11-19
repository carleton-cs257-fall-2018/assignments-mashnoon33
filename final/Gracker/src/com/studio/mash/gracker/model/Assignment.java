package com.studio.mash.gracker.model;

public class Assignment extends Gradable {
    public String name;
    public Float grade;
    public String lg; // stands for letter grade
    public AssignmentType type;

    /**
     * Creates an assignment object
     * @param name Assignment name
     * @param grade Grade
     * @param type Assignmtn type
     */
    public Assignment(String name, Float grade, AssignmentType type){
        this.lg=calc_lg(grade);
        this.name=name;
        this.grade=grade;
        this.type=type;
    }
    public Assignment() {}

    /**
     * Returns weighted avergage. Uses the word retrive instead of get so that it doesnt interfere with Jackson
     * @return Weighted grade devided by the number of grades in that category
     */
    public Float retriveWeightedAvgGrade() {
        return (this.grade*this.type.getWeight()/100)/this.type.getNumOfAssignment();
    }

    public String getLg() {
        return lg;
    }
    public void setType(AssignmentType type) {
        this.type = type;
    }

    public void setLg(String lg) {
        this.lg = lg;
    }

    public AssignmentType getType() {
        return type;
    }

    public Float getGrade() {
        return grade;
    }

    public String getName() {
        return name;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public void setName(String name) {
        this.name = name;
    }
}
