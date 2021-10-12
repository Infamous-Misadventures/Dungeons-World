package com.infamous.dungeons_world.structures;

import com.infamous.dungeons_world.DungeonsWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.EntityMobGriefingEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DungeonsWorld.MODID)
public class StructureEvents {

    @SubscribeEvent
    public static void OnMobGriefingEvent(EntityMobGriefingEvent event){
        World level = event.getEntity().level;
        if(!level.isClientSide() && level instanceof ServerWorld) {
            StructureManager structureManager = ((ServerWorld) level).structureFeatureManager();
            if (structureManager.getStructureAt(event.getEntity().blockPosition(), true, ModStructures.CW_CREEPER_HEAD.get()).isValid()) {
                event.setResult(Event.Result.DENY);
            }
        }
    }

    /*@SubscribeEvent
    public static void OnBreakEvent(BlockEvent.BreakEvent event){
        IWorld level = event.getWorld();
        if(!level.isClientSide() && level instanceof ServerWorld) {
            StructureManager structureManager = ((ServerWorld) level).structureFeatureManager();
            if (structureManager.getStructureAt(event.getPos(), true, ModStructures.CW_CREEPER_HEAD.get()).isValid()) {
                event.setCanceled(true);
            }
        }
    }*/
}
