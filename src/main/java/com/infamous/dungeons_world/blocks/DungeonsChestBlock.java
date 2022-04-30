package com.infamous.dungeons_world.blocks;

import com.infamous.dungeons_world.blockentity.DungeonsChestBlockEntity;
import com.infamous.dungeons_world.blockentity.DungeonsChestType;
import com.infamous.dungeons_world.blockentity.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;

import static com.infamous.dungeons_world.blockentity.ModBlockEntityTypes.CHEST;

public class DungeonsChestBlock extends ChestBlock implements EntityBlock {

    DungeonsChestType dungeonsChestType;

    public DungeonsChestBlock(Properties properties, DungeonsChestType dungeonsChestType) {
        super(properties, CHEST::get);
        this.dungeonsChestType = dungeonsChestType;
    }

    public DungeonsChestType getDungeonsChestType() {
        return dungeonsChestType;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new DungeonsChestBlockEntity(blockPos, blockState, dungeonsChestType);
    }
}