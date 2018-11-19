package com.studio.mash.gracker.model;

public class Gradable {
    /**
     * Some common methods that Course and Course Model Implements
     */

    /**
     * Bunch of if statemnts to calculate the letter grade
     * @param grade
     * @return a String with letter grade, null if theres an error or below 0
     */
    public String calc_lg(Float grade){
        if (grade> 94)
            return "A";
        else if(grade >89 && grade <=93)
            return "A-";
        else if(grade>86 && grade <=89)
            return "B+";
        else if(grade>82 && grade <=86)
            return "B";
        else if(grade> 79 && grade <=82)
            return "B-";
        else if(grade> 76 && grade <=79)
            return "C+";
        else if(grade> 72 && grade <=76)
            return "C";
        else if(grade> 69 && grade <=72)
            return "C-";
        else if(grade> 66 && grade <=69)
            return "D+";
        else if(grade> 64 && grade <=66)
            return "D";
        else if(grade>= 0 && grade <=64)
            return "F";
        return null;
    }

    /**
     * Bunch of if statements to calculate the term gpa
     * @param grade
     * @return gpa in float, Float.NaN if theres an error
     */
    public float calc_gpa(Float grade){
        if (grade> 94)
            return 4f;
        else if(grade >89 && grade <=93)
            return 3.7f;
        else if(grade>86 && grade <=89)
            return 3.3f;
        else if(grade>82 && grade <=86)
            return 3.0f;
        else if(grade> 79 && grade <=82)
            return 2.7f;
        else if(grade> 76 && grade <=79)
            return 2.3f;
        else if(grade> 72 && grade <=76)
            return 2.0f;
        else if(grade> 69 && grade <=72)
            return 1.7f;
        else if(grade> 66 && grade <=69)
            return 1.3f;
        else if(grade> 64 && grade <=66)
            return 1.0f;
        else if(grade>= 0 && grade <=64)
            return 0.00f;
        return Float.NaN;
    }


}
