package com.infamous.dungeons_world.blocks;

import com.infamous.dungeons_world.state.BlockPart;
import com.infamous.dungeons_world.state.BlockPartProperty;
import com.infamous.dungeons_world.util.VoxelShapeUtil;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.level.BlockGetter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.infamous.dungeons_world.state.BlockPart.*;
import static net.minecraft.core.Direction.*;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

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

    public VoxelShape getShape(BlockState p_220053_1_, BlockGetter p_220053_2_, BlockPos p_220053_3_, CollisionContext p_220053_4_) {
        Direction direction = p_220053_1_.getValue(FACING);
        String partNumber = p_220053_1_.getValue(PARTS).getPartNumber();
        if(!SHAPES.containsKey(partNumber) || !SHAPES.get(partNumber).containsKey(direction)){
            return Shapes.empty();
        }
        return SHAPES.get(partNumber).get(direction);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(FACING).add(PARTS);
    }

    public BlockState rotate(BlockState p_185499_1_, Rotation p_185499_2_) {
        return p_185499_1_.setValue(FACING, p_185499_2_.rotate(p_185499_1_.getValue(FACING)));
    }

    public static Map<String, Map<Direction, VoxelShape>> makeShapes(){
        Map<String, Map<Direction, VoxelShape>> shapes = new HashMap<>();
        VoxelShape shape110 = Shapes.empty();
        shape110 = Shapes.join(shape110, Shapes.box(0.3125, 0.25, 0.3125, 1, 1, 1), BooleanOp.OR);
        shape110 = Shapes.join(shape110, Shapes.box(0.1875, 0, 0.1875, 1, 0.25, 1), BooleanOp.OR);
        Map<Direction, VoxelShape> directions110 = new HashMap<>();
        directions110.put(NORTH, shape110);
        directions110.put(EAST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.EAST, shape110));
        directions110.put(WEST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.WEST, shape110));
        directions110.put(SOUTH, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.SOUTH, shape110));
        shapes.put("110", directions110);
        VoxelShape shape111 = Shapes.empty();
        shape111 = Shapes.join(shape111, Shapes.box(0.3125, 0.25, 0, 1, 1, 1), BooleanOp.OR);
        shape111 = Shapes.join(shape111, Shapes.box(0.1875, 0, 0, 1, 0.25, 1), BooleanOp.OR);
        Map<Direction, VoxelShape> directions111 = new HashMap<>();
        directions111.put(NORTH, shape111);
        directions111.put(EAST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.EAST, shape111));
        directions111.put(WEST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.WEST, shape111));
        directions111.put(SOUTH, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.SOUTH, shape111));
        shapes.put("111", directions111);
        VoxelShape shape112 = Shapes.empty();
        shape112 = Shapes.join(shape112, Shapes.box(0.3125, 0.25, 0, 1, 1, 0.6875), BooleanOp.OR);
        shape112 = Shapes.join(shape112, Shapes.box(0.1875, 0, 0, 1, 0.25, 0.8125), BooleanOp.OR);
        Map<Direction, VoxelShape> directions112 = new HashMap<>();
        directions112.put(NORTH, shape112);
        directions112.put(EAST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.EAST, shape112));
        directions112.put(WEST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.WEST, shape112));
        directions112.put(SOUTH, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.SOUTH, shape112));
        shapes.put("112", directions112);
        VoxelShape shape210 = Shapes.empty();
        shape210 = Shapes.join(shape210, Shapes.box(0, 0.25, 0.3125, 0.6875, 1, 1), BooleanOp.OR);
        shape210 = Shapes.join(shape210, Shapes.box(0, 0, 0.1875, 0.8125, 0.25, 1), BooleanOp.OR);
        Map<Direction, VoxelShape> directions210 = new HashMap<>();
        directions210.put(NORTH, shape210);
        directions210.put(EAST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.EAST, shape210));
        directions210.put(WEST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.WEST, shape210));
        directions210.put(SOUTH, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.SOUTH, shape210));
        shapes.put("210", directions210);
        VoxelShape shape211 = Shapes.empty();
        shape211 = Shapes.join(shape211, Shapes.box(0, 0.25, 0, 0.6875, 1, 1), BooleanOp.OR);
        shape211 = Shapes.join(shape211, Shapes.box(0, 0, 0, 0.8125, 0.25, 1), BooleanOp.OR);
        Map<Direction, VoxelShape> directions211 = new HashMap<>();
        directions211.put(NORTH, shape211);
        directions211.put(EAST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.EAST, shape211));
        directions211.put(WEST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.WEST, shape211));
        directions211.put(SOUTH, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.SOUTH, shape211));
        shapes.put("211", directions211);
        VoxelShape shape212 = Shapes.empty();
        shape212 = Shapes.join(shape212, Shapes.box(0, 0.25, 0, 0.6875, 1, 0.6875), BooleanOp.OR);
        shape212 = Shapes.join(shape212, Shapes.box(0, 0, 0, 0.8125, 0.25, 0.8125), BooleanOp.OR);
        Map<Direction, VoxelShape> directions212 = new HashMap<>();
        directions212.put(NORTH, shape212);
        directions212.put(EAST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.EAST, shape212));
        directions212.put(WEST, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.WEST, shape212));
        directions212.put(SOUTH, VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.SOUTH, shape212));
        shapes.put("212", directions212);

        return shapes;
    }
}
