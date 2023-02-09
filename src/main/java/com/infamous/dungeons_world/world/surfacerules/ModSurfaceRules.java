package com.infamous.dungeons_world.world.surfacerules;

import com.infamous.dungeons_world.init.BlocksInit;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

import static com.infamous.dungeons_world.world.biomes.ModBiomes.CREEPER_WOODS;
import static com.infamous.dungeons_world.world.biomes.ModBiomes.SOGGY_SWAMP;

public class ModSurfaceRules {
    // ConditionSources
    public static final SurfaceRules.ConditionSource WATER_CHECK = SurfaceRules.waterBlockCheck(-1, 0);
    public static final SurfaceRules.ConditionSource Y_BLOCK_CHECK_62 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(62), 0);
    public static final SurfaceRules.ConditionSource Y_BLOCK_CHECK_63 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(63), 0);
    // StateRules
    private static final SurfaceRules.RuleSource DEEP_DIRT = makeStateRule(BlocksInit.DEEP_DIRT.get());
    private static final SurfaceRules.RuleSource DEEP_GRASSY_DIRT = makeStateRule(BlocksInit.DEEP_GRASSY_DIRT.get());
    private static final SurfaceRules.RuleSource DEEP_DIRTY_GRASS = makeStateRule(BlocksInit.DEEP_DIRTY_GRASS.get());
    private static final SurfaceRules.RuleSource DEEP_GRASS_BLOCK = makeStateRule(BlocksInit.DEEP_GRASS_BLOCK.get());
    // SurfaceRules
    public static final SurfaceRules.RuleSource DEEP_GRASS_BLOCK_SURFACE = SurfaceRules.sequence(SurfaceRules.ifTrue(WATER_CHECK, SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, DEEP_GRASS_BLOCK)), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DEEP_DIRT));
    private static final SurfaceRules.RuleSource DEEP_DIRT_PATH = makeStateRule(BlocksInit.DEEP_DIRT_PATH.get());
    public static final SurfaceRules.RuleSource CREEPER_WOODS_SURFACE = SurfaceRules.ifTrue(SurfaceRules.isBiome(CREEPER_WOODS),
            SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(),
                    SurfaceRules.sequence(
                            getPathRuleSource(DEEP_DIRT_PATH, DEEP_GRASSY_DIRT, DEEP_DIRTY_GRASS),
//                            getBrookRuleSource(),
                            DEEP_GRASS_BLOCK_SURFACE
                    )
            )
    );
    private static final SurfaceRules.RuleSource WATER = makeStateRule(Blocks.WATER);
    public static final SurfaceRules.RuleSource SOGGY_SWAMP_SURFACE = SurfaceRules.ifTrue(SurfaceRules.isBiome(SOGGY_SWAMP),
            SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(),
                    SurfaceRules.ifTrue(Y_BLOCK_CHECK_62,
                            SurfaceRules.ifTrue(SurfaceRules.not(Y_BLOCK_CHECK_63),
                                    SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SWAMP, 0.0D), WATER)))));

    public static SurfaceRules.RuleSource getPathRuleSource(SurfaceRules.RuleSource pathState, SurfaceRules.RuleSource innerState, SurfaceRules.RuleSource outerState) {
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(ModSurfaceRules.simplexCondition(0.015D, 0.015D, -0.09D, 0.09D, 3254L), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, pathState)),
                SurfaceRules.ifTrue(ModSurfaceRules.simplexCondition(0.015D, 0.015D, -0.13D, 0.13D, 3254L), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, innerState)),
                SurfaceRules.ifTrue(ModSurfaceRules.simplexCondition(0.015D, 0.015D, -0.22D, 0.22D, 3254L), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, outerState))
        );
    }

    public static SurfaceRules.RuleSource getBrookRuleSource() {
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(ModSurfaceRules.simplexCondition(0.018D, 0.018D, -0.11D, 0.11D, 2L), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(Blocks.WATER.defaultBlockState()))),
                SurfaceRules.ifTrue(ModSurfaceRules.simplexCondition(0.018D, 0.018D, -0.15D, 0.15D, 2L), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(BlocksInit.DEEP_GRASSY_DIRT.get().defaultBlockState())))
//                SurfaceRules.ifTrue(ModSurfaceRules.simplexCondition(0.017D, 0.017D, -0.22D, 0.22D, 2L), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(ModBlocks.DEEP_DIRTY_GRASS.get().defaultBlockState())))
        );
    }

    private static SurfaceRules.ConditionSource simplexCondition(double xMultiplier, double zMultiplier, double minThreshold, double maxThreshold, Long seed) {
        return new SimplexTresholdConditionSource(xMultiplier, zMultiplier, minThreshold, maxThreshold, seed);
    }

    private static SurfaceRules.RuleSource makeStateRule(Block p_194811_) {
        return SurfaceRules.state(p_194811_.defaultBlockState());
    }

}
