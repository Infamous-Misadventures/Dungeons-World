package com.infamous.dungeons_world.blocks;

import com.infamous.dungeons_world.state.BlockPart;
import com.infamous.dungeons_world.state.BlockPartProperty;
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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.infamous.dungeons_world.state.BlockPart.*;
import static net.minecraft.util.Direction.*;

public class CreepySarcophagusBlock extends MultiPartBlock {
    protected static final Map<String, Map<Direction, VoxelShape>> SHAPES = makeShapes();
    public static final BlockPartProperty PARTS = BlockPartProperty.create("part", PART_110, PART_111, PART_112, PART_210, PART_211, PART_212);

    public CreepySarcophagusBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(PARTS, BlockPart.COMPLETE));
    }

    @Override
    public Collection<BlockPart> getBlockParts() {
        return PARTS.getPossibleValues().stream().filter(blockPart -> !blockPart.equals(COMPLETE)).collect(Collectors.toList());
    }

    @Override
    public BlockPartProperty getBlockPartProperty() {
        return PARTS;
    }

    @Override
    public BlockPart getPlacementBlockPart() {
        return PART_112;
    }

    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        Direction direction = p_220053_1_.getValue(FACING);
        String partNumber = p_220053_1_.getValue(PARTS).getPartNumber();
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

    public static Map<String, Map<Direction, VoxelShape>> makeShapes(){
        Map<String, Map<Direction, VoxelShape>> shapes = new HashMap<>();
        VoxelShape shape110 = VoxelShapes.empty();
        shape110 = VoxelShapes.join(shape110, VoxelShapes.box(0.3125, 0.25, 0.3125, 1, 1, 1), IBooleanFunction.OR);
        shape110 = VoxelShapes.join(shape110, VoxelShapes.box(0.1875, 0, 0.1875, 1, 0.25, 1), IBooleanFunction.OR);
        Map<Direction, VoxelShape> directions110 = new HashMap<>();
        directions110.put(NORTH, shape110);
        directions110.put(EAST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.EAST, shape110));
        directions110.put(WEST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.WEST, shape110));
        directions110.put(SOUTH, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.SOUTH, shape110));
        shapes.put("110", directions110);
        VoxelShape shape111 = VoxelShapes.empty();
        shape111 = VoxelShapes.join(shape111, VoxelShapes.box(0.3125, 0.25, 0, 1, 1, 1), IBooleanFunction.OR);
        shape111 = VoxelShapes.join(shape111, VoxelShapes.box(0.1875, 0, 0, 1, 0.25, 1), IBooleanFunction.OR);
        Map<Direction, VoxelShape> directions111 = new HashMap<>();
        directions111.put(NORTH, shape111);
        directions111.put(EAST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.EAST, shape111));
        directions111.put(WEST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.WEST, shape111));
        directions111.put(SOUTH, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.SOUTH, shape111));
        shapes.put("111", directions111);
        VoxelShape shape112 = VoxelShapes.empty();
        shape112 = VoxelShapes.join(shape112, VoxelShapes.box(0.3125, 0.25, 0, 1, 1, 0.6875), IBooleanFunction.OR);
        shape112 = VoxelShapes.join(shape112, VoxelShapes.box(0.1875, 0, 0, 1, 0.25, 0.8125), IBooleanFunction.OR);
        Map<Direction, VoxelShape> directions112 = new HashMap<>();
        directions112.put(NORTH, shape112);
        directions112.put(EAST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.EAST, shape112));
        directions112.put(WEST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.WEST, shape112));
        directions112.put(SOUTH, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.SOUTH, shape112));
        shapes.put("112", directions112);
        VoxelShape shape210 = VoxelShapes.empty();
        shape210 = VoxelShapes.join(shape210, VoxelShapes.box(0, 0.25, 0.3125, 0.6875, 1, 1), IBooleanFunction.OR);
        shape210 = VoxelShapes.join(shape210, VoxelShapes.box(0, 0, 0.1875, 0.8125, 0.25, 1), IBooleanFunction.OR);
        Map<Direction, VoxelShape> directions210 = new HashMap<>();
        directions210.put(NORTH, shape210);
        directions210.put(EAST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.EAST, shape210));
        directions210.put(WEST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.WEST, shape210));
        directions210.put(SOUTH, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.SOUTH, shape210));
        shapes.put("210", directions210);
        VoxelShape shape211 = VoxelShapes.empty();
        shape211 = VoxelShapes.join(shape211, VoxelShapes.box(0, 0.25, 0, 0.6875, 1, 1), IBooleanFunction.OR);
        shape211 = VoxelShapes.join(shape211, VoxelShapes.box(0, 0, 0, 0.8125, 0.25, 1), IBooleanFunction.OR);
        Map<Direction, VoxelShape> directions211 = new HashMap<>();
        directions211.put(NORTH, shape211);
        directions211.put(EAST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.EAST, shape211));
        directions211.put(WEST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.WEST, shape211));
        directions211.put(SOUTH, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.SOUTH, shape211));
        shapes.put("211", directions211);
        VoxelShape shape212 = VoxelShapes.empty();
        shape212 = VoxelShapes.join(shape212, VoxelShapes.box(0, 0.25, 0, 0.6875, 1, 0.6875), IBooleanFunction.OR);
        shape212 = VoxelShapes.join(shape212, VoxelShapes.box(0, 0, 0, 0.8125, 0.25, 0.8125), IBooleanFunction.OR);
        Map<Direction, VoxelShape> directions212 = new HashMap<>();
        directions212.put(NORTH, shape212);
        directions212.put(EAST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.EAST, shape212));
        directions212.put(WEST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.WEST, shape212));
        directions212.put(SOUTH, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.SOUTH, shape212));
        shapes.put("212", directions212);

        return shapes;
    }
}
