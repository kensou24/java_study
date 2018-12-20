package com.les.threaderrortest;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertTrue;

/**
 * @ClassName: BugTest
 * @Description:
 * @Author: king
 * @CreateDate: 2018/12/17 11:16
 */
public class BugTest {

    private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

    private CountDownLatch countDownLatch = new CountDownLatch(1);
    private AtomicInteger countData = new AtomicInteger(0);

    @Test
    public void testBug() throws InterruptedException {
        int threadNumber = 10;
        countDownLatch = new CountDownLatch(threadNumber);
        countData.set(0);
        for (int i = 0; i < threadNumber; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                        System.out.println(index + " " + countData.incrementAndGet());
                        countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await(1, TimeUnit.SECONDS);
        assertTrue(countData.get() == threadNumber);
    }

    @Test
    public void testBug2() throws InterruptedException {
        int threadNumber = 10;
        countDownLatch = new CountDownLatch(threadNumber);
        countData.set(0);
        for (int i = 0; i < threadNumber; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index + " " + countData.incrementAndGet());
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await(1, TimeUnit.SECONDS);
        assertTrue(countData.get() == threadNumber);
    }

}
