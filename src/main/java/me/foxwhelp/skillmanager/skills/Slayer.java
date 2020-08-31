package me.foxwhelp.skillmanager.skills;

import me.foxwhelp.skillmanager.SkillManager;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class Slayer extends GenericSkill {

    SlayerMob taskMob;
    int taskRemaining;
    int tasksCompleted;
    private final static String TASK_TYPE_TAG_SUFFIX = "TaskType";
    NamespacedKey slayerTaskTypeKey;
    private final static String TASK_REMAINING_TAG_SUFFIX = "TaskRemaining";
    NamespacedKey slayerTaskRemainingKey;
    private final static String TASKS_COMPLETED_TAG_SUFFIX = "TasksCompleted";
    NamespacedKey slayerTasksCompletedKey;

    public enum SlayerMob {
        //difficulty 1
        NONE("NONE", null, -1, -1, -1),
        PIG("Pig", EntityType.PIG, 1, 10, 20),
        COW("Cow", EntityType.COW, 1, 10, 20),
        CHICKEN("Chicken", EntityType.CHICKEN, 1, 10, 20),
        SHEEP("Sheep", EntityType.SHEEP, 1, 10, 20),
        SQUID("Squid", EntityType.SQUID, 1, 8, 15),
        RABBIT("Rabbit", EntityType.RABBIT, 1, 8, 15),
        COD("Cod", EntityType.COD, 1, 10, 20),
        SALMON("Salmon", EntityType.SALMON, 1, 10, 20),
        TROPICAL_FISH("Tropical Fish", EntityType.TROPICAL_FISH, 1, 10, 20),
        MOOSHROOM("Mooshroom", EntityType.MUSHROOM_COW, 1, 10, 20),
        BAT("Bat", EntityType.BAT, 1, 3, 7),
        FOX("Fox", EntityType.FOX, 1, 8, 15),
        TURTLE("Turtle", EntityType.TURTLE, 1, 3, 7),

        //difficulty 2
        SPIDER("Spider", EntityType.SPIDER, 2, 15, 30),
        ZOMBIE("Zombie", EntityType.ZOMBIE, 2, 15, 30),
        HUSK("Husk", EntityType.HUSK, 2, 15, 30),
        SLIME("Slime", EntityType.SLIME, 2, 15, 30),
        BEE("Bee", EntityType.BEE, 2, 3, 7),

        //difficulty 3
        SKELETON("Skeleton", EntityType.SKELETON, 3, 20, 40),
        CREEPER("Creeper", EntityType.CREEPER, 3, 20, 40),
        SILVERFISH("Silverfish", EntityType.SILVERFISH, 3, 20, 40),
        CAVE_SPIDER("Cave Spider", EntityType.CAVE_SPIDER, 3, 20, 40),
        PIGLIN("Piglin", EntityType.PIGLIN, 3, 20, 40),
        DROWNED("Drowned", EntityType.DROWNED, 3, 20, 40),
        ZOMBIFIED_PIGLIN("Zombified Piglin", EntityType.ZOMBIFIED_PIGLIN, 3, 20, 40),
        MAGMA_CUBE("Magma Cube", EntityType.MAGMA_CUBE, 3, 20, 40),
        STRAY("Stray", EntityType.MAGMA_CUBE, 3, 20, 40),
        PILLAGER("Pillager", EntityType.PILLAGER, 3, 20, 40),
        GHAST("Ghast", EntityType.GHAST, 3, 7, 15),
        POLAR_BEAR("Polar Bear", EntityType.POLAR_BEAR, 3, 7, 15),

        //difficulty 4
        ENDERMAN("Enderman", EntityType.ENDERMAN, 4, 20, 40),
        BLAZE("Blaze", EntityType.BLAZE, 4, 20, 40),
        GUARDIAN("Guardian", EntityType.GUARDIAN, 4, 20, 40),
        HOGLIN("Hoglin", EntityType.HOGLIN, 4, 20, 40),
        WITHER_SKELETON("Wither Skeleton", EntityType.WITHER_SKELETON, 4, 20, 30),
        SHULKER("Shulker", EntityType.SHULKER, 4, 20, 40),
        VINDICATOR("Vindicator", EntityType.VINDICATOR, 4, 7, 15),
        WITCH("Witch", EntityType.WITCH, 4, 5, 10),

        //difficulty 5
        PIGLIN_BRUTE("Piglin Brute", EntityType.PIGLIN_BRUTE, 5, 10, 20),
        EVOKER("Evoker", EntityType.EVOKER, 5, 5, 10),
        RAVAGER("Ravager", EntityType.RAVAGER, 5, 5, 10),

        //difficulty 6
        ELDER_GUARDIAN("Elder Guardian", EntityType.ELDER_GUARDIAN, 6, 3, 6),

        //difficulty 7
        ENDER_DRAGON("Ender Dragon", EntityType.ENDER_DRAGON, 7, 1, 4),
        WITHER("Wither", EntityType.WITHER, 7, 1, 4);


        public final String mobName;
        public final EntityType mobType;
        public final int mobDifficulty;
        public final int taskMinimum;
        public final int taskMaximum;

        private SlayerMob(String mn, EntityType mt, int difficulty, int baseTaskMin, int baseTaskMax) {
            this.mobName = mn;
            this.mobType = mt;
            this.mobDifficulty = difficulty;
            this.taskMinimum = baseTaskMin;
            this.taskMaximum = baseTaskMax;
        }

        //map of mobs by name
        private static final HashMap<String, SlayerMob> BY_NAME = new HashMap();

        static {
            for (SlayerMob sm : values()) {
                BY_NAME.put(sm.mobName, sm);
            }
        }

        /**
         * Returns a random mob within the specified difficulty.
         */
        public static SlayerMob mobDifficultyBetween(int minDifficulty, int maxDifficulty) {
            ArrayList<SlayerMob> mobs = new ArrayList<>();
            for(SlayerMob tm : values()) {
                if(tm.mobDifficulty >= minDifficulty && tm.mobDifficulty <= maxDifficulty) {
                    mobs.add(tm);
                }
            }
            Random rnd = new Random();
            return mobs.get(rnd.nextInt(mobs.size()));
        }

        /**
         * Generates a task length appropriate for the given mob
         * @param sm the Slayer task mob
         * @return a suitable amount of mobs to be killed for a task
         */
        public static int randomTaskNumber(SlayerMob sm) {
            Random rnd = new Random();
            return (rnd.nextInt(((sm.taskMaximum + 1) - sm.taskMinimum)) + sm.taskMinimum);
        }

        public static SlayerMob getSlayerMob(String name) {
            return BY_NAME.get(name);
        }

        @Override
        public String toString() {
            return mobName;
        }
    }

    public Slayer(Player p) {
        super(p);
        slayerTaskTypeKey = new NamespacedKey(JavaPlugin.getPlugin(SkillManager.class), this.toString() + TASK_TYPE_TAG_SUFFIX);
        slayerTaskRemainingKey = new NamespacedKey(JavaPlugin.getPlugin(SkillManager.class), this.toString() + TASK_REMAINING_TAG_SUFFIX);
        slayerTasksCompletedKey = new NamespacedKey(JavaPlugin.getPlugin(SkillManager.class), this.toString() + TASKS_COMPLETED_TAG_SUFFIX);
        readSkillSpecificFromNBT();
    }

    public boolean handleCommand(String label, String[] args) {
        //command is predetermined to be sent to the owner of this skill (player) and to be a slayer command
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("task")) {
                //"slayer task..."
                if (args.length == 1) {
                    //"slayer task"
                    if (taskMob.equals(SlayerMob.NONE)) {
                        //player has no task, assign one and inform them
                        newTask();
                        player.sendMessage("Your new Slayer task is to kill " + ChatColor.RED + taskRemaining + ' ' + taskMob + "s.");
                    } else {
                        //player has a task, give details
                        player.sendMessage("Your current Slayer task is to kill " + ChatColor.RED + taskRemaining + ' ' + taskMob + "s.");
                    }

                }
            }
            if (args[0].equalsIgnoreCase("stats")) {
                //"slayer stats..."
                player.sendMessage("Your " + ChatColor.AQUA + "Slayer" + ChatColor.WHITE + " level is " + ChatColor.GREEN + getLevel() +
                        ChatColor.WHITE + ". You have " + ChatColor.GREEN + (int) getXp() + ChatColor.WHITE + " Slayer XP." +
                        " You have completed " + ChatColor.GREEN + tasksCompleted + ChatColor.WHITE + " Slayer tasks.");
            }
            if (args[0].equalsIgnoreCase("reset")) {
                //"slayer reset..."
                resetStats();
                player.sendMessage("Slayer stats reset.");
            }
            return true;
        }
        return false;
    }

    /**
     * Determine whether a kill by this player was on-task, and award xp if so.
     *
     * @param e the Entity killed by the player
     */
    public void processEntityKill(Entity e) {
        //Bukkit.getServer().broadcastMessage(player.getName() + " just killed a " + e.getType() + ".");

        //if the kill is on task
        if (killIsOnTask(e)) {
            //on task kill
            //award (health point that the mob had * difficulty of the mob)
            addXp(((Attributable) e).getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() * taskMob.mobDifficulty);
            //player.sendMessage("Your Slayer XP is now " + getXp() + ".");
            if (taskRemaining > 0) {
                taskRemaining--;
            }
            if (taskRemaining <= 0) {
                player.sendMessage(ChatColor.GREEN + "Congratulations! " + ChatColor.WHITE + "You've completed your Slayer task.");
                completeTask();
            }
            saveToNBT();
        }
    }

    private boolean killIsOnTask(Entity e) {
        return e.getType().equals(taskMob.mobType);
    }

    private void newTask() {
        //TODO assign a new slayer task type and quantity based on player level, etc.
        //test values
        taskMob = SlayerMob.COW;
        taskRemaining = 1;

        int playerLevel = getLevel();

        //if under level 10 Slayer, 1 difficulty mob tasks only
        if (playerLevel < 10) {
            taskMob = SlayerMob.mobDifficultyBetween(1,1);
        }

        //level 10-19, 2 to 2 difficulty tasks
        if(playerLevel >= 10 && playerLevel < 20){
            taskMob = SlayerMob.mobDifficultyBetween(2,2);
        }

        //level 20-29, 3 to 3 difficulty tasks
        if(playerLevel >= 20 && playerLevel < 30){
            taskMob = SlayerMob.mobDifficultyBetween(3,3);
        }

        //level 30-44, 3 to 4 difficulty tasks
        if(playerLevel >= 30 && playerLevel < 45){
            taskMob = SlayerMob.mobDifficultyBetween(3,4);
        }

        //level 45-59, 4 to 5 difficulty tasks
        if(playerLevel >= 45 && playerLevel < 60){
            taskMob = SlayerMob.mobDifficultyBetween(4,5);
        }

        //level 60-74, 4 to 6 difficulty tasks
        if(playerLevel >= 60 && playerLevel < 75){
            taskMob = SlayerMob.mobDifficultyBetween(4,6);
        }

        //level 75-90, 4 to 7 difficulty tasks
        if(playerLevel >= 75){
            taskMob = SlayerMob.mobDifficultyBetween(4,7);
        }

        taskRemaining = SlayerMob.randomTaskNumber(taskMob);

        saveToNBT();
    }

    private void completeTask() {
        taskMob = SlayerMob.NONE;
        taskRemaining = 0;
        tasksCompleted++;
        saveToNBT();
    }

    protected void readSkillSpecificFromNBT() {

        // retrieve the current Slayer task from the NBT
        if (playerData.has(slayerTaskTypeKey, PersistentDataType.STRING)) {
            taskMob = SlayerMob.getSlayerMob(playerData.get(slayerTaskTypeKey, PersistentDataType.STRING));
            player.sendMessage("Slayer task type data found for player " + player.getName() + ", set to " + taskMob + '.');
        } else {
            player.sendMessage("No Slayer task data found for player " + player.getName() + '.');
            taskMob = SlayerMob.NONE;
        }

        // retrieve the current Slayer task kills remaining from the NBT
        if (playerData.has(slayerTaskRemainingKey, PersistentDataType.INTEGER)) {
            taskRemaining = playerData.get(slayerTaskRemainingKey, PersistentDataType.INTEGER);
            player.sendMessage("Slayer task kills remaining data found for player " + player.getName() + ", set to " + taskRemaining + '.');
        } else {
            player.sendMessage("No Slayer task kills remaining data found for player " + player.getName() + '.');
            taskRemaining = 0;
        }

        // retrieve the Slayer tasks completed from the NBT
        if (playerData.has(slayerTasksCompletedKey, PersistentDataType.INTEGER)) {
            tasksCompleted = playerData.get(slayerTasksCompletedKey, PersistentDataType.INTEGER);
            player.sendMessage("Slayer tasks completed data found for player " + player.getName() + ", set to " + tasksCompleted + '.');
        } else {
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
        playerData.set(slayerTaskTypeKey, PersistentDataType.STRING, taskMob.mobName);
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
        taskMob = SlayerMob.NONE;
        taskRemaining = 0;
        tasksCompleted = 0;
    }

    @Override
    public String toString() {
        return "Slayer";
    }
}
