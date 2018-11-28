package com.les.gctest;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: GcCase
 * @Description: 测试GC
 * @Author: king
 * @CreateDate: 2018/11/27 10:03
 */
// -Xmx2g -Xms2g -Xmn500m -XX:+PrintGCDetails
// -XX:+UseConcMarkSweepGC -XX:+PrintGC
    /*

    -XX:+PrintHeapAtGC
-verbose:gc
-XX:+PrintTenuringDistribution
-XX:+PrintGCDateStamps
 -XX:+PrintGCDetails
 -XX:+PrintReferenceGC
 -XX:+PrintGCTimeStamps
 -XX:+PrintGC

     */
public class GcCase {


    @Test
    public void testYGC()
    {
        for (int i = 0; i < 1000; i++) {
            allocate_1M();

            if(i > 200)
            {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void allocate_1M() {
        byte[] _1M = new byte[1024 * 1000];
    }

}
