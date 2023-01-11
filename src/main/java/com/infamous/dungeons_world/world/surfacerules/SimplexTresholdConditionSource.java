package com.infamous.dungeons_world.world.surfacerules;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import mod.patrigan.structure_toolkit.util.OpenSimplex2F;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.SurfaceRules;

public record SimplexTresholdConditionSource(double xMultiplier, double zMultiplier, double minThreshold, double maxThreshold, Long seed) implements SurfaceRules.ConditionSource {
    static final Codec<SimplexTresholdConditionSource> CODEC = RecordCodecBuilder.create((builder) -> builder.group(
            Codec.DOUBLE.fieldOf("x_multiplier").forGetter(SimplexTresholdConditionSource::xMultiplier),
            Codec.DOUBLE.fieldOf("z_multiplier").forGetter(SimplexTresholdConditionSource::zMultiplier),
            Codec.DOUBLE.fieldOf("min_threshold").forGetter(SimplexTresholdConditionSource::minThreshold),
            Codec.DOUBLE.fieldOf("max_threshold").forGetter(SimplexTresholdConditionSource::maxThreshold),
            Codec.LONG.fieldOf("seed").forGetter(SimplexTresholdConditionSource::seed)
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
                    setSeed(worldGenLevel.getSeed() + SimplexTresholdConditionSource.this.seed);
                }else{
                    setSeed(532148654248L + SimplexTresholdConditionSource.this.seed);
                }
                double d0 = noiseGen.noise2(context.blockX * SimplexTresholdConditionSource.this.xMultiplier, context.blockZ * SimplexTresholdConditionSource.this.zMultiplier);
                return d0 >= SimplexTresholdConditionSource.this.minThreshold && d0 <= SimplexTresholdConditionSource.this.maxThreshold;
            }
        }

        return new SimplexThresholdCondition();
    }
}
