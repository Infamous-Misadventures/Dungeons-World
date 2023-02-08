package com.infamous.dungeons_world.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class CreepmossSlabBlock extends SlabBlock implements Creepmoss {
    private final Creepmoss.CreepmossLevel creepmossLevel;

    public CreepmossSlabBlock(Creepmoss.CreepmossLevel creepmossLevel, BlockBehaviour.Properties settings) {
        super(settings);
        this.creepmossLevel = creepmossLevel;
    }

    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        this.onRandomTick(state, world, pos, random);
    }

    public boolean hasRandomTicks(BlockState state) {
        return Creepmoss.getIncreasedCreepmossBlock(state.getBlock()).isPresent();
    }

    public Creepmoss.CreepmossLevel getAge() {
        return this.creepmossLevel;
    }
}
