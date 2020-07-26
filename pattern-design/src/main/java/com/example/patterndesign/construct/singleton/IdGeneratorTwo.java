/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.example.patterndesign.construct.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 懒汉式
 * @author shenchun
 * @version $Id: IdGeneratorTwo.java, v 0.1 2020年07月26日 9:45 PM shenchun Exp $
 */
public class IdGeneratorTwo {
    private final AtomicLong id = new AtomicLong(0);
    private static IdGeneratorTwo INSTANCE = null;
    private IdGeneratorTwo(){

    }
    public static synchronized IdGeneratorTwo getInstance(){
        if(INSTANCE == null){
            return INSTANCE = new IdGeneratorTwo();
        }
        return INSTANCE;
    }

    public long getId(){
        return id.incrementAndGet();
    }
}