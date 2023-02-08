package com.infamous.dungeons_world.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class WaterloggedSaplingBlock extends SaplingBlock implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public WaterloggedSaplingBlock(AbstractTreeGrower grower, Properties properties) {
        super(grower, properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(STAGE, Integer.valueOf(0)).setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_221484_) {
        p_221484_.add(STAGE).add(WATERLOGGED);
    }
    public BlockState updateShape(BlockState p_221477_, Direction p_221478_, BlockState p_221479_, LevelAccessor p_221480_, BlockPos p_221481_, BlockPos p_221482_) {
        if (p_221477_.getValue(WATERLOGGED)) {
            p_221480_.scheduleTick(p_221481_, Fluids.WATER, Fluids.WATER.getTickDelay(p_221480_));
        }

        return p_221478_ == Direction.UP && !p_221477_.canSurvive(p_221480_, p_221481_) ? Blocks.AIR.defaultBlockState() : super.updateShape(p_221477_, p_221478_, p_221479_, p_221480_, p_221481_, p_221482_);
    }
    public FluidState getFluidState(BlockState p_221494_) {
        return p_221494_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_221494_);
    }
}
