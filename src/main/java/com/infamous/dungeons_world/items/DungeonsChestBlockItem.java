package com.infamous.dungeons_world.items;

import com.infamous.dungeons_world.blockentity.DungeonsChestBlockEntity;
import com.infamous.dungeons_world.blockentity.DungeonsChestType;
import com.infamous.dungeons_world.blocks.ModBlocks;
import com.infamous.dungeons_world.client.renderer.tileentity.itemstackrenderer.DungeonsChestISTER;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.util.NonNullLazy;

import java.util.function.Consumer;
import java.util.function.Supplier;

import net.minecraft.world.item.Item.Properties;

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
                    case COMMON -> () -> new DungeonsChestBlockEntity(BlockPos.ZERO, ModBlocks.COMMON_CHEST.get().defaultBlockState(), dungeonsChestType);
                    case FANCY -> () -> new DungeonsChestBlockEntity(BlockPos.ZERO, ModBlocks.FANCY_CHEST.get().defaultBlockState());
                    case OBSIDIAN -> () -> new DungeonsChestBlockEntity(BlockPos.ZERO, ModBlocks.OBSIDIAN_CHEST.get().defaultBlockState());
                };
                return new DungeonsChestISTER<>(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels(), modelToUse);
            }
        });
    }
}
