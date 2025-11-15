package io.github.lumine1909.offlineencryptor.paper;

import com.destroystokyo.paper.event.player.PlayerConnectionCloseEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static io.github.lumine1909.offlineencryptor.paper.OfflineEncryptor.plugin;

public class PlayerListener implements Listener {

    @EventHandler
    public void onConnectionClose(PlayerConnectionCloseEvent event) {
        plugin.getPacketProcessor().getCache().remove(event.getPlayerName());
    }
}
