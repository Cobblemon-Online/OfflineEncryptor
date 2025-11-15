package io.github.lumine1909.offlineencryptor.velocity;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.ConnectionHandshakeEvent;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.proxy.VelocityServer;
import com.velocitypowered.proxy.connection.MinecraftConnection;
import com.velocitypowered.proxy.connection.client.LoginInboundConnection;
import com.velocitypowered.proxy.protocol.packet.ServerLoginPacket;
import io.github.lumine1909.offlineencryptor.NetworkProcessor;
import io.github.lumine1909.offlineencryptor.velocity.metrics.Metrics;

import javax.inject.Inject;
import java.util.logging.Logger;

public class OfflineEncryptor {

    public static OfflineEncryptor plugin;

    private final VelocityServer server;
    private final Logger logger;
    private final Metrics.Factory metricsFactory;
    private final NetworkProcessor<ServerLoginPacket> processor = new VelocityNetworkProcessor();

    @Inject
    public OfflineEncryptor(ProxyServer server, Logger logger, Metrics.Factory metricsFactory) {
        this.server = (VelocityServer) server;
        this.logger = logger;
        this.metricsFactory = metricsFactory;
        if (server.getConfiguration().isOnlineMode()) {
            throw new IllegalStateException("Encryption is already enabled in online mode!");
        }
        plugin = this;
    }

    public VelocityServer getServer() {
        return server;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        metricsFactory.make(this, 27988);
    }

    @Subscribe
    public void onHandshake(ConnectionHandshakeEvent event) {
        LoginInboundConnection login = (LoginInboundConnection) event.getConnection();
        MinecraftConnection connection = ReflectionUtil.field$delegate.get(login).getConnection();
        System.out.println(connection);
        System.out.println(connection.getChannel());
        processor.inject(connection.getChannel());
    }

    @Subscribe
    public void onPlayerDisconnect(DisconnectEvent event) {
        processor.getCache().remove(event.getPlayer().getUsername());
    }
}
