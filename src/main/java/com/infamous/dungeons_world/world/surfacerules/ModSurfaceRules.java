package com.infamous.dungeons_world.world.surfacerules;

import com.infamous.dungeons_world.DungeonsWorld;
import com.infamous.dungeons_world.blocks.ModBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;

import static com.infamous.dungeons_world.DungeonsWorld.MODID;
import static com.infamous.dungeons_world.world.biomes.ModBiomes.CREEPER_WOODS;

public class ModSurfaceRules {

    public static Codec<? extends SurfaceRules.ConditionSource> SIMPLEX_TRESHOLD;

    public static final SurfaceRules.ConditionSource WATER_CHECK = SurfaceRules.waterBlockCheck(-1, 0);

    public static final SurfaceRules.RuleSource DEEP_GRASS_BLOCK_SURFACE = SurfaceRules.sequence(SurfaceRules.ifTrue(WATER_CHECK, SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(ModBlocks.DEEP_GRASS_BLOCK.get().defaultBlockState()))), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SurfaceRules.state(ModBlocks.DEEP_DIRT.get().defaultBlockState())));

    public static final SurfaceRules.RuleSource CREEPER_WOODS_SURFACE = SurfaceRules.ifTrue(SurfaceRules.isBiome(CREEPER_WOODS),
            SurfaceRules.sequence(
                    getPathRuleSource(),
//                    getBrookRuleSource(),
                    DEEP_GRASS_BLOCK_SURFACE
            )
    );

    public static SurfaceRules.RuleSource getPathRuleSource(){
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(ModSurfaceRules.simplexCondition(0.015D, 0.015D, -0.09D, 0.09D, 3254L), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(ModBlocks.DEEP_DIRT_PATH.get().defaultBlockState()))),
                SurfaceRules.ifTrue(ModSurfaceRules.simplexCondition(0.015D, 0.015D, -0.13D, 0.13D, 3254L), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,  SurfaceRules.state(ModBlocks.DEEP_GRASSY_DIRT.get().defaultBlockState()))),
                SurfaceRules.ifTrue(ModSurfaceRules.simplexCondition(0.015D, 0.015D, -0.22D, 0.22D, 3254L), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(ModBlocks.DEEP_DIRTY_GRASS.get().defaultBlockState())))
        );
    }

    public static SurfaceRules.RuleSource getBrookRuleSource(){
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(ModSurfaceRules.simplexCondition(0.018D, 0.018D, -0.11D, 0.11D, 2L), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(Blocks.WATER.defaultBlockState()))),
                SurfaceRules.ifTrue(ModSurfaceRules.simplexCondition(0.018D, 0.018D, -0.15D, 0.15D, 2L), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,  SurfaceRules.state(ModBlocks.DEEP_GRASSY_DIRT.get().defaultBlockState())))
//                SurfaceRules.ifTrue(ModSurfaceRules.simplexCondition(0.017D, 0.017D, -0.22D, 0.22D, 2L), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(ModBlocks.DEEP_DIRTY_GRASS.get().defaultBlockState())))
        );
    }

    private static SurfaceRules.ConditionSource simplexCondition(double xMultiplier, double zMultiplier, double minThreshold, double maxThreshold, Long seed) {
        return new SimplexTresholdConditionSource(xMultiplier, zMultiplier, minThreshold, maxThreshold, seed);
    }

    public static void init(){
        SIMPLEX_TRESHOLD = register("simplex_threshold", SimplexTresholdConditionSource.CODEC);
    }

    static <P extends SurfaceRules.ConditionSource> Codec<? extends SurfaceRules.ConditionSource> register(String name, Codec<P> codec) {
        return Registry.register(Registry.CONDITION, new ResourceLocation(MODID, name), codec);
    }

    public static void bootStrap() {
    }

    static {
        Registry.register(Registry.CONDITION, new ResourceLocation(MODID, "simplex_threshold"), SimplexTresholdConditionSource.CODEC);

        DungeonsWorld.LOGGER.info("Dungeons World Rule Sources class loaded.");

    }


}
