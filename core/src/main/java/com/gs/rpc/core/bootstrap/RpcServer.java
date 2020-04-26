package com.gs.rpc.core.bootstrap;

import com.gs.rpc.core.handler.InitHandler;
import com.gs.rpc.core.register.Register;
import com.gs.rpc.core.register.Service;
import com.gs.rpc.core.register.ServiceHolder;
import com.gs.rpc.core.spi.RpcServiceLoader;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RpcServer {

    private EventLoopGroup bossGroup = new NioEventLoopGroup();

    private EventLoopGroup workerGroup = new NioEventLoopGroup();

    private Register register;

    private int port;


    public RpcServer(int port) {
        this.port = port;
        init();
    }

    private void init() {
        register = RpcServiceLoader.load(Register.class);
        Service service = new Service();
        service.setId("service_id");
        service.setServiceName("rpc_test");
        service.setAddress("127.0.0.1");
        service.setPort(port);
        register.register(service);
    }

    public void start() {
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new InitHandler());
            serverBootstrap.bind(port).sync().addListener(ChannelFutureListener.CLOSE);
        } catch (Exception e) {
            log.error("start error", e);
            System.exit(-1);
        }
    }


    public RpcServer addService(Class serviceInterface, Object serviceImpl) {
        ServiceHolder.addService(serviceInterface, serviceImpl);
        return this;
    }

}
