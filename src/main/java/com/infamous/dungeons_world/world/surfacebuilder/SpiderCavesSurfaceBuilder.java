package com.infamous.dungeons_world.world.surfacebuilder;

import com.infamous.dungeons_world.util.OpenSimplex2F;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;

import static com.infamous.dungeons_world.biomes.ModBiomes.SPIDER_CAVES;
import static com.infamous.dungeons_world.blocks.ModBlocks.*;
import static net.minecraft.block.Blocks.AIR;

public class SpiderCavesSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {
    public SpiderCavesSurfaceBuilder(Codec<SurfaceBuilderConfig> codec) {
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


    public void apply(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
        //setSeed(((ISeedReader) chunkIn.getWorldForge()).getSeed());
        setSeed(seed);

        BlockPos.Mutable mutable = new BlockPos.Mutable(x, 0, z);
        mutable.move(Direction.UP, startHeight);
        for (int yPos = startHeight; yPos >= 0; --yPos) {
            if(chunkIn.getWorldForge().getBiome(mutable) == SPIDER_CAVES.get()) {
                if(!chunkIn.getBlockState(mutable).isAir()) {
                    chunkIn.setBlockState(mutable, config.getTopMaterial(), false);
                }
            }
            mutable.move(Direction.DOWN);
        }
    }
}
