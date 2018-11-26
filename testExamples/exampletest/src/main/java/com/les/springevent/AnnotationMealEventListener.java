package com.les.springevent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @ClassName: AnnotationMealEventListener
 * @Description: 使用注解@EventListener实现事件订阅
 *                 使用@Async实现事件的异步处理，指定执行器
 * @Author: king
 * @CreateDate: 2018/11/26 10:48
 */
@Slf4j
@Component
public class AnnotationMealEventListener {
    /**
     * @param asynMealEvent
     */
    @EventListener
    @Async("messageAsycExecutor")
    public void register(AsynMealEvent asynMealEvent)
    {
        log.info("AnnotationMealEventListener " + Thread.currentThread().getName() + ",@AsynMealEvent" + asynMealEvent);
    }
}
