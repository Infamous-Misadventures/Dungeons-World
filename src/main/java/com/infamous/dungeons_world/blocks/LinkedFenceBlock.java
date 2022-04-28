package com.infamous.dungeons_world.blocks;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import static net.minecraft.util.Direction.NORTH;

public class LinkedFenceBlock extends HorizontalBlock {
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
    public VoxelShape getShape(BlockState blockState, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return shapeByIndex[getAABBIndex(blockState)];
    }

    @Override
    public VoxelShape getCollisionShape(BlockState blockState, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return collisionShapeByIndex[getAABBIndex(blockState)];
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(FACING, OPEN);
    }

    public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
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
