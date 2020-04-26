package com.gs.rpc.core.example;

public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String hello) {
        return "response : " + hello;
    }
}
