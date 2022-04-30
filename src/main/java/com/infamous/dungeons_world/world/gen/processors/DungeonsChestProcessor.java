package com.infamous.dungeons_world.world.gen.processors;

import com.infamous.dungeons_world.blockentity.DungeonsChestType;
import com.infamous.dungeons_world.blocks.DungeonsChestBlock;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import mod.patrigan.structure_toolkit.util.GeneralUtils;
import mod.patrigan.structure_toolkit.util.RandomType;
import mod.patrigan.structure_toolkit.world.gen.processors.ProcessorUtil;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.server.level.ServerLevel;

import java.util.List;
import java.util.Random;

import static com.infamous.dungeons_world.blocks.ModBlocks.CHEST_TYPES;
import static com.infamous.dungeons_world.blocks.ModBlocks.COMMON_CHEST;
import static com.infamous.dungeons_world.world.gen.processors.ModProcessors.DUNGEONS_CHESTS;
import static mod.patrigan.structure_toolkit.util.RandomType.RANDOM_TYPE_CODEC;
import static net.minecraft.world.level.block.Blocks.AIR;
import static net.minecraft.world.level.block.Blocks.CHEST;
import static net.minecraft.world.level.block.ChestBlock.*;
import static net.minecraftforge.registries.ForgeRegistries.BLOCKS;

public class DungeonsChestProcessor extends StructureProcessor {

    public static final Codec<DungeonsChestProcessor> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            ResourceLocation.CODEC.optionalFieldOf("base_loot_table", new ResourceLocation("structure_toolkit:empty")).forGetter(data -> data.baseLootTable),
            ResourceLocation.CODEC.optionalFieldOf("replacer", new ResourceLocation("minecraft:air")).forGetter(data -> data.replacer),
            Codec.FLOAT.optionalFieldOf("rarity", 1.0F).forGetter(data -> data.rarity),
            Codec.mapPair(DungeonsChestType.CODEC.fieldOf("chest_type"), Codec.intRange(1, Integer.MAX_VALUE).fieldOf("weight")).codec().listOf().fieldOf("chest_types").forGetter(processor -> processor.chestTypes),
            RANDOM_TYPE_CODEC.optionalFieldOf("random_type", RandomType.BLOCK).forGetter(processor -> processor.randomType)
    ).apply(builder, builder.stable(DungeonsChestProcessor::new)));
    private static final long SEED = 2465482L;

    private final ResourceLocation baseLootTable;
    private final ResourceLocation replacer;
    private final float rarity;
    private final List<Pair<DungeonsChestType, Integer>> chestTypes;
    private final RandomType randomType;

    public DungeonsChestProcessor(ResourceLocation baseLootTable, ResourceLocation replacer, float rarity, List<Pair<DungeonsChestType, Integer>> chestTypes, RandomType randomType) {
        this.baseLootTable = baseLootTable;
        this.replacer = replacer;
        this.rarity = rarity;
        this.chestTypes = chestTypes;
        this.randomType = randomType;
    }

    @Override
    public StructureTemplate.StructureBlockInfo process(LevelReader world, BlockPos piecePos, BlockPos structurePos, StructureTemplate.StructureBlockInfo rawBlockInfo, StructureTemplate.StructureBlockInfo blockInfo, StructurePlaceSettings settings, StructureTemplate template) {
        if ((blockInfo.state.is(COMMON_CHEST.get()) || blockInfo.state.is(CHEST)) && blockInfo.state.hasBlockEntity()) {
            Random random;
            if(blockInfo.state.getValue(TYPE).equals(ChestType.LEFT)) {
                Direction connectedDirection = getConnectedDirection(blockInfo.state.rotate((LevelAccessor)world, blockInfo.pos, settings.getRotation()));
                random = ProcessorUtil.getRandom(randomType, blockInfo.pos.relative(connectedDirection), piecePos, structurePos, world, SEED);
            }else {
                random = ProcessorUtil.getRandom(randomType, blockInfo.pos, piecePos, structurePos, world, SEED);
            }
            if (random.nextFloat() < rarity) {
                DungeonsChestType chestType = GeneralUtils.getRandomEntry(chestTypes, random);
                BlockState blockState = CHEST_TYPES.get(chestType).get().defaultBlockState();
                blockState = copyProperties(blockState, blockInfo.state);
                BlockEntity tileEntity = ((DungeonsChestBlock) blockState.getBlock()).newBlockEntity(blockInfo.pos, blockState);
                tileEntity.load(blockInfo.nbt);
                ServerLevel serverWorld = ((ServerLevelAccessor) world).getLevel();
                if(!blockInfo.state.getValue(TYPE).equals(ChestType.LEFT)) {
                    ((RandomizableContainerBlockEntity) tileEntity).setLootTable(new ResourceLocation(this.baseLootTable.getNamespace(), this.baseLootTable.getPath() + "/" + chestType.name().toLowerCase()), serverWorld.random.nextLong());
                }
                return new StructureTemplate.StructureBlockInfo(
                        blockInfo.pos,
                        blockState,
                        tileEntity.saveWithId());
            } else {
                Block newBlock = BLOCKS.getValue(replacer);
                if (newBlock == null) {
                    newBlock = AIR;
                }
                return new StructureTemplate.StructureBlockInfo(
                        blockInfo.pos,
                        newBlock.defaultBlockState(),
                        null
                );
            }
        }
        return blockInfo;
    }

    private BlockState copyProperties(BlockState blockState, BlockState state) {
        return blockState.setValue(FACING, state.getValue(FACING)).setValue(TYPE, state.getValue(TYPE)).setValue(WATERLOGGED, state.getValue(WATERLOGGED));
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return DUNGEONS_CHESTS;
    }
}