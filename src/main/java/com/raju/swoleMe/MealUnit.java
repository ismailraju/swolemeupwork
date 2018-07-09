/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raju.swoleMe;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ismailu
 */
public class MealUnit {

    String name;
    List<FoodInfo> foodsInfo = new ArrayList<>();
    MealStats mealStats = new MealStats();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FoodInfo> getFoodsInfo() {
        return foodsInfo;
    }

    public void setFoodsInfo(List<FoodInfo> foodsInfo) {
        this.foodsInfo = foodsInfo;
    }

    public MealStats getMealStats() {
        return mealStats;
    }

    public void setMealStats(MealStats mealStats) {
        this.mealStats = mealStats;
    }

}
