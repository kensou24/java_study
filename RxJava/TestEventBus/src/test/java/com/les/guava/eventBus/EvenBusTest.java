package com.les.guava.eventBus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: TestEventBus
 * @Package: com.les.guava
 * @ClassName: EvenBusTest
 * @Description:
 * @Author: king
 * @CreateDate: 2018/11/15 8:21
 * @UpdateUser: king
 * @UpdateDate: 2018/11/15 8:21
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Slf4j
public class EvenBusTest {

    @Test
    public void test() {
        EventBus eventBusTest = new EventBus("test-bus");
        EventBusHandler eventBusHandler = new EventBusHandler();
        eventBusTest.register(eventBusHandler);

        EventBusMessage eventBusMessage = new EventBusMessage();
        eventBusTest.post(eventBusMessage);
    }

    @Test
    public void testAsyn() {
        AsyncEventBus asyncEventBus = new AsyncEventBus("test-bus", Executors.newFixedThreadPool(1));
        EventBusHandler eventBusHandler = new EventBusHandler();
        asyncEventBus.register(eventBusHandler);

        EventBusMessage eventBusMessage = new EventBusMessage();
        asyncEventBus.post(eventBusMessage);

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
