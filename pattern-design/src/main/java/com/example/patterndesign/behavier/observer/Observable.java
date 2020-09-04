/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.example.patterndesign.behavier.observer;

import java.lang.annotation.*;

/**
 *
 * @author shenchun
 * @version $Id: Observable.java, v 0.1 2020年09月01日 8:28 PM shenchun Exp $
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Observable {
}