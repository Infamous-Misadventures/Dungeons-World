package com.infamous.dungeons_world.client.init;

import com.infamous.dungeons_world.client.particles.GlowingMushroomParticle;
import com.infamous.dungeons_world.particles.ModParticleTypes;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.infamous.dungeons_world.DungeonsWorld.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSideRegistry {

    @SubscribeEvent
    public static void onParticleFactoryRegistration(RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticleTypes.GLOWING_MUSHROOM_PARTICLE.get(), GlowingMushroomParticle.Factory::new);
    }

}
