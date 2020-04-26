package com.gs.rpc.core.spi;

import java.util.ServiceLoader;

public class RpcServiceLoader {

    public static <S> S load(Class<S> serviceClass) {
        return ServiceLoader.load(serviceClass).iterator().next();
    }

}
