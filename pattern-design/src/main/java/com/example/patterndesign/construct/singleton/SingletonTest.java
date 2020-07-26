/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.example.patterndesign.construct.singleton;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @author shenchun
 * @version $Id: SingletonTest.java, v 0.1 2020年07月26日 6:03 PM shenchun Exp $
 */
public class SingletonTest {
    private static final ExecutorService executor   = new ThreadPoolExecutor(10, 100, 10, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(10000),
            Executors.defaultThreadFactory());
    private static final int             N          = 100;
    private static final CountDownLatch  startLatch = new CountDownLatch(1);
    private static final CountDownLatch  endLabtch  = new CountDownLatch(N);
    private static final Set<Long>       resultSet  = Sets.newConcurrentHashSet();

    public static void main(String[] args) {
        try {
            int i = N;
            while (i-- > 0) {
                executor.submit(new Runnable() {
                    @Override
                    public void run() {
                        doAWait(startLatch);
                        //System.out.println(IdGeneratorOne.getInstance().getId());
                        resultSet.add(IdGeneratorFive.getInstance().getId());
                        endLabtch.countDown();
                    }
                });
            }
            System.out.println("start");
            startLatch.countDown();
            doAWait(endLabtch);
            assert resultSet.size() == N;
            List<Long> resultList = Lists.newArrayList(resultSet);
            Collections.sort(resultList);
            resultList.forEach(e -> System.out.println(e));
            System.out.println("end");
        } finally {
            executor.shutdown();
        }
    }

    private static void doAWait(CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

}