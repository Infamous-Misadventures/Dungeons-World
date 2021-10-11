package com.infamous.dungeons_world.client.renderer.color;

import com.infamous.dungeons_world.DungeonsWorld;
import com.infamous.dungeons_world.blocks.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.BlockItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GrassColors;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.infamous.dungeons_world.blocks.ModBlocks.GRASSY_COARSE_DEEP_DIRT;
import static com.infamous.dungeons_world.blocks.ModBlocks.GRASSY_COARSE_DEEP_DIRT_PATH;

@Mod.EventBusSubscriber(modid = DungeonsWorld.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModItemColors {
    @SubscribeEvent
    public static void init(ColorHandlerEvent.Item event){
        ItemColors itemColors = event.getItemColors();
        BlockColors blockColors = event.getBlockColors();
        itemColors.register((p_210235_1_, p_210235_2_) -> {
                    BlockState blockstate = ((BlockItem)p_210235_1_.getItem()).getBlock().defaultBlockState();
                    return blockColors.getColor(blockstate, null, null, p_210235_2_);
        }, ModBlocks.DEEP_GRASS_BLOCK.get(), ModBlocks.DEEP_GRASSY_DIRT.get(), ModBlocks.DEEP_DIRTY_GRASS.get(), GRASSY_COARSE_DEEP_DIRT.get(), GRASSY_COARSE_DEEP_DIRT_PATH.get());
    }
}
