package me.foxwhelp.skillmanager.loot;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.security.SecureRandom;

public class LootGenerator {

    public enum LootType {ARMOR, WEAPON, FOOD}

    private static final SecureRandom random = new SecureRandom();

    public LootGenerator() {

    }

    public static ItemStack generateLoot() {
        ItemStack lootItem;
        LootType lt = randomEnum(LootType.class);
        switch (lt) {
            case ARMOR:
                lootItem = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
                break;
            case WEAPON:
                lootItem = new ItemStack(Material.NETHERITE_SWORD, 1);
                break;
            case FOOD:
                lootItem = new ItemStack(Material.COOKED_BEEF, 12);
                break;
            default:
                lootItem = new ItemStack(Material.BLUE_GLAZED_TERRACOTTA, 20);
                break;
        }
        return lootItem;
    }

    public static <T extends Enum<?>> T randomEnum(Class<T> c) {
        int x = random.nextInt(c.getEnumConstants().length);
        return c.getEnumConstants()[x];
    }

}
