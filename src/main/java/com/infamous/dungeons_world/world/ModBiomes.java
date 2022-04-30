package com.infamous.dungeons_world.world;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

import static com.infamous.dungeons_world.DungeonsWorld.MODID;

public class ModBiomes {
    public static ResourceKey<Biome>  CREEPER_WOODS = ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(MODID));
}
