package com.infamous.dungeons_world.world.gen.feature;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MushroomBlockClusterFeatureConfig implements FeatureConfiguration {
   public static final Codec<MushroomBlockClusterFeatureConfig> CODEC = RecordCodecBuilder.create(builder -> builder.group(
           BlockStateProvider.CODEC.fieldOf("state_provider").forGetter(config -> config.stateProvider),
           BlockStateProvider.CODEC.fieldOf("log_state_provider").forGetter(config -> config.logStateProvider),
           BlockStateProvider.CODEC.listOf().fieldOf("whitelist").forGetter(config -> new ArrayList<>(config.whitelist)),
           BlockState.CODEC.listOf().fieldOf("blacklist").forGetter(config -> ImmutableList.copyOf(config.blacklist)),
           Codec.INT.fieldOf("tries").orElse(128).forGetter(config -> config.tries),
           Codec.INT.fieldOf("xspread").orElse(7).forGetter(config -> config.xspread),
           Codec.INT.fieldOf("yspread").orElse(3).forGetter(config -> config.yspread),
           Codec.INT.fieldOf("zspread").orElse(7).forGetter(config -> config.zspread),
           Codec.BOOL.fieldOf("can_replace").orElse(false).forGetter(config -> config.canReplace),
           Codec.BOOL.fieldOf("project").orElse(true).forGetter(config -> config.project),
           Codec.BOOL.fieldOf("need_water").orElse(false).forGetter(config -> config.needWater))
           .apply(builder, MushroomBlockClusterFeatureConfig::new));
   public final BlockStateProvider stateProvider;
   public final BlockStateProvider logStateProvider;
   public final Set<BlockStateProvider> whitelist;
   public final Set<BlockState> blacklist;
   public final int tries;
   public final int xspread;
   public final int yspread;
   public final int zspread;
   public final boolean canReplace;
   public final boolean project;
   public final boolean needWater;

   private MushroomBlockClusterFeatureConfig(BlockStateProvider p_i232014_1_, BlockStateProvider logStateProvider, List<BlockStateProvider> p_i232014_3_, List<BlockState> p_i232014_4_, int p_i232014_5_, int p_i232014_6_, int p_i232014_7_, int p_i232014_8_, boolean p_i232014_9_, boolean p_i232014_10_, boolean p_i232014_11_) {
      this(p_i232014_1_, logStateProvider, new HashSet<>(p_i232014_3_), ImmutableSet.copyOf(p_i232014_4_), p_i232014_5_, p_i232014_6_, p_i232014_7_, p_i232014_8_, p_i232014_9_, p_i232014_10_, p_i232014_11_);
   }

   public MushroomBlockClusterFeatureConfig(BlockStateProvider stateProvider, BlockStateProvider logStateProvider, Set<BlockStateProvider> whitelist, Set<BlockState> blacklist, int tries, int xspread, int yspread, int zspread, boolean canReplace, boolean project, boolean needWater) {
      this.stateProvider = stateProvider;
      this.logStateProvider = logStateProvider;
      this.whitelist = whitelist;
      this.blacklist = blacklist;
      this.tries = tries;
      this.xspread = xspread;
      this.yspread = yspread;
      this.zspread = zspread;
      this.canReplace = canReplace;
      this.project = project;
      this.needWater = needWater;
   }

   public static class Builder {
      private final BlockStateProvider stateProvider;
      private BlockStateProvider logStateProvider;
      private Set<BlockStateProvider> whitelist = ImmutableSet.of();
      private Set<BlockState> blacklist = ImmutableSet.of();
      private int tries = 64;
      private int xspread = 7;
      private int yspread = 3;
      private int zspread = 7;
      private boolean canReplace;
      private boolean project = true;
      private boolean needWater = false;

      public Builder(BlockStateProvider p_i225838_1_) {
         this.stateProvider = p_i225838_1_;
      }

      public MushroomBlockClusterFeatureConfig.Builder logStateProvider(BlockStateProvider p_i225838_1_) {
         this.logStateProvider = p_i225838_1_;
         return this;
      }

      public MushroomBlockClusterFeatureConfig.Builder whitelist(Set<BlockStateProvider> p_227316_1_) {
         this.whitelist = p_227316_1_;
         return this;
      }

      public MushroomBlockClusterFeatureConfig.Builder blacklist(Set<BlockState> p_227319_1_) {
         this.blacklist = p_227319_1_;
         return this;
      }

      public MushroomBlockClusterFeatureConfig.Builder tries(int p_227315_1_) {
         this.tries = p_227315_1_;
         return this;
      }

      public MushroomBlockClusterFeatureConfig.Builder xspread(int p_227318_1_) {
         this.xspread = p_227318_1_;
         return this;
      }

      public MushroomBlockClusterFeatureConfig.Builder yspread(int p_227321_1_) {
         this.yspread = p_227321_1_;
         return this;
      }

      public MushroomBlockClusterFeatureConfig.Builder zspread(int p_227323_1_) {
         this.zspread = p_227323_1_;
         return this;
      }

      public MushroomBlockClusterFeatureConfig.Builder canReplace() {
         this.canReplace = true;
         return this;
      }

      public MushroomBlockClusterFeatureConfig.Builder noProjection() {
         this.project = false;
         return this;
      }

      public MushroomBlockClusterFeatureConfig.Builder needWater() {
         this.needWater = true;
         return this;
      }

      public MushroomBlockClusterFeatureConfig build() {
         return new MushroomBlockClusterFeatureConfig(this.stateProvider, this.logStateProvider, this.whitelist, this.blacklist, this.tries, this.xspread, this.yspread, this.zspread, this.canReplace, this.project, this.needWater);
      }
   }
}