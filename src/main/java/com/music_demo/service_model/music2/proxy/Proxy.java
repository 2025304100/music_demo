package com.music_demo.service_model.music2.proxy;

import org.apache.log4j.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


public class Proxy implements InvocationHandler {
    private Logger log = Logger.getLogger(Proxy.class);
    private Object ob;

    public Proxy(Object ob) {
        this.ob = ob;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long time = System.currentTimeMillis();
        System.out.println("用于记录时间");
        Object invoke = method.invoke(ob, args);
        long oldtime = System.currentTimeMillis();
        System.out.println("访问的时间:" + (oldtime - time));
        log.info(Proxy.class + "访问的时间:" + (oldtime - time));
        //返回结果默认采用被代理数据

        return invoke;
    }
}
