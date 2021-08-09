package com.infamous.dungeons_world.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class DirtySlabBlock extends SlabBlock implements Dirty {
    private final DirtLevel dirtLevel;

    public DirtySlabBlock(DirtLevel dirtLevel, Properties settings) {
        super(settings);
        this.dirtLevel = dirtLevel;
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        this.tickDegradation(state, world, pos, random);
    }

    public boolean hasRandomTicks(BlockState state) {
        return Creepmoss.getIncreasedCreepmossBlock(state.getBlock()).isPresent();
    }

    public DirtLevel getDegradationLevel() {
        return this.dirtLevel;
    }
}
