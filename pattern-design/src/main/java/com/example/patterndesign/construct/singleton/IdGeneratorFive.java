/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.example.patterndesign.construct.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 枚举
 * @author shenchun
 * @version $Id: IdGeneratorFive.java, v 0.1 2020年07月26日 10:25 PM shenchun Exp $
 */
public enum  IdGeneratorFive {
    INSTANCE;

    private IdGeneratorFive() {
    }

    private AtomicLong id = new AtomicLong(0);

    public static IdGeneratorFive getInstance(){
        return INSTANCE;
    }

    public long getId(){
        return id.incrementAndGet();
    }
}