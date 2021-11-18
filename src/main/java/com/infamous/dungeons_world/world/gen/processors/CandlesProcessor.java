package com.infamous.dungeons_world.world.gen.processors;

import com.blackgear.cavesandcliffs.common.block.CandleBlock;
import com.infamous.dungeons_world.compat.CavesAndCliffsCompat;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import mod.patrigan.structure_toolkit.StructureToolkit;
import mod.patrigan.structure_toolkit.util.RandomType;
import mod.patrigan.structure_toolkit.world.gen.processors.AttachmentProcessor;
import mod.patrigan.structure_toolkit.world.gen.processors.ProcessorUtil;
import net.minecraft.block.Block;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Random;

import static com.infamous.dungeons_world.world.gen.processors.ModProcessors.CANDLES;
import static mod.patrigan.structure_toolkit.init.ModProcessors.ATTACHMENT;
import static mod.patrigan.structure_toolkit.util.RandomType.RANDOM_TYPE_CODEC;
import static mod.patrigan.structure_toolkit.world.gen.processors.ProcessorUtil.*;

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
    public Template.BlockInfo process(IWorldReader world, BlockPos piecePos, BlockPos structurePos, Template.BlockInfo rawBlockInfo, Template.BlockInfo blockInfo, PlacementSettings settings, Template template) {
        if(CavesAndCliffsCompat.isLoaded()){
            AttachmentProcessor attachmentProcessor = new AttachmentProcessor(CavesAndCliffsCompat.getCandle().get().getRegistryName(), 2, false, true, rarity, randomType);
            Template.BlockInfo processed = attachmentProcessor.process(world, piecePos, structurePos, rawBlockInfo, blockInfo, settings, template);
            if(processed.state.is(CavesAndCliffsCompat.getCandle().get())){
                Random random = ProcessorUtil.getRandom(randomType, blockInfo.pos, piecePos, structurePos, world, SEED);
                return new Template.BlockInfo(processed.pos, processed.state.setValue(CandleBlock.CANDLES, random.nextInt(3)+1).setValue(CandleBlock.LIT, true), processed.nbt);
            }
            return blockInfo;
        }else{
            AttachmentProcessor attachmentProcessor = new AttachmentProcessor(vanilla, 2, false, true, rarity, randomType);
            return attachmentProcessor.process(world, piecePos, structurePos, rawBlockInfo, blockInfo, settings, template);
        }
    }

    protected IStructureProcessorType<?> getType() {
        return CANDLES;
    }
}