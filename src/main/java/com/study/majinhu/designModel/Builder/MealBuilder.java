package com.study.majinhu.designModel.Builder;

/**
 * @ClassName MealBuilder
 * @Description 创建一个 MealBuilder 类，实际的 builder 类负责创建 Meal 对象。
 * @Author majinhu
 * @Date 2019/8/15 10:01
 * @Version 1.0
 **/
public class MealBuilder {

    public Meal prepareVegMeal (){
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }

    public Meal prepareNonVegMeal (){
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }
}
