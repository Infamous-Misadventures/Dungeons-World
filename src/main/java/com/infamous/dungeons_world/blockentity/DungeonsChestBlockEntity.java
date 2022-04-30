package com.infamous.dungeons_world.blockentity;

import com.infamous.dungeons_world.blocks.DungeonsChestBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class DungeonsChestBlockEntity extends ChestBlockEntity {
    protected DungeonsChestType dungeonsChestType;

    public DungeonsChestBlockEntity(BlockPos p_155331_, BlockState blockState) {
        super(ModBlockEntityTypes.CHEST.get(), p_155331_, blockState);
        this.dungeonsChestType = ((DungeonsChestBlock)blockState.getBlock()).getDungeonsChestType();
    }

    public DungeonsChestBlockEntity(BlockPos p_155331_, BlockState p_155332_, DungeonsChestType dungeonsChestType) {
        super(p_155331_, p_155332_);
        this.dungeonsChestType = dungeonsChestType;
    }

    public DungeonsChestType getDungeonsChestType() {
        return dungeonsChestType;
    }

    @Override
    public BlockEntityType<?> getType() {
        return ModBlockEntityTypes.CHEST.get();
    }
}