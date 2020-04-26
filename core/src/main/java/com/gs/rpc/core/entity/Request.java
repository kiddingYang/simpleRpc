package com.gs.rpc.core.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Request {

    private long requestId;

    private String serviceName;

    private String methodName;

    private Class<?> clazz;

    private Class<?>[] parameterTypes;

    private Object[] params;

    private long requestTime;

}
