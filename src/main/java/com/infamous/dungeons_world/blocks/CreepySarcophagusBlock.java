package com.infamous.dungeons_world.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class CreepySarcophagusBlock extends Block {
    public static final DirectionProperty FACING = HorizontalBlock.FACING;
    protected static final VoxelShape SOUTH_AABB = Block.box(-16.0D, 0.0D, 0.0D, 32.0D, 16.0D, 32.0D);
    protected static final VoxelShape NORTH_AABB = Block.box(-16.0D, 0.0D, 0.0D, 32.0D, 16.0D, 32.0D);
    protected static final VoxelShape WEST_AABB = Block.box(-16.0D, 0.0D, 0.0D, 32.0D, 16.0D, 32.0D);
    protected static final VoxelShape EAST_AABB = Block.box(-16.0D, 0.0D, 0.0D, 32.0D, 16.0D, 32.0D);

    public CreepySarcophagusBlock(Properties p_i48440_1_) {
        super(p_i48440_1_);
    }

    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
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

    public BlockState getStateForPlacement(BlockItemUseContext useContext) {
        return this.defaultBlockState().setValue(FACING, useContext.getHorizontalDirection());
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(FACING);
    }

    public BlockState rotate(BlockState p_185499_1_, Rotation p_185499_2_) {
        return p_185499_1_.setValue(FACING, p_185499_2_.rotate(p_185499_1_.getValue(FACING)));
    }
}
