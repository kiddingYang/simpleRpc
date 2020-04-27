package com.gs.rpc.core.handler;

import com.gs.rpc.core.entity.FutureHolder;
import com.gs.rpc.core.entity.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientChannelHandler extends SimpleChannelInboundHandler<Response> {
    private static Logger log = LoggerFactory.getLogger(ClientChannelHandler.class);

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("error in channel:{} {}", ctx.channel(), cause);
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Response response) throws Exception {
        FutureHolder.put(response.getRequestId(), response);
    }

}
