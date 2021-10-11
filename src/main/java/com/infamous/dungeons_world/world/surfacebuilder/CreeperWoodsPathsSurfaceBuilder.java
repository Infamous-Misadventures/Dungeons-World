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

import static com.infamous.dungeons_world.blocks.ModBlocks.*;
import static net.minecraft.block.Blocks.AIR;

public class CreeperWoodsPathsSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {
    public CreeperWoodsPathsSurfaceBuilder(Codec<SurfaceBuilderConfig> codec) {
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
    }
}
