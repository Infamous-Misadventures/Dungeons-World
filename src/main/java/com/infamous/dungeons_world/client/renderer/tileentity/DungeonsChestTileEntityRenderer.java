package com.infamous.dungeons_world.client.renderer.tileentity;

import com.infamous.dungeons_world.client.renderer.ChestAtlas;
import com.infamous.dungeons_world.tileentity.DungeonsChestTileEntity;
import com.infamous.dungeons_world.tileentity.DungeonsChestType;
import net.minecraft.client.renderer.Atlases;
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

      return this.getMaterial(chestType, tileEntity.getDungeonsChestType());
   }

   public RenderMaterial getMaterial(ChestType type, DungeonsChestType dungeonsChestType) {
      switch(type) {
         case LEFT:
            return ChestAtlas.CHEST_LEFT_MATERIALS.getOrDefault(dungeonsChestType, Atlases.CHEST_LOCATION_LEFT);
         case RIGHT:
            return ChestAtlas.CHEST_RIGHT_MATERIALS.getOrDefault(dungeonsChestType, Atlases.CHEST_LOCATION_RIGHT);
         case SINGLE:
         default:
            return ChestAtlas.CHEST_MATERIALS.getOrDefault(dungeonsChestType, Atlases.CHEST_LOCATION);
      }
   }
}
