package com.infamous.dungeons_world.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class CreepmossSlabBlock extends SlabBlock implements Creepmoss {
    private final Creepmoss.CreepmossLevel creepmossLevel;

    public CreepmossSlabBlock(Creepmoss.CreepmossLevel creepmossLevel, AbstractBlock.Properties settings) {
        super(settings);
        this.creepmossLevel = creepmossLevel;
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        this.tickDegradation(state, world, pos, random);
    }

    public boolean hasRandomTicks(BlockState state) {
        return Creepmoss.getIncreasedCreepmossBlock(state.getBlock()).isPresent();
    }

    public Creepmoss.CreepmossLevel getDegradationLevel() {
        return this.creepmossLevel;
    }
}
