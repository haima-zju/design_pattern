/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.example.patterndesign.behavier.observer;

/**
 *
 * @author shenchun
 * @version $Id: ObserverTest.java, v 0.1 2020年09月03日 12:15 AM shenchun Exp $
 */
public class ObserverTest {
    public static void main(String[] args) {
        ObserverOne observerOne = new ObserverOne();
        observerOne.afterPropertiesSet();
        ObserverTwo observerTwo = new ObserverTwo();
        observerTwo.afterPropertiesSet();

        System.out.println("start");

        ObserverManager.execute(1L);
        ObserverManager.execute(2L);
        System.out.println("end");
    }
}