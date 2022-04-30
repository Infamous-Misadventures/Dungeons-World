package com.infamous.dungeons_world.world.gen.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import mod.patrigan.structure_toolkit.util.RandomType;
import mod.patrigan.structure_toolkit.world.gen.processors.ProcessorUtil;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import java.util.Random;

import static com.infamous.dungeons_world.blocks.Creepmoss.getIncreasedCreepmossState;
import static com.infamous.dungeons_world.world.gen.processors.ModProcessors.CREEPMOSS;
import static mod.patrigan.structure_toolkit.util.RandomType.RANDOM_TYPE_CODEC;

public class CreepmossProcessor extends StructureProcessor {

    public static final Codec<CreepmossProcessor> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            Codec.FLOAT.optionalFieldOf("rarity", 1.0F).forGetter(data -> data.rarity),
            Codec.INT.optionalFieldOf("steps", 1).forGetter(data -> data.steps),
            RANDOM_TYPE_CODEC.optionalFieldOf("random_type", RandomType.BLOCK).forGetter(processor -> processor.randomType)
    ).apply(builder, builder.stable(CreepmossProcessor::new)));
    private static final long SEED = 623154L;

    private final float rarity;
    private final int steps;
    private final RandomType randomType;

    public CreepmossProcessor(float rarity, int steps, RandomType randomType) {
        this.rarity = rarity;
        this.steps = steps;
        this.randomType = randomType;
    }

    @Override
    public StructureTemplate.StructureBlockInfo process(LevelReader world, BlockPos piecePos, BlockPos structurePos, StructureTemplate.StructureBlockInfo rawBlockInfo, StructureTemplate.StructureBlockInfo blockInfo, StructurePlaceSettings settings, StructureTemplate template) {
        if(getIncreasedCreepmossState(blockInfo.state).isPresent()){
            BlockState blockState = getIncreasedCreepmossState(blockInfo.state).get();
            Random random;
            random = ProcessorUtil.getRandom(randomType, blockInfo.pos, piecePos, structurePos, world, SEED);
            if (random.nextFloat() < rarity) {
                return new StructureTemplate.StructureBlockInfo(
                        blockInfo.pos,
                        blockState,
                        null
                );
            }
        }
        return blockInfo;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return CREEPMOSS;
    }
}