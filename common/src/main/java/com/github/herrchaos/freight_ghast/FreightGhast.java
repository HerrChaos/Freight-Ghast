package com.github.herrchaos.freight_ghast;

import com.github.herrchaos.freight_ghast.entity.client.ModModelLayers;
import com.github.herrchaos.freight_ghast.entity.client.model.ChestHarnessModel;
import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import net.minecraft.resources.ResourceLocation;

public final class FreightGhast {
    public static final String MOD_ID = "freight_ghast";

    public static ResourceLocation idOf(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }


    public static void init() {
        ModModelLayers.init();

        EntityModelLayerRegistry.register(ModModelLayers.CHEST_HARNESS, ChestHarnessModel::createHarnessLayer);
    }
}
