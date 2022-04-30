package com.infamous.dungeons_world.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class CreeperGraveBlock  extends Block {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    protected static final VoxelShape SOUTH_AABB = Block.box(2.0D, 0.0D, 12.0D, 14.0D, 14.0D, 16.0D);
    protected static final VoxelShape NORTH_AABB = Block.box(2.0D, 0.0D, 0.0D, 14.0D, 14.0D, 4.0D);
    protected static final VoxelShape WEST_AABB = Block.box(0.0D, 0.0D, 2.0D, 4.0D, 14.0D, 14.0D);
    protected static final VoxelShape EAST_AABB = Block.box(12.0D, 0.0D, 2.0D, 16.0D, 14.0D, 14.0D);

    public CreeperGraveBlock(Properties p_i48440_1_) {
        super(p_i48440_1_);
    }

    public VoxelShape getShape(BlockState p_220053_1_, BlockGetter p_220053_2_, BlockPos p_220053_3_, CollisionContext p_220053_4_) {
        Direction direction = p_220053_1_.getValue(FACING);
        switch(direction) {
            case EAST:
            default:
                return EAST_AABB;
            case SOUTH:
                return SOUTH_AABB;
            case WEST:
                return WEST_AABB;
            case NORTH:
                return NORTH_AABB;
        }
    }

    public BlockState getStateForPlacement(BlockPlaceContext useContext) {
        return this.defaultBlockState().setValue(FACING, useContext.getHorizontalDirection());
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(FACING);
    }

    public BlockState rotate(BlockState p_185499_1_, Rotation p_185499_2_) {
        return p_185499_1_.setValue(FACING, p_185499_2_.rotate(p_185499_1_.getValue(FACING)));
    }
}
