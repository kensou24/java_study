package com.les.rxBus;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.thread.ThreadHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * @ProjectName: TestEventBus
 * @Package: com.les.rxBus
 * @ClassName: RxBusTest
 * @Description:
 * @Author: king
 * @CreateDate: 2018/11/13 10:46
 * @UpdateUser: king
 * @UpdateDate: 2018/11/13 10:46
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Slf4j
public class RxBusTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testRxBus()
    {

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int testGoal = 1;
        int cousumerNumber = 8;
        for(int i = 1; i < 20; i++) {
            testNumber((int)(Math.pow(2, i)), cousumerNumber);
        }
    }

    private <T> void testNumber(int testGoal, int cousumerNumber) {
        ConsumerTest consumerTest = new ConsumerTest();
        consumerTest.onCreate();
        consumerTest.setCountDownLatch(new CountDownLatch(testGoal*cousumerNumber));
        long beginTime = System.currentTimeMillis();

        Executor executor = Executors.newCachedThreadPool();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                IntStream.range(0, testGoal).parallel().forEach((i)->{
                    ArrayList<String> stringArrayList = new ArrayList<>();
                    stringArrayList.add(i + ", 123456");
                    //stringArrayList.add(i + ", 234567");
                    //log.info("begin =======");
                    RxBus.get().post("EAT_MORE", stringArrayList);
                    //log.info("end=========");
                });
            }
        });

        try {
            consumerTest.getCountDownLatch().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        consumerTest.onDestroy();

        long endTime = System.currentTimeMillis();

        log.info("used time for test number " + testGoal + " times= " + (endTime - beginTime) + "ms, consumer number ==" + cousumerNumber);
    }
}