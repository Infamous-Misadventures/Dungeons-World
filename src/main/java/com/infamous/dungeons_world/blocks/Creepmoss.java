package com.infamous.dungeons_world.blocks;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.infamous.dungeons_world.init.BlocksInit;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;
import java.util.function.Supplier;

public interface Creepmoss extends ChangeOverTimeBlock<Creepmoss.CreepmossLevel> {
    Supplier<BiMap<Block, Block>> CREEPMOSS_LEVEL_INCREASES = () -> {
        BiMap<Block, Block> biMap = HashBiMap.create();
        addBuildingBlockHelpersToBimap(biMap, Blocks.STONE, Blocks.STONE_SLAB, Blocks.STONE_STAIRS, BlocksInit.LOW_CREEPMOSS_STONE);
        addBuildingBlockHelpersToBimap(biMap, BlocksInit.LOW_CREEPMOSS_STONE, BlocksInit.MEDIUM_CREEPMOSS_STONE);
        addBuildingBlockHelpersToBimap(biMap, BlocksInit.MEDIUM_CREEPMOSS_STONE, BlocksInit.HIGH_CREEPMOSS_STONE);
        addBuildingBlockHelpersToBimap(biMap, Blocks.COBBLESTONE, Blocks.COBBLESTONE_SLAB, Blocks.COBBLESTONE_STAIRS, BlocksInit.LOW_CREEPMOSS_COBBLESTONE);
        addBuildingBlockHelpersToBimap(biMap, BlocksInit.LOW_CREEPMOSS_COBBLESTONE, BlocksInit.MEDIUM_CREEPMOSS_COBBLESTONE);
        addBuildingBlockHelpersToBimap(biMap, BlocksInit.MEDIUM_CREEPMOSS_COBBLESTONE, BlocksInit.HIGH_CREEPMOSS_COBBLESTONE);
        addBuildingBlockHelpersToBimap(biMap, Blocks.STONE_BRICKS, Blocks.STONE_BRICK_SLAB, Blocks.STONE_BRICK_STAIRS, BlocksInit.LOW_CREEPMOSS_STONE_BRICKS);
        addBuildingBlockHelpersToBimap(biMap, BlocksInit.LOW_CREEPMOSS_STONE_BRICKS, BlocksInit.MEDIUM_CREEPMOSS_STONE_BRICKS);
        biMap.put(Blocks.CRACKED_STONE_BRICKS, BlocksInit.MEDIUM_CREEPMOSS_CRACKED_STONE_BRICKS.getBlock().get());
        biMap.put(Blocks.CHISELED_STONE_BRICKS, BlocksInit.LOW_CREEPMOSS_CHISELED_STONE_BRICKS.getBlock().get());
        addBuildingBlockHelpersToBimap(biMap, Blocks.POLISHED_ANDESITE, Blocks.POLISHED_ANDESITE_SLAB, Blocks.POLISHED_ANDESITE_STAIRS, BlocksInit.LOW_CREEPMOSS_POLISHED_ANDESITE);
        addBuildingBlockHelpersToBimap(biMap, BlocksInit.LOW_CREEPMOSS_POLISHED_ANDESITE, BlocksInit.MEDIUM_CREEPMOSS_POLISHED_ANDESITE);
        return biMap;
    };
    Supplier<BiMap<Block, Block>> CREEPMOSS_LEVEL_DECREASES = Suppliers.memoize(() -> CREEPMOSS_LEVEL_INCREASES.get().inverse());

    static Optional<Block> getDecreasedCreepmossBlock(Block block) {
        return Optional.ofNullable(CREEPMOSS_LEVEL_DECREASES.get().get(block));
    }

    static Block getUnaffectedCreepmossBlock(Block block) {
        Block block2 = block;

        for(Block block3 = CREEPMOSS_LEVEL_DECREASES.get().get(block); block3 != null; block3 = CREEPMOSS_LEVEL_DECREASES.get().get(block3)) {
            block2 = block3;
        }

        return block2;
    }

    static Optional<BlockState> getDecreasedCreepmossState(BlockState state) {
        return getDecreasedCreepmossBlock(state.getBlock()).map(block -> BlockHelper.getStateWithProperties(block, state));
    }

    static Optional<Block> getIncreasedCreepmossBlock(Block block) {
        return Optional.ofNullable(CREEPMOSS_LEVEL_INCREASES.get().get(block));
    }

    static Optional<BlockState> getIncreasedCreepmossState(BlockState state) {
        return getIncreasedCreepmossBlock(state.getBlock()).map(block -> BlockHelper.getStateWithProperties(block, state));
    }

    static BlockState getUnaffectedOxidationState(BlockState state) {
        return BlockHelper.getStateWithProperties(getUnaffectedCreepmossBlock(state.getBlock()), state);
    }

    default Optional<BlockState> getNext(BlockState state) {
        return getIncreasedCreepmossBlock(state.getBlock()).map(block -> BlockHelper.getStateWithProperties(block, state));
    }

    default float getChanceModifier() {
        return this.getAge() == Creepmoss.CreepmossLevel.UNAFFECTED ? 0.75F : 1.0F;
    }

    default void onRandomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        float f = 1;
        if (random.nextFloat() < f) {
            this.applyChangeOverTime(state, world, pos, random);
        }

    }

    public static enum CreepmossLevel {
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