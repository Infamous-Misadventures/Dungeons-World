package com.infamous.dungeons_world.world.gen.processors;

import com.mojang.serialization.Codec;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;
import net.minecraft.world.gen.feature.template.StructureProcessor;

import static com.infamous.dungeons_world.DungeonsWorld.MODID;

public class ModProcessors {
    public static IStructureProcessorType<DungeonsChestProcessor> DUNGEONS_CHESTS;


    public static void init(){
        DUNGEONS_CHESTS = register("dungeons_chests", DungeonsChestProcessor.CODEC);
    }

    static <P extends StructureProcessor> IStructureProcessorType<P> register(String name, Codec<P> codec) {
        return Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(MODID, name), () -> {
            return codec;
        });
    }
}
