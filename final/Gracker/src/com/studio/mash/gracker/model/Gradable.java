package com.studio.mash.gracker.model;

public class Gradable {

    public String calc_lg(Float grade){
        if (grade>= 94)
            return "A";
        else if(grade >=90 && grade <=93)
            return "A-";
        else if(grade>=87 && grade <=89)
            return "B+";
        else if(grade>=83 && grade <=86)
            return "B";
        else if(grade>= 80 && grade <=82)
            return "B-";
        else if(grade>= 77 && grade <=79)
            return "C+";
        else if(grade>= 73 && grade <=76)
            return "C";
        else if(grade>= 70 && grade <=72)
            return "C-";
        else if(grade>= 67 && grade <=69)
            return "D+";
        else if(grade>= 65 && grade <=66)
            return "D";
        else if(grade>= 0 && grade <=64)
            return "F";
        return "Error";
    }

    public float calc_gpa(Float grade){
        if (grade>= 94)
            return 4f;
        else if(grade >=90 && grade <=93)
            return 3.7f;
        else if(grade>=87 && grade <=89)
            return 3.3f;
        else if(grade>=83 && grade <=86)
            return 3.0f;
        else if(grade>= 80 && grade <=82)
            return 2.7f;
        else if(grade>= 77 && grade <=79)
            return 2.3f;
        else if(grade>= 73 && grade <=76)
            return 2.0f;
        else if(grade>= 70 && grade <=72)
            return 1.7f;
        else if(grade>= 67 && grade <=69)
            return 1.3f;
        else if(grade>= 65 && grade <=66)
            return 1.0f;
        else if(grade>= 0 && grade <=64)
            return 0.00f;
        return 0.00f;
    }


}
