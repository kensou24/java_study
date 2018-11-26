package com.les.springevent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName: AllAcceptedListener
 * @Description: 订阅所有的ApplicationEvent事件
 * @Author: king
 * @CreateDate: 2018/11/26 9:47
 */
//@Component
@Slf4j
public class AllAcceptedListener implements ApplicationListener<ApplicationEvent> {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        log.info(">>>>>>>>>>>>>>>>event:" + event);
    }
}

