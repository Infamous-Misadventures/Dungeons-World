package com.infamous.dungeons_world.client.renderer.color;

import com.infamous.dungeons_world.DungeonsWorld;
import com.infamous.dungeons_world.init.BlocksInit;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.infamous.dungeons_world.init.BlocksInit.GRASSY_COARSE_DEEP_DIRT;
import static com.infamous.dungeons_world.init.BlocksInit.GRASSY_COARSE_DEEP_DIRT_PATH;

@Mod.EventBusSubscriber(modid = DungeonsWorld.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModItemColors {
    @SubscribeEvent
    public static void init(RegisterColorHandlersEvent.Item event){
        ItemColors itemColors = event.getItemColors();
        BlockColors blockColors = event.getBlockColors();
        itemColors.register((p_210235_1_, p_210235_2_) -> {
                    BlockState blockstate = ((BlockItem)p_210235_1_.getItem()).getBlock().defaultBlockState();
                    return blockColors.getColor(blockstate, null, null, p_210235_2_);
        }, BlocksInit.DEEP_GRASS_BLOCK.get(), BlocksInit.DEEP_GRASSY_DIRT.get(), BlocksInit.DEEP_DIRTY_GRASS.get(), GRASSY_COARSE_DEEP_DIRT.get(), GRASSY_COARSE_DEEP_DIRT_PATH.get());
    }
}
