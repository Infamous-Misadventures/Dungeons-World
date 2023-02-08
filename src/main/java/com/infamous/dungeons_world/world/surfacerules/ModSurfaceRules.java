package com.infamous.dungeons_world.world.surfacerules;

import com.infamous.dungeons_world.init.BlocksInit;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;

import static com.infamous.dungeons_world.world.biomes.ModBiomes.CREEPER_WOODS;

public class ModSurfaceRules {

    public static final SurfaceRules.ConditionSource WATER_CHECK = SurfaceRules.waterBlockCheck(-1, 0);

    public static final SurfaceRules.RuleSource DEEP_GRASS_BLOCK_SURFACE = SurfaceRules.sequence(SurfaceRules.ifTrue(WATER_CHECK, SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(BlocksInit.DEEP_GRASS_BLOCK.get().defaultBlockState()))), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SurfaceRules.state(BlocksInit.DEEP_DIRT.get().defaultBlockState())));

    public static final SurfaceRules.RuleSource CREEPER_WOODS_SURFACE = SurfaceRules.ifTrue(SurfaceRules.isBiome(CREEPER_WOODS),
            SurfaceRules.sequence(
                    getPathRuleSource(),
//                    getBrookRuleSource(),
                    DEEP_GRASS_BLOCK_SURFACE
            )
    );


    public static SurfaceRules.RuleSource getPathRuleSource(){
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(ModSurfaceRules.simplexCondition(0.015D, 0.015D, -0.09D, 0.09D, 3254L), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(BlocksInit.DEEP_DIRT_PATH.get().defaultBlockState()))),
                SurfaceRules.ifTrue(ModSurfaceRules.simplexCondition(0.015D, 0.015D, -0.13D, 0.13D, 3254L), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,  SurfaceRules.state(BlocksInit.DEEP_GRASSY_DIRT.get().defaultBlockState()))),
                SurfaceRules.ifTrue(ModSurfaceRules.simplexCondition(0.015D, 0.015D, -0.22D, 0.22D, 3254L), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(BlocksInit.DEEP_DIRTY_GRASS.get().defaultBlockState())))
        );
    }

    public static SurfaceRules.RuleSource getBrookRuleSource(){
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(ModSurfaceRules.simplexCondition(0.018D, 0.018D, -0.11D, 0.11D, 2L), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(Blocks.WATER.defaultBlockState()))),
                SurfaceRules.ifTrue(ModSurfaceRules.simplexCondition(0.018D, 0.018D, -0.15D, 0.15D, 2L), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,  SurfaceRules.state(BlocksInit.DEEP_GRASSY_DIRT.get().defaultBlockState())))
//                SurfaceRules.ifTrue(ModSurfaceRules.simplexCondition(0.017D, 0.017D, -0.22D, 0.22D, 2L), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(ModBlocks.DEEP_DIRTY_GRASS.get().defaultBlockState())))
        );
    }

    private static SurfaceRules.ConditionSource simplexCondition(double xMultiplier, double zMultiplier, double minThreshold, double maxThreshold, Long seed) {
        return new SimplexTresholdConditionSource(xMultiplier, zMultiplier, minThreshold, maxThreshold, seed);
    }


}
