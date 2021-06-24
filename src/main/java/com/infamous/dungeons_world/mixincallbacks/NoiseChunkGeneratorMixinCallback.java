package com.infamous.dungeons_world.mixincallbacks;

import com.infamous.dungeons_world.util.OpenSimplex2F;
import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.provider.BiomeProvider;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Predicate;

import static com.infamous.dungeons_world.DungeonsWorld.MODID;

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

    private static boolean isHighGround(int x, int z, long seed) {
        setSeed(seed);
        double sampleNoise = noiseGen.noise2(x * 0.015D, z * 0.015D);
        if(sampleNoise < 0.3 && sampleNoise > (0.3 * -1)){
            return false;
        }else{
            return  true;
        }
    }

}
