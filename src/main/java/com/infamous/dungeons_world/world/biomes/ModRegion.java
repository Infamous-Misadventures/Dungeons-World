package com.infamous.dungeons_world.world.biomes;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

import static com.infamous.dungeons_world.world.biomes.ModBiomes.CREEPER_WOODS;

public class ModRegion extends Region {

    public ModRegion(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);

    }
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        ResourceKey<Biome> biomeKey = CREEPER_WOODS;
        if (!registry.containsKey(biomeKey)) {
            throw new IllegalArgumentException(String.format("\"%s\" is not a valid biome in the world registry!", biomeKey.location().toString()));
        }
        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
            // Simple example:
            // Replace the Vanilla desert with our hot_red biome
            builder.replaceBiome(Biomes.FOREST, ModBiomes.CREEPER_WOODS);
        });
    }
}