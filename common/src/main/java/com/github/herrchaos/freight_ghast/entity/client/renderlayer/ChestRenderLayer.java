package com.github.herrchaos.freight_ghast.entity.client.renderlayer;

import com.github.herrchaos.freight_ghast.FreightGhast;
import com.github.herrchaos.freight_ghast.datasaver.HappyGhastRenderStateDataSaver;
import com.github.herrchaos.freight_ghast.entity.client.ModModelLayers;
import com.github.herrchaos.freight_ghast.entity.client.model.ChestHarnessModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HappyGhastModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.HappyGhastRenderState;
import net.minecraft.resources.ResourceLocation;

public class ChestRenderLayer<M extends HappyGhastModel> extends RenderLayer<HappyGhastRenderState, M> {
    private static final ResourceLocation GHAST_CHEST_HOLSTER = FreightGhast.idOf("textures/entity/ghast/chest_harness.png");

    private final ChestHarnessModel model;

    public ChestRenderLayer(RenderLayerParent<HappyGhastRenderState, M> renderLayerParent, EntityModelSet entityModelSet) {
        super(renderLayerParent);
        this.model = new ChestHarnessModel(entityModelSet.bakeLayer(ModModelLayers.CHEST_HARNESS));
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int light, HappyGhastRenderState entityRenderState, float f, float g) {
        if (((HappyGhastRenderStateDataSaver) entityRenderState).freight_ghast$getChestAmount() > 0) {

            for (int i = 0; i < this.model.getHolsters().size(); i++) {
                ModelPart holster = this.model.getHolsters().get(i);

                holster.visible = i < ((HappyGhastRenderStateDataSaver) entityRenderState).freight_ghast$getChestAmount();
            }


            poseStack.pushPose();

            VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.entityCutout(GHAST_CHEST_HOLSTER));
            int j = LivingEntityRenderer.getOverlayCoords(entityRenderState, 0.0F);
            this.model.renderToBuffer(poseStack, vertexConsumer, light, j);

            poseStack.popPose();
        }
    }
}
