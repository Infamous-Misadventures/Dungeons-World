package com.infamous.dungeons_world.blocks;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

import static com.infamous.dungeons_world.blocks.ModBlocks.*;
import static net.minecraft.block.Blocks.POLISHED_GRANITE;
import static net.minecraftforge.common.Tags.Blocks.DIRT;


public interface Dirty extends Degradable<Dirty.DirtLevel> {
    Supplier<BiMap<Block, Block>> DIRTY_LEVEL_INCREASES = () -> {
        BiMap<Block, Block> biMap = HashBiMap.create();
        addBuildingBlockHelpersToBimap(biMap, ModBlocks.STONE_TILES, ModBlocks.DIRTY_STONE_TILES);
        addBuildingBlockHelpersToBimap(biMap, ModBlocks.DIRTY_STONE_TILES, ModBlocks.HIGH_DIRTY_STONE_TILES);
        addBuildingBlockHelpersToBimap(biMap, ModBlocks.DETAILED_STONE_TILES, ModBlocks.DIRTY_DETAILED_STONE_TILES);
        addBuildingBlockHelpersToBimap(biMap, ModBlocks.DIRTY_DETAILED_STONE_TILES, ModBlocks.HIGH_DIRTY_DETAILED_STONE_TILES);
        addBuildingBlockHelpersToBimap(biMap, POLISHED_GRANITE, Blocks.POLISHED_GRANITE_SLAB, Blocks.POLISHED_GRANITE_STAIRS, ModBlocks.DIRTY_POLISHED_GRANITE);
        biMap.put(DEEP_STONE_TILES_PATH.get(), DEEP_DIRTY_STONE_TILES_PATH.get());
        biMap.put(DEEP_DIRTY_STONE_TILES_PATH.get(), DEEP_HIGH_DIRTY_STONE_TILES_PATH.get());
        biMap.put(DEEP_DETAILED_STONE_TILES_PATH.get(), DEEP_DIRTY_DETAILED_STONE_TILES_PATH.get());
        biMap.put(DEEP_DIRTY_DETAILED_STONE_TILES_PATH.get(), DEEP_HIGH_DIRTY_DETAILED_STONE_TILES_PATH.get());
        return biMap;
    };
    Supplier<BiMap<Block, Block>> DIRTY_LEVEL_DECREASES = Suppliers.memoize(() -> DIRTY_LEVEL_INCREASES.get().inverse());

    static Optional<Block> getDecreasedDirtyBlock(Block block) {
        return Optional.ofNullable(DIRTY_LEVEL_DECREASES.get().get(block));
    }

    static Block getUnaffectedDirtyBlock(Block block) {
        Block block2 = block;

        for(Block block3 = DIRTY_LEVEL_DECREASES.get().get(block); block3 != null; block3 = DIRTY_LEVEL_DECREASES.get().get(block3)) {
            block2 = block3;
        }

        return block2;
    }

    static Optional<BlockState> getDecreasedDirtyState(BlockState state) {
        return getDecreasedDirtyBlock(state.getBlock()).map(block -> BlockHelper.getStateWithProperties(block, state));
    }

    static Optional<BlockState> getIncreasedDirtyState(BlockState state) {
        return getIncreasedDirtyBlock(state.getBlock()).map(block -> BlockHelper.getStateWithProperties(block, state));
    }

    static Optional<Block> getIncreasedDirtyBlock(Block block) {
        return Optional.ofNullable(DIRTY_LEVEL_INCREASES.get().get(block));
    }

    static BlockState getUnaffectedDirtyState(BlockState state) {
        return BlockHelper.getStateWithProperties(getUnaffectedDirtyBlock(state.getBlock()), state);
    }

    default Optional<BlockState> getDegradationResult(BlockState state) {
        return getIncreasedDirtyBlock(state.getBlock()).map(block -> BlockHelper.getStateWithProperties(block, state));
    }

    default float getDegradationChanceMultiplier() {
        return this.getDegradationLevel() == DirtLevel.UNAFFECTED ? 0.75F : 0.50F;
    }

    default void tickDegradation(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        float f = 0.25f;
        AxisAlignedBB area = new AxisAlignedBB(pos).inflate(1, 0, 1).expandTowards(0, 1, 0);
        if (random.nextFloat() < f && world.getBlockStates(area).anyMatch(blockState -> blockState.is(DIRT) || blockState.getBlock() instanceof Dirty)) {
            this.tryDegrade(state, world, pos, random);
        }
    }

    public static enum DirtLevel {
        UNAFFECTED,
        LOW,
        MEDIUM,
        HIGH;
    }

    static BiMap<Block, Block> addBuildingBlockHelpersToBimap(BiMap<Block, Block> biMap, BuildingBlockHelper from, BuildingBlockHelper to){
        biMap.put(from.getBlock().get(), to.getBlock().get());
        biMap.put(from.getSlab().get(), to.getSlab().get());
        biMap.put(from.getStairs().get(), to.getStairs().get());
        return biMap;
    }

    static BiMap<Block, Block> addBuildingBlockHelpersToBimap(BiMap<Block, Block> biMap, Block fromBlock, Block fromSlab, Block fromStairs, BuildingBlockHelper to){
        biMap.put(fromBlock, to.getBlock().get());
        biMap.put(fromSlab, to.getSlab().get());
        biMap.put(fromStairs, to.getStairs().get());
        return biMap;
    }
}