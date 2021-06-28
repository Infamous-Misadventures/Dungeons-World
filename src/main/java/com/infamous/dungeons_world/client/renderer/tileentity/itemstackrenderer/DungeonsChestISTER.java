package com.infamous.dungeons_world.client.renderer.tileentity.itemstackrenderer;

import com.infamous.dungeons_world.tileentity.DungeonsChestTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;

import java.util.function.Supplier;

public class DungeonsChestISTER <T extends DungeonsChestTileEntity> extends ItemStackTileEntityRenderer {
    private final Supplier<T> tileEntity;

    public DungeonsChestISTER(Supplier<T> tileEntity) {
        this.tileEntity = tileEntity;
    }

    @Override
    public void renderByItem(ItemStack itemStackIn, ItemCameraTransforms.TransformType transformType, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        TileEntityRendererDispatcher.instance.renderItem(tileEntity.get(), matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
    }
}