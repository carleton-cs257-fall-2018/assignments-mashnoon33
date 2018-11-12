package com.studio.mash.gracker.model;

public class Assignment {
    /**
     * Individual assignments, the name and the grade
     */
    public String name;
    public Integer grade;
    public Assignment(){
    }
    public Assignment(String name, Integer grade){

    }

    public Integer getGrade() {
        return grade;
    }

    public String getName() {
        return name;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public void setName(String name) {
        this.name = name;
    }
}
