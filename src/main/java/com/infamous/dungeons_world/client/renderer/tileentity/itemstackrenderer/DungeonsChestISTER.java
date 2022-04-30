package com.infamous.dungeons_world.client.renderer.tileentity.itemstackrenderer;

import com.infamous.dungeons_world.blockentity.DungeonsChestBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class DungeonsChestISTER <T extends DungeonsChestBlockEntity> extends BlockEntityWithoutLevelRenderer {
    private final Supplier<T> blockEntity;
    private final BlockEntityRenderDispatcher blockEntityRenderDispatcher;

    public DungeonsChestISTER(BlockEntityRenderDispatcher renderDispatcher, EntityModelSet entityModelSet, Supplier<T> blockEntity) {
        super(renderDispatcher, entityModelSet);
        this.blockEntity = blockEntity;
        this.blockEntityRenderDispatcher = renderDispatcher;
    }


    @Override
    public void renderByItem(ItemStack itemStackIn, ItemTransforms.TransformType transformType, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        this.blockEntityRenderDispatcher.renderItem(blockEntity.get(), matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
    }
}