package com.infamous.dungeons_world.mixin;

import com.infamous.dungeons_world.mixincallbacks.NoiseChunkGeneratorMixinCallback;
import net.minecraft.block.BlockState;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.NoiseChunkGenerator;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NoiseChunkGenerator.class)
public abstract class NoiseChunkGeneratorMixin extends ChunkGenerator {

    @Final
    @Shadow
    private long seed;

    @Final
    @Shadow
    protected BlockState defaultBlock;

    @Final
    @Shadow
    protected BlockState defaultFluid;

    @Shadow
    public abstract int getSeaLevel();

    public NoiseChunkGeneratorMixin(BiomeProvider p_i231888_1_, DimensionStructuresSettings p_i231888_2_) {
        super(p_i231888_1_, p_i231888_2_);
    }

    public NoiseChunkGeneratorMixin(BiomeProvider p_i231887_1_, BiomeProvider p_i231887_2_, DimensionStructuresSettings p_i231887_3_, long p_i231887_4_) {
        super(p_i231887_1_, p_i231887_2_, p_i231887_3_, p_i231887_4_);
    }

    /*@Redirect(method = "Lnet/minecraft/world/gen/NoiseChunkGenerator;fillFromNoise(Lnet/minecraft/world/IWorld;Lnet/minecraft/world/gen/feature/structure/StructureManager;Lnet/minecraft/world/chunk/IChunk;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/NoiseChunkGenerator;generateBaseState(DI)Lnet/minecraft/block/BlockState;"))
    public BlockState dungeons_world_fillFromNoise() {
        //return NoiseChunkGeneratorMixinCallback.dungeons_world_afterGenerateBaseState(d18, i3, i2, l3, this.getSeaLevel(), this.seed, this.biomeSource, defaultBlock, defaultFluid);
    }*/

}
