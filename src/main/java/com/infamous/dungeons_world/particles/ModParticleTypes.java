package com.infamous.dungeons_world.particles;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.infamous.dungeons_world.DungeonsWorld.MODID;

public class ModParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MODID);

    public static final RegistryObject<SimpleParticleType> GLOWING_MUSHROOM_PARTICLE = PARTICLES.register("glowing_mushroom_particle", () -> new SimpleParticleType(false));

}
