package io.github.lumine1909.offlineencryptor.velocity;

import com.velocitypowered.proxy.network.Connections;
import com.velocitypowered.proxy.protocol.packet.ServerLoginPacket;
import io.github.lumine1909.offlineencryptor.NetworkProcessor;
import io.netty.channel.Channel;

public class VelocityNetworkProcessor extends NetworkProcessor<ServerLoginPacket> {

    @Override
    public void inject(Channel channel) {
        channel.pipeline().addBefore(Connections.HANDLER, "oe_handler", new VelocityPacketInterceptor(channel, this));
    }

    @Override
    public void uninject(Channel channel) {
        if (channel.pipeline().get("oe_handler") != null) {
            channel.pipeline().remove("oe_handler");
        }
    }
}
