package com.gs.rpc.core.handler;

import com.gs.rpc.core.codec.ProtocolDecoder;
import com.gs.rpc.core.codec.ProtocolEncoder;
import com.gs.rpc.core.entity.Response;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LoggingHandler;


public class InitHandler extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("protocol decoder", new ProtocolDecoder(Response.class));
        pipeline.addLast("protocol encoder", new ProtocolEncoder());
        pipeline.addLast(new LoggingHandler());
        pipeline.addLast("ServerChannelHandler", new ServerChannelHandler());
    }
}
