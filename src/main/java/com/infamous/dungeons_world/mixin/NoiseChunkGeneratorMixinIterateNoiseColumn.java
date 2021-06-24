package com.infamous.dungeons_world.mixin;

import com.infamous.dungeons_world.mixincallbacks.NoiseChunkGeneratorMixinCallback;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.NoiseChunkGenerator;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

@Mixin(NoiseChunkGenerator.class)
public abstract class NoiseChunkGeneratorMixinIterateNoiseColumn extends ChunkGenerator {

    @Final
    @Shadow
    private int chunkCountY;

    @Final
    @Shadow
    private long seed;

    @Shadow
    public abstract int getSeaLevel();

    public NoiseChunkGeneratorMixinIterateNoiseColumn(BiomeProvider p_i231888_1_, DimensionStructuresSettings p_i231888_2_) {
        super(p_i231888_1_, p_i231888_2_);
    }

    public NoiseChunkGeneratorMixinIterateNoiseColumn(BiomeProvider p_i231887_1_, BiomeProvider p_i231887_2_, DimensionStructuresSettings p_i231887_3_, long p_i231887_4_) {
        super(p_i231887_1_, p_i231887_2_, p_i231887_3_, p_i231887_4_);
    }

    @ModifyConstant(method = "Lnet/minecraft/world/gen/NoiseChunkGenerator;iterateNoiseColumn(II[Lnet/minecraft/block/BlockState;Ljava/util/function/Predicate;)I", constant = @Constant(intValue = 1, ordinal = 13))
    public int dungeons_world_iterateNoiseColumn(int original, int p_236087_1_, int p_236087_2_, BlockState[] p_236087_3_, Predicate<BlockState> p_236087_4_) {
        return NoiseChunkGeneratorMixinCallback.dungeons_world_iterateNoiseColumn(p_236087_1_, p_236087_2_, p_236087_3_, p_236087_4_, this.getSeaLevel(), this.seed, this.biomeSource);
    }

}
