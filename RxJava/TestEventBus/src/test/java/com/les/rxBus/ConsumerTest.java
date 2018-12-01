package com.les.rxBus;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Produce;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;
import com.les.rxJava.O01HelloWord;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ProjectName: TestEventBus
 * @Package: com.les.rxBus
 * @ClassName: ConsumerTest
 * @Description:
 * @Author: king
 * @CreateDate: 2018/11/13 13:37
 * @UpdateUser: king
 * @UpdateDate: 2018/11/13 13:37
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Slf4j
@Data
public class ConsumerTest {

    private AtomicInteger atomicInteger = new AtomicInteger();

    private CountDownLatch countDownLatch;

    public void onCreate() {
        RxBus.get().register(this);
    }

    public void onDestroy() {
        RxBus.get().unregister(this);
    }

    @Subscribe(
            thread = EventThread.IO,
            tags = {
                    @Tag("EAT_MORE")
            }
    )
    public void eatMore(ArrayList<String> foods) {
        atomicInteger.getAndIncrement();
        if((atomicInteger.get() % 1000 == 0) || (atomicInteger.get() == 1)) {
            //log.info("===================================method 11111 " + atomicInteger.get());
        }
        countDownLatch.countDown();
        // purpose
        foods.forEach(O01HelloWord::printThreadAndData);
    }

    @Subscribe(
            thread = EventThread.IO,
            tags = {
                    @Tag("EAT_MORE")
            }
    )
    public void eatMore2(ArrayList<String> foods) {
        atomicInteger.getAndIncrement();
        if((atomicInteger.get() % 1000 == 0) || (atomicInteger.get() == 1)) {
            //log.info("===================================method 11111 " + atomicInteger.get());
        }
        countDownLatch.countDown();
        // purpose
        foods.forEach(O01HelloWord::printThreadAndData);
    }


    @Subscribe(
            thread = EventThread.IO,
            tags = {
                    @Tag("EAT_MORE")
            }
    )
    public void eatMore3(ArrayList<String> foods) {
        atomicInteger.getAndIncrement();
        if((atomicInteger.get() % 1000 == 0) || (atomicInteger.get() == 1)) {
            //log.info("===================================method 11111 " + atomicInteger.get());
        }
        countDownLatch.countDown();
        // purpose
        foods.forEach(O01HelloWord::printThreadAndData);
    }


    @Subscribe(
            thread = EventThread.IO,
            tags = {
                    @Tag("EAT_MORE")
            }
    )
    public void eatMore4(ArrayList<String> foods) {
        atomicInteger.getAndIncrement();
        if((atomicInteger.get() % 1000 == 0) || (atomicInteger.get() == 1)) {
            //log.info("===================================method 11111 " + atomicInteger.get());
        }
        countDownLatch.countDown();
        // purpose
        foods.forEach(O01HelloWord::printThreadAndData);
    }

    @Subscribe(
            thread = EventThread.IO,
            tags = {
                    @Tag("EAT_MORE")
            }
    )
    public void eatMore5(ArrayList<String> foods) {
        atomicInteger.getAndIncrement();
        if((atomicInteger.get() % 1000 == 0) || (atomicInteger.get() == 1)) {
            //log.info("===================================method 11111 " + atomicInteger.get());
        }
        countDownLatch.countDown();
        // purpose
        foods.forEach(O01HelloWord::printThreadAndData);
    }


    @Subscribe(
            thread = EventThread.IO,
            tags = {
                    @Tag("EAT_MORE")
            }
    )
    public void eatMore6(ArrayList<String> foods) {
        atomicInteger.getAndIncrement();
        if((atomicInteger.get() % 1000 == 0) || (atomicInteger.get() == 1)) {
            //log.info("===================================method 11111 " + atomicInteger.get());
        }
        countDownLatch.countDown();
        // purpose
        foods.forEach(O01HelloWord::printThreadAndData);
    }

    @Subscribe(
            thread = EventThread.IO,
            tags = {
                    @Tag("EAT_MORE")
            }
    )
    public void eatMore7(ArrayList<String> foods) {
        atomicInteger.getAndIncrement();
        if((atomicInteger.get() % 1000 == 0) || (atomicInteger.get() == 1)) {
            //log.info("===================================method 11111 " + atomicInteger.get());
        }
        countDownLatch.countDown();
        // purpose
        foods.forEach(O01HelloWord::printThreadAndData);
    }


    @Subscribe(
            thread = EventThread.IO,
            tags = {
                    @Tag("EAT_MORE")
            }
    )
    public void eatMore8(ArrayList<String> foods) {
        atomicInteger.getAndIncrement();
        if((atomicInteger.get() % 1000 == 0) || (atomicInteger.get() == 1)) {
            //log.info("===================================method 11111 " + atomicInteger.get());
        }
        countDownLatch.countDown();
        // purpose
        foods.forEach(O01HelloWord::printThreadAndData);
    }

}
