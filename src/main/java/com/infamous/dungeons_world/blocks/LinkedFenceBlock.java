package com.infamous.dungeons_world.blocks;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import static net.minecraft.core.Direction.NORTH;

public class LinkedFenceBlock extends HorizontalDirectionalBlock {
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    protected final VoxelShape[] collisionShapeByIndex;
    protected final VoxelShape[] shapeByIndex;
    private final Object2IntMap<BlockState> stateToIndex = new Object2IntOpenHashMap<>();

    protected LinkedFenceBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, NORTH).setValue(OPEN, false));
        this.collisionShapeByIndex = this.makeShapes(24D);
        this.shapeByIndex = this.makeShapes(16D);
    }

    protected VoxelShape[] makeShapes(double height) {
        return new VoxelShape[]{
                Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
                Block.box(0.0D, 0.0D, 14.0D, 16.0D, height, 16.0D),
                Block.box(0.0D, 0.0D, 0.0D, 16.0D, height, 2.0D),
                Block.box(14.0D, 0.0D, 0.0D, 16.0D, height, 16.0D),
                Block.box(0.0D, 0.0D, 0.0D, 2.0D, height, 16.0D),
        };
    }

    protected int getAABBIndex(BlockState blockState) {
        return this.stateToIndex.computeIntIfAbsent(blockState, (blockState1) -> {
            switch(blockState1.getValue(FACING)){
                case NORTH: return 1;
                case SOUTH: return 2;
                case WEST: return 3;
                case EAST: return 4;
                default: return 0;
            }
        });
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter p_220053_2_, BlockPos p_220053_3_, CollisionContext p_220053_4_) {
        return shapeByIndex[getAABBIndex(blockState)];
    }

    @Override
    public VoxelShape getCollisionShape(BlockState blockState, BlockGetter p_220053_2_, BlockPos p_220053_3_, CollisionContext p_220053_4_) {
        return collisionShapeByIndex[getAABBIndex(blockState)];
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(FACING, OPEN);
    }

    public BlockState getStateForPlacement(BlockPlaceContext p_196258_1_) {
        BlockState blockstate = this.defaultBlockState();
        Direction direction = p_196258_1_.getClickedFace();
        if (!p_196258_1_.replacingClickedOnBlock() && direction.getAxis().isHorizontal()) {
            blockstate = blockstate.setValue(FACING, direction);
        } else {
            blockstate = blockstate.setValue(FACING, p_196258_1_.getHorizontalDirection().getOpposite());
        }

        return blockstate;
    }
}
