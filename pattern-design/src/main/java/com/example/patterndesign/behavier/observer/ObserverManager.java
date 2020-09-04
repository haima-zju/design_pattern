/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.example.patterndesign.behavier.observer;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.*;

/**
 *
 * @author shenchun
 * @version $Id: ObserverManager.java, v 0.1 2020年09月01日 8:40 PM shenchun Exp $
 */
public class ObserverManager {
    private static final ConcurrentHashMap<Class, CopyOnWriteArrayList<ObserverAction>> map = new ConcurrentHashMap<Class, CopyOnWriteArrayList<ObserverAction>>();
    private static final ExecutorService executor = new ThreadPoolExecutor(10, 10, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100));

    public static void register(Object target){
        Preconditions.checkNotNull(target);
        List<Method> methods = MethodUtils.getMethodsListWithAnnotation(target.getClass(), Observable.class);
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(methods), "no method annotated with Observable");
        for (Method method : methods){
            Class[] classList = method.getParameterTypes();
            Preconditions.checkArgument(classList != null && classList.length == 1, "annotated method have exactly one param");
            Class firstParam = classList[0];
            List<ObserverAction> actionList = map.get(firstParam);
            if(CollectionUtils.isEmpty(actionList)){
                map.putIfAbsent(firstParam, Lists.newCopyOnWriteArrayList());
            }
            map.get(firstParam).add(new ObserverAction(target, method));
        }
    }

    public static void execute(Object param){
        Preconditions.checkNotNull(param);
        List<ObserverAction> matchedAction = Lists.newArrayList();
        map.entrySet().stream().filter(entry -> entry.getKey().isInstance(param)).forEach(entry -> matchedAction.addAll(entry.getValue()));
        for (ObserverAction action : matchedAction){
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    action.execute(param);
                }
            });
        }
    }
}