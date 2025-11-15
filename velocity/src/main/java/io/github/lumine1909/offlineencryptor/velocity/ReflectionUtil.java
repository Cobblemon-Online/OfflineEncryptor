package io.github.lumine1909.offlineencryptor.velocity;

import com.velocitypowered.proxy.connection.client.InitialInboundConnection;
import com.velocitypowered.proxy.connection.client.LoginInboundConnection;
import io.github.lumine1909.reflexion.Field;

public class ReflectionUtil {

    public static final Field<InitialInboundConnection> field$delegate = Field.of(
        LoginInboundConnection.class, "delegate", InitialInboundConnection.class);
}
