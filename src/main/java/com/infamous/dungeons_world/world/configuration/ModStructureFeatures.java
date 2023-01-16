package com.infamous.dungeons_world.world.configuration;

import com.infamous.dungeons_world.DungeonsWorld;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModStructureFeatures {
    public static final DeferredRegister<StructureFeature<?>> STRUCTURE_FEATURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, DungeonsWorld.MODID);

    public static final RegistryObject<StructureFeature<?>> TOOLKT_JIGSAW =  STRUCTURE_FEATURES.register(
            "toolkit_jigsaw",
            () -> new ToolkitJigsawFeature(ToolkitJigsawConfiguration.CODEC, 0, true, true, (p_197185_) -> true));

}
