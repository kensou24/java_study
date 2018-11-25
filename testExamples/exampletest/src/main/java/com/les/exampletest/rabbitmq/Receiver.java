package com.les.exampletest.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @ProjectName: exampletest
 * @Package: com.les.exampletest.rabbitmq
 * @ClassName: Receiver
 * @Description:
 * @Author: king
 * @CreateDate: 2018/11/16 13:57
 * @UpdateUser: king
 * @UpdateDate: 2018/11/16 13:57
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Slf4j
@Component
@Scope(value= WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(MessageQueueConfig.testNumber);

    public void resetLatch() {
        latch = new CountDownLatch(MessageQueueConfig.testNumber);
    }

    public void receiveMessage(String message) {
        log.info("Received <" + message + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}
