package me.foxwhelp.skillmanager.skills;

import me.foxwhelp.skillmanager.SkillManager;
import me.foxwhelp.skillmanager.loot.SlayerLootGenerator;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;

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

    public enum DifficultyLevel {
        ZERO(0),ONE(1),TWO(2),THREE(3),FOUR(4),FIVE(5),SIX(6),SEVEN(7);
        public final int NUM;
        private DifficultyLevel(int diff){
            NUM = diff;
        }

        private static final HashMap<Integer, DifficultyLevel> BY_NUMBER = new HashMap();

        static {
            for(DifficultyLevel dl : values()){
                BY_NUMBER.put(dl.NUM, dl);
            }
        }

        public static DifficultyLevel byInt(int difficultyLevel){
            return BY_NUMBER.get(difficultyLevel);
        }
    };

    public enum SlayerMob {
        //placeholder for storing a non-null value in the NBT
        NONE("NONE", null, DifficultyLevel.ZERO, -1, -1),

        //difficulty 1
        PIG("Pig", EntityType.PIG, DifficultyLevel.ONE, 10, 20),
        COW("Cow", EntityType.COW, DifficultyLevel.ONE, 10, 20),
        CHICKEN("Chicken", EntityType.CHICKEN, DifficultyLevel.ONE, 10, 20),
        SHEEP("Sheep", EntityType.SHEEP, DifficultyLevel.ONE, 10, 20),
        SQUID("Squid", EntityType.SQUID, DifficultyLevel.ONE, 8, 15),
        RABBIT("Rabbit", EntityType.RABBIT, DifficultyLevel.ONE, 8, 15),
        COD("Cod", EntityType.COD, DifficultyLevel.ONE, 10, 20),
        SALMON("Salmon", EntityType.SALMON, DifficultyLevel.ONE, 10, 20),
        TROPICAL_FISH("Tropical Fish", EntityType.TROPICAL_FISH, DifficultyLevel.ONE, 10, 20),
        MOOSHROOM("Mooshroom", EntityType.MUSHROOM_COW, DifficultyLevel.ONE, 10, 20),
        BAT("Bat", EntityType.BAT, DifficultyLevel.ONE, 3, 7),
        FOX("Fox", EntityType.FOX, DifficultyLevel.ONE, 8, 15),
        TURTLE("Turtle", EntityType.TURTLE, DifficultyLevel.ONE, 3, 7),

        //difficulty 2
        SPIDER("Spider", EntityType.SPIDER, DifficultyLevel.TWO, 15, 30),
        ZOMBIE("Zombie", EntityType.ZOMBIE, DifficultyLevel.TWO, 15, 30),
        HUSK("Husk", EntityType.HUSK, DifficultyLevel.TWO, 15, 30),
        SLIME("Slime", EntityType.SLIME, DifficultyLevel.TWO, 15, 30),
        BEE("Bee", EntityType.BEE, DifficultyLevel.TWO, 3, 7),

        //difficulty 3
        SKELETON("Skeleton", EntityType.SKELETON, DifficultyLevel.THREE, 20, 40),
        CREEPER("Creeper", EntityType.CREEPER, DifficultyLevel.THREE, 20, 40),
        SILVERFISH("Silverfish", EntityType.SILVERFISH, DifficultyLevel.THREE, 20, 40),
        CAVE_SPIDER("Cave Spider", EntityType.CAVE_SPIDER, DifficultyLevel.THREE, 20, 40),
        PIGLIN("Piglin", EntityType.PIGLIN, DifficultyLevel.THREE, 20, 40),
        DROWNED("Drowned", EntityType.DROWNED, DifficultyLevel.THREE, 20, 40),
        ZOMBIFIED_PIGLIN("Zombified Piglin", EntityType.ZOMBIFIED_PIGLIN, DifficultyLevel.THREE, 20, 40),
        MAGMA_CUBE("Magma Cube", EntityType.MAGMA_CUBE, DifficultyLevel.THREE, 20, 40),
        STRAY("Stray", EntityType.STRAY, DifficultyLevel.THREE, 20, 40),
        PILLAGER("Pillager", EntityType.PILLAGER, DifficultyLevel.THREE, 20, 40),
        GHAST("Ghast", EntityType.GHAST, DifficultyLevel.THREE, 7, 15),
        POLAR_BEAR("Polar Bear", EntityType.POLAR_BEAR, DifficultyLevel.THREE, 7, 15),

        //difficulty 4
        ENDERMAN("Enderman", EntityType.ENDERMAN, DifficultyLevel.FOUR, 20, 40),
        BLAZE("Blaze", EntityType.BLAZE, DifficultyLevel.FOUR, 20, 40),
        GUARDIAN("Guardian", EntityType.GUARDIAN, DifficultyLevel.FOUR, 20, 40),
        HOGLIN("Hoglin", EntityType.HOGLIN, DifficultyLevel.FOUR, 20, 40),
        WITHER_SKELETON("Wither Skeleton", EntityType.WITHER_SKELETON, DifficultyLevel.FOUR, 20, 30),
        SHULKER("Shulker", EntityType.SHULKER, DifficultyLevel.FOUR, 20, 40),
        VINDICATOR("Vindicator", EntityType.VINDICATOR, DifficultyLevel.FOUR, 7, 15),
        WITCH("Witch", EntityType.WITCH, DifficultyLevel.FOUR, 5, 10),

        //difficulty 5
        PIGLIN_BRUTE("Piglin Brute", EntityType.PIGLIN_BRUTE, DifficultyLevel.FIVE, 10, 20),
        EVOKER("Evoker", EntityType.EVOKER, DifficultyLevel.FIVE, 5, 10),
        RAVAGER("Ravager", EntityType.RAVAGER, DifficultyLevel.FIVE, 5, 10),

        //difficulty 6
        ELDER_GUARDIAN("Elder Guardian", EntityType.ELDER_GUARDIAN, DifficultyLevel.SIX, 3, 6),

        //difficulty 7
        ENDER_DRAGON("Ender Dragon", EntityType.ENDER_DRAGON, DifficultyLevel.SEVEN, 1, 4),
        WITHER("Wither", EntityType.WITHER, DifficultyLevel.SEVEN, 1, 4);

        public final String NAME;
        public final EntityType ENTITY_TYPE;
        public final DifficultyLevel DIFFICULTY;
        public final int TASK_MINIMUM;
        public final int TASK_MAXIMUM;

        private SlayerMob(String mn, EntityType mt, DifficultyLevel difficulty, int baseTaskMin, int baseTaskMax) {
            this.NAME = mn;
            this.ENTITY_TYPE = mt;
            this.DIFFICULTY = difficulty;
            this.TASK_MINIMUM = baseTaskMin;
            this.TASK_MAXIMUM = baseTaskMax;
        }

        //map of mobs by name
        private static final HashMap<String, SlayerMob> BY_NAME = new HashMap();

        static {
            for (SlayerMob sm : values()) {
                BY_NAME.put(sm.NAME, sm);
            }
        }

        /**
         * Returns a random mob within the specified difficulty.
         */
        public static SlayerMob mobDifficultyBetween(DifficultyLevel minDifficulty, DifficultyLevel maxDifficulty) {
            ArrayList<SlayerMob> mobs = new ArrayList<>();
            for(SlayerMob tm : values()) {
                if(tm.DIFFICULTY.NUM >= minDifficulty.NUM && tm.DIFFICULTY.NUM <= maxDifficulty.NUM) {
                    mobs.add(tm);
                }
            }
            SecureRandom rnd = new SecureRandom();
            return mobs.get(rnd.nextInt(mobs.size()));
        }

        /**
         * Generates a task length appropriate for the given mob
         * @param sm the Slayer task mob
         * @return a suitable amount of mobs to be killed for a task
         */
        public static int randomTaskNumber(SlayerMob sm) {
            SecureRandom rnd = new SecureRandom();
            return (rnd.nextInt(((sm.TASK_MAXIMUM + 1) - sm.TASK_MINIMUM)) + sm.TASK_MINIMUM);
        }

        public static SlayerMob getSlayerMob(String name) {
            return BY_NAME.get(name);
        }

        @Override
        public String toString() {
            return NAME;
        }
    }

    public Slayer(Player p) {
        super(p);
        slayerTaskTypeKey = new NamespacedKey(JavaPlugin.getPlugin(SkillManager.class), this.skillIdentifier() + TASK_TYPE_TAG_SUFFIX);
        slayerTaskRemainingKey = new NamespacedKey(JavaPlugin.getPlugin(SkillManager.class), this.skillIdentifier() + TASK_REMAINING_TAG_SUFFIX);
        slayerTasksCompletedKey = new NamespacedKey(JavaPlugin.getPlugin(SkillManager.class), this.skillIdentifier() + TASKS_COMPLETED_TAG_SUFFIX);
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
                        player.sendMessage("Your new Slayer task is to kill " + ChatColor.RED + taskRemaining + ' ' + taskMob + ".");
                    } else {
                        //player has a task, give details
                        player.sendMessage("Your current Slayer task is to kill " + ChatColor.RED + taskRemaining + ' ' + taskMob + ".");
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
            addXp(((Attributable) e).getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() * taskMob.DIFFICULTY.NUM);
            //player.sendMessage("Your Slayer XP is now " + getXp() + ".");
            if (taskRemaining > 0) {
                taskRemaining--;
            }
            if(taskRemaining % 5 == 0 && taskRemaining != 0) {
                player.sendMessage("You need to kill " + ChatColor.RED + taskRemaining + ' ' + taskMob.NAME + ChatColor.WHITE + " in order to complete your " + this.toString() + " task.");
            }
            if (taskRemaining <= 0) {
                completeTask();
            }
            saveToNBT();
        }
    }

    private boolean killIsOnTask(Entity e) {
        return e.getType().equals(taskMob.ENTITY_TYPE);
    }

    private void newTask() {
        //TODO assign a new slayer task type and quantity based on player level, etc.
        //test values
        taskMob = SlayerMob.COW;
        taskRemaining = 1;

        int playerLevel = getLevel();

        //if under level 10 Slayer, 1 difficulty mob tasks only
        if (playerLevel < 10) {
            taskMob = SlayerMob.mobDifficultyBetween(DifficultyLevel.ONE,DifficultyLevel.ONE);
        }

        //level 10-19, 2 to 2 difficulty tasks
        else if(playerLevel >= 10 && playerLevel < 20){
            taskMob = SlayerMob.mobDifficultyBetween(DifficultyLevel.TWO,DifficultyLevel.TWO);
        }

        //level 20-29, 3 to 3 difficulty tasks
        else if(playerLevel >= 20 && playerLevel < 30){
            taskMob = SlayerMob.mobDifficultyBetween(DifficultyLevel.THREE,DifficultyLevel.THREE);
        }

        //level 30-44, 3 to 4 difficulty tasks
        else if(playerLevel >= 30 && playerLevel < 45){
            taskMob = SlayerMob.mobDifficultyBetween(DifficultyLevel.THREE,DifficultyLevel.FOUR);
        }

        //level 45-59, 4 to 5 difficulty tasks
        else if(playerLevel >= 45 && playerLevel < 60){
            taskMob = SlayerMob.mobDifficultyBetween(DifficultyLevel.FOUR,DifficultyLevel.FIVE);
        }

        //level 60-74, 4 to 6 difficulty tasks
        else if(playerLevel >= 60 && playerLevel < 75){
            taskMob = SlayerMob.mobDifficultyBetween(DifficultyLevel.FOUR,DifficultyLevel.SIX);
        }

        //level 75-90, 4 to 7 difficulty tasks
        else if(playerLevel >= 75){
            taskMob = SlayerMob.mobDifficultyBetween(DifficultyLevel.FOUR,DifficultyLevel.SEVEN);
        }

        taskRemaining = SlayerMob.randomTaskNumber(taskMob);

        saveToNBT();
    }

    private void completeTask() {
        player.sendMessage(ChatColor.GREEN + "Congratulations! " + ChatColor.WHITE + "You've completed your " + this.toString() + " task.");

        //if inventory is full
        if(player.getInventory().firstEmpty() == -1) {
            //notify the player
            player.sendMessage("Your inventory was full! You should've received a level " + taskMob.DIFFICULTY.NUM + " reward. Please contact the admin.");
        }
        else {
            //generate a loot item from the table
            ItemStack lootItem = SlayerLootGenerator.generateLoot(taskMob.DIFFICULTY);
            player.sendMessage("Your reward is " + ChatColor.LIGHT_PURPLE + lootItem.getAmount() + " " + lootItem.getType().toString().replace('_', ' ').toLowerCase() + ".");
            //give it to the player
            player.getInventory().addItem(lootItem);
        }

        taskMob = SlayerMob.NONE;
        taskRemaining = 0;
        tasksCompleted++;
        saveToNBT();
    }

    public SlayerMob getTask(){
        return taskMob;
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
        playerData.set(slayerTaskTypeKey, PersistentDataType.STRING, taskMob.NAME);
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
        saveToNBT();
    }

    @Override
    public String skillIdentifier(){
        return "Slayer";
    }

    @Override
    public String toString() {
        return "Slayer";
    }
}
