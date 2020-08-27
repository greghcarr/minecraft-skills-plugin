package me.foxwhelp.skillmanager.skills;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Slayer extends GenericSkill {

    public Slayer(Player p) {
        super(p);
    }

    /**
     * TODO Determine whether a kill by this player was on-task, and award xp if so.
     * @param e the Entity killed by the player
     */
    public void processEntityKill(Entity e) {
        Bukkit.getServer().broadcastMessage(player.getName()
                + " just killed a "
                + e.getType() + ".");
        addXp(((Attributable) e).getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        Bukkit.getServer().broadcastMessage("Slayer XP for "
                + player.getName()
                + " is now "
                + getXp() + ".");
    }

    @Override
    public String toString() {
        return "Slayer";
    }
}
