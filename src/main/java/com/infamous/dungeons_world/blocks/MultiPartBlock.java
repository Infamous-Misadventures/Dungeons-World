package com.infamous.dungeons_world.blocks;

import com.infamous.dungeons_world.state.BlockPart;
import com.infamous.dungeons_world.state.BlockPartProperty;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;

import java.util.Collection;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public abstract class MultiPartBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    protected MultiPartBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockItemUseContext) {
        if (allBlocksPlaceAble(blockItemUseContext)) {
            return this.defaultBlockState().setValue(FACING, blockItemUseContext.getHorizontalDirection()).setValue(getBlockPartProperty(), getPlacementBlockPart());
        } else {
            return null;
        }
    }

    private boolean allBlocksPlaceAble(BlockPlaceContext blockItemUseContext) {
        BlockPos placementBlockPos = blockItemUseContext.getClickedPos();
        Level level = blockItemUseContext.getLevel();
        boolean result = true;
        Rotation rotation = getRotation(blockItemUseContext.getHorizontalDirection());
        BlockPos center = getPlacementBlockPart().getBlockPos();
        for (BlockPart blockPart : this.getBlockParts()) {
            BlockPos.MutableBlockPos newBlockPos = centerBlockPart(placementBlockPos, rotation, center, blockPart);
            result = result && newBlockPos.getY() < 256 && level.getBlockState(newBlockPos).canBeReplaced(blockItemUseContext);
        }
        return result;
    }


    public void setPlacedBy(Level level, BlockPos placementBlockPos, BlockState blockState, LivingEntity livingEntity, ItemStack itemStack) {
        Rotation rotation = getRotation(blockState.getValue(FACING));
        BlockPos center = getPlacementBlockPart().getBlockPos();
        this.getBlockParts().forEach(blockPart -> {
            BlockPos.MutableBlockPos newBlockPos = centerBlockPart(placementBlockPos, rotation, center, blockPart);
            level.setBlock(newBlockPos, blockState.setValue(getBlockPartProperty(), blockPart), 3);
        });
    }

    public boolean canSurvive(BlockState blockState1, LevelReader level, BlockPos blockPos) {
        boolean result = true;
        for (BlockPart blockPart : this.getBlockParts()) {
            result = result && blockPos.getY() + blockPart.getBlockPos().getY() - getPlacementBlockPart().getBlockPos().getY() < 256;
        }
        return result;
    }

    public BlockState updateShape(BlockState stateToUpdate, Direction direction, BlockState originState, LevelAccessor level, BlockPos toUpdateBlockPos, BlockPos originBlockPos) {
        if (sameMultiBlock(stateToUpdate, toUpdateBlockPos, originBlockPos) && !originState.is(this)) {
            return Blocks.AIR.defaultBlockState();
        } else {
            return stateToUpdate;
        }
    }

    protected boolean sameMultiBlock(BlockState blockState1, BlockPos blockPos1, BlockPos blockPos2) {
        Rotation rotation = getRotation(blockState1.getValue(FACING));
        BlockPos center = getPlacementBlockPart().getBlockPos();
        BlockPos.MutableBlockPos centerBlockPos = centerBlockPos(center, rotation, blockState1, blockPos1);
        for (BlockPart blockPart : this.getBlockParts()) {
            BlockPos.MutableBlockPos newBlockPos = centerBlockPart(centerBlockPos, rotation, center, blockPart);
            if (newBlockPos.equals(blockPos2)) {
                return true;
            }
        }
        return false;
    }

    private BlockPos.MutableBlockPos centerBlockPos(BlockPos center, Rotation rotation, BlockState blockState1, BlockPos blockPos1) {
        BlockPos.MutableBlockPos centeredBlockPartPos = centerBlockPart(center, rotation, center, blockState1.getValue(this.getBlockPartProperty())).move(-center.getX(), -center.getY(), -center.getZ());
        return blockPos1.mutable().move(-centeredBlockPartPos.getX(), -centeredBlockPartPos.getY(), -centeredBlockPartPos.getZ());
    }

    private BlockPos.MutableBlockPos centerBlockPart(BlockPos centerBlockPos, Rotation rotation, BlockPos centerBlockPartPos, BlockPart blockPart) {
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

    public void playerWillDestroy(Level level, BlockPos blockPos, BlockState blockState, Player playerEntity) {
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
