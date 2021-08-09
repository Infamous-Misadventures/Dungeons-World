package com.infamous.dungeons_world.world.gen.processors;

import com.infamous.dungeons_world.tileentity.DungeonsChestType;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import mod.patrigan.structure_toolkit.util.GeneralUtils;
import mod.patrigan.structure_toolkit.util.RandomType;
import mod.patrigan.structure_toolkit.world.gen.processors.ProcessorUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.ChestType;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.server.ServerWorld;

import java.util.List;
import java.util.Random;

import static com.infamous.dungeons_world.blocks.ModBlocks.CHEST_TYPES;
import static com.infamous.dungeons_world.blocks.ModBlocks.COMMON_CHEST;
import static com.infamous.dungeons_world.world.gen.processors.ModProcessors.DUNGEONS_CHESTS;
import static mod.patrigan.structure_toolkit.util.RandomType.RANDOM_TYPE_CODEC;
import static net.minecraft.block.Blocks.AIR;
import static net.minecraft.block.Blocks.CHEST;
import static net.minecraft.block.ChestBlock.*;
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
    public Template.BlockInfo process(IWorldReader world, BlockPos piecePos, BlockPos structurePos, Template.BlockInfo rawBlockInfo, Template.BlockInfo blockInfo, PlacementSettings settings, Template template) {
        if ((blockInfo.state.is(COMMON_CHEST.get()) || blockInfo.state.is(CHEST)) && blockInfo.state.hasTileEntity()) {
            Random random;
            if(blockInfo.state.getValue(TYPE).equals(ChestType.LEFT)) {
                Direction connectedDirection = getConnectedDirection(blockInfo.state.rotate((IWorld)world, blockInfo.pos, settings.getRotation()));
                random = ProcessorUtil.getRandom(randomType, blockInfo.pos.relative(connectedDirection), piecePos, structurePos, world, SEED);
            }else {
                random = ProcessorUtil.getRandom(randomType, blockInfo.pos, piecePos, structurePos, world, SEED);
            }
            if (random.nextFloat() < rarity) {
                DungeonsChestType chestType = GeneralUtils.getRandomEntry(chestTypes, random);
                BlockState blockState = CHEST_TYPES.get(chestType).get().defaultBlockState();
                blockState = copyProperties(blockState, blockInfo.state);
                TileEntity tileEntity = blockState.createTileEntity(world);
                ServerWorld serverWorld = ((IServerWorld) world).getLevel();
                LockableLootTileEntity lockableLootTileEntity = (LockableLootTileEntity) tileEntity;
                lockableLootTileEntity.setLevelAndPosition(serverWorld, blockInfo.pos);
                CompoundNBT nbt = blockInfo.nbt;
                if(!blockInfo.state.getValue(TYPE).equals(ChestType.LEFT)) {
                    nbt.putString("LootTable", this.baseLootTable.toString() + "/" + chestType.name().toLowerCase());
                    nbt.putLong("LootTableSeed", serverWorld.random.nextLong());
                }
                return new Template.BlockInfo(
                        blockInfo.pos,
                        blockState,
                        nbt);
            } else {
                Block newBlock = BLOCKS.getValue(replacer);
                if (newBlock == null) {
                    newBlock = AIR;
                }
                return new Template.BlockInfo(
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
    protected IStructureProcessorType<?> getType() {
        return DUNGEONS_CHESTS;
    }
}