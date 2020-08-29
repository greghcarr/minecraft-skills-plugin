package me.foxwhelp.skillmanager.skills;

import me.foxwhelp.skillmanager.SkillManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import static me.foxwhelp.skillmanager.SkillManager.MAX_LEVEL;

public class GenericSkill {

    private double xp;
    private final static String XP_TAG_SUFFIX = "XP";
    NamespacedKey xpKey;
    private int level;
    private final static String LEVEL_TAG_SUFFIX = "Level";
    NamespacedKey levelKey;
    Player player;
    PersistentDataContainer playerData;
    Server server;
    SkillManager skillManager;

    GenericSkill(Player p){
        server = Bukkit.getServer();
        skillManager = JavaPlugin.getPlugin(SkillManager.class);
        player = p;
        playerData = player.getPersistentDataContainer();
        xpKey = new NamespacedKey(JavaPlugin.getPlugin(SkillManager.class), this.toString() + XP_TAG_SUFFIX);
        levelKey = new NamespacedKey(JavaPlugin.getPlugin(SkillManager.class), this.toString() + LEVEL_TAG_SUFFIX);
        readGenericFromNBT();
    }

    protected void readGenericFromNBT() {

        // retrieve the player's XP value for this skill from the NBT
        if(playerData.has(xpKey, PersistentDataType.DOUBLE)) {
            xp = playerData.get(xpKey, PersistentDataType.DOUBLE);
            player.sendMessage(this.toString() + " XP data found for player " + player.getName() + ", set to " + xp);
        }
        else {
            player.sendMessage("No " + this.toString() + " XP data found for player " + player.getName() + ", setting to 0.");
            xp = 0;
        }

        // retrieve the player's level value for this skill from the NBT
        if(playerData.has(levelKey, PersistentDataType.INTEGER)) {
            level = playerData.get(levelKey, PersistentDataType.INTEGER);
            player.sendMessage(this.toString() + " level data found for player " + player.getName() + ", set to " + level);
        }
        else {
            player.sendMessage("No " + this.toString() + " level data found for player " + player.getName() + ", setting to 0.");
            level = 0;
        }
    }

    public void saveToNBT() {
        saveXpToNBT();
        saveLevelToNBT();
    }

    private void saveXpToNBT() {
        playerData.set(xpKey, PersistentDataType.DOUBLE, xp);
    }

    private void saveLevelToNBT() {
        playerData.set(levelKey, PersistentDataType.INTEGER, level);
    }

    public double getXp() {
        /** Retrieve this Skill's current total XP
         *
         */
        return xp;
    }

    public int getLevel() {
        /** Retrieve this Skill's current level
         *
         */
        return level;
    }

    public void addXp(double d) {
        /** Adds XP to this Skill's total, and sets the level of the Skill according to the total XP afterwards
         *
         * @param d the amount of XP to be added
         * @return true if the skill leveled up, false if not
         */
        this.xp += d;
        //while player isn't max level and player XP is greater than the requirement for the next level
        while (level < MAX_LEVEL && xp >= SkillManager.LEVEL_XP_REQUIREMENT[level + 1]) {
            //level up the skill
            levelUp();
        }
    }

    private void levelUp() {
        this.level++;
        player.sendMessage("Congratulations! You just reached level " + ChatColor.GREEN + level + ChatColor.WHITE + " in the " + ChatColor.AQUA + this.toString() + ChatColor.WHITE + " skill!");
    }

    protected void resetStats() {
        //debugging method
        xp = 0;
        level = 0;
        saveToNBT();
    }

    @Override
    public String toString() {
        return "GenericSkill";
    }

}
