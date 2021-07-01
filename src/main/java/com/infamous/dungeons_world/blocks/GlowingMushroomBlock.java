package com.infamous.dungeons_world.blocks;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

import static com.infamous.dungeons_world.particles.ModParticleTypes.GLOWING_MUSHROOM_PARTICLE;

public class GlowingMushroomBlock  extends BushBlock implements IGrowable {
    public static final IntegerProperty MUSHROOMS = IntegerProperty.create("mushrooms", 1, 4);
    public static final BooleanProperty SQUEEZED = BooleanProperty.create("squeezed");;
    protected static final VoxelShape ONE_AABB = Block.box(4.5D, 0D, 4.5D, 11.5D, 8D, 11.5D);
    protected static final AxisAlignedBB ONE_TOUCH_AABB =  new AxisAlignedBB(4.5D/16, 0D/16, 4.5D/16, 11.5D/16, 13D/16, 11.5D/16);
    protected static final VoxelShape TWO_AABB = Block.box(3.0D, 0.0D, 4.0D, 13.0D, 5D, 13.0D);
    protected static final AxisAlignedBB TWO_TOUCH_AABB = new AxisAlignedBB(3.0D/16, 0.0D/16, 4.0D/16, 13.0D/16, 11.0D/16, 13.0D/16);
    protected static final VoxelShape THREE_AABB = Block.box(2.0D, 0.0D, 0.0D, 15.0D, 6.0D, 14.0D);
    protected static final AxisAlignedBB THREE_TOUCH_AABB = new AxisAlignedBB(2.0D/16, 0.0D/16, 0.0D/16, 15.0D/16, 12.0D/16, 14.0D/16);
    protected static final VoxelShape FOUR_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 15.0D);
    protected static final AxisAlignedBB FOUR_TOUCH_AABB = new AxisAlignedBB(0.0D/16, 0.0D/16, 0.0D/16, 16.0D/16, 13.0D/16, 15.0D/16);

    private static final int SQUEEZE_TIME = 5;

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
        return true;
    }

    private void setSqueezed(World world, BlockPos blockPos, boolean squeezed){
        BlockState blockState = world.getBlockState(blockPos);
        if(blockState.is(this)) {
            world.setBlockAndUpdate(blockPos, blockState.setValue(SQUEEZED, squeezed));
        }
    }

    public void playOnSound(World world, BlockPos blockPos) {
        world.playSound(null, blockPos, SoundEvents.NOTE_BLOCK_CHIME, SoundCategory.BLOCKS, 3.0F, 1.0f);
    }

    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        for(int i = 0; i < rand.nextInt(1) + 1; ++i) {
            if(rand.nextFloat() < 0.15F) {
                double posX = (double) pos.above().above().getX() + rand.nextDouble() + rand.nextInt(3) - 1.5D;
                double posY = (double) pos.above().above().getY() + rand.nextDouble() + rand.nextInt(3) - 1.5D;
                double posZ = (double) pos.above().above().getZ() + rand.nextDouble() + rand.nextInt(3) - 1.5D;
                worldIn.addParticle(GLOWING_MUSHROOM_PARTICLE.get(),
                        posX,
                        posY,
                        posZ,
                        (rand.nextFloat() / 2.0F),
                        (rand.nextFloat() / 2.0F),
                        (rand.nextFloat() / 2.0F));
            }
        }
    }

    public void tick(BlockState blockState, ServerWorld world, BlockPos blockPos, Random random) {
        boolean squeezed = isSqueezed(blockState);
        if (squeezed) {
            this.checkSqueezed(world, blockPos, blockState, squeezed);
        }
    }

    public void entityInside(BlockState blockState, World world, BlockPos blockPos, Entity entity) {
        if (!world.isClientSide) {
            boolean squeezed = isSqueezed(blockState);
            if (!squeezed) {
                this.checkSqueezed(world, blockPos, blockState, squeezed);
            }
        }
    }

    protected void checkSqueezed(World world, BlockPos blockPos, BlockState blockState, boolean flag) {
        boolean flag1 = this.shouldSqueeze(world, blockState, blockPos);
        if (flag1 && flag != flag1) {
            this.setSqueezed(world, blockPos, true);
            this.playOnSound(world, blockPos);
        }

        if (flag1) {
            world.getBlockTicks().scheduleTick(new BlockPos(blockPos), this, this.getSqueezedTime());
        }else{
            this.setSqueezed(world, blockPos, false);
        }
    }

    private int getSqueezedTime() {
        return SQUEEZE_TIME;
    }


    protected boolean shouldSqueeze(World world,BlockState blockState, BlockPos blockPos) {
        AxisAlignedBB axisalignedbb = this.getTouchAABB(blockState).move(blockPos);
        List<? extends Entity> list = world.getEntities(null, axisalignedbb);

        if (!list.isEmpty()) {
            for(Entity entity : list) {
                if (!entity.isIgnoringBlockTriggers()) {
                    return true;
                }
            }
        }

        return false;
    }

    protected AxisAlignedBB getTouchAABB(BlockState blockState){
        switch(blockState.getValue(MUSHROOMS)) {
            case 1:
            default:
                return ONE_TOUCH_AABB;
            case 2:
                return TWO_TOUCH_AABB;
            case 3:
                return THREE_TOUCH_AABB;
            case 4:
                return FOUR_TOUCH_AABB;
        }
    }

}