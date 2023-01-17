package com.infamous.dungeons_world.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.tags.FluidTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.Random;
import java.util.stream.Collectors;

import static com.infamous.dungeons_world.blocks.GlowingMushroomBlock.MUSHROOMS;
import static com.infamous.dungeons_world.blocks.ModBlocks.FULL_GLOWING_MUSHROOM;

public class GlowingMushroomsFeature extends Feature<MushroomBlockClusterFeatureConfig> {
   public GlowingMushroomsFeature(Codec<MushroomBlockClusterFeatureConfig> p_i231979_1_) {
      super(p_i231979_1_);
   }

   @Override
   public boolean place(FeaturePlaceContext<MushroomBlockClusterFeatureConfig> featurePlaceContext) {
      RandomSource random = featurePlaceContext.random();
      BlockPos blockPos = featurePlaceContext.origin();
      MushroomBlockClusterFeatureConfig config = featurePlaceContext.config();
      WorldGenLevel worldgenlevel = featurePlaceContext.level();
      BlockState blockstate = config.stateProvider.getState(random, blockPos);
      BlockPos blockpos;
      if (config.project) {
         blockpos = worldgenlevel.getHeightmapPos(Heightmap.Types.WORLD_SURFACE_WG, blockPos);
      } else {
         blockpos = blockPos;
      }

      if(random.nextFloat() < 0.20){
         BlockPos blockpos1 = blockpos.below();
         BlockState blockstate1 = worldgenlevel.getBlockState(blockpos1);
         if((worldgenlevel.isEmptyBlock(blockpos) || config.canReplace && worldgenlevel.getBlockState(blockpos).getMaterial().isReplaceable())
                 && blockstate.canSurvive(worldgenlevel, blockpos) && (config.whitelist.isEmpty() || config.whitelist.stream().map(provider -> provider.getState(random, blockpos1).getBlock()).collect(Collectors.toList()).contains(blockstate1.getBlock()))
                 && !config.blacklist.contains(blockstate1)
                 && (!config.needWater || worldgenlevel.getFluidState(blockpos1.west()).is(FluidTags.WATER) || worldgenlevel.getFluidState(blockpos1.east()).is(FluidTags.WATER) || worldgenlevel.getFluidState(blockpos1.north()).is(FluidTags.WATER) || worldgenlevel.getFluidState(blockpos1.south()).is(FluidTags.WATER))) {
            worldgenlevel.setBlock(blockpos, config.logStateProvider.getState(random, blockPos), 2);
         }
      }

      int i = 0;
      BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();

      for(int j = 0; j < config.tries; ++j) {
         blockpos$mutable.setWithOffset(blockpos, random.nextInt(config.xspread + 1) - random.nextInt(config.xspread + 1), random.nextInt(config.yspread + 1) - random.nextInt(config.yspread + 1), random.nextInt(config.zspread + 1) - random.nextInt(config.zspread + 1));
         BlockPos blockpos1 = blockpos$mutable.below();
         BlockState blockstate1 = worldgenlevel.getBlockState(blockpos1);
         BlockState blockstate2 = worldgenlevel.getBlockState(blockpos$mutable);
         if ((worldgenlevel.isEmptyBlock(blockpos$mutable) || config.canReplace && worldgenlevel.getBlockState(blockpos$mutable).getMaterial().isReplaceable())
                 && blockstate.canSurvive(worldgenlevel, blockpos$mutable)
                 && (config.whitelist.isEmpty() || config.whitelist.stream().map(provider -> provider.getState(random, blockpos1).getBlock()).collect(Collectors.toList()).contains(blockstate1.getBlock()))
                 && !config.blacklist.contains(blockstate1)
                 && (!config.needWater || worldgenlevel.getFluidState(blockpos1.west()).is(FluidTags.WATER) || worldgenlevel.getFluidState(blockpos1.east()).is(FluidTags.WATER) || worldgenlevel.getFluidState(blockpos1.north()).is(FluidTags.WATER) || worldgenlevel.getFluidState(blockpos1.south()).is(FluidTags.WATER))) {
            worldgenlevel.setBlock(blockpos$mutable, blockstate, 2);
            ++i;
         }else if(blockstate2.is(FULL_GLOWING_MUSHROOM.get()) && blockstate2.getValue(MUSHROOMS) < 4){
            BlockState newBlockState = blockstate2.setValue(MUSHROOMS, blockstate2.getValue(MUSHROOMS) + 1);
            worldgenlevel.setBlock(blockpos$mutable, newBlockState, 2);
         }
      }

      return i > 0;
   }
}