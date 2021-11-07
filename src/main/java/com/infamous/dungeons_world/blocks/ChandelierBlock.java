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
import static com.infamous.dungeons_world.state.BlockPart.PART_212;
import static net.minecraft.util.Direction.*;

public class ChandelierBlock extends MultiPartBlock {
    protected static final Map<String, Map<Direction, VoxelShape>> SHAPES = makeShapes();
    public static final BlockPartProperty PARTS = BlockPartProperty.create("part", PART_010, PART_011, PART_012, PART_110, PART_111, PART_112, PART_121, PART_210, PART_211, PART_212);

    public ChandelierBlock(Properties properties) {
        super(properties);
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
        return PART_111;
    }

    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        Direction direction = p_220053_1_.getValue(FACING);
        String partNumber = p_220053_1_.getValue(getBlockPartProperty()).getPartNumber();
        if(!SHAPES.containsKey(partNumber) || !SHAPES.get(partNumber).containsKey(direction)){
            return VoxelShapes.empty();
        }
        return SHAPES.get(partNumber).get(direction);
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(FACING).add(getBlockPartProperty());
    }

    public BlockState rotate(BlockState p_185499_1_, Rotation p_185499_2_) {
        return p_185499_1_.setValue(FACING, p_185499_2_.rotate(p_185499_1_.getValue(FACING)));
    }

    public static Map<String, Map<Direction, VoxelShape>> makeShapes(){
        Map<String, Map<Direction, VoxelShape>> shapes = new HashMap<>();
        VoxelShape shape121 = VoxelShapes.empty();
        shape121 = VoxelShapes.join(shape121, VoxelShapes.box(0.25, 0.875, 0.25, 0.75, 1, 0.75), IBooleanFunction.OR);
        shape121 = VoxelShapes.join(shape121, VoxelShapes.box(0.375, 0, 0.375, 0.625, 0.875, 0.625), IBooleanFunction.OR);
        shape121 = VoxelShapes.join(shape121, VoxelShapes.box(0.125, 0.375, 0.4375, 0.875, 0.5, 0.5625), IBooleanFunction.OR);
        shape121 = VoxelShapes.join(shape121, VoxelShapes.box(0.4375, 0.375, 0.125, 0.5625, 0.5, 0.875), IBooleanFunction.OR);
        shape121 = VoxelShapes.join(shape121, VoxelShapes.box(0, 0.375, 0, 1, 0.5, 0.125), IBooleanFunction.OR);
        shape121 = VoxelShapes.join(shape121, VoxelShapes.box(0, 0.375, 0.875, 1, 0.5, 1), IBooleanFunction.OR);
        shape121 = VoxelShapes.join(shape121, VoxelShapes.box(0.875, 0.375, 0, 1, 0.5, 1), IBooleanFunction.OR);
        shape121 = VoxelShapes.join(shape121, VoxelShapes.box(0, 0.375, 0, 0.125, 0.5, 1), IBooleanFunction.OR);
        Map<Direction, VoxelShape> directions121 = new HashMap<>();
        directions121.put(NORTH, shape121);
        directions121.put(EAST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.EAST, shape121));
        directions121.put(WEST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.WEST, shape121));
        directions121.put(SOUTH, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.SOUTH, shape121));
        shapes.put("121", directions121);
        VoxelShape shape111 = VoxelShapes.empty();
        shape111 = VoxelShapes.join(shape111, VoxelShapes.box(0.25, 0, 0.25, 0.75, 0.375, 0.75), IBooleanFunction.OR);
        shape111 = VoxelShapes.join(shape111, VoxelShapes.box(0.375, 0.0625, 0.375, 0.625, 1, 0.625), IBooleanFunction.OR);
        shape111 = VoxelShapes.join(shape111, VoxelShapes.box(0, 0.8125, 0.4375, 1, 0.9375, 0.5625), IBooleanFunction.OR);
        shape111 = VoxelShapes.join(shape111, VoxelShapes.box(0.4375, 0.8125, 0, 0.5625, 0.9375, 1), IBooleanFunction.OR);
        Map<Direction, VoxelShape> directions111 = new HashMap<>();
        directions111.put(NORTH, shape111);
        directions111.put(EAST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.EAST, shape111));
        directions111.put(WEST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.WEST, shape111));
        directions111.put(SOUTH, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.SOUTH, shape111));
        shapes.put("111", directions111);
        VoxelShape shape011 = VoxelShapes.empty();
        shape011 = VoxelShapes.join(shape011, VoxelShapes.box(0.75, 0.8125, 0.4375, 1, 0.9375, 0.5625), IBooleanFunction.OR);
        shape011 = VoxelShapes.join(shape011, VoxelShapes.box(0.5000000000000002, 0.75, 0, 0.7500000000000002, 1, 1), IBooleanFunction.OR);
        Map<Direction, VoxelShape> directions011 = new HashMap<>();
        directions011.put(NORTH, shape011);
        directions011.put(EAST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.EAST, shape011));
        directions011.put(WEST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.WEST, shape011));
        directions011.put(SOUTH, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.SOUTH, shape011));
        shapes.put("011", directions011);
        VoxelShape shape211 = VoxelShapes.empty();
        shape211 = VoxelShapes.join(shape211, VoxelShapes.box(0, 0.8125, 0.4375, 0.25, 0.9375, 0.5625), IBooleanFunction.OR);
        shape211 = VoxelShapes.join(shape211, VoxelShapes.box(0.2500000000000002, 0.75, 0, 0.5000000000000002, 1, 1), IBooleanFunction.OR);
        Map<Direction, VoxelShape> directions211 = new HashMap<>();
        directions211.put(NORTH, shape211);
        directions211.put(EAST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.EAST, shape211));
        directions211.put(WEST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.WEST, shape211));
        directions211.put(SOUTH, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.SOUTH, shape211));
        shapes.put("211", directions211);
        VoxelShape shape110 = VoxelShapes.empty();
        shape110 = VoxelShapes.join(shape110, VoxelShapes.box(0.4375, 0.8125, 0.75, 0.5625, 0.9375, 1), IBooleanFunction.OR);
        shape110 = VoxelShapes.join(shape110, VoxelShapes.box(0, 0.75, 0.5, 1, 1, 0.75), IBooleanFunction.OR);
        Map<Direction, VoxelShape> directions110 = new HashMap<>();
        directions110.put(NORTH, shape110);
        directions110.put(EAST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.EAST, shape110));
        directions110.put(WEST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.WEST, shape110));
        directions110.put(SOUTH, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.SOUTH, shape110));
        shapes.put("110", directions110);
        VoxelShape shape112 = VoxelShapes.empty();
        shape112 = VoxelShapes.join(shape112, VoxelShapes.box(0.4375, 0.8125, 0, 0.5625, 0.9375, 0.25), IBooleanFunction.OR);
        shape112 = VoxelShapes.join(shape112, VoxelShapes.box(0, 0.75, 0.2500000000000002, 1, 1, 0.5000000000000002), IBooleanFunction.OR);
        Map<Direction, VoxelShape> directions112 = new HashMap<>();
        directions112.put(NORTH, shape112);
        directions112.put(EAST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.EAST, shape112));
        directions112.put(WEST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.WEST, shape112));
        directions112.put(SOUTH, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.SOUTH, shape112));
        shapes.put("112", directions112);
        VoxelShape shape010 = VoxelShapes.empty();
        shape010 = VoxelShapes.join(shape010, VoxelShapes.box(0.5, 0.75, 0.5, 1, 1, 0.75), IBooleanFunction.OR);
        shape010 = VoxelShapes.join(shape010, VoxelShapes.box(0.5000000000000002, 0.75, 0.5, 0.7500000000000002, 1, 1), IBooleanFunction.OR);
        Map<Direction, VoxelShape> directions010 = new HashMap<>();
        directions010.put(NORTH, shape010);
        directions010.put(EAST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.EAST, shape010));
        directions010.put(WEST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.WEST, shape010));
        directions010.put(SOUTH, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.SOUTH, shape010));
        shapes.put("010", directions010);
        VoxelShape shape210 = VoxelShapes.empty();
        shape210 = VoxelShapes.join(shape210, VoxelShapes.box(0, 0.75, 0.5, 0.5, 1, 0.75), IBooleanFunction.OR);
        shape210 = VoxelShapes.join(shape210, VoxelShapes.box(0.2500000000000002, 0.75, 0.5, 0.5000000000000002, 1, 1), IBooleanFunction.OR);
        Map<Direction, VoxelShape> directions210 = new HashMap<>();
        directions210.put(NORTH, shape210);
        directions210.put(EAST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.EAST, shape210));
        directions210.put(WEST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.WEST, shape210));
        directions210.put(SOUTH, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.SOUTH, shape210));
        shapes.put("210", directions210);
        VoxelShape shape012 = VoxelShapes.empty();
        shape012 = VoxelShapes.join(shape012, VoxelShapes.box(0.5000000000000004, 0.75, 0.2500000000000002, 1, 1, 0.5000000000000002), IBooleanFunction.OR);
        shape012 = VoxelShapes.join(shape012, VoxelShapes.box(0.5000000000000002, 0.75, 0, 0.7500000000000002, 1, 0.5), IBooleanFunction.OR);
        Map<Direction, VoxelShape> directions012 = new HashMap<>();
        directions012.put(NORTH, shape012);
        directions012.put(EAST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.EAST, shape012));
        directions012.put(WEST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.WEST, shape012));
        directions012.put(SOUTH, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.SOUTH, shape012));
        shapes.put("012", directions012);
        VoxelShape shape212 = VoxelShapes.empty();
        shape212 = VoxelShapes.join(shape212, VoxelShapes.box(0, 0.75, 0.2500000000000002, 0.5000000000000002, 1, 0.5000000000000002), IBooleanFunction.OR);
        shape212 = VoxelShapes.join(shape212, VoxelShapes.box(0.2500000000000002, 0.75, 0, 0.5000000000000002, 1, 0.5), IBooleanFunction.OR);
        Map<Direction, VoxelShape> directions212 = new HashMap<>();
        directions212.put(NORTH, shape212);
        directions212.put(EAST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.EAST, shape212));
        directions212.put(WEST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.WEST, shape212));
        directions212.put(SOUTH, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.SOUTH, shape212));
        shapes.put("212", directions212);

        return shapes;
    }
}
