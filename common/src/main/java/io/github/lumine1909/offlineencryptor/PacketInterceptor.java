package io.github.lumine1909.offlineencryptor;

import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;

public abstract class PacketInterceptor<C2SHello, C2SResponse> extends ChannelDuplexHandler {

    protected final Channel channel;
    protected final NetworkProcessor<C2SHello> processor;

    protected String username;

    protected PacketInterceptor(Channel channel, NetworkProcessor<C2SHello> processor) {
        this.channel = channel;
        this.processor = processor;
    }

    protected abstract void processC2SHello(ChannelHandlerContext ctx, C2SHello packet);

    protected abstract void processC2SResponse(ChannelHandlerContext ctx, C2SResponse packet);
}