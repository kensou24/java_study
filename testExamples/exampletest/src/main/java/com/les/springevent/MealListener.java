package com.les.springevent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: exampletest
 * @Package: com.les.springevent
 * @ClassName: MealListener
 * @Description:
 * @Author: king
 * @CreateDate: 2018/11/26 9:49
 * @UpdateUser: king
 * @UpdateDate: 2018/11/26 9:49
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Component
@Slf4j
public class MealListener implements ApplicationListener<MealEvent> {
    @Override
    public void onApplicationEvent(MealEvent event) {
        log.info(String.format(">>>>>>>>>>>thread:%s,type:%s,event:%s",
                Thread.currentThread().getName(), event.getMealEnum(), event));

        dispatchEvent(event);
    }

    private void dispatchEvent(MealEvent event) {
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
