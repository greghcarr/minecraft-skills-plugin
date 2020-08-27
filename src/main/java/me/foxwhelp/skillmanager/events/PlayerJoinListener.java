package me.foxwhelp.skillmanager.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener extends ServerEventListener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        /* TODO pull joining player's skill info from database into skillMap */
        server.broadcastMessage("A player just joined the game: " + e.getPlayer().getName() +
                ", UUID: " + e.getPlayer().getUniqueId());

        // add the joined player to the skillMap
        skillManager.addToSkillMap(e.getPlayer());
    }
}
