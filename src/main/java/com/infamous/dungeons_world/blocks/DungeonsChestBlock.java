package com.infamous.dungeons_world.blocks;

import com.infamous.dungeons_world.tileentity.DungeonsChestTileEntity;
import com.infamous.dungeons_world.tileentity.DungeonsChestType;
import com.infamous.dungeons_world.tileentity.ModTileEntityTypes;
import net.minecraft.block.ChestBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class DungeonsChestBlock extends ChestBlock {

    DungeonsChestType dungeonsChestType;

    public DungeonsChestBlock(Properties properties, DungeonsChestType dungeonsChestType) {
        super(properties, ModTileEntityTypes.CHEST::get);
        this.dungeonsChestType = dungeonsChestType;
    }

    @Override
    public TileEntity newBlockEntity(IBlockReader reader) {
        return new DungeonsChestTileEntity(dungeonsChestType);
    }
}