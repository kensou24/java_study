package com.les.guava.eventBus;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

/**
 * @ProjectName: TestEventBus
 * @Package: com.les.guava
 * @ClassName: EventBusHandler
 * @Description:
 * @Author: king
 * @CreateDate: 2018/11/15 8:23
 * @UpdateUser: king
 * @UpdateDate: 2018/11/15 8:23
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Slf4j
public class EventBusHandler {

        @Subscribe
        public void messageHandler(EventBusMessage eventBusMessage)
        {
            log.info(" receive event message");
        }

        @Subscribe
        @AllowConcurrentEvents
        public void messageHandler2(EventBusMessage eventBusMessage)
        {
            log.info(" receive event message");
        }
}
