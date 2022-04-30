package com.infamous.dungeons_world.blocks;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;

import java.util.Random;

public class CreepmossBlock extends Block implements Creepmoss {
    private final Creepmoss.CreepmossLevel creepmossLevel;

    public CreepmossBlock(Creepmoss.CreepmossLevel creepmossLevel, BlockBehaviour.Properties settings) {
        super(settings.randomTicks());
        this.creepmossLevel = creepmossLevel;
    }

    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random random) {
        this.tickDegradation(state, world, pos, random);
    }

    public boolean hasRandomTicks(BlockState state) {
        return Creepmoss.getIncreasedCreepmossBlock(state.getBlock()).isPresent();
    }

    public Creepmoss.CreepmossLevel getDegradationLevel() {
        return this.creepmossLevel;
    }
}