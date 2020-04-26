package com.gs.rpc.core.bootstrap;

public class RpcServerTest {

    public static void main(String[] args) {
        RpcServer rpcServer = new RpcServer(9999);
        rpcServer.addService(HelloService.class, HelloServiceImpl.class);
        rpcServer.start();
    }

}