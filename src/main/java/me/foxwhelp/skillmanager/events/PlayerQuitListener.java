package me.foxwhelp.skillmanager.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener extends ServerEventListener{

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        skillManager.playerLeft(e.getPlayer());
    }
}
