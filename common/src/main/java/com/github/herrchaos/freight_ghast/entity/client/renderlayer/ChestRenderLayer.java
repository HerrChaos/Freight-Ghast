package com.github.herrchaos.freight_ghast.entity.client.renderlayer;

import com.github.herrchaos.freight_ghast.FreightGhast;
import com.github.herrchaos.freight_ghast.datasaver.HappyGhastRenderStateDataSaver;
import com.github.herrchaos.freight_ghast.entity.client.ModModelLayers;
import com.github.herrchaos.freight_ghast.entity.client.model.ChestHarnessModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HappyGhastModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.HappyGhastRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class ChestRenderLayer<M extends HappyGhastModel> extends RenderLayer<HappyGhastRenderState, M> {
    private static final ResourceLocation GHAST_CHEST_HOLSTER = FreightGhast.idOf("textures/entity/ghast/chest_harness.png");

    private final ChestHarnessModel<HappyGhastRenderState> model;

    public ChestRenderLayer(RenderLayerParent<HappyGhastRenderState, M> renderLayerParent, EntityModelSet entityModelSet) {
        super(renderLayerParent);
        this.model = new ChestHarnessModel<>(entityModelSet.bakeLayer(ModModelLayers.CHEST_HARNESS));
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int light, HappyGhastRenderState entityRenderState, float f, float g) {
        if (((HappyGhastRenderStateDataSaver) entityRenderState).freight_ghast$getChestAmount() > 0) {

            for (int i = 0; i < this.model.getHolsters().size(); i++) {
                ModelPart holster = this.model.getHolsters().get(i);

                holster.visible = i < ((HappyGhastRenderStateDataSaver) entityRenderState).freight_ghast$getChestAmount();
            }


            poseStack.pushPose();

            submitNodeCollector.submitModel(this.model, entityRenderState, poseStack, RenderType.entityCutout(GHAST_CHEST_HOLSTER), light, OverlayTexture.NO_OVERLAY, entityRenderState.outlineColor, null);

            poseStack.popPose();
        }
    }
}
