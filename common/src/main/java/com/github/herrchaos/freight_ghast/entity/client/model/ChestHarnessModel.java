package com.github.herrchaos.freight_ghast.entity.client.model;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

import java.util.List;

public class ChestHarnessModel<T extends LivingEntityRenderState> extends Model<T> {
    private final ModelPart holder;
    private final ModelPart holster5;
    private final ModelPart holster3;
    private final ModelPart holster1;
    private final ModelPart holster2;
    private final ModelPart holster4;
    private final ModelPart holster6;

    public ChestHarnessModel(ModelPart modelPart) {
        super(modelPart, RenderType::entityCutout);
        this.holder = root.getChild("holder");
        this.holster5 = this.holder.getChild("right").getChild("holster5");
        this.holster3 = this.holder.getChild("right").getChild("holster3");
        this.holster1 = this.holder.getChild("right").getChild("holster1");
        this.holster2 = this.holder.getChild("left").getChild("holster2");
        this.holster4 = this.holder.getChild("left").getChild("holster4");
        this.holster6 = this.holder.getChild("left").getChild("holster6");
    }

    public ModelPart getHolder() {
        return holder;
    }

    public List<ModelPart> getHolsters() {
        return List.of(holster1, holster2, holster3, holster4, holster5, holster6);
    }

    public ModelPart getHarness(int i) {
        return switch (i) {
            case 0 -> this.holster1;
            case 1 -> this.holster2;
            case 2 -> this.holster3;
            case 3 -> this.holster4;
            case 4 -> this.holster5;
            case 5 -> this.holster6;
            default -> throw new IllegalArgumentException("Invalid harness index: " + i);
        };
    }

    public static LayerDefinition createHarnessLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition holder = partdefinition.addOrReplaceChild("holder", CubeListBuilder.create().texOffs(0, 78).addBox(-32.0F, -65.25F, -24.5F, 64.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 78).addBox(-32.0F, -65.25F, -11.5F, 64.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 78).addBox(-32.0F, -65.25F, -7.5F, 64.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 74).addBox(-32.0F, -65.25F, 5.5F, 64.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 74).addBox(-32.0F, -65.25F, 9.5F, 64.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 74).addBox(-32.0F, -65.25F, 22.5F, 64.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition right = holder.addOrReplaceChild("right", CubeListBuilder.create().texOffs(2, 46).addBox(-2.0F, -6.0F, -4.0F, 3.0F, 6.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(36, 46).addBox(-1.0F, -5.5F, -21.0F, 2.0F, 6.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(36, 46).addBox(-1.0F, -5.5F, 13.0F, 2.0F, 6.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(-33.0F, -36.5F, -2.0F));

        PartDefinition holster5 = right.addOrReplaceChild("holster5", CubeListBuilder.create().texOffs(0, 0).addBox(-15.0F, 0.0F, -7.5F, 15.0F, 29.0F, 15.0F, new CubeDeformation(0.0F))
                .texOffs(20, 54).mirror().addBox(-2.0F, 0.0F, -7.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(20, 54).mirror().addBox(-2.0F, 0.0F, 5.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, -28.75F, -15.0F, 0.0F, 0.0F, 0.0785F));

        PartDefinition chest1 = holster5.addOrReplaceChild("chest1", CubeListBuilder.create(), PartPose.offsetAndRotation(-7.5F, 19.5F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r1 = chest1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(72, 104).addBox(-7.0F, -5.0F, -7.0F, 14.0F, 10.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.75F, 0.0F, 0.0F, 0.0F, 3.1416F));

        PartDefinition lid1 = chest1.addOrReplaceChild("lid1", CubeListBuilder.create().texOffs(72, 85).addBox(-1.0F, -7.0F, 7.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.75F, 0.0F));

        PartDefinition cube_r2 = lid1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(72, 85).addBox(-7.0F, -5.0F, -7.0F, 14.0F, 5.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, 0.0F, 0.0F, 0.0F, 3.1416F));

        PartDefinition holster3 = right.addOrReplaceChild("holster3", CubeListBuilder.create().texOffs(0, 0).addBox(-15.0F, 0.0F, -7.5F, 15.0F, 29.0F, 15.0F, new CubeDeformation(0.0F))
                .texOffs(20, 54).mirror().addBox(-2.0F, 0.0F, -7.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(20, 54).mirror().addBox(-2.0F, 0.0F, 5.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, -28.75F, 2.0F, 0.0F, 0.0F, 0.1134F));

        PartDefinition chest2 = holster3.addOrReplaceChild("chest2", CubeListBuilder.create(), PartPose.offsetAndRotation(-7.5F, 19.5F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r3 = chest2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(72, 104).addBox(-7.0F, -5.0F, -7.0F, 14.0F, 10.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.75F, 0.0F, 0.0F, 0.0F, 3.1416F));

        PartDefinition lid2 = chest2.addOrReplaceChild("lid2", CubeListBuilder.create().texOffs(72, 85).addBox(-1.0F, -7.0F, 7.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.75F, 0.0F));

        PartDefinition cube_r4 = lid2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(72, 85).addBox(-7.0F, -5.0F, -7.0F, 14.0F, 5.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, 0.0F, 0.0F, 0.0F, 3.1416F));

        PartDefinition holster1 = right.addOrReplaceChild("holster1", CubeListBuilder.create().texOffs(0, 0).addBox(-15.0F, 0.0F, -7.5F, 15.0F, 29.0F, 15.0F, new CubeDeformation(0.0F))
                .texOffs(20, 54).mirror().addBox(-2.0F, 0.0F, -7.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(20, 54).mirror().addBox(-2.0F, 0.0F, 5.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, -28.75F, 19.0F, 0.0F, 0.0F, 0.0611F));

        PartDefinition chest3 = holster1.addOrReplaceChild("chest3", CubeListBuilder.create(), PartPose.offsetAndRotation(-7.5F, 19.5F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r5 = chest3.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(72, 104).addBox(-7.0F, -5.0F, -7.0F, 14.0F, 10.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.75F, 0.0F, 0.0F, 0.0F, 3.1416F));

        PartDefinition lid3 = chest3.addOrReplaceChild("lid3", CubeListBuilder.create().texOffs(72, 85).addBox(-1.0F, -7.0F, 7.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.75F, 0.0F));

        PartDefinition cube_r6 = lid3.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(72, 85).addBox(-7.0F, -5.0F, -7.0F, 14.0F, 5.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, 0.0F, 0.0F, 0.0F, 3.1416F));

        PartDefinition left = holder.addOrReplaceChild("left", CubeListBuilder.create().texOffs(2, 46).mirror().addBox(-1.0F, -6.0F, -8.0F, 3.0F, 6.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(36, 46).mirror().addBox(-1.0F, -5.5F, 9.0F, 2.0F, 6.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(36, 46).mirror().addBox(-1.0F, -5.5F, -25.0F, 2.0F, 6.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(33.0F, -36.5F, 2.0F));

        PartDefinition holster2 = left.addOrReplaceChild("holster2", CubeListBuilder.create().texOffs(60, 0).addBox(0.0F, 0.0F, -7.5F, 15.0F, 29.0F, 15.0F, new CubeDeformation(0.0F))
                .texOffs(20, 54).addBox(0.0F, 0.0F, 5.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(20, 54).addBox(0.0F, 0.0F, -7.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -28.75F, 15.0F, 0.0F, 0.0F, -0.0611F));

        PartDefinition chest4 = holster2.addOrReplaceChild("chest4", CubeListBuilder.create(), PartPose.offsetAndRotation(7.5F, 19.5F, 0.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition cube_r7 = chest4.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(72, 104).mirror().addBox(-7.0F, -5.0F, -7.0F, 14.0F, 10.0F, 14.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 3.75F, 0.0F, 0.0F, 0.0F, -3.1416F));

        PartDefinition lid4 = chest4.addOrReplaceChild("lid4", CubeListBuilder.create().texOffs(72, 85).mirror().addBox(-1.0F, -7.0F, 7.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 3.75F, 0.0F));

        PartDefinition cube_r8 = lid4.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(72, 85).mirror().addBox(-7.0F, -5.0F, -7.0F, 14.0F, 5.0F, 14.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -10.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

        PartDefinition holster4 = left.addOrReplaceChild("holster4", CubeListBuilder.create().texOffs(60, 0).addBox(0.0F, 0.0F, -7.5F, 15.0F, 29.0F, 15.0F, new CubeDeformation(0.0F))
                .texOffs(20, 54).addBox(0.0F, 0.0F, -7.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(20, 54).addBox(0.0F, 0.0F, 5.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -28.75F, -2.0F, 0.0F, 0.0F, -0.1134F));

        PartDefinition chest5 = holster4.addOrReplaceChild("chest5", CubeListBuilder.create(), PartPose.offsetAndRotation(7.5F, 19.5F, 0.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition cube_r9 = chest5.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(72, 104).mirror().addBox(-7.0F, -5.0F, -7.0F, 14.0F, 10.0F, 14.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 3.75F, 0.0F, 0.0F, 0.0F, -3.1416F));

        PartDefinition lid5 = chest5.addOrReplaceChild("lid5", CubeListBuilder.create().texOffs(72, 85).mirror().addBox(-1.0F, -7.0F, 7.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 3.75F, 0.0F));

        PartDefinition cube_r10 = lid5.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(72, 85).mirror().addBox(-7.0F, -5.0F, -7.0F, 14.0F, 5.0F, 14.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -10.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

        PartDefinition holster6 = left.addOrReplaceChild("holster6", CubeListBuilder.create().texOffs(60, 0).addBox(0.0F, 0.0F, -7.5F, 15.0F, 29.0F, 15.0F, new CubeDeformation(0.0F))
                .texOffs(20, 54).addBox(0.0F, 0.0F, -7.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(20, 54).addBox(0.0F, 0.0F, 5.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -28.75F, -19.0F, 0.0F, 0.0F, -0.0785F));

        PartDefinition chest6 = holster6.addOrReplaceChild("chest6", CubeListBuilder.create(), PartPose.offsetAndRotation(7.5F, 19.5F, 0.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition cube_r11 = chest6.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(72, 104).mirror().addBox(-7.0F, -5.0F, -7.0F, 14.0F, 10.0F, 14.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 3.75F, 0.0F, 0.0F, 0.0F, -3.1416F));

        PartDefinition lid6 = chest6.addOrReplaceChild("lid6", CubeListBuilder.create().texOffs(72, 85).mirror().addBox(-1.0F, -7.0F, 7.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 3.75F, 0.0F));

        PartDefinition cube_r12 = lid6.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(72, 85).mirror().addBox(-7.0F, -5.0F, -7.0F, 14.0F, 5.0F, 14.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -10.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }
}
