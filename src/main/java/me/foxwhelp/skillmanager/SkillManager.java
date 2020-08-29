package me.foxwhelp.skillmanager;

import me.foxwhelp.skillmanager.events.EntityDeathListener;
import me.foxwhelp.skillmanager.events.PlayerJoinListener;
import me.foxwhelp.skillmanager.events.PlayerQuitListener;
import me.foxwhelp.skillmanager.skills.Slayer;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class SkillManager extends JavaPlugin {

    Server server;
    HashMap<UUID, SkillSet> skillSetMap;

    /**
     * table of XP values required for progressing to the given level
     * LEVEL_UP_TABLE[x] = XP required to reach level x
     *
     * TODO add actual XP level up values
     */
    public static final int[] LEVEL_XP_REQUIREMENT = {
            0,
            100,
            200,
            300,
            400,
            500,
            600,
            700,
            800,
            900,
            1000};
    //max level is one less than the length of the level up table
    public static final int MAX_LEVEL = LEVEL_XP_REQUIREMENT.length - 1;

    public static final Map<String, EntityType> ENTITY_MAP = new HashMap<String, EntityType>() {
        {
            put("Creeper", EntityType.CREEPER);
            put("Zombie", EntityType.ZOMBIE);
        }
    };

    @Override
    public void onEnable() {
        /* plugin startup */

        server = getServer();
        server.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        server.getPluginManager().registerEvents(new PlayerQuitListener(), this);
        server.getPluginManager().registerEvents(new EntityDeathListener(), this);

        // Map used to store skill data of all currently logged in players
        skillSetMap = new HashMap<UUID, SkillSet>();
    }

    @Override
    public void onDisable() {
        /* plugin shutdown */
        for(UUID uuid:skillSetMap.keySet()){
            skillSetMap.get(uuid).saveSkillsToNBT();
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("test")){
            //System.out.println("Foxwhelp's Slayer XP: " + this.getSkillSet().getSlayer().getXp());
            return true;
        }
        if(command.getName().equalsIgnoreCase("slayer")){
            if(sender instanceof Player){
                Player p = (Player) sender;
                SkillSet ss = getSkillSet(p);
                Slayer s = ss.getSlayer();
                s.handleCommand(label, args);
            }
            else {
                sender.sendMessage("This command must be run by a player.");
            }
            return true;
        }
        return false;
    }

    public void playerJoined(Player p) {
        //add player to SkillMap
        skillSetMap.put(p.getUniqueId(), new SkillSet(p));
    }

    public void playerLeft(Player p) {
        //save player XP data
        skillSetMap.get(p.getUniqueId()).saveSkillsToNBT();
        //remove player from SkillMap
        skillSetMap.remove(p.getUniqueId());
    }

    public SkillSet getSkillSet(Player p) {
        return skillSetMap.get(p.getUniqueId());
    }

}
