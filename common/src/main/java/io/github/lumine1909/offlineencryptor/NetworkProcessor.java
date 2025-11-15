package io.github.lumine1909.offlineencryptor;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;

public abstract class NetworkProcessor<C2SHello> {

    protected final Map<String, C2SHello> C2S_HELLO_CACHE = new HashMap<>();

    public abstract void inject(Channel channel);

    public abstract void uninject(Channel channel);

    public Map<String, C2SHello> getCache() {
        return C2S_HELLO_CACHE;
    }
}
