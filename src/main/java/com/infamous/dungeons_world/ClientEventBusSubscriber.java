package com.infamous.dungeons_world;

import mod.patrigan.slimierslimes.SlimierSlimes;
import mod.patrigan.slimierslimes.client.entity.render.*;
import mod.patrigan.slimierslimes.client.renderer.entity.layers.BipedTranslucentArmorLayer;
import mod.patrigan.slimierslimes.init.ModEntityTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.Map;

import static com.infamous.dungeons_world.DungeonsWorld.MODID;
import static mod.patrigan.slimierslimes.init.ModEntityTypes.SLIME_BALL_PROJECTILE;
import static net.minecraftforge.api.distmarker.Dist.CLIENT;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.COMMON_SLIME.get(), CommonSlimeRenderer::new);
    }

}
