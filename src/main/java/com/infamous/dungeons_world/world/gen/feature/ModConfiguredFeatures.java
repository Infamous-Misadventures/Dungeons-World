package com.infamous.dungeons_world.world.gen.feature;

import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.infamous.dungeons_world.DungeonsWorld.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModConfiguredFeatures {

    //private static ConfiguredFeature<?,?> FULL_GLOWING_MUSHROOM;

    public static void registerConfiguredFeatures(){
        //FULL_GLOWING_MUSHROOM = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(MODID, "glowing_mushrooms"), SLIMY_STONE_CONFIGURED_FEATURE.get(dyeColor));
    }

    @SubscribeEvent
    public static void biomeLoading(final BiomeLoadingEvent event)
    {
    }
}
