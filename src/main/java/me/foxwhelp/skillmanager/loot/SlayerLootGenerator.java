package me.foxwhelp.skillmanager.loot;

import me.foxwhelp.skillmanager.skills.Slayer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.security.SecureRandom;

public class SlayerLootGenerator {

    public enum SlayerLootItem {

        BIRCH_LOG("Birch Log",  Material.BIRCH_LOG, 10, 20),
        OAK_LOG("Oak Log",  Material.OAK_LOG, 10, 20),
        COAL("Coal", Material.COAL,20, 40),
        SHULKER_BOX("Shulker Box", Material.SHULKER_BOX, 1, 1),
        CONDUIT("Conduit", Material.CONDUIT, 1, 1),
        DRAGON_EGG("Dragon Egg", Material.DRAGON_EGG, 1, 1);

        public final String name;
        public final int minQuantity;
        public final int maxQuantity;
        public final Material material;

        SlayerLootItem(String name, Material material, int minQuantity, int maxQuantity) {
            this.name = name;
            this.material = material;
            this.minQuantity = minQuantity;
            this.maxQuantity = maxQuantity;
        }

        public static SlayerLootItem randomItemForDifficulty(Slayer.DifficultyLevel level) {
            SecureRandom rnd = new SecureRandom();
            SlayerLootItem lootItem;

            //TODO remove default item for testing
            lootItem = BIRCH_LOG;

            return lootItem;
        }

        public static int randomQuantityForItem(SlayerLootItem item){
            SecureRandom rnd = new SecureRandom();
            return (rnd.nextInt(((item.maxQuantity + 1) - item.minQuantity)) + item.minQuantity);
        }
    }

    private static final SecureRandom random = new SecureRandom();

    public static ItemStack generateLoot(Slayer.DifficultyLevel level) {
        ItemStack lootItemStack;
        SlayerLootItem lootItem = SlayerLootItem.randomItemForDifficulty(level);
        int lootQuantity = SlayerLootItem.randomQuantityForItem(lootItem);
        lootItemStack = new ItemStack(lootItem.material, lootQuantity);
        return lootItemStack;
    }

    public static <T extends Enum<?>> T randomEnum(Class<T> c) {
        int x = random.nextInt(c.getEnumConstants().length);
        return c.getEnumConstants()[x];
    }

}
