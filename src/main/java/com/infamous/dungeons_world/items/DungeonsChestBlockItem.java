package com.infamous.dungeons_world.items;

import com.infamous.dungeons_world.blockentity.DungeonsChestBlockEntity;
import com.infamous.dungeons_world.blockentity.DungeonsChestType;
import com.infamous.dungeons_world.init.BlocksInit;
import com.infamous.dungeons_world.client.renderer.tileentity.itemstackrenderer.DungeonsChestISTER;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class DungeonsChestBlockItem extends BlockItem {
    private DungeonsChestType dungeonsChestType;

    public DungeonsChestBlockItem(Block p_40565_, Properties p_40566_, DungeonsChestType dungeonsChestType) {
        super(p_40565_, p_40566_);
        this.dungeonsChestType = dungeonsChestType;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer)
    {
        consumer.accept(new IClientItemExtensions()
        {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer()
            {
                Supplier<DungeonsChestBlockEntity> modelToUse;
                modelToUse = switch (dungeonsChestType) {
                    case COMMON -> () -> new DungeonsChestBlockEntity(BlockPos.ZERO, BlocksInit.COMMON_CHEST.get().defaultBlockState(), dungeonsChestType);
                    case FANCY -> () -> new DungeonsChestBlockEntity(BlockPos.ZERO, BlocksInit.FANCY_CHEST.get().defaultBlockState());
                    case OBSIDIAN -> () -> new DungeonsChestBlockEntity(BlockPos.ZERO, BlocksInit.OBSIDIAN_CHEST.get().defaultBlockState());
                };
                return new DungeonsChestISTER<>(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels(), modelToUse);
            }
        });
    }
}
