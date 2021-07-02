/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.example.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author shenchun
 * @version $Id: SyncCompareTest.java, v 0.1 2021年05月26日 4:49 PM shenchun Exp $
 */
public class SyncCompareTest {

    public static void main(String[] args) {
        run("sync");
        run("lock");
    }

    private static void run(String lockType) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(100);
        CountDownLatch countDownLatch = new CountDownLatch(100);
        LongHolder longHolder = new LongHolder();
        Object lock = new Object();
        ReentrantLock reentrantLock = new ReentrantLock();
        long now = System.nanoTime();
        System.out.println("start, now = "+now);
        for (int i = 0; i < 100; i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        cyclicBarrier.await();
                    }catch (Exception e){

                    }
                    for (int i = 0; i < 100000; i++){
                        if("sync".equals(lockType)) {
                            synchronized (lock) {
                                longHolder.count++;
                            }
                        }else{
                            reentrantLock.lock();
                            try {
                                longHolder.count++;
                            }finally {
                                reentrantLock.unlock();
                            }
                        }
                    }
                    countDownLatch.countDown();
                }
            });
            thread.start();
        }
        try {
            countDownLatch.await();
        }catch (Exception e){

        }
        System.out.println("end, cost = "+(System.nanoTime()-now)/1000000+", count = "+longHolder.count);
    }

    public static class LongHolder{
        private long count = 0;
    }
}