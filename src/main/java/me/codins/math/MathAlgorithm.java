package me.codins.math;

//Help from Chris Vazha with actual math
//https://github.com/chrisvazha


public class MathAlgorithm {

    public static void main(String[] args) {
        double needed = calculateWeightedGrade(90.00, 92.00, 0.4, 94.58, 0.3, 0.3);
        System.out.println(needed);
    }
    public static double calculateWeightedGrade(double gradeWanted, double examsGrade, double examWeight, double assignmentsGrade, double assignmentsWeight, double finalWeightage){

        double examScore = (examsGrade / 100) * examWeight;
        double assignmentScore = (assignmentsGrade / 100) * assignmentsWeight;

        double preFinalScore = examScore + assignmentScore;

        double finalGradeNeeded = (gradeWanted /100) - preFinalScore;
        finalGradeNeeded = (finalGradeNeeded / finalWeightage) * 100;

        return Double.parseDouble(String.format("%.3f", finalGradeNeeded));
    }
}
