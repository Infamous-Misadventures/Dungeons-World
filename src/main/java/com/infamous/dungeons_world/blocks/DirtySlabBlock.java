package com.infamous.dungeons_world.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;

public class DirtySlabBlock extends SlabBlock implements Dirty {
    private final DirtLevel dirtLevel;

    public DirtySlabBlock(DirtLevel dirtLevel, Properties settings) {
        super(settings);
        this.dirtLevel = dirtLevel;
    }

    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        this.onRandomTick(state, world, pos, random);
    }

    public boolean hasRandomTicks(BlockState state) {
        return Creepmoss.getIncreasedCreepmossBlock(state.getBlock()).isPresent();
    }

    public DirtLevel getAge() {
        return this.dirtLevel;
    }
}
