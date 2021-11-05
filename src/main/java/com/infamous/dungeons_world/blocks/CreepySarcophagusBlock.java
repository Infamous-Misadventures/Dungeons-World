package com.infamous.dungeons_world.blocks;

import com.infamous.dungeons_world.util.VoxelShapeUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import java.util.HashMap;
import java.util.Map;

import static net.minecraft.util.Direction.*;

public class CreepySarcophagusBlock extends MultiPartBlock {
      protected static final Map<Integer, Map<Direction, VoxelShape>> SHAPES = makeShapes();
//    protected static final VoxelShape EAST_AABB = VoxelShapes.or(Block.box(0, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), Block.box(2,4,2,16,16,16));
//    protected static final VoxelShape SOUTH_AABB = VoxelShapeUtil.rotateShape(Direction.EAST, Direction.SOUTH, EAST_AABB);
//    protected static final VoxelShape NORTH_AABB = VoxelShapeUtil.rotateShape(Direction.EAST, Direction.NORTH, EAST_AABB);
//    protected static final VoxelShape WEST_AABB = VoxelShapeUtil.rotateShape(Direction.EAST, Direction.WEST, EAST_AABB);

    public CreepySarcophagusBlock(Properties properties, BlockPos size) {
        super(properties, size);
    }

    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        Direction direction = p_220053_1_.getValue(FACING);
        int partNumber = p_220053_1_.getValue(PARTS).getPartNumber();
        if(!SHAPES.containsKey(partNumber) || !SHAPES.get(partNumber).containsKey(direction)){
            return VoxelShapes.empty();
        }
        return SHAPES.get(partNumber).get(direction);
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(FACING).add(PARTS);
    }

    public BlockState rotate(BlockState p_185499_1_, Rotation p_185499_2_) {
        return p_185499_1_.setValue(FACING, p_185499_2_.rotate(p_185499_1_.getValue(FACING)));
    }

    public static Map<Integer, Map<Direction, VoxelShape>> makeShapes(){
        Map<Integer, Map<Direction, VoxelShape>> shapes = new HashMap<>();
        VoxelShape shape0 = VoxelShapes.empty();
        shape0 = VoxelShapes.join(shape0, VoxelShapes.box(0.3125, 0.25, 0.3125, 1, 1, 1), IBooleanFunction.OR);
        shape0 = VoxelShapes.join(shape0, VoxelShapes.box(0.1875, 0, 0.1875, 1, 0.25, 1), IBooleanFunction.OR);
        Map<Direction, VoxelShape> directions0 = new HashMap<>();
        directions0.put(EAST, shape0);
        directions0.put(NORTH, VoxelShapeUtil.rotateShape(Direction.EAST, Direction.NORTH, shape0));
        directions0.put(WEST, VoxelShapeUtil.rotateShape(Direction.EAST, Direction.WEST, shape0));
        directions0.put(SOUTH, VoxelShapeUtil.rotateShape(Direction.EAST, Direction.SOUTH, shape0));
        shapes.put(0, directions0);
        VoxelShape shape1 = VoxelShapes.empty();
        shape1 = VoxelShapes.join(shape1, VoxelShapes.box(0.3125, 0.25, 0, 1, 1, 1), IBooleanFunction.OR);
        shape1 = VoxelShapes.join(shape1, VoxelShapes.box(0.1875, 0, 0, 1, 0.25, 1), IBooleanFunction.OR);
        Map<Direction, VoxelShape> directions1 = new HashMap<>();
        directions1.put(EAST, shape1);
        directions1.put(NORTH, VoxelShapeUtil.rotateShape(Direction.EAST, Direction.NORTH, shape1));
        directions1.put(WEST, VoxelShapeUtil.rotateShape(Direction.EAST, Direction.WEST, shape1));
        directions1.put(SOUTH, VoxelShapeUtil.rotateShape(Direction.EAST, Direction.SOUTH, shape1));
        shapes.put(1, directions1);
        VoxelShape shape2 = VoxelShapes.empty();
        shape2 = VoxelShapes.join(shape2, VoxelShapes.box(0.3125, 0.25, 0, 1, 1, 0.6875), IBooleanFunction.OR);
        shape2 = VoxelShapes.join(shape2, VoxelShapes.box(0.1875, 0, 0, 1, 0.25, 0.8125), IBooleanFunction.OR);
        Map<Direction, VoxelShape> directions2 = new HashMap<>();
        directions2.put(EAST, shape2);
        directions2.put(NORTH, VoxelShapeUtil.rotateShape(Direction.EAST, Direction.NORTH, shape2));
        directions2.put(WEST, VoxelShapeUtil.rotateShape(Direction.EAST, Direction.WEST, shape2));
        directions2.put(SOUTH, VoxelShapeUtil.rotateShape(Direction.EAST, Direction.SOUTH, shape2));
        shapes.put(2, directions2);
        VoxelShape shape3 = VoxelShapes.empty();
        shape3 = VoxelShapes.join(shape3, VoxelShapes.box(0, 0.25, 0.3125, 0.6875, 1, 1), IBooleanFunction.OR);
        shape3 = VoxelShapes.join(shape3, VoxelShapes.box(0, 0, 0.1875, 0.8125, 0.25, 1), IBooleanFunction.OR);
        Map<Direction, VoxelShape> directions3 = new HashMap<>();
        directions3.put(EAST, shape3);
        directions3.put(NORTH, VoxelShapeUtil.rotateShape(Direction.EAST, Direction.NORTH, shape3));
        directions3.put(WEST, VoxelShapeUtil.rotateShape(Direction.EAST, Direction.WEST, shape3));
        directions3.put(SOUTH, VoxelShapeUtil.rotateShape(Direction.EAST, Direction.SOUTH, shape3));
        shapes.put(3, directions3);
        VoxelShape shape4 = VoxelShapes.empty();
        shape4 = VoxelShapes.join(shape4, VoxelShapes.box(0, 0.25, 0, 0.6875, 1, 1), IBooleanFunction.OR);
        shape4 = VoxelShapes.join(shape4, VoxelShapes.box(0, 0, 0, 0.8125, 0.25, 1), IBooleanFunction.OR);
        Map<Direction, VoxelShape> directions4 = new HashMap<>();
        directions4.put(EAST, shape4);
        directions4.put(NORTH, VoxelShapeUtil.rotateShape(Direction.EAST, Direction.NORTH, shape4));
        directions4.put(WEST, VoxelShapeUtil.rotateShape(Direction.EAST, Direction.WEST, shape4));
        directions4.put(SOUTH, VoxelShapeUtil.rotateShape(Direction.EAST, Direction.SOUTH, shape4));
        shapes.put(4, directions4);
        VoxelShape shape5 = VoxelShapes.empty();
        shape5 = VoxelShapes.join(shape5, VoxelShapes.box(0, 0.25, 0, 0.6875, 1, 0.6875), IBooleanFunction.OR);
        shape5 = VoxelShapes.join(shape5, VoxelShapes.box(0, 0, 0, 0.8125, 0.25, 0.8125), IBooleanFunction.OR);
        Map<Direction, VoxelShape> directions5 = new HashMap<>();
        directions5.put(EAST, shape5);
        directions5.put(NORTH, VoxelShapeUtil.rotateShape(Direction.EAST, Direction.NORTH, shape5));
        directions5.put(WEST, VoxelShapeUtil.rotateShape(Direction.EAST, Direction.WEST, shape5));
        directions5.put(SOUTH, VoxelShapeUtil.rotateShape(Direction.EAST, Direction.SOUTH, shape5));
        shapes.put(5, directions5);

        return shapes;
    }
}
