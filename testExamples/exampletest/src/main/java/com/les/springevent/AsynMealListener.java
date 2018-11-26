package com.les.springevent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @ClassName: MealListener
 * @Description: @Async的异步事件，指定执行器
 * @Author: king
 */
@Component
@Slf4j
public class AsynMealListener implements ApplicationListener<AsynMealEvent> {
    @Override
    @Async("messageAsycExecutor2")
    public void onApplicationEvent(AsynMealEvent event) {
        log.info(String.format(">>>>>>>>>>>thread:%s,type:%s,event:%s",
                Thread.currentThread().getName(), event.getMealEnum(), event));

        dispatchEvent(event);
    }

    private void dispatchEvent(AsynMealEvent event) {
        switch (event.getMealEnum()) {
            case breakfast:
                log.info(event.getMealEnum() + " to handle!!!");
                break;
            case lunch:
                log.info(event.getMealEnum() + " to handle!!!");
                break;
            case dinner:
                log.info(event.getMealEnum() + " to handle!!!");
                break;
            default:
                log.info(event.getMealEnum() + " error!!!");
                break;
        }
    }
}
