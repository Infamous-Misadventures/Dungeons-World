package com.infamous.dungeons_world.world.gen.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import mod.patrigan.structure_toolkit.util.RandomType;
import mod.patrigan.structure_toolkit.world.gen.processors.AttachmentProcessor;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import static com.infamous.dungeons_world.world.gen.processors.ModProcessors.CANDLES;
import static mod.patrigan.structure_toolkit.util.RandomType.RANDOM_TYPE_CODEC;

public class CandlesProcessor extends StructureProcessor {
    public static final Codec<CandlesProcessor> CODEC = RecordCodecBuilder.create(builder ->
            builder.group(
                    ResourceLocation.CODEC.fieldOf("vanilla").forGetter(data -> data.vanilla),
                    Codec.FLOAT.fieldOf("rarity").forGetter(processor -> processor.rarity),
                    RANDOM_TYPE_CODEC.optionalFieldOf("random_type", RandomType.BLOCK).forGetter(processor -> processor.randomType)
            ).apply(builder, CandlesProcessor::new));
    private static final long SEED = 356179L;

    private final ResourceLocation vanilla;
    private final float rarity;
    private final RandomType randomType;

    public CandlesProcessor(ResourceLocation vanilla, float rarity, RandomType randomType) {
        this.vanilla = vanilla;
        this.rarity = rarity;
        this.randomType = randomType;
    }

    @Override
    public StructureTemplate.StructureBlockInfo process(LevelReader world, BlockPos piecePos, BlockPos structurePos, StructureTemplate.StructureBlockInfo rawBlockInfo, StructureTemplate.StructureBlockInfo blockInfo, StructurePlaceSettings settings, StructureTemplate template) {
        AttachmentProcessor attachmentProcessor = new AttachmentProcessor(vanilla, 2, false, true, rarity, randomType);
        return attachmentProcessor.process(world, piecePos, structurePos, rawBlockInfo, blockInfo, settings, template);
    }

    protected StructureProcessorType<?> getType() {
        return CANDLES;
    }
}