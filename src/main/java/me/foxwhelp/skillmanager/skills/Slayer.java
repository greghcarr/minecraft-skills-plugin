package me.foxwhelp.skillmanager.skills;

import me.foxwhelp.skillmanager.SkillManager;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class Slayer extends GenericSkill {

    String taskType;
    int taskRemaining;
    int tasksCompleted;
    private final static String TASK_TYPE_TAG_SUFFIX = "TaskType";
    NamespacedKey slayerTaskTypeKey;
    private final static String TASK_REMAINING_TAG_SUFFIX = "TaskRemaining";
    NamespacedKey slayerTaskRemainingKey;
    private final static String TASKS_COMPLETED_TAG_SUFFIX = "TasksCompleted";
    NamespacedKey slayerTasksCompletedKey;
    public static final String NO_TASK = "NONE";

    public Slayer(Player p) {
        super(p);
        slayerTaskTypeKey = new NamespacedKey(JavaPlugin.getPlugin(SkillManager.class), this.toString() + TASK_TYPE_TAG_SUFFIX);
        slayerTaskRemainingKey = new NamespacedKey(JavaPlugin.getPlugin(SkillManager.class), this.toString() + TASK_REMAINING_TAG_SUFFIX);
        slayerTasksCompletedKey = new NamespacedKey(JavaPlugin.getPlugin(SkillManager.class), this.toString() + TASKS_COMPLETED_TAG_SUFFIX);
        readSkillSpecificFromNBT();
    }

    public void handleCommand(String label, String[] args) {
        //command is predetermined to be sent to the owner of this skill (player) and to be a slayer command
        if(args.length > 0) {
            if (args[0].equalsIgnoreCase("task")) {
                //"slayer task..."
                if(args.length == 1){
                    //"slayer task"
                    if(taskType.equals(NO_TASK)){
                        //player has no task, assign one and inform them
                        newTask();
                        player.sendMessage("Your new Slayer task is to kill " + ChatColor.RED + taskRemaining + ' ' + taskType + "s.");
                    }
                    else{
                        //player has a task, give details
                        player.sendMessage("Your current Slayer task is to kill " + ChatColor.RED + taskRemaining + ' ' + taskType + "s.");
                    }

                }
            }
            if (args[0].equalsIgnoreCase("stats")) {
                //"slayer stats..."
                player.sendMessage("Your " + ChatColor.AQUA + "Slayer" + ChatColor.WHITE + " level is " + ChatColor.GREEN + getLevel() +
                        ChatColor.WHITE + ". You have " + ChatColor.GREEN + (int)getXp() + ChatColor.WHITE + " Slayer XP." +
                        " You have completed " + ChatColor.GREEN + tasksCompleted + ChatColor.WHITE + " Slayer tasks.");
            }
            if (args[0].equalsIgnoreCase("reset")) {
                //"slayer reset..."
                resetStats();
                player.sendMessage("Slayer stats reset.");
            }
        }
    }

    /**
     * Determine whether a kill by this player was on-task, and award xp if so.
     *
     * @param e the Entity killed by the player
     */
    public void processEntityKill(Entity e) {
        //Bukkit.getServer().broadcastMessage(player.getName() + " just killed a " + e.getType() + ".");

        //if the kill is on task
        if(killIsOnTask(e)) {
            //on task kill
            //award 1 xp per health point that the mob had
            addXp(((Attributable) e).getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
            //Bukkit.getServer().broadcastMessage("Slayer XP for " + player.getName() + " is now " + getXp() + ".");
            if(taskRemaining > 0){
                taskRemaining--;
            }
            if(taskRemaining <= 0) {
                player.sendMessage(ChatColor.GREEN + "Congratulations! " + ChatColor.WHITE + "You've completed your Slayer task.");
                completeTask();
            }
            saveToNBT();
        }
    }

    private boolean killIsOnTask(Entity e) {
        return e.getType().equals(SkillManager.ENTITY_MAP.get(taskType));
    }

    private void newTask() {
        //TODO assign a new slayer task type and quantity based on player level, etc.
        //test values
        taskType = "Creeper";
        taskRemaining = 5;
        saveToNBT();
    }

    private void completeTask() {
        taskType = NO_TASK;
        taskRemaining = 0;
        tasksCompleted++;
        saveToNBT();
    }

    protected void readSkillSpecificFromNBT() {

        // retrieve the current Slayer task from the NBT
        if(playerData.has(slayerTaskTypeKey, PersistentDataType.STRING)) {
            taskType = playerData.get(slayerTaskTypeKey, PersistentDataType.STRING);
            player.sendMessage("Slayer task type data found for player " + player.getName() + ", set to " + taskType + '.');
        }
        else {
            player.sendMessage("No Slayer task data found for player " + player.getName() + '.');
            taskType = NO_TASK;
        }

        // retrieve the current Slayer task kills remaining from the NBT
        if(playerData.has(slayerTaskRemainingKey, PersistentDataType.INTEGER)) {
            taskRemaining = playerData.get(slayerTaskRemainingKey, PersistentDataType.INTEGER);
            player.sendMessage("Slayer task kills remaining data found for player " + player.getName() + ", set to " + taskRemaining + '.');
        }
        else {
            player.sendMessage("No Slayer task kills remaining data found for player " + player.getName() + '.');
            taskRemaining = 0;
        }

        // retrieve the Slayer tasks completed from the NBT
        if(playerData.has(slayerTasksCompletedKey, PersistentDataType.INTEGER)) {
            tasksCompleted = playerData.get(slayerTasksCompletedKey, PersistentDataType.INTEGER);
            player.sendMessage("Slayer tasks completed data found for player " + player.getName() + ", set to " + tasksCompleted + '.');
        }
        else {
            player.sendMessage("No Slayer tasks completed data found for player " + player.getName() + '.');
            tasksCompleted = 0;
        }

    }

    @Override
    public void saveToNBT() {
        super.saveToNBT();
        saveTaskTypeToNBT();
        saveTaskRemainingToNBT();
        saveTasksCompletedToNBT();
    }

    public void saveTaskTypeToNBT() {
        playerData.set(slayerTaskTypeKey, PersistentDataType.STRING, taskType);
    }

    public void saveTaskRemainingToNBT() {
        playerData.set(slayerTaskRemainingKey, PersistentDataType.INTEGER, taskRemaining);
    }

    public void saveTasksCompletedToNBT() {
        playerData.set(slayerTasksCompletedKey, PersistentDataType.INTEGER, tasksCompleted);
    }

    @Override
    protected void resetStats() {
        //debugging method
        super.resetStats();
        taskType = NO_TASK;
        taskRemaining = 0;
        tasksCompleted = 0;
    }

    @Override
    public String toString() {
        return "Slayer";
    }
}
