package com.infamous.dungeons_world.world.biomes;

import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static com.infamous.dungeons_world.DungeonsWorld.MODID;
import static com.infamous.dungeons_world.Util.modLoc;
import static com.infamous.dungeons_world.world.surfacerules.ModSurfaceRules.CREEPER_WOODS_SURFACE;

public class ModBiomes {
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, MODID);

    public static final List<String> BIOME_NAMES = new ArrayList<>();

    public static final ResourceKey<Biome> CREEPER_WOODS = createBiome("creeper_woods", OverworldBiomes::theVoid);
    public static final ResourceKey<Biome> SOGGY_SWAMP = createBiome("soggy_swamp", OverworldBiomes::theVoid);

//    public static final RegistryObject<Biome> SPIDER_CAVES = null;

    // Dummy biomes to reserve the numeric ID safely for the json biomes to overwrite.
    // No static variable to hold as these dummy biomes should NOT be held and referenced elsewhere.
    static {
//        SPIDER_CAVES = createBiome("spider_caves", BiomeMaker::theVoidBiome);
    }

    public static ResourceKey<Biome> createBiome(String id, Supplier<Biome> biomeSupplier) {
        BIOME_NAMES.add(id);
        BIOMES.register(id, biomeSupplier);

        ResourceLocation biomeID = new ResourceLocation(MODID, id);
        if (BuiltinRegistries.BIOME.keySet().contains(biomeID)) {
            throw new IllegalStateException("Biome ID: \"" + biomeID.toString() + "\" already exists in the Biome registry!");
        }
        return ResourceKey.create(Registry.BIOME_REGISTRY, biomeID);
    }

    public static void initBiomes() {
        addBiomeTypesCreeperWoods("creeper_woods");
        addBiomeTypesSoggySwamp("soggy_swamp");
        Regions.register(new ModRegion(new ResourceLocation(MODID, "overworld"), 2));
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MODID, CREEPER_WOODS_SURFACE);
    }

    public static void addBiomeTypesCreeperWoods(String biomeName) {
        ResourceKey<Biome> biomeKey = ResourceKey.create(Registry.BIOME_REGISTRY, modLoc(biomeName));
        BiomeManager.addBiome(
                BiomeManager.BiomeType.WARM,
                new BiomeManager.BiomeEntry(
                        biomeKey,
                        5
                )
        );
//        BiomeDictionary.addTypes(biomeKey, OVERWORLD, FOREST, SPOOKY);
    }

    public static void addBiomeTypesSoggySwamp(String biomeName) {
        ResourceKey<Biome> biomeKey = ResourceKey.create(Registry.BIOME_REGISTRY, modLoc(biomeName));
        BiomeManager.addBiome(
                BiomeManager.BiomeType.WARM,
                new BiomeManager.BiomeEntry(
                        biomeKey,
                        5
                )
        );
//        BiomeDictionary.addTypes(biomeKey, OVERWORLD, SWAMP, SPOOKY);
    }
}
