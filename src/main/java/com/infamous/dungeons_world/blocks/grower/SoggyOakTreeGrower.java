package com.infamous.dungeons_world.blocks.grower;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static com.infamous.dungeons_world.Util.modLoc;
import static com.infamous.dungeons_world.blocks.WaterloggedSaplingBlock.WATERLOGGED;
import static net.minecraft.data.worldgen.features.TreeFeatures.OAK;

public class SoggyOakTreeGrower  extends AbstractTreeGrower {
    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource p_222910_, boolean p_222911_) {
        return null;
    }

    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(ServerLevel level, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, RandomSource random, boolean hasFlowers) {
        if(state.getValue(WATERLOGGED)){
            Optional<Holder<ConfiguredFeature<?, ?>>> optional = level.registryAccess().registryOrThrow(Registry.CONFIGURED_FEATURE_REGISTRY).getHolder(
                    ResourceKey.create(Registry.CONFIGURED_FEATURE_REGISTRY, modLoc("soggy_oak")));
            return optional.orElse(null);
        }else{
            return OAK;
        }
    }
}
