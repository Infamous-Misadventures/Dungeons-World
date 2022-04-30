package com.infamous.dungeons_world.world.gen.processors;

import com.infamous.dungeons_world.blocks.Dirty;
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
import static com.infamous.dungeons_world.blocks.Dirty.getIncreasedDirtyState;
import static com.infamous.dungeons_world.world.gen.processors.ModProcessors.CREEPMOSS;
import static com.infamous.dungeons_world.world.gen.processors.ModProcessors.DIRTY;
import static mod.patrigan.structure_toolkit.util.RandomType.RANDOM_TYPE_CODEC;

public class DirtyProcessor extends StructureProcessor {

    public static final Codec<DirtyProcessor> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            Codec.FLOAT.optionalFieldOf("rarity", 1.0F).forGetter(data -> data.rarity),
            Codec.INT.optionalFieldOf("steps", 1).forGetter(data -> data.steps),
            RANDOM_TYPE_CODEC.optionalFieldOf("random_type", RandomType.BLOCK).forGetter(processor -> processor.randomType)
    ).apply(builder, builder.stable(DirtyProcessor::new)));
    private static final long SEED = 623154L;

    private final float rarity;
    private final int steps;
    private final RandomType randomType;

    public DirtyProcessor(float rarity, int steps, RandomType randomType) {
        this.rarity = rarity;
        this.steps = steps;
        this.randomType = randomType;
    }

    @Override
    public StructureTemplate.StructureBlockInfo process(LevelReader world, BlockPos piecePos, BlockPos structurePos, StructureTemplate.StructureBlockInfo rawBlockInfo, StructureTemplate.StructureBlockInfo blockInfo, StructurePlaceSettings settings, StructureTemplate template) {
        Random random;
        random = ProcessorUtil.getRandom(randomType, blockInfo.pos, piecePos, structurePos, world, SEED);
        BlockState blockState = blockInfo.state;
        if (random.nextFloat() < rarity) {
            blockState = getIncreasedBlockState(blockState);
            if(steps > 1) {
                for (int i = 2; i <= steps; i++) {
                    if(random.nextFloat() < Dirty.INCREASE_CHANCE) {
                        blockState = getIncreasedBlockState(blockState);
                    }
                }
            }
        }
        return new StructureTemplate.StructureBlockInfo(
                blockInfo.pos,
                blockState,
                blockInfo.nbt
        );
    }

    private BlockState getIncreasedBlockState(BlockState blockState) {
        return getIncreasedDirtyState(blockState).orElse(blockState);
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return DIRTY;
    }
}