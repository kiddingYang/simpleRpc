package com.gs.rpc.core.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Response {
    private long requestId;

    private int code;

    private long responseTime;

    private Object result;

}
