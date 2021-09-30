package com.infamous.dungeons_world.blocks;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

import static net.minecraftforge.common.Tags.Blocks.DIRT;


public interface Dirty extends Degradable<Dirty.DirtLevel> {
    Supplier<BiMap<Block, Block>> DIRTY_LEVEL_INCREASES = () -> {
        BiMap<Block, Block> biMap = HashBiMap.create();
        addBuildingBlockHelpersToBimap(biMap, ModBlocks.STONE_TILES, ModBlocks.DIRTY_STONE_TILES);
        addBuildingBlockHelpersToBimap(biMap, ModBlocks.DIRTY_STONE_TILES, ModBlocks.HIGH_DIRTY_STONE_TILES);
        addBuildingBlockHelpersToBimap(biMap, ModBlocks.DETAILED_STONE_TILES, ModBlocks.DIRTY_DETAILED_STONE_TILES);
        addBuildingBlockHelpersToBimap(biMap, ModBlocks.DIRTY_DETAILED_STONE_TILES, ModBlocks.HIGH_DIRTY_DETAILED_STONE_TILES);
        return biMap;
    };
    Supplier<BiMap<Block, Block>> DIRTY_LEVEL_DECREASES = Suppliers.memoize(() -> DIRTY_LEVEL_INCREASES.get().inverse());

    static Optional<Block> getDecreasedOxidationBlock(Block block) {
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
        return getDecreasedOxidationBlock(state.getBlock()).map(block -> BlockHelper.getStateWithProperties(block, state));
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