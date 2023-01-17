package com.infamous.dungeons_world.world.biomes;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.infamous.dungeons_world.DungeonsWorld.MODID;
import static net.minecraft.client.renderer.FogRenderer.FogMode.FOG_TERRAIN;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class BiomeClientEvents {

    @SubscribeEvent
    public static void onRenderFogEvent(ViewportEvent.RenderFog event) {
        if(event.getMode() == FOG_TERRAIN){
            Entity entity = event.getRenderer().getMainCamera().getEntity();
            if(entity != null && entity instanceof Player player && !player.isSpectator() && !player.isCreative()){
                if(entity.getLevel().getBiome(entity.blockPosition()).is(ModBiomes.CREEPER_WOODS)){
                    event.setNearPlaneDistance(5F);
                    event.setFarPlaneDistance(30.0F);
                    event.setCanceled(true);
                }
            }
        }
    }
}
