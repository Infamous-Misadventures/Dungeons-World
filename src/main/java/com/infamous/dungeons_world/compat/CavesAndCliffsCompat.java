package com.infamous.dungeons_world.compat;

import com.infamous.dungeons_world.DungeonsWorld;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

import static net.minecraft.block.Blocks.AIR;

@Mod.EventBusSubscriber(modid = DungeonsWorld.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CavesAndCliffsCompat {
    public static final String COMPAT_MOD_ID = "cavesandcliffs";
    private static boolean IS_LOADED = false;
    private static Supplier<Block> CANDLE = () -> AIR;

    @SubscribeEvent
    public static void onInterMod(InterModProcessEvent event){
        if(ModList.get().isLoaded(COMPAT_MOD_ID)){
            IS_LOADED = true;
            CANDLE = () -> getRegisteredBlock("candle");
        }
    }

    private static Block getRegisteredBlock(String block) {
        return ForgeRegistries.BLOCKS.getValue(getCompatResource(block));
    }

    public static boolean isLoaded(){
        return IS_LOADED;
    }

    public static Supplier<Block> getCandle() {
        return CANDLE;
    }

    private static ResourceLocation getCompatResource(String resourceId) {
        return new ResourceLocation(COMPAT_MOD_ID, resourceId);
    }
}
