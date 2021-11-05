package com.infamous.dungeons_world.blocks;

import com.infamous.dungeons_world.state.BlockPart;
import com.infamous.dungeons_world.state.BlockPartProperty;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class MultiPartBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BlockPartProperty PARTS = BlockPartProperty.create("part", (BlockPart blockPart) -> blockPart.getPartNumber() < 6);
    protected final BlockPos size;

    public MultiPartBlock(Properties properties, BlockPos size) {
        super(properties);
        this.size = size;
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(PARTS, BlockPart.COMPLETE));
//        this.PARTS = BlockPartProperty.create("part", (BlockPart blockPart) -> blockPart.getPartNumber() < size.getX()*size.getY()*size.getZZ();
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext blockItemUseContext) {
        if (allBlocksPlaceAble(blockItemUseContext)) {
            return this.defaultBlockState().setValue(FACING, blockItemUseContext.getHorizontalDirection()).setValue(PARTS, BlockPart.PART_0);
        } else {
            return null;
        }
    }

    private boolean allBlocksPlaceAble(BlockItemUseContext blockItemUseContext) {
        BlockPos blockPos = blockItemUseContext.getClickedPos();
        World level = blockItemUseContext.getLevel();
        AxisAlignedBB rotatedDimensions = getRotatedDimensions(blockItemUseContext.getHorizontalDirection());
        return blockPos.getY()+size.getY() < 256 && level.getBlockStates(rotatedDimensions.move(blockPos)).allMatch(blockState -> blockState.canBeReplaced(blockItemUseContext));
    }


    public void setPlacedBy(World level, BlockPos blockPos, BlockState blockState, LivingEntity livingEntity, ItemStack itemStack) {
        int counter = 0;
        Rotation rotation = getRotation(blockState.getValue(FACING));

        for (int x = 0; x < size.getX(); x++) {
            for (int y = 0; y < size.getY(); y++) {
                for (int z = 0; z < size.getZ(); z++) {
                    BlockPos.Mutable newBlockPos = new BlockPos(x, y, z).rotate(rotation).mutable().move(blockPos);
                    level.setBlock(newBlockPos, blockState.setValue(PARTS, BlockPart.getByPartNumber(counter)), 3);
                    counter++;
                }
            }
        }
    }

    public boolean canSurvive(BlockState blockState1, IWorldReader level, BlockPos blockPos) {
            return blockPos.getY() + size.getY() < 256;
//            return blockPos.getY() + dimensions.maxY + 1 < 256 && level.getBlockStates(dimensions.move(blockPos)).allMatch(blockState -> blockState.is(this));
    }

    public BlockState updateShape(BlockState stateToUpdate, Direction direction, BlockState originState, IWorld level, BlockPos toUpdateBlockPos, BlockPos originBlockPos) {
        if(sameMultiBlock(stateToUpdate, toUpdateBlockPos, originBlockPos) && !originState.is(this)){
            return Blocks.AIR.defaultBlockState();
        }else{
            return stateToUpdate;
        }
    }

    protected boolean sameMultiBlock(BlockState blockState1, BlockPos blockPos1, BlockPos blockPos2){
        AxisAlignedBB rotatedDimensions = getRotatedDimensions(blockState1.getValue(FACING));
        BlockPos origin1 = getOriginBlockPos(blockState1, blockPos1);
        AxisAlignedBB multiBlockPositions = rotatedDimensions.move(origin1);
        return multiBlockPositions.contains(blockPos2.getX(), blockPos2.getY(), blockPos2.getZ());
    }

    protected BlockPos getOriginBlockPos(BlockState blockState, BlockPos blockPos) {
        BlockPart blockPart = blockState.getValue(PARTS);
        int partNumber = blockPart.getPartNumber();
        int x = size.getX();
        int y = size.getY();
        int z = size.getZ();
        return new BlockPos(-(partNumber / (z * y)) % x, -(partNumber / z) % y, -partNumber % z).rotate(getRotation(blockState.getValue(FACING))).mutable().move(blockPos);
    }

    private AxisAlignedBB getRotatedDimensions(Direction facing) {
        BlockPos rotatedSize = size.rotate(getRotation(facing));
        BlockPos.Mutable sizeMutable = rotatedSize.mutable();
        BlockPos.Mutable zeroMutable = BlockPos.ZERO.mutable();
        return new AxisAlignedBB(zeroMutable.move(rotatedSize.getX() < 0 ? 1 : 0, rotatedSize.getY() < 0 ? 1 : 0, rotatedSize.getZ() < 0 ? 1 : 0), sizeMutable.move(rotatedSize.getX() < 0 ? 1 : 0, rotatedSize.getY() < 0 ? 1 : 0, rotatedSize.getZ() < 0 ? 1 : 0));
    }

    private Rotation getRotation(Direction facing) {
        switch(facing) {
            case NORTH://counter clockwise
                return Rotation.COUNTERCLOCKWISE_90;
            case SOUTH://clockwise
                return Rotation.CLOCKWISE_90;
            case WEST://opposite
                return Rotation.CLOCKWISE_180;
            default:
                return Rotation.NONE;
        }
    }

}
