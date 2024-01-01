package me.codins.categories;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Category {

    private String name;
    private double grade;
    private double weight;
    private double calculatedScore;

    public Category(String name, double grade, double weight){
        this.name = name;
        this.grade = grade;
        this.weight = weight;
        calculatedScore = (getGrade() / 100) * getWeight();
    }

    @Override
    public String toString(){
        return name + " - " + grade + " - " + weight;
    }
}
