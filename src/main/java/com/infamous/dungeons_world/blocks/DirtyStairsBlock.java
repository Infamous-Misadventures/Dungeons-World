package com.infamous.dungeons_world.blocks;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;

import java.util.Random;
import java.util.function.Supplier;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class DirtyStairsBlock extends StairBlock implements Dirty {
    private final DirtLevel dirtLevel;

    public DirtyStairsBlock(DirtLevel dirtLevel, Supplier<BlockState> baseBlockState, Properties settings) {
        super(baseBlockState, settings);
        this.dirtLevel = dirtLevel;
    }

    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random random) {
        this.tickDegradation(state, world, pos, random);
    }

    public boolean hasRandomTicks(BlockState state) {
        return Dirty.getIncreasedDirtyBlock(state.getBlock()).isPresent();
    }

    public DirtLevel getDegradationLevel() {
        return this.dirtLevel;
    }
}
