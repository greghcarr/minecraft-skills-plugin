package me.foxwhelp.skillmanager.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeathListener extends ServerEventListener {


    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {

        //if a player killed the mob
        if(e.getEntity().getKiller() instanceof Player) {
            Player killer = (Player) e.getEntity().getKiller();
            skillManager.getSkillSet(killer).getSlayer().processEntityKill(e.getEntity());
        }
    }
}
