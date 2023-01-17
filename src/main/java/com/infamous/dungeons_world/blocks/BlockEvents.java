package com.infamous.dungeons_world.blocks;


import com.infamous.dungeons_world.DungeonsWorld;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import static com.infamous.dungeons_world.blocks.ModBlocks.PATH_BLOCKS;
import static net.minecraftforge.common.ToolActions.SHOVEL_FLATTEN;

@Mod.EventBusSubscriber(modid = DungeonsWorld.MODID)
public class BlockEvents {

    @SubscribeEvent
    public static void onShovelBlock(BlockEvent.BlockToolModificationEvent event) {
        Player player = event.getPlayer();
        Level world = player.level;
        if(!world.isClientSide()){
            BlockState originalBlockstate = event.getState();
            BlockPos blockpos = event.getPos();
            BlockState pathState = getPathState(originalBlockstate);
            if(event.getToolAction().equals(SHOVEL_FLATTEN) && originalBlockstate != null && world.isEmptyBlock(blockpos.above())) {
                event.setFinalState(pathState);
                world.playSound(player, blockpos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
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
