package com.gs.rpc.core.bootstrap;

import com.gs.rpc.core.codec.ProtocolEncoder;
import com.gs.rpc.core.entity.FutureHolder;
import com.gs.rpc.core.entity.Request;
import com.gs.rpc.core.entity.Response;
import com.gs.rpc.core.handler.ClientChannelHandler;
import com.gs.rpc.core.register.DefaultRegister;
import com.gs.rpc.core.register.Register;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class RpcClient {

    private EventLoopGroup workGroup = new NioEventLoopGroup();

    private Register register = new DefaultRegister();

    private AtomicInteger incr = new AtomicInteger(1);

    private List<Channel> channelCache = new ArrayList<>();

    private Bootstrap bootstrap;

    public RpcClient() {

        try {
            bootstrap = new Bootstrap();
            bootstrap.group(workGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ProtocolEncoder())
                                    .addLast(new ProtocolEncoder())
                                    .addLast(new LoggingHandler(LogLevel.DEBUG))
                                    .addLast(new ClientChannelHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 9999).sync();
            Channel channel = channelFuture.channel();
            channelCache.add(channel);
            channelFuture.addListener(ChannelFutureListener.CLOSE);
        } catch (Exception e) {
            log.error("", e);
            System.exit(-1);
        }
    }

    public Response write(Request request) {
        request.setRequestId(incr.getAndIncrement());
//        channelCache.get(0).writeAndFlush(request);
        ChannelFuture channelFuture = null;
        try {
            channelFuture = bootstrap.connect("127.0.0.1", 9999).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Channel channel = channelFuture.channel();
        channel.writeAndFlush(request);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Response response = FutureHolder.get(request.getRequestId());
        return response;
    }

}
