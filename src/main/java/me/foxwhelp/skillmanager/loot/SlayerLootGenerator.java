package me.foxwhelp.skillmanager.loot;

import me.foxwhelp.skillmanager.skills.Slayer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;

public class SlayerLootGenerator {

    private static HashMap<Slayer.DifficultyLevel, ArrayList<LootTableEntry>> lootTable = new HashMap<>();

    static {
        for(Slayer.DifficultyLevel dl : Slayer.DifficultyLevel.values()) {
            lootTable.put(dl, new ArrayList<>());
        }

        //all loot table item initializations
        //level 1
        addItemToLootTable(Slayer.DifficultyLevel.ONE, new LootTableEntry(Material.FISHING_ROD,                 1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.ONE, new LootTableEntry(Material.LEAD,                        1,      1),     5);
        addItemToLootTable(Slayer.DifficultyLevel.ONE, new LootTableEntry(Material.SADDLE,                      1,      1),     5);
        addItemToLootTable(Slayer.DifficultyLevel.ONE, new LootTableEntry(Material.POTATO,                      15,     30),    15);
        addItemToLootTable(Slayer.DifficultyLevel.ONE, new LootTableEntry(Material.CARROT,                      15,     30),    15);
        addItemToLootTable(Slayer.DifficultyLevel.ONE, new LootTableEntry(Material.APPLE,                       5,      10),    15);
        addItemToLootTable(Slayer.DifficultyLevel.ONE, new LootTableEntry(Material.ARROW,                       20,     30),    5);
        addItemToLootTable(Slayer.DifficultyLevel.ONE, new LootTableEntry(Material.BEETROOT,                    15,     30),    15);
        addItemToLootTable(Slayer.DifficultyLevel.ONE, new LootTableEntry(Material.HAY_BLOCK,                   10,     15),    15);
        addItemToLootTable(Slayer.DifficultyLevel.ONE, new LootTableEntry(Material.LEATHER,                     5,      15),    5);
        addItemToLootTable(Slayer.DifficultyLevel.ONE, new LootTableEntry(Material.CHAINMAIL_HELMET,            1,      1),     5);
        addItemToLootTable(Slayer.DifficultyLevel.ONE, new LootTableEntry(Material.CHAINMAIL_CHESTPLATE,        1,      1),     5);
        addItemToLootTable(Slayer.DifficultyLevel.ONE, new LootTableEntry(Material.CHAINMAIL_LEGGINGS,          1,      1),     5);
        addItemToLootTable(Slayer.DifficultyLevel.ONE, new LootTableEntry(Material.CHAINMAIL_BOOTS,             1,      1),     5);
        addItemToLootTable(Slayer.DifficultyLevel.ONE, new LootTableEntry(Material.IRON_PICKAXE,                1,      1),     5);
        addItemToLootTable(Slayer.DifficultyLevel.ONE, new LootTableEntry(Material.IRON_AXE,                    1,      1),     5);
        addItemToLootTable(Slayer.DifficultyLevel.ONE, new LootTableEntry(Material.IRON_SHOVEL,                 1,      1),     5);
        addItemToLootTable(Slayer.DifficultyLevel.ONE, new LootTableEntry(Material.IRON_SWORD,                  1,      1),     5);
        addItemToLootTable(Slayer.DifficultyLevel.ONE, new LootTableEntry(Material.COOKED_RABBIT,               8,      15),    12);
        addItemToLootTable(Slayer.DifficultyLevel.ONE, new LootTableEntry(Material.COAL,                        20,     40),    10);
        addItemToLootTable(Slayer.DifficultyLevel.ONE, new LootTableEntry(Material.NAME_TAG,                    1,      3),     5);
        addItemToLootTable(Slayer.DifficultyLevel.ONE, new LootTableEntry(Material.SHIELD,                      1,      1),     5);
        addItemToLootTable(Slayer.DifficultyLevel.ONE, new LootTableEntry(Material.EMERALD,                     3,      9),     1);
        addItemToLootTable(Slayer.DifficultyLevel.ONE, new LootTableEntry(Material.NAUTILUS_SHELL,              3,      7),     1);
        addItemToLootTable(Slayer.DifficultyLevel.ONE, new LootTableEntry(Material.SQUID_SPAWN_EGG,             1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.ONE, new LootTableEntry(Material.RABBIT_SPAWN_EGG,            1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.ONE, new LootTableEntry(Material.BAT_SPAWN_EGG,               1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.ONE, new LootTableEntry(Material.FOX_SPAWN_EGG,               1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.ONE, new LootTableEntry(Material.TURTLE_SPAWN_EGG,            1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.ONE, new LootTableEntry(Material.MOOSHROOM_SPAWN_EGG,         1,      1),     1);

        //level 2
        addItemToLootTable(Slayer.DifficultyLevel.TWO, new LootTableEntry(Material.EXPERIENCE_BOTTLE,           5,      10),    3);
        addItemToLootTable(Slayer.DifficultyLevel.TWO, new LootTableEntry(Material.BOW,                         1,      1),     5);
        addItemToLootTable(Slayer.DifficultyLevel.TWO, new LootTableEntry(Material.SHIELD,                      1,      1),     5);
        addItemToLootTable(Slayer.DifficultyLevel.TWO, new LootTableEntry(Material.IRON_HELMET,                 1,      1),     5);
        addItemToLootTable(Slayer.DifficultyLevel.TWO, new LootTableEntry(Material.IRON_CHESTPLATE,             1,      1),     5);
        addItemToLootTable(Slayer.DifficultyLevel.TWO, new LootTableEntry(Material.IRON_LEGGINGS,               1,      1),     5);
        addItemToLootTable(Slayer.DifficultyLevel.TWO, new LootTableEntry(Material.IRON_BOOTS,                  1,      1),     5);
        addItemToLootTable(Slayer.DifficultyLevel.TWO, new LootTableEntry(Material.IRON_PICKAXE,                1,      1),     5);
        addItemToLootTable(Slayer.DifficultyLevel.TWO, new LootTableEntry(Material.IRON_AXE,                    1,      1),     5);
        addItemToLootTable(Slayer.DifficultyLevel.TWO, new LootTableEntry(Material.IRON_SHOVEL,                 1,      1),     5);
        addItemToLootTable(Slayer.DifficultyLevel.TWO, new LootTableEntry(Material.IRON_SWORD,                  1,      1),     5);
        addItemToLootTable(Slayer.DifficultyLevel.TWO, new LootTableEntry(Material.COOKED_CHICKEN,              10,     20),    7);
        addItemToLootTable(Slayer.DifficultyLevel.TWO, new LootTableEntry(Material.COOKED_SALMON,               10,     20),    7);
        addItemToLootTable(Slayer.DifficultyLevel.TWO, new LootTableEntry(Material.IRON_INGOT,                  5,      15),    5);
        addItemToLootTable(Slayer.DifficultyLevel.TWO, new LootTableEntry(Material.OBSIDIAN,                    2,      10),    5);
        addItemToLootTable(Slayer.DifficultyLevel.TWO, new LootTableEntry(Material.LAPIS_LAZULI,                5,      20),    5);
        addItemToLootTable(Slayer.DifficultyLevel.TWO, new LootTableEntry(Material.SADDLE,                      1,      1),     5);
        addItemToLootTable(Slayer.DifficultyLevel.TWO, new LootTableEntry(Material.GOLD_INGOT,                  5,      15),    5);
        addItemToLootTable(Slayer.DifficultyLevel.TWO, new LootTableEntry(Material.BAKED_POTATO,                8,      15),    7);
        addItemToLootTable(Slayer.DifficultyLevel.TWO, new LootTableEntry(Material.APPLE,                       5,      15),    7);
        addItemToLootTable(Slayer.DifficultyLevel.TWO, new LootTableEntry(Material.EMERALD,                     7,      15),    3);
        addItemToLootTable(Slayer.DifficultyLevel.TWO, new LootTableEntry(Material.NAUTILUS_SHELL,              5,      10),    3);
        addItemToLootTable(Slayer.DifficultyLevel.TWO, new LootTableEntry(Material.SPIDER_SPAWN_EGG,            1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.TWO, new LootTableEntry(Material.ZOMBIE_SPAWN_EGG,            1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.TWO, new LootTableEntry(Material.HUSK_SPAWN_EGG,              1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.TWO, new LootTableEntry(Material.BEE_SPAWN_EGG,               1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.TWO, new LootTableEntry(Material.SLIME_SPAWN_EGG,             1,      1),     1);

        //level 3
        addItemToLootTable(Slayer.DifficultyLevel.THREE, new LootTableEntry(Material.IRON_HELMET,               1,      1),     3);
        addItemToLootTable(Slayer.DifficultyLevel.THREE, new LootTableEntry(Material.IRON_CHESTPLATE,           1,      1),     3);
        addItemToLootTable(Slayer.DifficultyLevel.THREE, new LootTableEntry(Material.IRON_LEGGINGS,             1,      1),     3);
        addItemToLootTable(Slayer.DifficultyLevel.THREE, new LootTableEntry(Material.IRON_BOOTS,                1,      1),     3);
        addItemToLootTable(Slayer.DifficultyLevel.THREE, new LootTableEntry(Material.IRON_PICKAXE,              1,      1),     3);
        addItemToLootTable(Slayer.DifficultyLevel.THREE, new LootTableEntry(Material.IRON_AXE,                  1,      1),     3);
        addItemToLootTable(Slayer.DifficultyLevel.THREE, new LootTableEntry(Material.IRON_SHOVEL,               1,      1),     3);
        addItemToLootTable(Slayer.DifficultyLevel.THREE, new LootTableEntry(Material.IRON_SWORD,                1,      1),     3);
        addItemToLootTable(Slayer.DifficultyLevel.THREE, new LootTableEntry(Material.IRON_INGOT,                10,     20),    7);
        addItemToLootTable(Slayer.DifficultyLevel.THREE, new LootTableEntry(Material.COOKED_BEEF,               10,     20),    7);
        addItemToLootTable(Slayer.DifficultyLevel.THREE, new LootTableEntry(Material.COOKED_PORKCHOP,           10,     20),    7);
        addItemToLootTable(Slayer.DifficultyLevel.THREE, new LootTableEntry(Material.SHIELD,                    1,      1),     3);
        addItemToLootTable(Slayer.DifficultyLevel.THREE, new LootTableEntry(Material.BOW,                       1,      1),     3);
        addItemToLootTable(Slayer.DifficultyLevel.THREE, new LootTableEntry(Material.SHULKER_SHELL,             2,      2),     1);
        addItemToLootTable(Slayer.DifficultyLevel.THREE, new LootTableEntry(Material.EXPERIENCE_BOTTLE,         15,     30),    3);
        addItemToLootTable(Slayer.DifficultyLevel.THREE, new LootTableEntry(Material.GUNPOWDER,                 10,     20),    3);
        addItemToLootTable(Slayer.DifficultyLevel.THREE, new LootTableEntry(Material.SKELETON_SPAWN_EGG,        1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.THREE, new LootTableEntry(Material.CREEPER_SPAWN_EGG,         1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.THREE, new LootTableEntry(Material.PIGLIN_SPAWN_EGG,          1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.THREE, new LootTableEntry(Material.DROWNED_SPAWN_EGG,         1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.THREE, new LootTableEntry(Material.ZOMBIFIED_PIGLIN_SPAWN_EGG,1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.THREE, new LootTableEntry(Material.MAGMA_CUBE_SPAWN_EGG,      1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.THREE, new LootTableEntry(Material.SILVERFISH_SPAWN_EGG,      1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.THREE, new LootTableEntry(Material.CAVE_SPIDER_SPAWN_EGG,     1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.THREE, new LootTableEntry(Material.STRAY_SPAWN_EGG,           1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.THREE, new LootTableEntry(Material.PILLAGER_SPAWN_EGG,        1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.THREE, new LootTableEntry(Material.GHAST_SPAWN_EGG,           1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.THREE, new LootTableEntry(Material.POLAR_BEAR_SPAWN_EGG,      1,      1),     1);

        //level 4
        addItemToLootTable(Slayer.DifficultyLevel.FOUR, new LootTableEntry(Material.DIAMOND_HELMET,             1,      1),     7);
        addItemToLootTable(Slayer.DifficultyLevel.FOUR, new LootTableEntry(Material.DIAMOND_CHESTPLATE,         1,      1),     7);
        addItemToLootTable(Slayer.DifficultyLevel.FOUR, new LootTableEntry(Material.DIAMOND_LEGGINGS,           1,      1),     7);
        addItemToLootTable(Slayer.DifficultyLevel.FOUR, new LootTableEntry(Material.DIAMOND_BOOTS,              1,      1),     7);
        addItemToLootTable(Slayer.DifficultyLevel.FOUR, new LootTableEntry(Material.DIAMOND_PICKAXE,            1,      1),     7);
        addItemToLootTable(Slayer.DifficultyLevel.FOUR, new LootTableEntry(Material.DIAMOND_AXE,                1,      1),     7);
        addItemToLootTable(Slayer.DifficultyLevel.FOUR, new LootTableEntry(Material.DIAMOND_SHOVEL,             1,      1),     7);
        addItemToLootTable(Slayer.DifficultyLevel.FOUR, new LootTableEntry(Material.DIAMOND_SWORD,              1,      1),     7);
        addItemToLootTable(Slayer.DifficultyLevel.FOUR, new LootTableEntry(Material.GOLDEN_APPLE,               2,      4),     5);
        addItemToLootTable(Slayer.DifficultyLevel.FOUR, new LootTableEntry(Material.ENCHANTED_GOLDEN_APPLE,     1,      2),     3);
        addItemToLootTable(Slayer.DifficultyLevel.FOUR, new LootTableEntry(Material.TOTEM_OF_UNDYING,           1,      1),     3);
        addItemToLootTable(Slayer.DifficultyLevel.FOUR, new LootTableEntry(Material.SHULKER_SHELL,              2,      6),     3);
        addItemToLootTable(Slayer.DifficultyLevel.FOUR, new LootTableEntry(Material.DIAMOND,                    5,      12),    3);
        addItemToLootTable(Slayer.DifficultyLevel.FOUR, new LootTableEntry(Material.WITHER_SKELETON_SKULL,      1,      3),     3);
        addItemToLootTable(Slayer.DifficultyLevel.FOUR, new LootTableEntry(Material.ELYTRA,                     1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.FOUR, new LootTableEntry(Material.NETHERITE_SCRAP,            1,      2),     1);
        addItemToLootTable(Slayer.DifficultyLevel.FOUR, new LootTableEntry(Material.COOKED_BEEF,                40,     64),    5);
        addItemToLootTable(Slayer.DifficultyLevel.FOUR, new LootTableEntry(Material.COOKED_PORKCHOP,            40,     64),    5);
        addItemToLootTable(Slayer.DifficultyLevel.FOUR, new LootTableEntry(Material.ENDER_PEARL,                12,     16),    5);
        addItemToLootTable(Slayer.DifficultyLevel.FOUR, new LootTableEntry(Material.BLAZE_ROD,                  4,      8),     5);
        addItemToLootTable(Slayer.DifficultyLevel.FOUR, new LootTableEntry(Material.GHAST_TEAR,                 6,      12),    5);
        addItemToLootTable(Slayer.DifficultyLevel.FOUR, new LootTableEntry(Material.IRON_INGOT,                 40,     64),    9);
        addItemToLootTable(Slayer.DifficultyLevel.FOUR, new LootTableEntry(Material.HEART_OF_THE_SEA,           1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.FOUR, new LootTableEntry(Material.EXPERIENCE_BOTTLE,          40,     64),    1);
        addItemToLootTable(Slayer.DifficultyLevel.FOUR, new LootTableEntry(Material.FIREWORK_ROCKET,            16,     32),    3);
        addItemToLootTable(Slayer.DifficultyLevel.FOUR, new LootTableEntry(Material.PHANTOM_MEMBRANE,           10,     20),    3);
        addItemToLootTable(Slayer.DifficultyLevel.FOUR, new LootTableEntry(Material.ENDERMAN_SPAWN_EGG,         1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.FOUR, new LootTableEntry(Material.HOGLIN_SPAWN_EGG,           1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.FOUR, new LootTableEntry(Material.BLAZE_SPAWN_EGG,            1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.FOUR, new LootTableEntry(Material.GUARDIAN_SPAWN_EGG,         1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.FOUR, new LootTableEntry(Material.WITHER_SKELETON_SPAWN_EGG,  1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.FOUR, new LootTableEntry(Material.VINDICATOR_SPAWN_EGG,       1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.FOUR, new LootTableEntry(Material.WITCH_SPAWN_EGG,            1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.FOUR, new LootTableEntry(Material.SHULKER_SPAWN_EGG,          1,      1),     1);


        //level 5
        addItemToLootTable(Slayer.DifficultyLevel.FIVE, new LootTableEntry(Material.DIAMOND_HELMET,             1,      1),     5);
        addItemToLootTable(Slayer.DifficultyLevel.FIVE, new LootTableEntry(Material.DIAMOND_CHESTPLATE,         1,      1),     5);
        addItemToLootTable(Slayer.DifficultyLevel.FIVE, new LootTableEntry(Material.DIAMOND_LEGGINGS,           1,      1),     5);
        addItemToLootTable(Slayer.DifficultyLevel.FIVE, new LootTableEntry(Material.DIAMOND_BOOTS,              1,      1),     5);
        addItemToLootTable(Slayer.DifficultyLevel.FIVE, new LootTableEntry(Material.DIAMOND_PICKAXE,            1,      1),     5);
        addItemToLootTable(Slayer.DifficultyLevel.FIVE, new LootTableEntry(Material.DIAMOND_AXE,                1,      1),     5);
        addItemToLootTable(Slayer.DifficultyLevel.FIVE, new LootTableEntry(Material.DIAMOND_SHOVEL,             1,      1),     5);
        addItemToLootTable(Slayer.DifficultyLevel.FIVE, new LootTableEntry(Material.DIAMOND_SWORD,              1,      1),     5);
        addItemToLootTable(Slayer.DifficultyLevel.FIVE, new LootTableEntry(Material.GOLDEN_APPLE,               8,      12),    5);
        addItemToLootTable(Slayer.DifficultyLevel.FIVE, new LootTableEntry(Material.ENCHANTED_GOLDEN_APPLE,     3,      7),     3);
        addItemToLootTable(Slayer.DifficultyLevel.FIVE, new LootTableEntry(Material.SHULKER_SHELL,              6,      12),    5);
        addItemToLootTable(Slayer.DifficultyLevel.FIVE, new LootTableEntry(Material.DIAMOND,                    15,     30),    5);
        addItemToLootTable(Slayer.DifficultyLevel.FIVE, new LootTableEntry(Material.WITHER_SKELETON_SKULL,      1,      3),     4);
        addItemToLootTable(Slayer.DifficultyLevel.FIVE, new LootTableEntry(Material.ELYTRA,                     1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.FIVE, new LootTableEntry(Material.NETHERITE_SCRAP,            4,      8),     1);
        addItemToLootTable(Slayer.DifficultyLevel.FIVE, new LootTableEntry(Material.ENDER_PEARL,                12,     16),    10);
        addItemToLootTable(Slayer.DifficultyLevel.FIVE, new LootTableEntry(Material.BLAZE_ROD,                  15,     25),    10);
        addItemToLootTable(Slayer.DifficultyLevel.FIVE, new LootTableEntry(Material.GHAST_TEAR,                 15,     24),    10);
        addItemToLootTable(Slayer.DifficultyLevel.FIVE, new LootTableEntry(Material.EXPERIENCE_BOTTLE,          64,     64),    3);
        addItemToLootTable(Slayer.DifficultyLevel.FIVE, new LootTableEntry(Material.FIREWORK_ROCKET,            50,     64),    6);
        addItemToLootTable(Slayer.DifficultyLevel.FIVE, new LootTableEntry(Material.EVOKER_SPAWN_EGG,           1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.FIVE, new LootTableEntry(Material.RAVAGER_SPAWN_EGG,          1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.FIVE, new LootTableEntry(Material.PIGLIN_BRUTE_SPAWN_EGG,     1,      1),     1);

        //level 6
        addItemToLootTable(Slayer.DifficultyLevel.SIX, new LootTableEntry(Material.DIAMOND_HELMET,              1,      1),     6);
        addItemToLootTable(Slayer.DifficultyLevel.SIX, new LootTableEntry(Material.DIAMOND_CHESTPLATE,          1,      1),     6);
        addItemToLootTable(Slayer.DifficultyLevel.SIX, new LootTableEntry(Material.DIAMOND_LEGGINGS,            1,      1),     6);
        addItemToLootTable(Slayer.DifficultyLevel.SIX, new LootTableEntry(Material.DIAMOND_BOOTS,               1,      1),     6);
        addItemToLootTable(Slayer.DifficultyLevel.SIX, new LootTableEntry(Material.DIAMOND_PICKAXE,             1,      1),     6);
        addItemToLootTable(Slayer.DifficultyLevel.SIX, new LootTableEntry(Material.DIAMOND_AXE,                 1,      1),     6);
        addItemToLootTable(Slayer.DifficultyLevel.SIX, new LootTableEntry(Material.DIAMOND_SHOVEL,              1,      1),     6);
        addItemToLootTable(Slayer.DifficultyLevel.SIX, new LootTableEntry(Material.DIAMOND_SWORD,               1,      1),     6);
        addItemToLootTable(Slayer.DifficultyLevel.SIX, new LootTableEntry(Material.GOLDEN_APPLE,                12,     20),    10);
        addItemToLootTable(Slayer.DifficultyLevel.SIX, new LootTableEntry(Material.ENCHANTED_GOLDEN_APPLE,      5,      10),    6);
        addItemToLootTable(Slayer.DifficultyLevel.SIX, new LootTableEntry(Material.SHULKER_SHELL,               10,     16),    6);
        addItemToLootTable(Slayer.DifficultyLevel.SIX, new LootTableEntry(Material.DIAMOND,                     30,     50),    6);
        addItemToLootTable(Slayer.DifficultyLevel.SIX, new LootTableEntry(Material.WITHER_SKELETON_SKULL,       3,      9),     6);
        addItemToLootTable(Slayer.DifficultyLevel.SIX, new LootTableEntry(Material.ELYTRA,                      1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.SIX, new LootTableEntry(Material.NETHERITE_SCRAP,             4,      8),     1);
        addItemToLootTable(Slayer.DifficultyLevel.SIX, new LootTableEntry(Material.ENDER_PEARL,                 12,     16),    10);
        addItemToLootTable(Slayer.DifficultyLevel.SIX, new LootTableEntry(Material.BLAZE_ROD,                   15,     25),    10);
        addItemToLootTable(Slayer.DifficultyLevel.SIX, new LootTableEntry(Material.GHAST_TEAR,                  15,     24),    10);

        //level 7
        addItemToLootTable(Slayer.DifficultyLevel.SEVEN, new LootTableEntry(Material.NETHERITE_HELMET,          1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.SEVEN, new LootTableEntry(Material.NETHERITE_CHESTPLATE,      1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.SEVEN, new LootTableEntry(Material.NETHERITE_LEGGINGS,        1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.SEVEN, new LootTableEntry(Material.NETHERITE_BOOTS,           1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.SEVEN, new LootTableEntry(Material.NETHERITE_INGOT,           1,      3),     3);
        addItemToLootTable(Slayer.DifficultyLevel.SEVEN, new LootTableEntry(Material.NETHERITE_BLOCK,           1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.SEVEN, new LootTableEntry(Material.NETHERITE_PICKAXE,         1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.SEVEN, new LootTableEntry(Material.NETHERITE_AXE,             1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.SEVEN, new LootTableEntry(Material.NETHERITE_SHOVEL,          1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.SEVEN, new LootTableEntry(Material.NETHERITE_SWORD,           1,      1),     1);
        addItemToLootTable(Slayer.DifficultyLevel.SEVEN, new LootTableEntry(Material.DIAMOND_HELMET,            1,      1),     7);
        addItemToLootTable(Slayer.DifficultyLevel.SEVEN, new LootTableEntry(Material.DIAMOND_CHESTPLATE,        1,      1),     7);
        addItemToLootTable(Slayer.DifficultyLevel.SEVEN, new LootTableEntry(Material.DIAMOND_LEGGINGS,          1,      1),     7);
        addItemToLootTable(Slayer.DifficultyLevel.SEVEN, new LootTableEntry(Material.DIAMOND_BOOTS,             1,      1),     7);
        addItemToLootTable(Slayer.DifficultyLevel.SEVEN, new LootTableEntry(Material.DIAMOND_PICKAXE,           1,      1),     7);
        addItemToLootTable(Slayer.DifficultyLevel.SEVEN, new LootTableEntry(Material.DIAMOND_AXE,               1,      1),     7);
        addItemToLootTable(Slayer.DifficultyLevel.SEVEN, new LootTableEntry(Material.DIAMOND_SHOVEL,            1,      1),     7);
        addItemToLootTable(Slayer.DifficultyLevel.SEVEN, new LootTableEntry(Material.DIAMOND_SWORD,             1,      1),     7);
        addItemToLootTable(Slayer.DifficultyLevel.SEVEN, new LootTableEntry(Material.GOLDEN_APPLE,              12,     24),    5);
        addItemToLootTable(Slayer.DifficultyLevel.SEVEN, new LootTableEntry(Material.ENCHANTED_GOLDEN_APPLE,    5,      10),    3);
        addItemToLootTable(Slayer.DifficultyLevel.SEVEN, new LootTableEntry(Material.SHULKER_SHELL,             16,     32),    3);
        addItemToLootTable(Slayer.DifficultyLevel.SEVEN, new LootTableEntry(Material.DIAMOND,                   45,     64),    3);
        addItemToLootTable(Slayer.DifficultyLevel.SEVEN, new LootTableEntry(Material.WITHER_SKELETON_SKULL,     6,      12),    3);
        addItemToLootTable(Slayer.DifficultyLevel.SEVEN, new LootTableEntry(Material.ELYTRA,                    1,      1),     3);
        addItemToLootTable(Slayer.DifficultyLevel.SEVEN, new LootTableEntry(Material.ENDER_CHEST,               10,     18),    5);
        addItemToLootTable(Slayer.DifficultyLevel.SEVEN, new LootTableEntry(Material.END_CRYSTAL,               4,      12),    3);
        addItemToLootTable(Slayer.DifficultyLevel.SEVEN, new LootTableEntry(Material.SPAWNER,                   1,      1),     1);

    }

    private static void addItemToLootTable(Slayer.DifficultyLevel table, LootTableEntry entry, int weight) {
        //add the specified entry a number of times equal to the weight specified
        for(int i = 0; i < weight; i++){
            lootTable.get(table).add(entry);
        }
    }

    public static ItemStack generateLoot(Slayer.DifficultyLevel dl) {
        SecureRandom rnd = new SecureRandom();

        //get a random entry from the loot list associated with this difficulty level
        LootTableEntry entry = lootTable.get(dl).get(rnd.nextInt(lootTable.get(dl).size()));

        return new ItemStack(entry.getMaterial(), entry.randomQuantity());
    }
}
