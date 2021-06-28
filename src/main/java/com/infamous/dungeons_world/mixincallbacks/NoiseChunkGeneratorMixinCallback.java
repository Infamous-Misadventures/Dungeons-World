package com.infamous.dungeons_world.mixincallbacks;

import com.infamous.dungeons_world.util.OpenSimplex2F;
import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.provider.BiomeProvider;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Predicate;

import static com.infamous.dungeons_world.DungeonsWorld.MODID;
import static net.minecraft.block.Blocks.AIR;

public class NoiseChunkGeneratorMixinCallback {
    protected static long seed;
    protected static OpenSimplex2F noiseGen;

    public static void setSeed(long seed) {
        if (NoiseChunkGeneratorMixinCallback.seed != seed || noiseGen == null) {
            noiseGen = new OpenSimplex2F(seed);
            NoiseChunkGeneratorMixinCallback.seed = seed;
        }
    }

    public static void dungeons_world_fillNoiseColumn(double[] p_222548_1_, int p_222548_2_, int p_222548_3_, CallbackInfo ci, int seaLevel, long seed, int chunkCountY, BiomeProvider biomeSource) {
        if(biomeSource.getNoiseBiome(p_222548_2_, seaLevel, p_222548_3_).getRegistryName().equals(new ResourceLocation(MODID, "creeper_woods")) && isHighGround(p_222548_2_, p_222548_3_, seed)) {
            for(int i1 = 0; i1 <= chunkCountY; ++i1) {
                p_222548_1_[i1] = p_222548_1_[i1] + 1;
            }
        }
    }

    public static int dungeons_world_iterateNoiseColumn(int x, int z, BlockState[] blockStates, Predicate<BlockState> predicate, int seaLevel, long seed, BiomeProvider biomeSource) {
        if(biomeSource.getNoiseBiome(x >> 2, seaLevel, z >> 2).getRegistryName().equals(new ResourceLocation(MODID, "creeper_woods"))) {
            if(isHighGround(x, z, seed)){
                return 4;
            }else{
                return 0;
            }
        }else{
            return 1;
        }
    }

    public static BlockState dungeons_world_afterGenerateBaseState(double noise, int x, int y, int z, int seaLevel, long seed, BiomeProvider biomeSource, BlockState defaultBlock, BlockState defaultFluid) {
        BlockState blockState = null;
        if(biomeSource.getNoiseBiome(x, seaLevel, z).getRegistryName().equals(new ResourceLocation(MODID, "creeper_woods"))) {
            if (isHighGround(x, z, seed) && noise > -0.2D) {
                blockState = defaultBlock;
            } else if (!isHighGround(x, z, seed) && noise > 0.2D) {
                blockState = defaultBlock;
            } else if (y < seaLevel) {
                blockState = defaultFluid;
            } else {
                blockState = AIR.defaultBlockState();
            }
        }else{
            blockState = vanillaGenerateBaseState(noise, y, seaLevel, defaultBlock, defaultFluid);
        }
        return blockState;
    }

    private static boolean isHighGround(int x, int z, long seed) {
        setSeed(seed);
        double sampleNoise = noiseGen.noise2(x * 0.015D, z * 0.015D);
        if(sampleNoise < 0.3 && sampleNoise > (0.3 * -1)){
            return false;
        }else{
            return  true;
        }
    }

    private static BlockState vanillaGenerateBaseState(double noise, int y, int seaLevel, BlockState defaultBlock, BlockState defaultFluid) {
        BlockState blockstate;
        if (noise > 0.0D) {
            blockstate = defaultBlock;
        } else if (y < seaLevel) {
            blockstate = defaultFluid;
        } else {
            blockstate = AIR.defaultBlockState();
        }

        return blockstate;
    }

}
