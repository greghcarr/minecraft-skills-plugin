package me.foxwhelp.skillmanager.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener extends ServerEventListener{

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {

        /* TODO save quitting player's skill info from skillMap to database and delete their entry from skillMap */
        server.broadcastMessage("A player just left the game: " + e.getPlayer().getName() +
                ", UUID: " + e.getPlayer().getUniqueId());
    }
}
