package com.infamous.dungeons_world.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class PathBlock extends Block {
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);

    private final Block unshoveled;
    private final ResourceLocation sideBlock;

    public PathBlock(Properties properties, Block unshoveled, ResourceLocation sideBlock) {
        super(properties);
        this.unshoveled = unshoveled;
        this.sideBlock = sideBlock;
    }

    public Block getUnshoveled() {
        return unshoveled;
    }

    public ResourceLocation getSideBlock() {
        return sideBlock;
    }

    public boolean useShapeForLightOcclusion(BlockState p_220074_1_) {
        return true;
    }

    public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
        return !this.defaultBlockState().canSurvive(p_196258_1_.getLevel(), p_196258_1_.getClickedPos()) ? Block.pushEntitiesUp(this.defaultBlockState(), unshoveled.defaultBlockState(), p_196258_1_.getLevel(), p_196258_1_.getClickedPos()) : super.getStateForPlacement(p_196258_1_);
    }

    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState1, IWorld world, BlockPos blockPos, BlockPos blockPos1) {
        if (direction == Direction.UP && !blockState.canSurvive(world, blockPos)) {
            world.getBlockTicks().scheduleTick(blockPos, this, 1);
        }

        return super.updateShape(blockState, direction, blockState1, world, blockPos, blockPos1);
    }

    public void tick(BlockState blockState, ServerWorld world, BlockPos blockPos, Random random) {
        world.setBlockAndUpdate(blockPos, pushEntitiesUp(blockState, unshoveled.defaultBlockState(), world, blockPos));
    }

    public boolean canSurvive(BlockState blockState, IWorldReader world, BlockPos blockPos) {
        BlockState blockstate = world.getBlockState(blockPos.above());
        return !blockstate.getMaterial().isSolid() || blockstate.getBlock() instanceof FenceGateBlock;
    }

    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return SHAPE;
    }

    public boolean isPathfindable(BlockState p_196266_1_, IBlockReader p_196266_2_, BlockPos p_196266_3_, PathType p_196266_4_) {
        return false;
    }
}
