package com.infamous.dungeons_world.biomes;

import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.Registry;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static com.infamous.dungeons_world.DungeonsWorld.MODID;
import static com.infamous.dungeons_world.Util.ModLoc;
import static net.minecraftforge.common.BiomeDictionary.Type.*;

public class ModBiomes {
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, MODID);

    public static final List<String> BIOME_NAMES = new ArrayList<>();

//    public static final RegistryObject<Biome> SPIDER_CAVES = null;

    // Dummy biomes to reserve the numeric ID safely for the json biomes to overwrite.
    // No static variable to hold as these dummy biomes should NOT be held and referenced elsewhere.
    static {
        RegistryObject<Biome> creeper_woods = createBiome("creeper_woods", OverworldBiomes::theVoid);
        RegistryObject<Biome> soggy_swamp = createBiome("soggy_swamp", OverworldBiomes::theVoid);
//        SPIDER_CAVES = createBiome("spider_caves", BiomeMaker::theVoidBiome);
    }

    public static RegistryObject<Biome> createBiome(String name, Supplier<Biome> biomeSupplier) {
        BIOME_NAMES.add(name);
        return BIOMES.register(name, biomeSupplier);
    }

    public static void initBiomes() {
        addBiomeTypesCreeperWoods("creeper_woods");
        addBiomeTypesSoggySwamp("soggy_swamp");
    }

    public static void addBiomeTypesCreeperWoods(String biomeName) {
        ResourceKey<Biome> biomeKey = ResourceKey.create(Registry.BIOME_REGISTRY, ModLoc(biomeName));
        BiomeManager.addBiome(
                BiomeManager.BiomeType.WARM,
                new BiomeManager.BiomeEntry(
                        biomeKey,
                        5
                )
        );
        BiomeDictionary.addTypes(biomeKey, OVERWORLD, FOREST, SPOOKY);
    }

    public static void addBiomeTypesSoggySwamp(String biomeName) {
        ResourceKey<Biome> biomeKey = ResourceKey.create(Registry.BIOME_REGISTRY, ModLoc(biomeName));
        BiomeManager.addBiome(
                BiomeManager.BiomeType.WARM,
                new BiomeManager.BiomeEntry(
                        biomeKey,
                        5
                )
        );
        BiomeDictionary.addTypes(biomeKey, OVERWORLD, SWAMP, SPOOKY);
    }
}
