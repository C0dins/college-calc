package me.codins.math;

//Help from Chris Vazha with actual math
//https://github.com/chrisvazha


import me.codins.categories.Category;

import java.util.ArrayList;

public class MathAlgorithm {

    public static double calculateGrade(double gradeWanted, double finalWeight, ArrayList<Category> categories){

        double preFinalScore = 0;

        for(Category category : categories){
            preFinalScore += category.getCalculatedScore();
        }

        double finalGradeNeeded = (((gradeWanted / 100) - preFinalScore) / finalWeight) * 100;

        return Double.parseDouble(String.format("%.3f", finalGradeNeeded));
    }
}
