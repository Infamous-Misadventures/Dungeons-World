package com.infamous.dungeons_world.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.server.level.ServerLevel;

import java.util.Random;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

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

    public BlockState getStateForPlacement(BlockPlaceContext p_196258_1_) {
        return !this.defaultBlockState().canSurvive(p_196258_1_.getLevel(), p_196258_1_.getClickedPos()) ? Block.pushEntitiesUp(this.defaultBlockState(), unshoveled.defaultBlockState(), p_196258_1_.getLevel(), p_196258_1_.getClickedPos()) : super.getStateForPlacement(p_196258_1_);
    }

    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState1, LevelAccessor world, BlockPos blockPos, BlockPos blockPos1) {
        if (direction == Direction.UP && !blockState.canSurvive(world, blockPos)) {
            world.scheduleTick(blockPos, this, 1);
        }

        return super.updateShape(blockState, direction, blockState1, world, blockPos, blockPos1);
    }

    public void tick(BlockState blockState, ServerLevel world, BlockPos blockPos, Random random) {
        world.setBlockAndUpdate(blockPos, pushEntitiesUp(blockState, unshoveled.defaultBlockState(), world, blockPos));
    }

    public boolean canSurvive(BlockState blockState, LevelReader world, BlockPos blockPos) {
        BlockState blockstate = world.getBlockState(blockPos.above());
        return !blockstate.getMaterial().isSolid() || blockstate.getBlock() instanceof FenceGateBlock;
    }

    public VoxelShape getShape(BlockState p_220053_1_, BlockGetter p_220053_2_, BlockPos p_220053_3_, CollisionContext p_220053_4_) {
        return SHAPE;
    }

    public boolean isPathfindable(BlockState p_196266_1_, BlockGetter p_196266_2_, BlockPos p_196266_3_, PathComputationType p_196266_4_) {
        return false;
    }
}
