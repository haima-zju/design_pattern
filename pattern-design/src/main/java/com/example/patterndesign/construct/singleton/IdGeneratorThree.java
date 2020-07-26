/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.example.patterndesign.construct.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 懒汉式，双重检测
 * @author shenchun
 * @version $Id: IdGeneratorThree.java, v 0.1 2020年07月26日 9:55 PM shenchun Exp $
 */
public class IdGeneratorThree {
    private final  AtomicLong     id       = new AtomicLong(0);
    private static IdGeneratorThree INSTANCE = null;
    private IdGeneratorThree(){

    }
    public static IdGeneratorThree getInstance(){
        if(INSTANCE == null){
            synchronized (IdGeneratorThree.class) {
                if (INSTANCE == null) {
                    return INSTANCE = new IdGeneratorThree();
                }
            }
        }
        return INSTANCE;
    }

    public long getId(){
        return id.incrementAndGet();
    }
}