package me.foxwhelp.skillmanager.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener extends ServerEventListener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        skillManager.playerJoined(e.getPlayer());
    }
}
