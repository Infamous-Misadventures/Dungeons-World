package com.infamous.dungeons_world.world.surfacerules;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import mod.patrigan.structure_toolkit.util.OpenSimplex2F;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.SurfaceRules;

public record SimplexTresholdConditionSource(double xMultiplier, double zMultiplier, double minThreshold, double maxThreshold) implements SurfaceRules.ConditionSource {
    static final Codec<SimplexTresholdConditionSource> CODEC = RecordCodecBuilder.create((builder) -> builder.group(
            Codec.DOUBLE.fieldOf("x_multiplier").forGetter(SimplexTresholdConditionSource::xMultiplier),
            Codec.DOUBLE.fieldOf("z_multiplier").forGetter(SimplexTresholdConditionSource::zMultiplier),
            Codec.DOUBLE.fieldOf("min_threshold").forGetter(SimplexTresholdConditionSource::minThreshold),
            Codec.DOUBLE.fieldOf("max_threshold").forGetter(SimplexTresholdConditionSource::maxThreshold)
    ).apply(builder, SimplexTresholdConditionSource::new));

    @Override
    public Codec<? extends SurfaceRules.ConditionSource> codec() {
        return CODEC;
    }

    public SurfaceRules.Condition apply(final SurfaceRules.Context appliedContext) {

        class SimplexThresholdCondition extends SurfaceRules.LazyXZCondition {
            protected static OpenSimplex2F noiseGen;
            protected long seed;

            public void setSeed(long seed) {
                if (this.seed != seed || noiseGen == null) {
                    noiseGen = new OpenSimplex2F(seed);
                    this.seed = seed;
                }
            }

            protected SimplexThresholdCondition() {
                super(appliedContext);
            }

            protected boolean compute() {
                LevelAccessor level = context.chunk.getWorldForge();
                if(level instanceof WorldGenLevel worldGenLevel) {
                    setSeed(worldGenLevel.getSeed());
                }else{
                    setSeed(532148654248L);
                }
                double d0 = noiseGen.noise2(context.blockX * SimplexTresholdConditionSource.this.xMultiplier, context.blockZ * SimplexTresholdConditionSource.this.zMultiplier);
                return d0 >= SimplexTresholdConditionSource.this.minThreshold && d0 <= SimplexTresholdConditionSource.this.maxThreshold;
            }
        }

        return new SimplexThresholdCondition();
    }


    /*extends SurfaceBuilder<SurfaceBuilderBaseConfiguration> {
    public CreeperWoodsPathsSurfaceBuilder(Codec<SurfaceBuilderBaseConfiguration> codec) {
        super(codec);
    }

    protected long seed;
    protected static OpenSimplex2F noiseGen;

    public void setSeed(long seed) {
        if (this.seed != seed || noiseGen == null) {
            noiseGen = new OpenSimplex2F(seed);
            this.seed = seed;
        }
    }


    public void apply(Random random, ChunkAccess chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderBaseConfiguration config) {
        //setSeed(((ISeedReader) chunkIn.getWorldForge()).getSeed());
        setSeed(seed);

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos(x, 0, z);

        double sampleNoise = noiseGen.noise2(x * 0.015D, z * 0.015D);
        BlockState topBlock;
        if(sampleNoise < 0.09 && sampleNoise > (0.09 * -1)){
            topBlock = DEEP_DIRT_PATH.get().defaultBlockState();
        } else if(sampleNoise < 0.13 && sampleNoise > (0.13 * -1)) {
            topBlock = DEEP_GRASSY_DIRT.get().defaultBlockState();
        } else if(sampleNoise < 0.22 && sampleNoise > (0.22 * -1)) {
            topBlock = DEEP_DIRTY_GRASS.get().defaultBlockState();
        } else {
            topBlock = config.getTopMaterial();
        }
        mutable.move(Direction.UP, startHeight);
        for (int yPos = startHeight; yPos >= 0; --yPos) {
            if (yPos >= startHeight) {
                chunkIn.setBlockState(mutable, AIR.defaultBlockState(), false);
            } else if (yPos == startHeight -1){
                chunkIn.setBlockState(mutable, topBlock, false);
            }else if (yPos > startHeight - 5)
                chunkIn.setBlockState(mutable, config.getUnderMaterial(), false);
            else
                chunkIn.setBlockState(mutable, defaultBlock, false);
            mutable.move(Direction.DOWN);
        }
    }*/
}
