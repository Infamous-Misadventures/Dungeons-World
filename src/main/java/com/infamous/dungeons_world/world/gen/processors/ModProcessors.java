package com.infamous.dungeons_world.world.gen.processors;

import com.mojang.serialization.Codec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;

import static com.infamous.dungeons_world.DungeonsWorld.MODID;

public class ModProcessors {
    public static StructureProcessorType<DungeonsChestProcessor> DUNGEONS_CHESTS;
    public static StructureProcessorType<CreepmossProcessor> CREEPMOSS;
    public static StructureProcessorType<DirtyProcessor> DIRTY;
    public static StructureProcessorType<CandlesProcessor> CANDLES;


    public static void init(){
        DUNGEONS_CHESTS = register("dungeons_chests", DungeonsChestProcessor.CODEC);
        CREEPMOSS = register("creepmoss", CreepmossProcessor.CODEC);
        DIRTY = register("dirty", DirtyProcessor.CODEC);
        CANDLES = register("candles", CandlesProcessor.CODEC);
    }

    static <P extends StructureProcessor> StructureProcessorType<P> register(String name, Codec<P> codec) {
        return Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(MODID, name), () -> {
            return codec;
        });
    }
}
