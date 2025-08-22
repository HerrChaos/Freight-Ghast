package com.github.herrchaos.freight_ghast.fabric;

import com.github.herrchaos.freight_ghast.FreightGhast;
import net.fabricmc.api.ModInitializer;

public final class FreightGhastFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        FreightGhast.init();
    }
}
