package com.gs.rpc.core.bootstrap;

public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String hello) {
        return "response : " + hello;
    }
}
