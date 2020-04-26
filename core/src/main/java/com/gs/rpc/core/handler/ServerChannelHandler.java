package com.gs.rpc.core.handler;

import com.gs.rpc.core.entity.Request;
import com.gs.rpc.core.entity.Response;
import com.gs.rpc.core.proxy.DefaultResponseHandler;
import com.gs.rpc.core.proxy.ResponseHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServerChannelHandler extends SimpleChannelInboundHandler<Request> {

    private ResponseHandler responseHandler = new DefaultResponseHandler();

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Request request) throws Exception {
        long start = System.currentTimeMillis();
        Response response = responseHandler.handle(request);
        log.debug("cost : {} ms, server get request:{}, return response:{}", (System.currentTimeMillis() - start), request, response);
        ctx.writeAndFlush(response);
    }
}
