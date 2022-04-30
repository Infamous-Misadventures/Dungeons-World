package com.infamous.dungeons_world.blocks;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;

import java.util.Random;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class DirtySlabBlock extends SlabBlock implements Dirty {
    private final DirtLevel dirtLevel;

    public DirtySlabBlock(DirtLevel dirtLevel, Properties settings) {
        super(settings);
        this.dirtLevel = dirtLevel;
    }

    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random random) {
        this.tickDegradation(state, world, pos, random);
    }

    public boolean hasRandomTicks(BlockState state) {
        return Creepmoss.getIncreasedCreepmossBlock(state.getBlock()).isPresent();
    }

    public DirtLevel getDegradationLevel() {
        return this.dirtLevel;
    }
}
