package com.gs.rpc.core.proxy;

import com.gs.rpc.core.invoker.SyncInvoker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyFactory {

    private InvocationHandler invocationHandler;


    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class<T> interfaceClazz) {
        return (T) Proxy.newProxyInstance(ProxyFactory.class.getClassLoader(), new Class[]{interfaceClazz}, new SyncInvoker());
    }

}
