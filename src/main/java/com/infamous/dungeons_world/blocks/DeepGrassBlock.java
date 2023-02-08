package com.infamous.dungeons_world.blocks;

import com.infamous.dungeons_world.init.BlocksInit;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.tags.FluidTags;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.lighting.LayerLightEngine;
import net.minecraft.server.level.ServerLevel;

public class DeepGrassBlock extends GrassBlock {
    public DeepGrassBlock(Properties p_i48388_1_) {
        super(p_i48388_1_);
    }

    private static boolean canBeGrass(BlockState p_220257_0_, LevelReader p_220257_1_, BlockPos p_220257_2_) {
        BlockPos blockpos = p_220257_2_.above();
        BlockState blockstate = p_220257_1_.getBlockState(blockpos);
        if (blockstate.is(Blocks.SNOW) && blockstate.getValue(SnowLayerBlock.LAYERS) == 1) {
            return true;
        } else if (blockstate.getFluidState().getAmount() == 8) {
            return false;
        } else {
            int i = LayerLightEngine.getLightBlockInto(p_220257_1_, p_220257_0_, p_220257_2_, blockstate, blockpos, Direction.UP, blockstate.getLightBlock(p_220257_1_, blockpos));
            return i < p_220257_1_.getMaxLightLevel();
        }
    }

    private static boolean canPropagate(BlockState p_220256_0_, LevelReader p_220256_1_, BlockPos p_220256_2_) {
        BlockPos blockpos = p_220256_2_.above();
        return canBeGrass(p_220256_0_, p_220256_1_, p_220256_2_) && !p_220256_1_.getFluidState(blockpos).is(FluidTags.WATER);
    }

    @Override
    public void randomTick(BlockState p_225542_1_, ServerLevel p_225542_2_, BlockPos p_225542_3_, RandomSource p_225542_4_) {
        if (!canBeGrass(p_225542_1_, p_225542_2_, p_225542_3_)) {
            if (!p_225542_2_.isAreaLoaded(p_225542_3_, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            p_225542_2_.setBlockAndUpdate(p_225542_3_, BlocksInit.DEEP_DIRT.get().defaultBlockState());
        } else {
            if (p_225542_2_.getMaxLocalRawBrightness(p_225542_3_.above()) >= 9) {
                BlockState blockstate = this.defaultBlockState();

                for(int i = 0; i < 4; ++i) {
                    BlockPos blockpos = p_225542_3_.offset(p_225542_4_.nextInt(3) - 1, p_225542_4_.nextInt(5) - 3, p_225542_4_.nextInt(3) - 1);
                    if (p_225542_2_.getBlockState(blockpos).is(BlocksInit.DEEP_DIRT.get()) && canPropagate(blockstate, p_225542_2_, blockpos)) {
                        p_225542_2_.setBlockAndUpdate(blockpos, blockstate.setValue(SNOWY, Boolean.valueOf(p_225542_2_.getBlockState(blockpos.above()).is(Blocks.SNOW))));
                    }
                }
            }

        }
    }
}
