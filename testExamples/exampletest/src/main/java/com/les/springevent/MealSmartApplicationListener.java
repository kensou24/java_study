package com.les.springevent;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: exampletest
 * @Package: com.les.springevent
 * @ClassName: MealListener
 * @Description: 实现有序的事件处理
 * @Author: king
 * @CreateDate: 2018/11/26 9:49
 * @UpdateUser: king
 * @UpdateDate: 2018/11/26 9:49
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Component
@Slf4j
public class MealSmartApplicationListener implements SmartApplicationListener {

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

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return eventType == AsynMealEvent.class;
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return true;
    }

    @Override
    public int getOrder() {
        return 100;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {

        AsynMealEvent asynMealEvent = (AsynMealEvent)event;

        log.info(String.format("SmartApplicationListener >>>>>>>>>>>thread:%s,type:%s,event:%s",
                Thread.currentThread().getName(), asynMealEvent.getMealEnum(), asynMealEvent));

        dispatchEvent(asynMealEvent);
    }
}
