package com.github.herrchaos.freight_ghast.mixin;

import com.github.herrchaos.freight_ghast.datasaver.HappyGhastInventoryDataSaver;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;

@Mixin(HopperBlockEntity.class)
public class HopperBlockEntityMixin {
    @WrapMethod(method = "getEntityContainer")
    private static Container wrapGetEntityContainer(Level level, double d, double e, double f, Operation<Container> originalMethod) {
        Container original = originalMethod.call(level, d, e, f);

        if (original == null) {
            List<Entity> list = level.getEntities((Entity)null, new AABB(d - (double)0.5F, e - (double)0.5F, f - (double)0.5F, d + (double)0.5F, e + (double)0.5F, f + (double)0.5F), (entity) -> entity instanceof HappyGhastInventoryDataSaver);

            if (!list.isEmpty()) {
                HappyGhastInventoryDataSaver happyGhast = ((HappyGhastInventoryDataSaver)list.get(level.random.nextInt(list.size())));

                if (happyGhast.freight_ghast$getChestAmount() != 6) {
                    return null;
                }

                SimpleContainer container = new SimpleContainer(happyGhast.freight_ghast$getInventory().toArray(new ItemStack[0]));

                container.addListener((newContainer) -> happyGhast.freight_ghast$setInventory(((SimpleContainer) newContainer).getItems()));

                return container;
            }
        }

        return original;
    }
}
