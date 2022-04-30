package com.infamous.dungeons_world.client.renderer.color;

import com.infamous.dungeons_world.DungeonsWorld;
import com.infamous.dungeons_world.blocks.ModBlocks;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.world.level.GrassColor;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.infamous.dungeons_world.blocks.ModBlocks.GRASSY_COARSE_DEEP_DIRT;
import static com.infamous.dungeons_world.blocks.ModBlocks.GRASSY_COARSE_DEEP_DIRT_PATH;

@Mod.EventBusSubscriber(modid = DungeonsWorld.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModBlockColors {
    @SubscribeEvent
    public static void init(ColorHandlerEvent.Block event){
        BlockColors blockColors = event.getBlockColors();
        blockColors.register((p_228064_0_, p_228064_1_, p_228064_2_, p_228064_3_) -> {
            return p_228064_1_ != null && p_228064_2_ != null ? BiomeColors.getAverageGrassColor(p_228064_1_, p_228064_2_) : GrassColor.get(0.5D, 1.0D);
        }, ModBlocks.DEEP_GRASS_BLOCK.get(), ModBlocks.DEEP_GRASSY_DIRT.get(), ModBlocks.DEEP_DIRTY_GRASS.get(), GRASSY_COARSE_DEEP_DIRT.get(), GRASSY_COARSE_DEEP_DIRT_PATH.get());
    }
}
