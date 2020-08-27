package me.foxwhelp.skillmanager.skills;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class GenericSkill {

    private double xp;
    private int level;
    Player player;
    Server server = Bukkit.getServer();

    /**
     * table of XP values required for progressing to the given level
     * LEVEL_UP_TABLE[x] = XP required to reach level x
     *
     * TODO add actual XP level up values
     */
    public final static int[] LEVEL_XP_REQUIREMENT = {
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
    public final static int MAX_LEVEL = LEVEL_XP_REQUIREMENT.length - 1;

    GenericSkill(Player p){
        player = p;

        /* TODO xp and level should be set in the appropriate Skill subclass
             based on metadata values in the Player PersistentDataContainer */
        xp = 0;
        level = 0;
    }

    public double getXp() {
        /**
         * Retrieve this Skill's current total XP
         */
        return xp;
    }

    public int getLevel() {
        /**
         * Retrieve this Skill's current level
         */
        return level;
    }

    public void addXp(double d) {
        /**
         * Adds XP to this Skill's total, and sets the level of the Skill according to the total XP afterwards
         * @param d the amount of XP to be added
         * @return true if the skill leveled up, false if not
         */
        this.xp += d;
        boolean leveledUp = false;
        //while player isn't max level and player XP is greater than the requirement for the next level
        while (level < MAX_LEVEL && xp > LEVEL_XP_REQUIREMENT[level + 1]) {
            //level up the skill
            levelUp();
        }
    }

    private void levelUp() {
        this.level++;
        server.broadcastMessage("Attention gamers! "
                + player.getName()
                + " just reached level " + level
                + " in the " + this.toString() + " skill!");
    }

    @Override
    public String toString() {
        return "GenericSkill";
    }

}
