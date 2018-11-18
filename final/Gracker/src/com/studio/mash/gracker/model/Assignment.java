package com.studio.mash.gracker.model;

public class Assignment extends Gradable {
    /**
     * Individual assignments, the name and the grade
     */
    public String name;
    public Float grade;
    public String lg;
    public AssignmentType type;

    public Assignment(String name, Float grade, AssignmentType type){
        this.lg=calc_lg(grade);
        this.name=name;
        this.grade=grade;
        this.type=type;
        type.addGrade(this);

    }

    public String getLg() {
        return lg;
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
