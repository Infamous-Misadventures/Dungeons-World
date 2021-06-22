package com.infamous.dungeons_world.mixin;

import com.infamous.dungeons_world.DungeonsWorld;
import net.minecraft.world.gen.NoiseChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NoiseChunkGenerator.class)
public class NoiseChunkGeneratorTestMixin {

    @Inject(at = @At("TAIL"), method = "Lnet/minecraft/world/gen/NoiseChunkGenerator;fillNoiseColumn([DII)V", cancellable = false)
    public void dungeons_world_fillNoiseColumn(double[] p_222548_1_, int p_222548_2_, int p_222548_3_, CallbackInfo ci) {
        DungeonsWorld.LOGGER.info("Mixed In");
    }

}
