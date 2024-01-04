package me.codins.math;

//Help from Chris Vazha with actual math
//https://github.com/chrisvazha


import me.codins.categories.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MathAlgorithm {

    public static double calculateGrade(double gradeWanted, double finalWeight, Map<String, Category> categories){

        double preFinalScore = 0;

        for(Category category : categories.values()){
            preFinalScore += category.getCalculatedScore();
        }

        double finalGradeNeeded = (((gradeWanted / 100) - preFinalScore) / finalWeight) * 100;

        return Double.parseDouble(String.format("%.3f", finalGradeNeeded));
    }
}
