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
        addItemToLootTable(Slayer.DifficultyLevel.ONE, new LootTableEntry(Material.BIRCH_LOG,                   10,     20),    1);
        addItemToLootTable(Slayer.DifficultyLevel.ONE, new LootTableEntry(Material.COOKED_BEEF,                 20,     25),    1);

        //level 2
        addItemToLootTable(Slayer.DifficultyLevel.TWO, new LootTableEntry(Material.IRON_BARS,                   10,     20),    1);
        addItemToLootTable(Slayer.DifficultyLevel.TWO, new LootTableEntry(Material.BOW,                         1,      1),     1);

        //level 3
        addItemToLootTable(Slayer.DifficultyLevel.THREE, new LootTableEntry(Material.SHULKER_BOX,               1,      1),     1);

        //level 4
        addItemToLootTable(Slayer.DifficultyLevel.FOUR, new LootTableEntry(Material.DIAMOND,                    5,      20),    1);

        //level 5
        addItemToLootTable(Slayer.DifficultyLevel.FIVE, new LootTableEntry(Material.NETHERITE_SCRAP,            1,      1),     1);

        //level 6
        addItemToLootTable(Slayer.DifficultyLevel.SIX, new LootTableEntry(Material.NETHERITE_CHESTPLATE,        1,      1),     1);

        //level 7
        addItemToLootTable(Slayer.DifficultyLevel.SEVEN, new LootTableEntry(Material.NETHERITE_BLOCK,           1,      1),     1);

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
