package com.gs.rpc.core.invoker;

import com.gs.rpc.core.entity.Request;

public interface Dispatcher {

    Object dispatch(Request request);

}
