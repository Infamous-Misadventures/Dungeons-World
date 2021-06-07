package com.infamous.dungeons_world.blocks;

import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class GlowingMushroomBlock  extends BushBlock implements IGrowable {
    public static final IntegerProperty MUSHROOMS = IntegerProperty.create("mushrooms", 1, 4);
    public static final BooleanProperty SQUEEZED = BooleanProperty.create("squeezed");;
    protected static final VoxelShape ONE_AABB = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 6.0D, 10.0D);
    protected static final VoxelShape TWO_AABB = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 6.0D, 13.0D);
    protected static final VoxelShape THREE_AABB = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 6.0D, 14.0D);
    protected static final VoxelShape FOUR_AABB = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 7.0D, 14.0D);

    public GlowingMushroomBlock(AbstractBlock.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(MUSHROOMS, 1).setValue(SQUEEZED, Boolean.FALSE));
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
        BlockState blockstate = p_196258_1_.getLevel().getBlockState(p_196258_1_.getClickedPos());
        if (blockstate.is(this)) {
            return blockstate.setValue(MUSHROOMS, Integer.valueOf(Math.min(4, blockstate.getValue(MUSHROOMS) + 1)));
        } else {
            return super.getStateForPlacement(p_196258_1_);
        }
    }

    public static boolean isSqueezed(BlockState blockState) {
        return blockState.getValue(SQUEEZED);
    }

    protected boolean mayPlaceOn(BlockState p_200014_1_, IBlockReader p_200014_2_, BlockPos p_200014_3_) {
        return !p_200014_1_.getCollisionShape(p_200014_2_, p_200014_3_).getFaceShape(Direction.UP).isEmpty() || p_200014_1_.isFaceSturdy(p_200014_2_, p_200014_3_, Direction.UP);
    }

    public boolean canSurvive(BlockState p_196260_1_, IWorldReader p_196260_2_, BlockPos p_196260_3_) {
        BlockPos blockpos = p_196260_3_.below();
        return this.mayPlaceOn(p_196260_2_.getBlockState(blockpos), p_196260_2_, blockpos);
    }

    public BlockState updateShape(BlockState p_196271_1_, Direction p_196271_2_, BlockState p_196271_3_, IWorld p_196271_4_, BlockPos p_196271_5_, BlockPos p_196271_6_) {
        if (!p_196271_1_.canSurvive(p_196271_4_, p_196271_5_)) {
            return Blocks.AIR.defaultBlockState();
        } else {
            return super.updateShape(p_196271_1_, p_196271_2_, p_196271_3_, p_196271_4_, p_196271_5_, p_196271_6_);
        }
    }

    public boolean canBeReplaced(BlockState p_196253_1_, BlockItemUseContext p_196253_2_) {
        return p_196253_2_.getItemInHand().getItem() == this.asItem() && p_196253_1_.getValue(MUSHROOMS) < 4 ? true : super.canBeReplaced(p_196253_1_, p_196253_2_);
    }

    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        switch(p_220053_1_.getValue(MUSHROOMS)) {
            case 1:
            default:
                return ONE_AABB;
            case 2:
                return TWO_AABB;
            case 3:
                return THREE_AABB;
            case 4:
                return FOUR_AABB;
        }
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(MUSHROOMS, SQUEEZED);
    }

    public boolean isValidBonemealTarget(IBlockReader p_176473_1_, BlockPos p_176473_2_, BlockState p_176473_3_, boolean p_176473_4_) {
        return true;
    }

    public boolean isBonemealSuccess(World p_180670_1_, Random p_180670_2_, BlockPos p_180670_3_, BlockState p_180670_4_) {
        return true;
    }

    public void performBonemeal(ServerWorld p_225535_1_, Random random, BlockPos p_225535_3_, BlockState p_225535_4_) {
        int j = 1;
        int l = 0;
        int i1 = p_225535_3_.getX() - 2;
        int j1 = 0;

        for(int k1 = 0; k1 < 5; ++k1) {
            for(int l1 = 0; l1 < j; ++l1) {
                int i2 = 2 + p_225535_3_.getY() - 1;

                for(int j2 = i2 - 2; j2 < i2; ++j2) {
                    BlockPos blockpos = new BlockPos(i1 + k1, j2, p_225535_3_.getZ() - j1 + l1);
                    if (blockpos != p_225535_3_ && random.nextInt(6) == 0) {
                        BlockState blockstate = p_225535_1_.getBlockState(blockpos.below());
                        if (blockstate.is(BlockTags.MUSHROOM_GROW_BLOCK)) {
                            p_225535_1_.setBlock(blockpos, Blocks.SEA_PICKLE.defaultBlockState().setValue(MUSHROOMS, Integer.valueOf(random.nextInt(4) + 1)), 3);
                        }
                    }
                }
            }

            if (l < 2) {
                j += 2;
                ++j1;
            } else {
                j -= 2;
                --j1;
            }

            ++l;
        }

        p_225535_1_.setBlock(p_225535_3_, p_225535_4_.setValue(MUSHROOMS, Integer.valueOf(4)), 2);

    }

    public boolean isPathfindable(BlockState p_196266_1_, IBlockReader p_196266_2_, BlockPos p_196266_3_, PathType p_196266_4_) {
        return false;
    }
}