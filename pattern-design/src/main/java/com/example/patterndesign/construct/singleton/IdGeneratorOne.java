/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.example.patterndesign.construct.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 饿汉式
 * @author shenchun
 * @version $Id: IdGeneratorOne.java, v 0.1 2020年07月26日 5:57 PM shenchun Exp $
 */
public class IdGeneratorOne {
    private AtomicLong id = new AtomicLong(0);

    private static final IdGeneratorOne INSTANCE = new IdGeneratorOne();

    private IdGeneratorOne() {
    }

    public static IdGeneratorOne getInstance(){
        return INSTANCE;
    }

    public long getId(){
        return id.incrementAndGet();
    }
}