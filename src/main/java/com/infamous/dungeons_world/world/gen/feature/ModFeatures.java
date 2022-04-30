package com.infamous.dungeons_world.world.gen.feature;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static com.infamous.dungeons_world.DungeonsWorld.MODID;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, MODID);

    public static final RegistryObject<Feature<MushroomBlockClusterFeatureConfig>> FULL_GLOWING_MUSHROOMS = registerFeature("full_glowing_mushrooms", () -> new GlowingMushroomsFeature(MushroomBlockClusterFeatureConfig.CODEC));

    public static <T extends Feature<?>> RegistryObject<T> registerFeature(String name, Supplier<T> feature) {
        return FEATURES.register(name, feature);
    }
}
