package com.infamous.dungeons_world.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;
import java.util.stream.Collectors;

import static com.infamous.dungeons_world.blocks.GlowingMushroomBlock.MUSHROOMS;
import static com.infamous.dungeons_world.blocks.ModBlocks.FULL_GLOWING_MUSHROOM;

public class GlowingMushroomsFeature extends Feature<MushroomBlockClusterFeatureConfig> {
   public GlowingMushroomsFeature(Codec<MushroomBlockClusterFeatureConfig> p_i231979_1_) {
      super(p_i231979_1_);
   }

   public boolean place(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, MushroomBlockClusterFeatureConfig config) {
      BlockState blockstate = config.stateProvider.getState(random, blockPos);
      BlockPos blockpos;
      if (config.project) {
         blockpos = world.getHeightmapPos(Heightmap.Type.WORLD_SURFACE_WG, blockPos);
      } else {
         blockpos = blockPos;
      }

      if(random.nextFloat() < 0.20){
         BlockPos blockpos1 = blockpos.below();
         BlockState blockstate1 = world.getBlockState(blockpos1);
         if((world.isEmptyBlock(blockpos) || config.canReplace && world.getBlockState(blockpos).getMaterial().isReplaceable())
                 && blockstate.canSurvive(world, blockpos) && (config.whitelist.isEmpty() || config.whitelist.stream().map(provider -> provider.getState(random, blockpos1).getBlock()).collect(Collectors.toList()).contains(blockstate1.getBlock()))
                 && !config.blacklist.contains(blockstate1)
                 && (!config.needWater || world.getFluidState(blockpos1.west()).is(FluidTags.WATER) || world.getFluidState(blockpos1.east()).is(FluidTags.WATER) || world.getFluidState(blockpos1.north()).is(FluidTags.WATER) || world.getFluidState(blockpos1.south()).is(FluidTags.WATER))) {
            config.blockPlacer.place(world, blockpos, config.logStateProvider.getState(random, blockPos), random);
         }
      }

      int i = 0;
      BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

      for(int j = 0; j < config.tries; ++j) {
         blockpos$mutable.setWithOffset(blockpos, random.nextInt(config.xspread + 1) - random.nextInt(config.xspread + 1), random.nextInt(config.yspread + 1) - random.nextInt(config.yspread + 1), random.nextInt(config.zspread + 1) - random.nextInt(config.zspread + 1));
         BlockPos blockpos1 = blockpos$mutable.below();
         BlockState blockstate1 = world.getBlockState(blockpos1);
         BlockState blockstate2 = world.getBlockState(blockpos$mutable);
         if ((world.isEmptyBlock(blockpos$mutable) || config.canReplace && world.getBlockState(blockpos$mutable).getMaterial().isReplaceable())
                 && blockstate.canSurvive(world, blockpos$mutable)
                 && (config.whitelist.isEmpty() || config.whitelist.stream().map(provider -> provider.getState(random, blockpos1).getBlock()).collect(Collectors.toList()).contains(blockstate1.getBlock()))
                 && !config.blacklist.contains(blockstate1)
                 && (!config.needWater || world.getFluidState(blockpos1.west()).is(FluidTags.WATER) || world.getFluidState(blockpos1.east()).is(FluidTags.WATER) || world.getFluidState(blockpos1.north()).is(FluidTags.WATER) || world.getFluidState(blockpos1.south()).is(FluidTags.WATER))) {
            config.blockPlacer.place(world, blockpos$mutable, blockstate, random);
            ++i;
         }else if(blockstate2.is(FULL_GLOWING_MUSHROOM.get()) && blockstate2.getValue(MUSHROOMS) < 4){
            BlockState newBlockState = blockstate2.setValue(MUSHROOMS, blockstate2.getValue(MUSHROOMS) + 1);
            config.blockPlacer.place(world, blockpos$mutable, newBlockState, random);
         }
      }

      return i > 0;
   }
}