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

public class GenericSkill {

    /**
     * table of XP values required for progressing to the given level
     * LEVEL_UP_TABLE[x] = XP required to reach level x
     */
    public static final int[] LEVEL_XP_REQUIREMENT = {
            0,      100,    200,    300,    400,    500,   600,   700,   800,   900,
            1000,   1200,   1400,   1600,   1800,   2000,   2200,   2400,   2600,   2800,
            3000,   3600,   4200,   4800,   5400,   6000,   6600,   7200,   7800,   8400,
            10000,  11000,  12000,  13000,  14000,  15000,  16000,  17000,  18000,  19000,
            20000,  21000,  22000,  23000,  24000,  25000,  26000,  27000,  28000,  29000,
            30000,  31000,  32000,  33000,  34000,  35000,  36000,  37000,  38000,  39000,
            40000,  41000,  42000,  43000,  44000,  45000,  46000,  47000,  48000,  49000,
            50000,  52000,  54000,  56000,  58000,  60000,  62000,  64000,  66000,  68000,
            70000,  72000,  74000,  76000,  78000,  80000,  82000,  84000,  86000,  88000,
            90000,  94000,  98000,  102000, 106000, 110000, 114000, 118000, 122000, 126000
    };
    //max level is one less than the length of the level up table
    public static final int MAX_LEVEL = LEVEL_XP_REQUIREMENT.length - 1;
    private final static String XP_TAG_SUFFIX = "XP";
    private final static String LEVEL_TAG_SUFFIX = "Level";
    NamespacedKey xpKey;
    NamespacedKey levelKey;
    Player player;
    PersistentDataContainer playerData;
    Server server;
    SkillManager skillManager;
    private double xp;
    private int level;

    GenericSkill(Player p){
        server = Bukkit.getServer();
        skillManager = JavaPlugin.getPlugin(SkillManager.class);
        player = p;
        playerData = player.getPersistentDataContainer();
        xpKey = new NamespacedKey(JavaPlugin.getPlugin(SkillManager.class), this.skillIdentifier() + XP_TAG_SUFFIX);
        levelKey = new NamespacedKey(JavaPlugin.getPlugin(SkillManager.class), this.skillIdentifier() + LEVEL_TAG_SUFFIX);
        readGenericFromNBT();
    }

    protected void readGenericFromNBT() {

        // retrieve the player's XP value for this skill from the NBT
        if(playerData.has(xpKey, PersistentDataType.DOUBLE)) {
            xp = playerData.get(xpKey, PersistentDataType.DOUBLE);
            //player.sendMessage(this.toString() + " XP data found for player " + player.getName() + ", set to " + xp);
        }
        else {
            //player.sendMessage("No " + this.toString() + " XP data found for player " + player.getName() + ", setting to 0.");
            xp = 0;
        }

        // retrieve the player's level value for this skill from the NBT
        if(playerData.has(levelKey, PersistentDataType.INTEGER)) {
            level = playerData.get(levelKey, PersistentDataType.INTEGER);
            //player.sendMessage(this.toString() + " level data found for player " + player.getName() + ", set to " + level);
        }
        else {
            //player.sendMessage("No " + this.toString() + " level data found for player " + player.getName() + ", setting to 0.");
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
        if (level < MAX_LEVEL) {
            while(xp >= LEVEL_XP_REQUIREMENT[level + 1]) {
                //level up the skill
                levelUp();
                if(level == MAX_LEVEL) break;
            }
        }
    }

    private void levelUp() {
        this.level++;
        player.sendMessage("Congratulations! You just reached level "
                + ChatColor.GREEN + level
                + ChatColor.WHITE + " in the "
                + ChatColor.AQUA + this.toString()
                + ChatColor.WHITE + " skill!");
        player.sendMessage("To reach level " + ChatColor.GREEN + (level+1)
                + ChatColor.WHITE + ", you need "
                + ChatColor.RED + ((int)(LEVEL_XP_REQUIREMENT[level+1] - getXp()))
                + ChatColor.WHITE + " more "
                + ChatColor.AQUA + this.toString()
                + ChatColor.WHITE + " XP.");
    }

    protected void resetStats() {
        //debugging method
        xp = 0;
        level = 0;
        saveToNBT();
    }

    public String skillIdentifier(){
        return "GenericSkill";
    }

    @Override
    public String toString() {
        return "GenericSkill";
    }

}
