package com.infamous.dungeons_world.client.renderer.tileentity.itemstackrenderer;

import com.infamous.dungeons_world.tileentity.DungeonsChestTileEntity;
import com.infamous.dungeons_world.tileentity.DungeonsChestType;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;

import java.util.function.Supplier;

public class ModISTERs {

    public static ItemStackTileEntityRenderer createChestTileEntity(DungeonsChestType chestType) {
        return  new DungeonsChestISTER<>(getChestTEForWoodType(chestType));
    }

    protected static Supplier<DungeonsChestTileEntity> getChestTEForWoodType(DungeonsChestType woodType) {
        return () -> new DungeonsChestTileEntity(woodType);
    }
}
