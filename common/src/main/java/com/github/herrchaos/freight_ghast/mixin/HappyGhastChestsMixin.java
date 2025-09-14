package com.github.herrchaos.freight_ghast.mixin;

import com.github.herrchaos.freight_ghast.datasaver.HappyGhastInventoryDataSaver;
import net.minecraft.core.NonNullList;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.HappyGhast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(HappyGhast.class)
public abstract class HappyGhastChestsMixin extends Animal implements HappyGhastInventoryDataSaver {
    @Unique
    private static final EntityDataAccessor<Integer> CHEST_AMOUNT = SynchedEntityData.defineId(HappyGhastChestsMixin.class, EntityDataSerializers.INT);

    @Unique
    public List<ItemStack> freight_ghast$chestsItems = NonNullList.withSize(54, ItemStack.EMPTY);

    protected HappyGhastChestsMixin(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "defineSynchedData", at = @At(value = "TAIL"))
    protected void defineSynchedData(SynchedEntityData.Builder builder, CallbackInfo ci) {
        builder.define(CHEST_AMOUNT, 0);
    }

    @Override
    protected void dropEquipment(ServerLevel serverLevel) {
        super.dropEquipment(serverLevel);

        this.freight_ghast$chestsItems.forEach((itemStack) -> this.spawnAtLocation(serverLevel, itemStack));
        this.freight_ghast$chestsItems.clear();

        if (this.freight_ghast$getChestAmount() > 0) {
            this.spawnAtLocation(serverLevel, new ItemStack(Items.CHEST, this.freight_ghast$getChestAmount()));
            this.freight_ghast$setChestAmount(0);
        }
    }

    @Inject(method = "mobInteract", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;getItemInHand(Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/item/ItemStack;"), cancellable = true)
    public void openChests(Player player, InteractionHand interactionHand, CallbackInfoReturnable<InteractionResult> cir) {
        HappyGhast happyGhast = (HappyGhast) (Object) this;
        if (player.getItemInHand(interactionHand).is(Items.CHEST)) {
            if (this.freight_ghast$getChestAmount() >= 6) {
                cir.setReturnValue(InteractionResult.FAIL);
                cir.cancel();
                return;
            }

            this.freight_ghast$setChestAmount(this.freight_ghast$getChestAmount() + 1);
            player.getItemInHand(interactionHand).shrink(1);

            player.level().playSound(null, happyGhast.getX(), happyGhast.getY(), happyGhast.getZ(), SoundEvents.HARNESS_EQUIP.value(), SoundSource.PLAYERS);
            cir.setReturnValue(InteractionResult.SUCCESS);
            cir.cancel();
            return;
        }

        if (player.getItemInHand(interactionHand).is(Items.SHEARS) && player.level() instanceof ServerLevel serverLevel) {
            serverLevel.playSound(null, happyGhast.getX(), happyGhast.getY(), happyGhast.getZ(), SoundEvents.SHEARS_SNIP, SoundSource.PLAYERS);

            this.spawnAtLocation(serverLevel, new ItemStack(Items.CHEST, 1));
            if (this.freight_ghast$getChestAmount() == 6) {
                this.freight_ghast$chestsItems.forEach((itemStack) -> this.spawnAtLocation(serverLevel, itemStack));
                this.freight_ghast$chestsItems.clear();
            }

            this.freight_ghast$setChestAmount(this.freight_ghast$getChestAmount() - 1);

            cir.setReturnValue(InteractionResult.SUCCESS);
            cir.cancel();
            return;
        }

        if (player.isSecondaryUseActive() && this.freight_ghast$getChestAmount() == 6) {
            SimpleContainer container = new SimpleContainer(freight_ghast$chestsItems.toArray(new ItemStack[0]));

            container.addListener((newContainer) -> this.freight_ghast$chestsItems = ((SimpleContainer) newContainer).getItems());

            player.openMenu(new SimpleMenuProvider((i, inventory, player1) ->
                    new ChestMenu(
                            MenuType.GENERIC_9x6,
                            i,
                            inventory,
                            container,
                            6
                    ),
                    happyGhast.getName()));
            cir.setReturnValue(InteractionResult.SUCCESS);
        }
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    public void addAdditionalSaveData(ValueOutput valueOutput, CallbackInfo ci) {
        ValueOutput.TypedOutputList<ItemStackWithSlot> typedoutputlist = valueOutput.list("Items", ItemStackWithSlot.CODEC);

        for (int i = 0; i < this.freight_ghast$getInventory().size(); i++) {
            ItemStack itemStack = this.freight_ghast$getInventory().get(i);
            if (!itemStack.isEmpty()) {
                typedoutputlist.add(new ItemStackWithSlot(i, itemStack));
            }
        }

        valueOutput.putInt("chest_amount", this.freight_ghast$getChestAmount());
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    public void readAdditionalSaveData(ValueInput valueInput, CallbackInfo ci) {
        for (ItemStackWithSlot itemStack : valueInput.listOrEmpty("Items", ItemStackWithSlot.CODEC)) {
            this.freight_ghast$chestsItems.set(itemStack.slot(), itemStack.stack());
        }

        valueInput.getInt("chest_amount").ifPresent(this::freight_ghast$setChestAmount);
    }

    @Override
    public List<ItemStack> freight_ghast$getInventory() {
        return this.freight_ghast$chestsItems;
    }

    @Override
    public void freight_ghast$setInventory(List<ItemStack> inventory) {
        this.freight_ghast$chestsItems = inventory;
    }

    @Override
    public int freight_ghast$getChestAmount() {
        HappyGhast happyGhast = (HappyGhast) (Object) this;
        return happyGhast.getEntityData().get(CHEST_AMOUNT);
    }

    @Override
    public void freight_ghast$setChestAmount(int chestAmount) {
        HappyGhast happyGhast = (HappyGhast) (Object) this;
        happyGhast.getEntityData().set(CHEST_AMOUNT, chestAmount);
    }
}
