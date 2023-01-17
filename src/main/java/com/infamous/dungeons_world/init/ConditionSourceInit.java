package com.infamous.dungeons_world.init;

import com.infamous.dungeons_world.world.surfacerules.SimplexTresholdConditionSource;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.infamous.dungeons_world.DungeonsWorld.MODID;

public class ConditionSourceInit {
    public static final DeferredRegister<Codec<? extends SurfaceRules.ConditionSource>> CONDITION_SOURCES = DeferredRegister.create(Registry.CONDITION_REGISTRY, MODID);
    public static RegistryObject<Codec<? extends SurfaceRules.ConditionSource>> SIMPLEX_TRESHOLD = CONDITION_SOURCES.register("simplex_threshold", SimplexTresholdConditionSource.CODEC::codec);
}
