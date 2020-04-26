package com.gs.rpc.core.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultRegister implements Register {

    private Map<String, Service> serviceProvider = new ConcurrentHashMap<>();


    @Override
    public void register(Service service) {
        serviceProvider.put(service.getServiceName(), service);
    }

    @Override
    public void unRegister(Service service) {
        serviceProvider.remove(service.getServiceName());
    }

    @Override
    public Service discover(String serviceName) {
        return serviceProvider.get(serviceName);
    }

}
