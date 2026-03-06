package com.intellifleet.cgw.metrics;

import io.micrometer.core.instrument.*;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class CgwMetrics {

    private final Counter totalPackets;
    private final Counter errorPackets;
    private final AtomicInteger activeConnections;
    private final Timer packetProcessingTimer;

    public CgwMetrics(MeterRegistry registry) {

        this.totalPackets = Counter.builder("cgw_packets_total")
                .description("Total GPS packets received")
                .register(registry);

        this.errorPackets = Counter.builder("cgw_packets_error_total")
                .description("Total GPS packet errors")
                .register(registry);

        this.activeConnections = new AtomicInteger(0);
        Gauge.builder("cgw_active_connections", activeConnections, AtomicInteger::get)
                .description("Active device connections")
                .register(registry);

        this.packetProcessingTimer = Timer.builder("cgw_packet_processing_time")
                .description("GPS packet processing time")
                .publishPercentiles(0.5, 0.95, 0.99)
                .register(registry);
    }

    public void packetReceived() {
        totalPackets.increment();
    }

    public void packetError() {
        errorPackets.increment();
    }

    public void connectionOpened() {
        activeConnections.incrementAndGet();
    }

    public void connectionClosed() {
        activeConnections.decrementAndGet();
    }

    public Timer.Sample startPacketTimer() {
        return Timer.start();
    }

    public void stopPacketTimer(Timer.Sample sample) {
        sample.stop(packetProcessingTimer);
    }
}

