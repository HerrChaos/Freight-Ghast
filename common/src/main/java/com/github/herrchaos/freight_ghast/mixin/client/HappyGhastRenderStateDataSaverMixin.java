package com.github.herrchaos.freight_ghast.mixin.client;

import com.github.herrchaos.freight_ghast.datasaver.HappyGhastRenderStateDataSaver;
import net.minecraft.client.renderer.entity.state.HappyGhastRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(HappyGhastRenderState.class)
public class HappyGhastRenderStateDataSaverMixin implements HappyGhastRenderStateDataSaver {
    @Unique
    public int freight_ghast$chestAmount = 0;

    @Override
    public int freight_ghast$getChestAmount() {
        return freight_ghast$chestAmount;
    }

    @Override
    public void freight_ghast$setChestAmount(int hasChests) {
        this.freight_ghast$chestAmount = hasChests;
    }
}
