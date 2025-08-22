package com.github.herrchaos.freight_ghast.datasaver;

import net.minecraft.world.item.ItemStack;

import java.util.List;

public interface HappyGhastInventoryDataSaver {
    List<ItemStack> freight_ghast$getInventory();

    void freight_ghast$setInventory(List<ItemStack> inventory);

    int freight_ghast$getChestAmount();

    void freight_ghast$setChestAmount(int hasChests);
}
