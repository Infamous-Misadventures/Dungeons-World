package com.infamous.dungeons_world.world.gen.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import mod.patrigan.structure_toolkit.util.RandomType;
import mod.patrigan.structure_toolkit.world.gen.processors.AttachmentProcessor;
import mod.patrigan.structure_toolkit.world.gen.processors.ProcessorUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

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
        StructureTemplate.StructureBlockInfo processed = attachmentProcessor.process(world, piecePos, structurePos, rawBlockInfo, blockInfo, settings, template);
        if(ForgeRegistries.BLOCKS.getKey(processed.state.getBlock()).equals(vanilla)){
            RandomSource random = ProcessorUtil.getRandom(randomType, blockInfo.pos, piecePos, structurePos, world, SEED);
            return new StructureTemplate.StructureBlockInfo(processed.pos, processed.state.setValue(CandleBlock.CANDLES, random.nextInt(3)+1).setValue(CandleBlock.LIT, true), processed.nbt);
        }
        return blockInfo;
    }

    protected StructureProcessorType<?> getType() {
        return CANDLES;
    }
}