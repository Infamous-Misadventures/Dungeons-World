package com.infamous.dungeons_world.mixin;

import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.NoiseChunkGenerator;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NoiseChunkGenerator.class)
public abstract class NoiseChunkGeneratorMixin extends ChunkGenerator {

    @Final
    @Shadow
    private int chunkCountY;

    @Final
    @Shadow
    private long seed;

    @Shadow
    public abstract int getSeaLevel();

    public NoiseChunkGeneratorMixin(BiomeProvider p_i231888_1_, DimensionStructuresSettings p_i231888_2_) {
        super(p_i231888_1_, p_i231888_2_);
    }

    public NoiseChunkGeneratorMixin(BiomeProvider p_i231887_1_, BiomeProvider p_i231887_2_, DimensionStructuresSettings p_i231887_3_, long p_i231887_4_) {
        super(p_i231887_1_, p_i231887_2_, p_i231887_3_, p_i231887_4_);
    }

    @Inject(at = @At("TAIL"), method = "Lnet/minecraft/world/gen/NoiseChunkGenerator;fillNoiseColumn([DII)V", cancellable = true)
    public void dungeons_world_fillNoiseColumn(double[] p_222548_1_, int p_222548_2_, int p_222548_3_, CallbackInfo ci) {
        NoiseChunkGeneratorMixinCallback.dungeons_world_fillNoiseColumn(p_222548_1_, p_222548_2_, p_222548_3_, ci, this.getSeaLevel(), this.seed, this.chunkCountY, this.biomeSource);
    }

}
