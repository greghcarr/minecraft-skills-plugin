package me.foxwhelp.skillmanager;

import me.foxwhelp.skillmanager.skills.Slayer;
import org.bukkit.entity.Player;

public class SkillSet {

    Slayer slayer;

    SkillSet(Player p){
        slayer = new Slayer(p);
    }

    public Slayer getSlayer() {
        return slayer;
    }

    public void saveSkillsToNBT() {
        slayer.saveToNBT();
    }
}
