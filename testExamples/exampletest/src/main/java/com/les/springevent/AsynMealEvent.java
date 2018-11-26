package com.les.springevent;

import org.springframework.context.ApplicationEvent;

/**
 * @ClassName: MealEvent
 * @Description: 测试的异步事件
 * @Author: king
 */
public class AsynMealEvent extends ApplicationEvent {

    private MealEnum mealEnum;

    /**
     * @param mealContent
     *        吃什么
     * @param mealEnum
     *        早餐还是午餐？
     */
    public AsynMealEvent(String mealContent, MealEnum mealEnum) {
        super(mealContent);
        this.mealEnum = mealEnum;
    }

    public MealEnum getMealEnum() {
        return mealEnum;
    }
}