package com.les.springevent;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: GenneralObjectHandler
 * @Description:
 * @Author: king
 * @CreateDate: 2018/11/26 13:48
 */
@Component
@Slf4j
@Data
public class GeneralObjectHandler {

    private volatile  AtomicInteger atomicInteger = new AtomicInteger();

    public void reset()
    {
        atomicInteger.set(0);
    }

    @EventListener
    @Async("messageAsycExecutor")
    public void register(GeneralObject genneralObject)
    {
        int current = atomicInteger.getAndIncrement();
        if(current%10000 == 0)
        {
            log.info(current + ",AnnotationMealEventListener " + Thread.currentThread().getName() + ",@AsynMealEvent" + genneralObject);
        }
    }
}
