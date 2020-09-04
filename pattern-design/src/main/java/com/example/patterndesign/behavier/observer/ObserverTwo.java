/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.example.patterndesign.behavier.observer;

import org.springframework.beans.factory.InitializingBean;

/**
 *
 * @author shenchun
 * @version $Id: ObserverTwo.java, v 0.1 2020年09月03日 12:21 AM shenchun Exp $
 */
public class ObserverTwo implements InitializingBean {
    @Observable
    public void execute(Long param){
        System.out.println("ObserverTwo: "+param);
    }
    @Override
    public void afterPropertiesSet() {
        ObserverManager.register(this);
    }
}