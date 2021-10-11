package com.infamous.dungeons_world.blocks;


import com.infamous.dungeons_world.DungeonsWorld;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

import static com.infamous.dungeons_world.blocks.ModBlocks.PATH_BLOCKS;

@Mod.EventBusSubscriber(modid = DungeonsWorld.MODID)
public class BlockEvents {

    @SubscribeEvent
    public static void onShovelBlock(BlockEvent.BlockToolInteractEvent event) {
        PlayerEntity player = event.getPlayer();
        World world = player.level;
        if(!world.isClientSide()){
            BlockState originalBlockstate = event.getState();
            BlockPos blockpos = event.getPos();
            BlockState pathState = getPathState(originalBlockstate);
            if(event.getToolType().equals(ToolType.SHOVEL) && originalBlockstate != null && world.isEmptyBlock(blockpos.above())) {
                event.setFinalState(pathState);
                world.playSound(player, blockpos, SoundEvents.SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
            }
        }
    }

    public static BlockState getPathState(BlockState blockState){
        return PATH_BLOCKS.stream()
                .map(RegistryObject::get)
                .filter(pathBlock -> pathBlock instanceof PathBlock && ((PathBlock) pathBlock).getUnshoveled().equals(blockState.getBlock()))
                .map(Block::defaultBlockState)
                .findFirst().orElse(null);
    }
}
