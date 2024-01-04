package me.codins;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import me.codins.categories.Category;
import me.codins.math.MathAlgorithm;
import me.codins.utils.Logger;

public class Main {
    private static Map<String, Category> categories = new HashMap<>();

    public static void main(String[] args){
        Logger logger = new Logger();
        logger.printSplitter();
        logger.printAsciiArt();
        logger.printSplitter();

        System.out.println(getMenu());

        Scanner in = new Scanner(System.in);
        boolean exit = false;
        while(!exit){
            logger.logQuestion("What would you like to do? (Type the name not #) > ");
            switch (in.nextLine().trim().toLowerCase()){
                case "list":
                    if(categories.isEmpty()){
                        logger.error("Nothing in list to display");
                        break;
                    }
                    logger.printSplitter();
                    logger.log("Name - Grade - Weight");
                    for(Category cat : categories.values()){
                        logger.log(cat.toString());
                    }
                    logger.printSplitter();
                    break;
                case "add":
                    logger.logQuestion("What is the name of this grading category? (Ex. exams) > ");
                    String name = in.nextLine().trim().toLowerCase();
                    logger.logQuestion("What is the grade you received or will receive in the category? (Ex. 92.50) > ");
                    double grade = Double.parseDouble(in.nextLine());
                    logger.logQuestion("What is the weightage of this grading category on your overall grade? (Ex. 0.4 for 40%) > ");
                    double weight = Double.parseDouble(in.nextLine());

                    if(categories.containsKey(name)){
                        logger.error("Category already exists");
                        break;
                    }

                    categories.put(name, new Category(name, grade, weight));
                    logger.success("Successfully added '" + name + "' grading category!");
                    break;
                case "remove":
                    if(categories.isEmpty()){
                        logger.error("Nothing to remove");
                        break;
                    }
                    logger.printSplitter();
                    for(Category cat : categories.values()){
                        logger.log(cat.getName());
                    }
                    logger.printSplitter();
                    logger.logQuestion("Which category would you like to remove? > ");
                    String removeName = in.nextLine().trim().toLowerCase();
                    if(!categories.containsKey(removeName)){
                        logger.error(removeName + " does not exist in categories");
                        break;
                    }
                    categories.remove(removeName);
                    break;
                case "calculate":
                    logger.logQuestion("How much is your final worth in terms of weightage (Ex. 0.4 for 40%) > ");
                    double finalWeightage = Double.parseDouble(in.nextLine());
                    logger.logQuestion("What grade do you want? (Ex. 89.5) > ");
                    double gradeWanted = Double.parseDouble(in.nextLine());
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

    public static boolean validateWeight(double finalWeight, Map<String, Category> categories){
        double totalWeight = 0;
        totalWeight += finalWeight;

        for(Category cat : categories.values()){
            totalWeight += cat.getWeight();
        }
        return totalWeight == 1.00;
    }
}
