package com.les.springevent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName: TroubleListener
 * @Description:
 * @Author: king
 */
@Component
@Slf4j
public class TroubleListener implements ApplicationListener<TroubleEvent> {
    @Override
    public void onApplicationEvent(TroubleEvent event) {
        log.info(">>>>>>>>>>>>>>>>event:" + event);
    }
}
