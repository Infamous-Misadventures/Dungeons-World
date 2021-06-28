package com.infamous.dungeons_world.client.renderer.tileentity;

import com.infamous.dungeons_world.tileentity.DungeonsChestTileEntity;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.tileentity.ChestTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.state.properties.ChestType;
import net.minecraft.tileentity.IChestLid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DungeonsChestTileEntityRenderer<T extends DungeonsChestTileEntity & IChestLid> extends ChestTileEntityRenderer<T> {

   public DungeonsChestTileEntityRenderer(TileEntityRendererDispatcher renderer) {
      super(renderer);
   }

   @Override
   protected RenderMaterial getMaterial(T tileEntity, ChestType chestType) {
      return tileEntity.getMaterial(chestType);
   }
}
