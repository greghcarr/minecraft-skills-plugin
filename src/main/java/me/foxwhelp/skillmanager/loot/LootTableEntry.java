package me.foxwhelp.skillmanager.loot;

import org.bukkit.Material;

import java.security.SecureRandom;

public class LootTableEntry {
    private final Material material;
    private final int minQuantity;
    private final int maxQuantity;

    public LootTableEntry(Material material, int minQuantity, int maxQuantity) {
        this.material = material;
        this.minQuantity = minQuantity;
        this.maxQuantity = maxQuantity;
    }

    public Material getMaterial() {
        return material;
    }

    public int randomQuantity() {
        SecureRandom rnd = new SecureRandom();
        return (rnd.nextInt(((maxQuantity + 1) - minQuantity)) + minQuantity);
    }
}
