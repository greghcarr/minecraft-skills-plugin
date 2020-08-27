package me.foxwhelp.skillmanager.events;

import me.foxwhelp.skillmanager.SkillManager;
import org.bukkit.Server;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerEventListener implements Listener {

    SkillManager skillManager = JavaPlugin.getPlugin(SkillManager.class);
    Server server = skillManager.getServer();
}
