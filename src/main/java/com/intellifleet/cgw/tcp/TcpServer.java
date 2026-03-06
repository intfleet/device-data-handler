package com.intellifleet.cgw.tcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TcpServer {

    private final CgwTcpInitializer initializer;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    @Value("${cgw.tcp.port:9000}")
    private int port;

    public TcpServer(CgwTcpInitializer initializer) {
        this.initializer = initializer;
    }

    @PostConstruct
    public void start() {
        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(initializer)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            bootstrap.bind(port).sync();
            log.info("CGW TCP Server started on port {}", port);

        } catch (Exception e) {
            log.error("Failed to start TCP server", e);
        }
    }

    @PreDestroy
    public void shutdown() {
        log.info("Shutting down TCP server...");
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}


