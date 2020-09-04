/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.example.patterndesign.behavier.observer;

import org.springframework.beans.factory.InitializingBean;

/**
 *
 * @author shenchun
 * @version $Id: ObserverOne.java, v 0.1 2020年09月01日 8:36 PM shenchun Exp $
 */
public class ObserverOne implements InitializingBean {

    @Observable
    public void execute(Long param) {
        System.out.println("ObserverOne: "+param);
    }

    @Override
    public void afterPropertiesSet()  {
        ObserverManager.register(this);
    }
}