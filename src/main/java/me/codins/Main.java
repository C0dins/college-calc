package me.codins;

import me.codins.categories.Category;
import me.codins.math.MathAlgorithm;
import me.codins.utils.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Category> categories = new ArrayList<>();
    private static double gradeWanted;
    private static double finalWeight;

    public static void main(String[] args) throws IOException {
        categories.add(new Category("exams", 92.00, 0.4));
        categories.add(new Category("assignments", 94.58, 0.3));
        Logger logger = new Logger();
        logger.printSplitter();
        logger.printAsciiArt();
        logger.printSplitter();

        System.out.println(getMenu());

        Scanner in = new Scanner(System.in);
        boolean exit = false;
        while(!exit){
            logger.logQuestion("What would you like to do? (Type the name not #) > ");
            switch (in.nextLine().toLowerCase()){
                case "list":
                    if(categories.size() == 0 ){
                        logger.error("Nothing in list to display");
                        break;
                    }
                    logger.printSplitter();
                    logger.log("Name - Grade - Weight");
                    for(Category cat : categories){
                        logger.log(cat.toString());
                    }
                    logger.printSplitter();
                    break;
                case "add":
                    logger.logQuestion("What is the name of this grading category? (Ex. exams) > ");
                    String name = in.nextLine().toLowerCase();
                    logger.logQuestion("What is the grade you received or will receive in the category? (Ex. 92.50) > ");
                    double grade = in.nextDouble();
                    logger.logQuestion("What is the weightage of this grading category on your overall grade? (Ex. 0.4 for 40%) > ");
                    double weight = in.nextDouble();

                    categories.add(new Category(name, grade, weight));
                    logger.success("Successfully added '" + name + "' grading category!");
                    break;
                case "remove":
                    if(categories.size() == 0 ){
                        logger.error("Nothing to remove");
                        break;
                    }
                    logger.printSplitter();
                    for(Category cat : categories){
                        logger.log(cat.getName());
                    }
                    logger.printSplitter();
                    logger.logQuestion("Which category would you like to remove? > ");
                    String removeName = in.nextLine().toLowerCase();
                    if(contains(removeName, categories) == false){
                        logger.error(removeName + " does not exist in categories");
                        break;
                    }
                    //fix remove
                    categories.remove("");
                    break;
                case "calculate":
                    logger.logQuestion("How much is your final worth in terms of weightage (Ex. 0.4 for 40%) > ");
                    double finalWeightage = in.nextDouble();
                    logger.logQuestion("What grade do you want? (Ex. 89.5) > ");
                    double gradeWanted = in.nextDouble();
                    if (validateWeight(finalWeightage, categories)){
                        double gradeNeeded = MathAlgorithm.calculateGrade(gradeWanted, finalWeightage, categories);
                        logger.log("The grade you need on your final exam to get a " + gradeWanted + " in your class is " + gradeNeeded);
                        break;
                    } else {
                        logger.error("The weights of all your categories and final exam weight don't add up to 1 / 100% please re-enter the categories");
                        categories.clear();
                        break;
                    }
                case "exit":
                    exit = true;
                    System.exit(0);
                default:
                    logger.error("Please select from the list");
                    System.out.println(getMenu());
            }
        }


    }

    public static String getMenu(){
        return " 1. List - List Current Categories\n 2. Add - Add new Grade Category\n 3. Remove - Remove a Grade Category\n 4. Calculate - Calculate Final Grade\n 5. Exit - Exit Program\n";
    }

    public static boolean validateWeight(double finalWeight, ArrayList<Category> categories){
        double totalWeight = 0;
        totalWeight += finalWeight;

        for(Category cat : categories){
            totalWeight += cat.getWeight();
        }
        return totalWeight == 1.00;
    }

    public static boolean contains(String value, ArrayList<Category> categories){
        for(Category category : categories){
            if(category.getName().equalsIgnoreCase(value)){
                return true;
            }
        }

        return false;
    }
}
