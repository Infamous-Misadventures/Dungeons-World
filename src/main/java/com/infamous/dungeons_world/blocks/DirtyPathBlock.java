package com.infamous.dungeons_world.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

import static net.minecraft.util.Hand.OFF_HAND;

public class DirtyPathBlock extends PathBlock implements Dirty {
    private final DirtLevel dirtLevel;

    public DirtyPathBlock(Properties properties, Block unshoveled, ResourceLocation sideBlock, DirtLevel dirtLevel) {
        super(properties.randomTicks(), unshoveled, sideBlock);
        this.dirtLevel = dirtLevel;
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        this.tickDegradation(state, world, pos, random);
    }

    public boolean hasRandomTicks(BlockState state) {
        return Dirty.getIncreasedDirtyBlock(state.getBlock()).isPresent();
    }

    public DirtLevel getDegradationLevel() {
        return this.dirtLevel;
    }

    @Override
    public ActionResultType use(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult blockRayTraceResult) {
        ItemStack itemstack = playerEntity.getItemInHand(hand);
        if(!world.isClientSide() && !this.dirtLevel.equals(DirtLevel.UNAFFECTED) && itemstack.getItem().equals(Items.WATER_BUCKET) && hand.equals(OFF_HAND)){
            world.setBlockAndUpdate(blockPos, Dirty.getUnaffectedDirtyState(blockState));
        }
        return super.use(blockState, world, blockPos, playerEntity, hand, blockRayTraceResult);
    }
}