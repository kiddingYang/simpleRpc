package com.gs.rpc.core.register;

public interface Register {

    void register(Service service);

    void unRegister(Service service);

    Service discover(String serviceName);

}
