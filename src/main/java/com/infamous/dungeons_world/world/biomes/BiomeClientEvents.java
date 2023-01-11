package com.infamous.dungeons_world.world.biomes;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.infamous.dungeons_world.DungeonsWorld.MODID;
import static net.minecraft.client.renderer.FogRenderer.FogMode.FOG_TERRAIN;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class BiomeClientEvents {

    @SubscribeEvent
    public static void onRenderFogEvent(EntityViewRenderEvent.RenderFogEvent event) {
        if(event.getMode() == FOG_TERRAIN){
            Entity entity = event.getRenderer().getMainCamera().getEntity();
            if(entity != null){
                if(entity.getLevel().getBiome(entity.blockPosition()).is(ModBiomes.CREEPER_WOODS)){
                    event.setNearPlaneDistance(5F);
                    event.setFarPlaneDistance(30.0F);
                    event.setCanceled(true);
                }
            }
        }
    }
}
