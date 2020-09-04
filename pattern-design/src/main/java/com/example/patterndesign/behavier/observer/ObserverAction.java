/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.example.patterndesign.behavier.observer;

import java.lang.reflect.Method;

/**
 *
 * @author shenchun
 * @version $Id: ObserverAction.java, v 0.1 2020年09月01日 8:41 PM shenchun Exp $
 */
public class ObserverAction {
    private Object target;
    private Method method;

    public ObserverAction(Object target, Method method) {
        this.target = target;
        this.method = method;
        this.method.setAccessible(true);
    }

    void execute(Object param){
        try {
            method.invoke(target, param);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}