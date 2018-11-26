package com.les.springevent;

import com.les.exampletest.ExampletestApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @ClassName: MealEventTest
 * @Description:
 * @Author: king
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExampletestApplication.class)
@ContextConfiguration(classes = SpringEventConfig.class)
@Slf4j
public class MealEventTest {

    @Autowired
    CustomizePublisher customizePublisher;

    @Autowired
    MyApplicationEventPublisher myApplicationEventPublisher;

    @Autowired
    GeneralObjectHandler generalObjectHandler;


    @Before
    public void cleanIndex()
    {
        myApplicationEventPublisher.reset();
        generalObjectHandler.reset();
    }

    @Test
    public void testSpringEvent() throws InterruptedException {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    log.info("开始吃饭：");

                    MealEvent lunchEvent = new MealEvent("A吃午饭了", MealEnum.lunch);
                    MealEvent breakfastEvent = new MealEvent("B吃早饭了", MealEnum.breakfast);
                    MealEvent dinnerEvent = new MealEvent("C吃晚饭了", MealEnum.dinner);

                    AsynMealEvent asynMealEvent = new AsynMealEvent("异步F吃午饭了", MealEnum.lunch);
                    customizePublisher.publish(lunchEvent);
                    TimeUnit.SECONDS.sleep(1l);
                    customizePublisher.publish(breakfastEvent);
                    TimeUnit.SECONDS.sleep(1l);
                    customizePublisher.publish(dinnerEvent);
                    TimeUnit.SECONDS.sleep(1l);
                    customizePublisher.publish(asynMealEvent);
                    log.info("他们吃完了！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        log.info("meal-thread");
        thread.start();

        log.info(Thread.currentThread().getName() + " is waiting for ....");
        thread.join();
        log.info("Done!!!!!!!!!!!!");
    }

    @Test
    public void testMyApplicationEventPublisher() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    log.info("开始吃饭：");
                    AsynMealEvent asynMealEvent = new AsynMealEvent("异步F吃午饭了", MealEnum.lunch);
                    TimeUnit.SECONDS.sleep(1l);
                    myApplicationEventPublisher.publishEvent(asynMealEvent);
                    log.info("他们吃完了！");
                    TimeUnit.SECONDS.sleep(2l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        log.info("meal-thread");
        thread.start();

        log.info(Thread.currentThread().getName() + " is waiting for ....");
        thread.join();
        log.info("Done!!!!!!!!!!!!");
    }


    @Test
    public void testSmartSpringEvent() throws InterruptedException {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    log.info("开始吃饭：");

                    AsynMealEvent asynMealEvent = new AsynMealEvent("异步F吃午饭了", MealEnum.lunch);
                    TimeUnit.SECONDS.sleep(1l);
                    customizePublisher.publish(asynMealEvent);
                    log.info("他们吃完了！");
                    TimeUnit.SECONDS.sleep(2l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        log.info("meal-thread");
        thread.start();

        log.info(Thread.currentThread().getName() + " is waiting for ....");
        thread.join();
        log.info("Done!!!!!!!!!!!!");
    }

    @Test
    public void testGeneralObject() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    log.info("开始吃饭：");
                    GeneralObject genneralObject = new GeneralObject("ceshi");
                    TimeUnit.SECONDS.sleep(1l);
                    myApplicationEventPublisher.publishEvent(genneralObject);
                    log.info("他们吃完了！");
                    TimeUnit.SECONDS.sleep(2l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        log.info("meal-thread");
        thread.start();

        log.info(Thread.currentThread().getName() + " is waiting for ....");
        thread.join();
        log.info("Done!!!!!!!!!!!!");
    }

    @Test
    public void testAlotGeneralObject() throws InterruptedException {

        long startTime = System.currentTimeMillis();

        ArrayList<Thread> threadArrayList = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    {
                        log.info("开始吃饭：");
                        GeneralObject genneralObject = new GeneralObject("ceshi");
                        try {
                            TimeUnit.SECONDS.sleep(1l);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        for (int i = 0; i < 100000; i++) {
                            myApplicationEventPublisher.publishEvent(genneralObject);
                        }
                        log.info("他们吃完了！");
                        try {
                            TimeUnit.SECONDS.sleep(2l);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            threadArrayList.add(thread);
        }

        threadArrayList.stream().parallel().forEach((thread)->{
            thread.start();
        });
        threadArrayList.stream().parallel().forEach((thread)->{
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        log.info("time passed ==>" + (System.currentTimeMillis() - startTime) + "ms");
        log.info("reject event message number ==>" + myApplicationEventPublisher.getAtomicInteger().get());
        log.info("Done!!!!!!!!!!!! total processed messages ==" + generalObjectHandler.getAtomicInteger().get());
    }

}