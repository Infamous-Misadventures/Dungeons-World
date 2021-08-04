package com.infamous.dungeons_world.world.surfacebuilder;

import com.infamous.dungeons_world.util.OpenSimplex2F;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.surfacebuilders.DefaultSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;

import static net.minecraft.block.Blocks.AIR;

public class CreeperWoodsSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {
    public CreeperWoodsSurfaceBuilder(Codec<SurfaceBuilderConfig> codec) {
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
        int groundLevel = chunkIn.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, x, z);

        double sampleNoise = noiseGen.noise2(x * 0.015D, z * 0.015D);
        if(sampleNoise < 0.3 && sampleNoise > (0.3 * -1)){
            int topHeight = startHeight - 2;
            mutable.move(Direction.UP, startHeight);
            for (int yPos = startHeight; yPos >= 0; --yPos) {
                if (yPos > topHeight) {
                    chunkIn.setBlockState(mutable, AIR.defaultBlockState(), false);
                } else if (yPos == topHeight){
                    chunkIn.setBlockState(mutable, config.getTopMaterial(), false);
                }else if (yPos > topHeight - 4)
                    chunkIn.setBlockState(mutable, config.getUnderMaterial(), false);
                else
                    chunkIn.setBlockState(mutable, defaultBlock, false);
                mutable.move(Direction.DOWN);
            }
        }else{
            int topHeight = startHeight + 2;
            mutable.move(Direction.UP, topHeight);
            for (int yPos = topHeight; yPos >= 0; --yPos) {
                if (yPos == topHeight){
                    chunkIn.setBlockState(mutable, config.getTopMaterial(), false);
                } else if (yPos > topHeight - 4) {
                    chunkIn.setBlockState(mutable, config.getUnderMaterial(), false);
                } else {
                    chunkIn.setBlockState(mutable, defaultBlock, false);
                }
                mutable.move(Direction.DOWN);
            }
        }
    }

}
