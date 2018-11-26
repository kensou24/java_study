package com.les.springevent;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: MyApplicationEventPublisher
 * @Description: 使用ApplicationEventPublisher进行事件的发布
 * @Author: king
 * @CreateDate: 2018/11/26 13:35
 */
@Component
@Slf4j
@Data
public class MyApplicationEventPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    private volatile AtomicInteger atomicInteger = new AtomicInteger(0);

    public void reset()
    {
        atomicInteger.set(0);
    }

    public void publishEvent(ApplicationEvent event)
    {
        applicationEventPublisher.publishEvent(event);
    }

    public void publishEvent(Object object)
    {
        try {
            applicationEventPublisher.publishEvent(object);
        }catch(TaskRejectedException taske)
        {
            int current = atomicInteger.getAndIncrement();
            log.error("recieve TaskRejectedException index = " + current);
        }
    }

}
