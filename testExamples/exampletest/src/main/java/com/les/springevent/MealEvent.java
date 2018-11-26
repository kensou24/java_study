package com.les.springevent;

import org.springframework.context.ApplicationEvent;

/**
 * @ProjectName: exampletest
 * @Package: com.les.springevent
 * @ClassName: MealEvent
 * @Description:
 * @Author: king
 * @CreateDate: 2018/11/26 9:49
 * @UpdateUser: king
 * @UpdateDate: 2018/11/26 9:49
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MealEvent extends ApplicationEvent {

    private MealEnum mealEnum;

    /**
     * @param mealContent
     *        吃什么
     * @param mealEnum
     *        早餐还是午餐？
     */
    public MealEvent(String mealContent, MealEnum mealEnum) {
        super(mealContent);
        this.mealEnum = mealEnum;
    }

    public MealEnum getMealEnum() {
        return mealEnum;
    }
}