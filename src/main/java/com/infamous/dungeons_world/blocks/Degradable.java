package com.infamous.dungeons_world.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Iterator;
import java.util.Optional;
import java.util.Random;

public interface Degradable<T extends Enum<T>> {
    Optional<BlockState> getDegradationResult(BlockState state);

    float getDegradationChanceMultiplier();

    default void tickDegradation(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        float f = 0.05688889F;
        if (random.nextFloat() < f) {
            this.tryDegrade(state, world, pos, random);
        }

    }

    T getDegradationLevel();

    default void tryDegrade(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int i = this.getDegradationLevel().ordinal();
        int j = 0;
        int k = 0;
        Iterator var8 = BlockPos.withinManhattan(pos, 4, 4, 4).iterator();

        while(var8.hasNext()) {
            BlockPos blockPos = (BlockPos)var8.next();
            int l = blockPos.distManhattan(pos);
            if (l > 4) {
                break;
            }

            if (!blockPos.equals(pos)) {
                BlockState blockState = world.getBlockState(blockPos);
                Block block = blockState.getBlock();
                if (block instanceof Degradable) {
                    Enum<?> enum_ = ((Degradable)block).getDegradationLevel();
                    if (this.getDegradationLevel().getClass() == enum_.getClass()) {
                        int m = enum_.ordinal();
                        if (m < i) {
                            return;
                        }

                        if (m > i) {
                            ++k;
                        } else {
                            ++j;
                        }
                    }
                }
            }
        }

        float f = (float)(k + 1) / (float)(k + j + 1);
        float g = f * f * this.getDegradationChanceMultiplier();
        if (random.nextFloat() < g) {
            this.getDegradationResult(state).ifPresent((statex) -> {
                world.setBlockAndUpdate(pos, statex);
            });
        }

    }
}
