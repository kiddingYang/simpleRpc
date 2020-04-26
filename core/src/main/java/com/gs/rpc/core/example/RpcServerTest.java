package com.gs.rpc.core.example;

import com.gs.rpc.core.bootstrap.RpcServer;

public class RpcServerTest {

    public static void main(String[] args) {
        RpcServer rpcServer = new RpcServer(9999);
        rpcServer.addService(HelloService.class, HelloServiceImpl.class);
        rpcServer.start();
    }

}