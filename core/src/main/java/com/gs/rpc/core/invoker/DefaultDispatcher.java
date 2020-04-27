package com.gs.rpc.core.invoker;

import com.gs.rpc.core.bootstrap.RpcClient;
import com.gs.rpc.core.entity.Request;

public class DefaultDispatcher implements Dispatcher {

    private RpcClient rpcClient = new RpcClient();

    @Override
    public Object dispatch(Request request) {
        return rpcClient.write(request);
    }

}
