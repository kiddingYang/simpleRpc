package com.gs.rpc.core.proxy;

import com.gs.rpc.core.entity.Request;
import com.gs.rpc.core.entity.Response;
import com.gs.rpc.core.register.ServiceHolder;

import java.lang.reflect.Method;

public class DefaultResponseHandler implements ResponseHandler {

    @Override
    public Response handle(Request request) {
        Object serviceImpl = ServiceHolder.findServiceImpl(request.getClazz());
        Response response = new Response();
        if (serviceImpl == null) {
            response.setCode(-1);
            return response;
        }
        try {
            Method method = serviceImpl.getClass().getMethod(request.getMethodName(), request.getParameterTypes());
            Object invoke = method.invoke(serviceImpl, request.getParams());
            response.setResult(0);
            response.setResult(invoke);
        } catch (Exception e) {
            response.setCode(-1);
            response.setResult(e);
        }
        return response;
    }
}
