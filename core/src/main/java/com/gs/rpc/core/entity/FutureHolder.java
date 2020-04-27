package com.gs.rpc.core.entity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FutureHolder {

    private static Map<Long, Response> responseMap = new ConcurrentHashMap<>();


    public static void put(long requestId, Response response) {
        responseMap.put(requestId, response);
    }


    public static Response get(long requestId) {
        return responseMap.get(requestId);
    }


    public static void remove(long requestId) {
        responseMap.remove(requestId);
    }

}
