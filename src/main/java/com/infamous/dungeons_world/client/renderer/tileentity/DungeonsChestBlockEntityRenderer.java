package com.infamous.dungeons_world.client.renderer.tileentity;

import com.infamous.dungeons_world.blockentity.DungeonsChestBlockEntity;
import com.infamous.dungeons_world.client.renderer.ChestAtlas;
import com.infamous.dungeons_world.blockentity.DungeonsChestType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DungeonsChestBlockEntityRenderer<T extends DungeonsChestBlockEntity & LidBlockEntity> extends ChestRenderer<T> {


   public DungeonsChestBlockEntityRenderer(BlockEntityRendererProvider.Context p_173607_) {
      super(p_173607_);
   }

   @Override
   protected Material getMaterial(T tileEntity, ChestType chestType) {

      return this.getMaterial(chestType, tileEntity.getDungeonsChestType());
   }

   public Material getMaterial(ChestType type, DungeonsChestType dungeonsChestType) {
      switch(type) {
         case LEFT:
            return ChestAtlas.CHEST_LEFT_MATERIALS.getOrDefault(dungeonsChestType, Sheets.CHEST_LOCATION_LEFT);
         case RIGHT:
            return ChestAtlas.CHEST_RIGHT_MATERIALS.getOrDefault(dungeonsChestType, Sheets.CHEST_LOCATION_RIGHT);
         case SINGLE:
         default:
            return ChestAtlas.CHEST_MATERIALS.getOrDefault(dungeonsChestType, Sheets.CHEST_LOCATION);
      }
   }
}
