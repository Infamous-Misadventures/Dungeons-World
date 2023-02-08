package com.infamous.dungeons_world.blocks;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.infamous.dungeons_world.init.BlocksInit;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;

import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

import static com.infamous.dungeons_world.init.BlocksInit.*;
import static net.minecraft.tags.BlockTags.DIRT;
import static net.minecraft.world.level.block.Blocks.POLISHED_GRANITE;


public interface Dirty extends Degradable<Dirty.DirtLevel> {
    float INCREASE_CHANCE = 0.25f;
    Supplier<BiMap<Block, Block>> DIRTY_LEVEL_INCREASES = () -> {
        BiMap<Block, Block> biMap = HashBiMap.create();
        addBuildingBlockHelpersToBimap(biMap, BlocksInit.STONE_TILES, BlocksInit.DIRTY_STONE_TILES);
        addBuildingBlockHelpersToBimap(biMap, BlocksInit.DIRTY_STONE_TILES, BlocksInit.HIGH_DIRTY_STONE_TILES);
        addBuildingBlockHelpersToBimap(biMap, BlocksInit.DETAILED_STONE_TILES, BlocksInit.DIRTY_DETAILED_STONE_TILES);
        addBuildingBlockHelpersToBimap(biMap, BlocksInit.DIRTY_DETAILED_STONE_TILES, BlocksInit.HIGH_DIRTY_DETAILED_STONE_TILES);
        addBuildingBlockHelpersToBimap(biMap, POLISHED_GRANITE, Blocks.POLISHED_GRANITE_SLAB, Blocks.POLISHED_GRANITE_STAIRS, BlocksInit.DIRTY_POLISHED_GRANITE);
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

    default void tickDegradation(BlockState state, ServerLevel world, BlockPos pos, Random random) {
        float f = INCREASE_CHANCE;
        AABB area = new AABB(pos).inflate(1, 0, 1).expandTowards(0, 1, 0);
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