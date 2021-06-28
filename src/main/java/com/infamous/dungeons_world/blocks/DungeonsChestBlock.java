package com.infamous.dungeons_world.blocks;

import com.infamous.dungeons_world.tileentity.DungeonsChestTileEntity;
import com.infamous.dungeons_world.tileentity.DungeonsChestType;
import com.infamous.dungeons_world.tileentity.ModTileEntityTypes;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.WoodType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class DungeonsChestBlock extends ChestBlock {

    DungeonsChestType chestType;

    public DungeonsChestBlock(Properties properties, DungeonsChestType chestType) {
        super(properties, ModTileEntityTypes.CHEST::get);
        this.chestType = chestType;
    }

    @Override
    public TileEntity newBlockEntity(IBlockReader reader) {
        DungeonsChestTileEntity te = new DungeonsChestTileEntity();
        te.setRenderMaterial(chestType);
        return te;
    }
}