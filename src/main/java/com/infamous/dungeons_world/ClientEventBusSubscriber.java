package com.infamous.dungeons_world;

import com.infamous.dungeons_world.client.renderer.entity.ShieldItemFrameEntityRenderer;
import com.infamous.dungeons_world.entity.ModEntityTypes;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static com.infamous.dungeons_world.DungeonsWorld.MODID;
import static net.minecraftforge.api.distmarker.Dist.CLIENT;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.SHIELD_ITEM_FRAME.get(), ShieldItemFrameEntityRenderer::new);
    }

}
