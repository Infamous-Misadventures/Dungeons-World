package com.infamous.dungeons_world.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import static net.minecraft.world.InteractionHand.OFF_HAND;

public class DirtyBlock extends Block implements Dirty {
    private final DirtLevel dirtLevel;

    public DirtyBlock(DirtLevel dirtLevel, BlockBehaviour.Properties settings) {
        super(settings.randomTicks());
        this.dirtLevel = dirtLevel;
    }

    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        this.onRandomTick(state, world, pos, random);
    }

    public boolean hasRandomTicks(BlockState state) {
        return Dirty.getIncreasedDirtyBlock(state.getBlock()).isPresent();
    }

    public DirtLevel getAge() {
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