package com.infamous.dungeons_world.world.surfacebuilder;

import com.infamous.dungeons_world.DungeonsWorld;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModSurfaceBuilders {
    public static final DeferredRegister<SurfaceBuilder<?>> SURFACE_BUILDERS = DeferredRegister.create(ForgeRegistries.SURFACE_BUILDERS, DungeonsWorld.MODID);

    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> CREEPER_WOODS = register("creeper_woods", () -> new CreeperWoodsPathsSurfaceBuilder(SurfaceBuilderConfig.CODEC));
    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> SPIDER_CAVES = register("spider_caves", () -> new SpiderCavesSurfaceBuilder(SurfaceBuilderConfig.CODEC));

    private static RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> register(String id, Supplier<SurfaceBuilder<SurfaceBuilderConfig>> sup) {
        return SURFACE_BUILDERS.register(id, sup);
    }


}
