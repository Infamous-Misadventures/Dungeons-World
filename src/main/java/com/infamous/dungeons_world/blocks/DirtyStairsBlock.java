package com.infamous.dungeons_world.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class DirtyStairsBlock extends StairBlock implements Dirty {
    private final DirtLevel dirtLevel;

    public DirtyStairsBlock(DirtLevel dirtLevel, Supplier<BlockState> baseBlockState, Properties settings) {
        super(baseBlockState, settings);
        this.dirtLevel = dirtLevel;
    }

    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        this.onRandomTick(state, world, pos, random);
    }

    public boolean hasRandomTicks(BlockState state) {
        return Dirty.getIncreasedDirtyBlock(state.getBlock()).isPresent();
    }

    public DirtLevel getAge() {
        return this.dirtLevel;
    }
}
