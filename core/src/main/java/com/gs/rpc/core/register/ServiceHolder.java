package com.gs.rpc.core.register;

import com.gs.rpc.core.exception.ServiceRegisterException;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class ServiceHolder {

    private static Map<String, Object> serviceMap = new ConcurrentHashMap<>();


    public static void addService(Class serviceInterface, Object serviceImpl) {
        serviceMap.put(serviceInterface.getName(), serviceImpl);
    }


    public static void addService(Class classImpl) {
        try {
            serviceMap.put(classImpl.getName(), classImpl.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            throw new ServiceRegisterException();
        }
    }

    public static Object findServiceImpl(Class serviceInterface) {
        return serviceMap.get(serviceInterface.getName());
    }

}
