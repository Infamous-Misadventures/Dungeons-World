package com.infamous.dungeons_world.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;
import java.util.function.Supplier;

public class DirtyStairsBlock extends StairsBlock implements Dirty {
    private final DirtLevel dirtLevel;

    public DirtyStairsBlock(DirtLevel dirtLevel, Supplier<BlockState> baseBlockState, Properties settings) {
        super(baseBlockState, settings);
        this.dirtLevel = dirtLevel;
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        this.tickDegradation(state, world, pos, random);
    }

    public boolean hasRandomTicks(BlockState state) {
        return Dirty.getIncreasedDirtyBlock(state.getBlock()).isPresent();
    }

    public DirtLevel getDegradationLevel() {
        return this.dirtLevel;
    }
}
