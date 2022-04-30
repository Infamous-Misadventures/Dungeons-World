package com.infamous.dungeons_world.world.surfacerules;

import com.infamous.dungeons_world.blocks.ModBlocks;
import com.infamous.dungeons_world.world.ModBiomes;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class ModSurfaceRules {

    public static final SurfaceRules.ConditionSource WATER_CHECK = SurfaceRules.waterBlockCheck(-1, 0);

    public static final SurfaceRules.RuleSource DEEP_GRASS_BLOCK_SURFACE = SurfaceRules.sequence(SurfaceRules.ifTrue(WATER_CHECK, SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(ModBlocks.DEEP_GRASS_BLOCK.get().defaultBlockState()))), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SurfaceRules.state(ModBlocks.DEEP_DIRT.get().defaultBlockState())));

    public static final SurfaceRules.RuleSource CREEPER_WOODS_SURFACE = SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.CREEPER_WOODS),
            SurfaceRules.sequence(
                    SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SURFACE, 0.12121212121212122F, 1.7976931348623157F),//Todo create own condition
                            SurfaceRules.state(ModBlocks.COARSE_DEEP_DIRT.get().defaultBlockState())),
                    DEEP_GRASS_BLOCK_SURFACE
            )
    );


}
