package com.infamous.dungeons_world.blocks;

import com.infamous.dungeons_world.state.BlockPart;
import com.infamous.dungeons_world.state.BlockPartProperty;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import java.util.Collection;

public abstract class MultiPartBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    protected MultiPartBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext blockItemUseContext) {
        if (allBlocksPlaceAble(blockItemUseContext)) {
            return this.defaultBlockState().setValue(FACING, blockItemUseContext.getHorizontalDirection()).setValue(getBlockPartProperty(), getPlacementBlockPart());
        } else {
            return null;
        }
    }

    private boolean allBlocksPlaceAble(BlockItemUseContext blockItemUseContext) {
        BlockPos placementBlockPos = blockItemUseContext.getClickedPos();
        World level = blockItemUseContext.getLevel();
        boolean result = true;
        Rotation rotation = getRotation(blockItemUseContext.getHorizontalDirection());
        BlockPos center = getPlacementBlockPart().getBlockPos();
        for (BlockPart blockPart : this.getBlockParts()) {
            BlockPos.Mutable newBlockPos = centerBlockPart(placementBlockPos, rotation, center, blockPart);
            result = result && newBlockPos.getY() < 256 && level.getBlockState(newBlockPos).canBeReplaced(blockItemUseContext);
        }
        return result;
    }


    public void setPlacedBy(World level, BlockPos placementBlockPos, BlockState blockState, LivingEntity livingEntity, ItemStack itemStack) {
        Rotation rotation = getRotation(blockState.getValue(FACING));
        BlockPos center = getPlacementBlockPart().getBlockPos();
        this.getBlockParts().forEach(blockPart -> {
            BlockPos.Mutable newBlockPos = centerBlockPart(placementBlockPos, rotation, center, blockPart);
            level.setBlock(newBlockPos, blockState.setValue(getBlockPartProperty(), blockPart), 3);
        });
    }

    public boolean canSurvive(BlockState blockState1, IWorldReader level, BlockPos blockPos) {
        boolean result = true;
        for (BlockPart blockPart : this.getBlockParts()) {
            result = result && blockPos.getY() + blockPart.getBlockPos().getY() - getPlacementBlockPart().getBlockPos().getY() < 256;
        }
        return result;
    }

    public BlockState updateShape(BlockState stateToUpdate, Direction direction, BlockState originState, IWorld level, BlockPos toUpdateBlockPos, BlockPos originBlockPos) {
        if (sameMultiBlock(stateToUpdate, toUpdateBlockPos, originBlockPos) && !originState.is(this)) {
            return Blocks.AIR.defaultBlockState();
        } else {
            return stateToUpdate;
        }
    }

    protected boolean sameMultiBlock(BlockState blockState1, BlockPos blockPos1, BlockPos blockPos2) {
        Rotation rotation = getRotation(blockState1.getValue(FACING));
        BlockPos center = getPlacementBlockPart().getBlockPos();
        BlockPos.Mutable centerBlockPos = centerBlockPos(center, rotation, blockState1, blockPos1);
        for (BlockPart blockPart : this.getBlockParts()) {
            BlockPos.Mutable newBlockPos = centerBlockPart(centerBlockPos, rotation, center, blockPart);
            if (newBlockPos.equals(blockPos2)) {
                return true;
            }
        }
        return false;
    }

    private BlockPos.Mutable centerBlockPos(BlockPos center, Rotation rotation, BlockState blockState1, BlockPos blockPos1) {
        BlockPos.Mutable centeredBlockPartPos = centerBlockPart(center, rotation, center, blockState1.getValue(this.getBlockPartProperty())).move(-center.getX(), -center.getY(), -center.getZ());
        return blockPos1.mutable().move(-centeredBlockPartPos.getX(), -centeredBlockPartPos.getY(), -centeredBlockPartPos.getZ());
    }

    private BlockPos.Mutable centerBlockPart(BlockPos centerBlockPos, Rotation rotation, BlockPos centerBlockPartPos, BlockPart blockPart) {
        return blockPart.getBlockPos().mutable().move(new BlockPos(-centerBlockPartPos.getX(), -centerBlockPartPos.getY(), -centerBlockPartPos.getZ())).rotate(rotation).mutable().move(centerBlockPos);
    }

    private Rotation getRotation(Direction facing) {
        switch (facing) {
            case WEST://counter clockwise
                return Rotation.COUNTERCLOCKWISE_90;
            case EAST://clockwise
                return Rotation.CLOCKWISE_90;
            case SOUTH://opposite
                return Rotation.CLOCKWISE_180;
            default:
                return Rotation.NONE;
        }
    }

    public void playerWillDestroy(World level, BlockPos blockPos, BlockState blockState, PlayerEntity playerEntity) {
        if (!level.isClientSide && playerEntity.isCreative()) {
            BlockPart blockPart = blockState.getValue(getBlockPartProperty());
            if (blockPart != getPlacementBlockPart()) {
                Rotation rotation = getRotation(blockState.getValue(FACING));
                BlockPos center = getPlacementBlockPart().getBlockPos();
                BlockPos centerBlockPos = centerBlockPos(center, rotation, blockState, blockPos);
                BlockState centerBlockState = level.getBlockState(centerBlockPos);
                if (centerBlockState.getBlock() == blockState.getBlock() && centerBlockState.getValue(getBlockPartProperty()) == getPlacementBlockPart()) {
                    level.setBlock(centerBlockPos, Blocks.AIR.defaultBlockState(), 35);
                    level.levelEvent(playerEntity, 2001, centerBlockPos, Block.getId(centerBlockState));
                }
            }

        }

        super.playerWillDestroy(level, blockPos, blockState, playerEntity);
    }

    abstract public Collection<BlockPart> getBlockParts();

    abstract public BlockPartProperty getBlockPartProperty();

    abstract public BlockPart getPlacementBlockPart();

}
