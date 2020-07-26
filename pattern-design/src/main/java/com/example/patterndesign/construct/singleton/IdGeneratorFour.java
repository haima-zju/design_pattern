/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.example.patterndesign.construct.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 相比IdGeneratorOne，就是将单例放到内部静态类中，当调用getInstance时，jvm加载内部静态类，达到延迟加载的目的
 * @author shenchun
 * @version $Id: IdGeneratorFour.java, v 0.1 2020年07月26日 10:01 PM shenchun Exp $
 */
public class IdGeneratorFour {
    private AtomicLong id = new AtomicLong(0);
    private IdGeneratorFour(){}

    public static IdGeneratorFour getInstance(){
        return IdGeneratorHolder.INSTANCE;
    }

    public long getId(){
        return id.incrementAndGet();
    }

    private static class IdGeneratorHolder{
        private static final IdGeneratorFour INSTANCE = new IdGeneratorFour();
    }
}