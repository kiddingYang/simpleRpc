package com.gs.rpc.core.example;

import com.gs.rpc.core.bootstrap.RpcClient;
import com.gs.rpc.core.entity.Request;

import java.lang.reflect.Method;

public class RpcClientTest {

    public static void main(String[] args) throws Exception {
        RpcClient rpcClient = new RpcClient();
        Request request = new Request();
        Method sayHello = HelloService.class.getMethod("sayHello", String.class);
        request.setParameterTypes(sayHello.getParameterTypes());
        request.setMethodName(sayHello.getName());
        request.setParams(new Object[]{"hello aaaa"});
        rpcClient.write(request);
    }

}
