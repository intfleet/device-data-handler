package com.intellifleet.cgw.tcp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellifleet.cgw.metrics.CgwMetrics;
import com.intellifleet.cgw.model.GpsPacket;
import com.intellifleet.kafka.GPSDataPublisher;
import com.intellifleet.utility.GPSFileUtility;
import io.micrometer.core.instrument.Timer;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@AllArgsConstructor
@ChannelHandler.Sharable
public class CgwTcpHandler extends SimpleChannelInboundHandler<String> {

    private final CgwMetrics metrics;
    private final GPSDataPublisher gpsDataPublisher;
    private final GPSFileUtility gpsFileUtility;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        metrics.connectionOpened();
        log.info("Device connected: {}", ctx.channel().remoteAddress());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        Timer.Sample timer = metrics.startPacketTimer();
        try {
            if("STOP".equals(msg)) {
                gpsFileUtility.saveFile();
            } else {
                metrics.packetReceived();
                // Example incoming message:
                // IMEI=123456789012345,LAT=22.5726,LON=88.3639,SPEED=45
//                gpsFileUtility.recevLine(msg);
                gpsDataPublisher.sendMessageToTopic(msg);
                System.out.println("CGW Received GPS packet: " + msg);
//            GpsPacket packet = parse(msg);
//            System.out.println("Received GPS packet: " + packet);


                // Send ACK to device
                ctx.writeAndFlush("OK\n");
            }


        } catch (Exception e) {
            ctx.writeAndFlush("ERROR\n");
            metrics.packetError();
        } finally {
            metrics.stopPacketTimer(timer);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        metrics.connectionClosed();
        log.info("Device disconnected: {}", ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {

        if (cause instanceof IOException) {
            log.warn("Connection aborted by client: {}", ctx.channel().remoteAddress());
        } else {
            log.error("Unexpected exception", cause);
        }

        ctx.close();
    }

    private GpsPacket parse(String msg) {
        String[] parts = msg.split(",");
        GpsPacket packet = new GpsPacket();

        for (String p : parts) {
            String[] kv = p.split("=");
            switch (kv[0]) {
                case "IMEI" -> packet.setImei(kv[1]);
                case "LAT" -> packet.setLat(Double.parseDouble(kv[1]));
                case "LON" -> packet.setLon(Double.parseDouble(kv[1]));
                case "SPEED" -> packet.setSpeed(Integer.parseInt(kv[1]));
            }
        }
        packet.setTimestamp(System.currentTimeMillis());
        return packet;
    }
}

