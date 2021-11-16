package com.infamous.dungeons_world.tileentity;

import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class DungeonsChestTileEntity extends ChestTileEntity {
    DungeonsChestType dungeonsChestType;

    public DungeonsChestTileEntity() {
        this(ModTileEntityTypes.CHEST.get());
    }

    public DungeonsChestTileEntity(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
    }

    public DungeonsChestTileEntity(DungeonsChestType dungeonsChestType) {
        this(ModTileEntityTypes.CHEST.get());
        this.dungeonsChestType = dungeonsChestType;
    }

    public DungeonsChestType getDungeonsChestType() {
        return dungeonsChestType;
    }

    @Override
    public TileEntityType<?> getType() {
        return ModTileEntityTypes.CHEST.get();
    }
}