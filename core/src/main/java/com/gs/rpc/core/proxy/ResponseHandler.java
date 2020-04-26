package com.gs.rpc.core.proxy;

import com.gs.rpc.core.entity.Request;
import com.gs.rpc.core.entity.Response;

public interface ResponseHandler {
    Response handle(Request request);
}
