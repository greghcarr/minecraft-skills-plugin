package me.foxwhelp.skillmanager;

import me.foxwhelp.skillmanager.events.EntityDeathListener;
import me.foxwhelp.skillmanager.events.PlayerJoinListener;
import me.foxwhelp.skillmanager.events.PlayerQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class SkillManager extends JavaPlugin {

    Server server;
    HashMap<UUID, SkillSet> skillMap;

    @Override
    public void onEnable() {
        /* plugin startup */

        server = getServer();
        server.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        server.getPluginManager().registerEvents(new PlayerQuitListener(), this);
        server.getPluginManager().registerEvents(new EntityDeathListener(), this);

        // Map used to store skill data of all currently logged in players
        skillMap = new HashMap<UUID, SkillSet>();
    }

    @Override
    public void onDisable() {
        /* plugin shutdown */
        saveAllSkillStatsToDb();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("test")){
            //System.out.println("Foxwhelp's Slayer XP: " + this.getSkillSet().getSlayer().getXp());
            return true;
        }
        return false;
    }

    public void addToSkillMap(Player p) {
        skillMap.put(p.getUniqueId(), new SkillSet(p));
    }

    public SkillSet getSkillSet(Player p) {
        return skillMap.get(p.getUniqueId());
    }

    public void saveAllSkillStatsToDb() {
        /* TODO save stats of all players to database */
    }
}
