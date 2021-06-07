package com.infamous.dungeons_world.blocks;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

public interface Creepmoss extends Degradable<Creepmoss.CreepmossLevel> {
    Supplier<BiMap<Block, Block>> CREEPMOSS_LEVEL_INCREASES = () -> {
        BiMap<Block, Block> biMap = HashBiMap.create();
        addBuildingBlockHelpersToBimap(biMap, Blocks.STONE, Blocks.STONE_SLAB, Blocks.STONE_STAIRS, ModBlocks.LOW_CREEPMOSS_STONE);
        addBuildingBlockHelpersToBimap(biMap, ModBlocks.LOW_CREEPMOSS_STONE, ModBlocks.MEDIUM_CREEPMOSS_STONE);
        addBuildingBlockHelpersToBimap(biMap, ModBlocks.MEDIUM_CREEPMOSS_STONE, ModBlocks.HIGH_CREEPMOSS_STONE);
        addBuildingBlockHelpersToBimap(biMap, Blocks.COBBLESTONE, Blocks.COBBLESTONE_SLAB, Blocks.COBBLESTONE_STAIRS, ModBlocks.LOW_CREEPMOSS_COBBLESTONE);
        addBuildingBlockHelpersToBimap(biMap, ModBlocks.LOW_CREEPMOSS_COBBLESTONE, ModBlocks.MEDIUM_CREEPMOSS_COBBLESTONE);
        addBuildingBlockHelpersToBimap(biMap, ModBlocks.MEDIUM_CREEPMOSS_COBBLESTONE, ModBlocks.HIGH_CREEPMOSS_COBBLESTONE);
        addBuildingBlockHelpersToBimap(biMap, Blocks.STONE_BRICKS, Blocks.STONE_BRICK_SLAB, Blocks.STONE_BRICK_STAIRS, ModBlocks.LOW_CREEPMOSS_STONE_BRICKS);
        addBuildingBlockHelpersToBimap(biMap, ModBlocks.LOW_CREEPMOSS_STONE_BRICKS, ModBlocks.MEDIUM_CREEPMOSS_STONE_BRICKS);
        biMap.put(Blocks.CRACKED_STONE_BRICKS, ModBlocks.MEDIUM_CREEPMOSS_CRACKED_STONE_BRICKS.getBlock().get());
        biMap.put(Blocks.CHISELED_STONE_BRICKS, ModBlocks.LOW_CREEPMOSS_CHISELED_STONE_BRICKS.getBlock().get());
        addBuildingBlockHelpersToBimap(biMap, Blocks.POLISHED_ANDESITE, Blocks.POLISHED_ANDESITE_SLAB, Blocks.POLISHED_ANDESITE_STAIRS, ModBlocks.LOW_CREEPMOSS_POLISHED_ANDESITE);
        addBuildingBlockHelpersToBimap(biMap, ModBlocks.LOW_CREEPMOSS_POLISHED_ANDESITE, ModBlocks.MEDIUM_CREEPMOSS_POLISHED_ANDESITE);
        return biMap;
    };
    Supplier<BiMap<Block, Block>> CREEPMOSS_LEVEL_DECREASES = Suppliers.memoize(() -> CREEPMOSS_LEVEL_INCREASES.get().inverse());

    static Optional<Block> getDecreasedOxidationBlock(Block block) {
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
        return getDecreasedOxidationBlock(state.getBlock()).map(block -> BlockHelper.getStateWithProperties(block, state));
    }

    static Optional<Block> getIncreasedCreepmossBlock(Block block) {
        return Optional.ofNullable(CREEPMOSS_LEVEL_INCREASES.get().get(block));
    }

    static BlockState getUnaffectedOxidationState(BlockState state) {
        return BlockHelper.getStateWithProperties(getUnaffectedCreepmossBlock(state.getBlock()), state);
    }

    default Optional<BlockState> getDegradationResult(BlockState state) {
        return getIncreasedCreepmossBlock(state.getBlock()).map(block -> BlockHelper.getStateWithProperties(block, state));
    }

    default float getDegradationChanceMultiplier() {
        return this.getDegradationLevel() == Creepmoss.CreepmossLevel.UNAFFECTED ? 0.75F : 1.0F;
    }

    default void tickDegradation(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        float f = 1;
        if (random.nextFloat() < f) {
            this.tryDegrade(state, world, pos, random);
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