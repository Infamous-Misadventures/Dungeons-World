package com.infamous.dungeons_world.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

import java.util.Random;

import static net.minecraft.world.InteractionHand.OFF_HAND;


import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class DirtyPathBlock extends PathBlock implements Dirty {
    private final DirtLevel dirtLevel;

    public DirtyPathBlock(Properties properties, Block unshoveled, ResourceLocation sideBlock, DirtLevel dirtLevel) {
        super(properties.randomTicks(), unshoveled, sideBlock);
        this.dirtLevel = dirtLevel;
    }

    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random random) {
        this.tickDegradation(state, world, pos, random);
    }

    public boolean hasRandomTicks(BlockState state) {
        return Dirty.getIncreasedDirtyBlock(state.getBlock()).isPresent();
    }

    public DirtLevel getDegradationLevel() {
        return this.dirtLevel;
    }

    @Override
    public InteractionResult use(BlockState blockState, Level world, BlockPos blockPos, Player playerEntity, InteractionHand hand, BlockHitResult blockRayTraceResult) {
        ItemStack itemstack = playerEntity.getItemInHand(hand);
        if(!world.isClientSide() && !this.dirtLevel.equals(DirtLevel.UNAFFECTED) && itemstack.getItem().equals(Items.WATER_BUCKET) && hand.equals(OFF_HAND)){
            world.setBlockAndUpdate(blockPos, Dirty.getUnaffectedDirtyState(blockState));
        }
        return super.use(blockState, world, blockPos, playerEntity, hand, blockRayTraceResult);
    }
}