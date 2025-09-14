package com.github.herrchaos.freight_ghast.mixin.client;

import com.github.herrchaos.freight_ghast.datasaver.HappyGhastInventoryDataSaver;
import com.github.herrchaos.freight_ghast.datasaver.HappyGhastRenderStateDataSaver;
import com.github.herrchaos.freight_ghast.entity.client.renderlayer.ChestRenderLayer;
import net.minecraft.client.model.HappyGhastModel;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HappyGhastRenderer;
import net.minecraft.client.renderer.entity.state.HappyGhastRenderState;
import net.minecraft.world.entity.animal.HappyGhast;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HappyGhastRenderer.class)
public abstract class GhastChestRenderMixin extends AgeableMobRenderer<HappyGhast, HappyGhastRenderState, HappyGhastModel> {
    public GhastChestRenderMixin(EntityRendererProvider.Context context, HappyGhastModel entityModel, HappyGhastModel entityModel2, float f) {
        super(context, entityModel, entityModel2, f);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(EntityRendererProvider.Context context, CallbackInfo ci) {
        this.addLayer(new ChestRenderLayer<>(this, context.getModelSet()));
    }

    @Inject(method = "extractRenderState(Lnet/minecraft/world/entity/animal/HappyGhast;Lnet/minecraft/client/renderer/entity/state/HappyGhastRenderState;F)V", at = @At("TAIL"))
    public void extractRenderState(HappyGhast happyGhast, HappyGhastRenderState happyGhastRenderState, float f, CallbackInfo ci) {
        ((HappyGhastRenderStateDataSaver) happyGhastRenderState).freight_ghast$setChestAmount(((HappyGhastInventoryDataSaver) happyGhast).freight_ghast$getChestAmount());
    }
}
