package com.infamous.dungeons_world.particles;

import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.infamous.dungeons_world.DungeonsWorld.MODID;

public class ModParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MODID);

    public static final RegistryObject<BasicParticleType> GLOWING_MUSHROOM_PARTICLE = PARTICLES.register("glowing_mushroom_particle", () -> new BasicParticleType(false));

}
