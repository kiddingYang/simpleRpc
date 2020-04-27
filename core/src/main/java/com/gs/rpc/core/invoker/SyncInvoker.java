package com.gs.rpc.core.invoker;

import com.gs.rpc.core.entity.Request;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;

public class SyncInvoker implements InvocationHandler {

    private Dispatcher dispatcher;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request = new Request();
        request.setClazz(method.getDeclaringClass());
        request.setMethodName(method.getName());
        request.setParams(args);
        request.setRequestTime(new Date().getTime());
        request.setParameterTypes(method.getParameterTypes());
        return dispatcher.dispatch(request);
    }

}
