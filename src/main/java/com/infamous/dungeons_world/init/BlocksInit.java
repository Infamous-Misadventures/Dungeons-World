package com.infamous.dungeons_world.init;

import com.infamous.dungeons_world.DungeonsWorld;
import com.infamous.dungeons_world.blockentity.DungeonsChestType;
import com.infamous.dungeons_world.blocks.*;
import com.infamous.dungeons_world.blocks.grower.SoggyOakTreeGrower;
import com.infamous.dungeons_world.items.DungeonsChestBlockItem;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static com.infamous.dungeons_world.Util.modLoc;
import static com.infamous.dungeons_world.blockentity.DungeonsChestType.COMMON;
import static com.infamous.dungeons_world.blockentity.DungeonsChestType.FANCY;
import static com.infamous.dungeons_world.blocks.BuildingBlockHelper.*;
import static com.infamous.dungeons_world.items.ModItems.registerBlockItem;
import static net.minecraft.world.level.block.Blocks.*;

public class BlocksInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DungeonsWorld.MODID);
    public static final List<String> BLOCK_IDS = new ArrayList<>();
    // Block Groupings
    public static final List<BuildingBlockHelper> BUILDING_BLOCK_HELPERS = new ArrayList();
    public static final List<RegistryObject<Block>> SINGLE_BLOCKS = new ArrayList();
    public static final List<RegistryObject<Block>> PATH_BLOCKS = new ArrayList();
    public static final List<RegistryObject<Block>> ROTTEN_BLOCKS = new ArrayList();
    public static final List<RegistryObject<Block>> SPECIAL_CLASS_BLOCKS = new ArrayList();
    public static final Map<DungeonsChestType, RegistryObject<Block>> CHEST_TYPES = new HashMap<>();

    //All
    public static final RegistryObject<Block> COMMON_CHEST = registerChestBlock("common_chest", () -> new DungeonsChestBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD), COMMON), COMMON);
    public static final RegistryObject<Block> FANCY_CHEST = registerChestBlock("fancy_chest", () -> new DungeonsChestBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD), FANCY), FANCY);
    public static final RegistryObject<Block> OBSIDIAN_CHEST = registerChestBlock("obsidian_chest", () -> new DungeonsChestBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD), DungeonsChestType.OBSIDIAN), DungeonsChestType.OBSIDIAN);
    public static final RegistryObject<Block> COMMON_URN = registerBlock("common_urn", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> ROTTEN_OAK_PLANKS =  registerRottenBlock("rotten_oak_planks", () -> new Block(BlockBehaviour.Properties.copy(OAK_PLANKS)));
    public static final RegistryObject<Block> ROTTEN_SPRUCE_PLANKS =  registerRottenBlock("rotten_spruce_planks", () -> new Block(BlockBehaviour.Properties.copy(SPRUCE_PLANKS)));
    public static final RegistryObject<Block> ROTTEN_BIRCH_PLANKS =  registerRottenBlock("rotten_birch_planks", () -> new Block(BlockBehaviour.Properties.copy(BIRCH_PLANKS)));
    public static final RegistryObject<Block> ROTTEN_JUNGLE_PLANKS =  registerRottenBlock("rotten_jungle_planks", () -> new Block(BlockBehaviour.Properties.copy(JUNGLE_PLANKS)));
    public static final RegistryObject<Block> ROTTEN_ACACIA_PLANKS =  registerRottenBlock("rotten_acacia_planks", () -> new Block(BlockBehaviour.Properties.copy(ACACIA_PLANKS)));
    public static final RegistryObject<Block> ROTTEN_DARK_OAK_PLANKS =  registerRottenBlock("rotten_dark_oak_planks", () -> new Block(BlockBehaviour.Properties.copy(DARK_OAK_PLANKS)));
    public static final RegistryObject<Block> ROTTEN_CRIMSON_PLANKS =  registerRottenBlock("rotten_crimson_planks", () -> new Block(BlockBehaviour.Properties.copy(CRIMSON_PLANKS)));
    public static final RegistryObject<Block> ROTTEN_WARPED_PLANKS =  registerRottenBlock("rotten_warped_planks", () -> new Block(BlockBehaviour.Properties.copy(WARPED_PLANKS)));

    public static final RegistryObject<Block> OAK_LINKED_FENCE = registerSpecialClassBlock("oak_linked_fence", () -> new LinkedFenceBlock(BlockBehaviour.Properties.copy(OAK_WOOD).noOcclusion()));
    public static final RegistryObject<Block> SPRUCE_LINKED_FENCE = registerSpecialClassBlock("spruce_linked_fence", () -> new LinkedFenceBlock(BlockBehaviour.Properties.copy(SPRUCE_WOOD).noOcclusion()));
    public static final RegistryObject<Block> BIRCH_LINKED_FENCE = registerSpecialClassBlock("birch_linked_fence", () -> new LinkedFenceBlock(BlockBehaviour.Properties.copy(BIRCH_WOOD).noOcclusion()));
    public static final RegistryObject<Block> JUNGLE_LINKED_FENCE = registerSpecialClassBlock("jungle_linked_fence", () -> new LinkedFenceBlock(BlockBehaviour.Properties.copy(JUNGLE_WOOD).noOcclusion()));
    public static final RegistryObject<Block> ACACIA_LINKED_FENCE = registerSpecialClassBlock("acacia_linked_fence", () -> new LinkedFenceBlock(BlockBehaviour.Properties.copy(ACACIA_WOOD).noOcclusion()));
    public static final RegistryObject<Block> DARK_OAK_LINKED_FENCE = registerSpecialClassBlock("dark_oak_linked_fence", () -> new LinkedFenceBlock(BlockBehaviour.Properties.copy(DARK_OAK_WOOD).noOcclusion()));
    public static final RegistryObject<Block> CRIMSON_LINKED_FENCE = registerSpecialClassBlock("crimson_linked_fence", () -> new LinkedFenceBlock(BlockBehaviour.Properties.copy(CRIMSON_HYPHAE).noOcclusion()));
    public static final RegistryObject<Block> WARPED_LINKED_FENCE = registerSpecialClassBlock("warped_linked_fence", () -> new LinkedFenceBlock(BlockBehaviour.Properties.copy(WARPED_HYPHAE).noOcclusion()));

    //Creeper Woods && Creepy Crypts
    public static final BuildingBlockHelper LOW_CREEPMOSS_STONE = registerCreepmossBuildingBlock("low_creepmoss_stone", () -> new CreepmossBlock(Creepmoss.CreepmossLevel.LOW, BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), Creepmoss.CreepmossLevel.LOW);
    public static final BuildingBlockHelper MEDIUM_CREEPMOSS_STONE = registerCreepmossBuildingBlock("medium_creepmoss_stone", () -> new CreepmossBlock(Creepmoss.CreepmossLevel.MEDIUM, BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), Creepmoss.CreepmossLevel.MEDIUM);
    public static final BuildingBlockHelper HIGH_CREEPMOSS_STONE = registerCreepmossBuildingBlock("high_creepmoss_stone", () -> new CreepmossBlock(Creepmoss.CreepmossLevel.HIGH, BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), Creepmoss.CreepmossLevel.HIGH);
    public static final BuildingBlockHelper LOW_CREEPMOSS_COBBLESTONE = registerCreepmossBuildingBlock("low_creepmoss_cobblestone", () -> new CreepmossBlock(Creepmoss.CreepmossLevel.LOW, BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), Creepmoss.CreepmossLevel.LOW);
    public static final BuildingBlockHelper MEDIUM_CREEPMOSS_COBBLESTONE = registerCreepmossBuildingBlock("medium_creepmoss_cobblestone", () -> new CreepmossBlock(Creepmoss.CreepmossLevel.MEDIUM, BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), Creepmoss.CreepmossLevel.MEDIUM);
    public static final BuildingBlockHelper HIGH_CREEPMOSS_COBBLESTONE = registerCreepmossBuildingBlock("high_creepmoss_cobblestone", () -> new CreepmossBlock(Creepmoss.CreepmossLevel.HIGH, BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), Creepmoss.CreepmossLevel.HIGH);
    public static final BuildingBlockHelper LOW_CREEPMOSS_STONE_BRICKS = registerCreepmossBuildingBlock("low_creepmoss_stone_bricks", () -> new CreepmossBlock(Creepmoss.CreepmossLevel.LOW, BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), Creepmoss.CreepmossLevel.LOW);
    public static final BuildingBlockHelper MEDIUM_CREEPMOSS_STONE_BRICKS = registerCreepmossBuildingBlock("medium_creepmoss_stone_bricks", () -> new CreepmossBlock(Creepmoss.CreepmossLevel.MEDIUM, BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), Creepmoss.CreepmossLevel.MEDIUM);
    public static final BuildingBlockHelper MEDIUM_CREEPMOSS_CRACKED_STONE_BRICKS = registerCreepmossBuildingBlock("medium_creepmoss_cracked_stone_bricks", () -> new CreepmossBlock(Creepmoss.CreepmossLevel.MEDIUM, BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), Creepmoss.CreepmossLevel.MEDIUM);
    public static final BuildingBlockHelper LOW_CREEPMOSS_CHISELED_STONE_BRICKS = registerCreepmossBuildingBlock("low_creepmoss_chiseled_stone_bricks", () -> new CreepmossBlock(Creepmoss.CreepmossLevel.LOW, BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), Creepmoss.CreepmossLevel.LOW);
    public static final BuildingBlockHelper LOW_CREEPMOSS_POLISHED_ANDESITE = registerCreepmossBuildingBlock("low_creepmoss_polished_andesite", () -> new CreepmossBlock(Creepmoss.CreepmossLevel.LOW, BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), Creepmoss.CreepmossLevel.LOW);
    public static final BuildingBlockHelper MEDIUM_CREEPMOSS_POLISHED_ANDESITE = registerCreepmossBuildingBlock("medium_creepmoss_polished_andesite", () -> new CreepmossBlock(Creepmoss.CreepmossLevel.MEDIUM, BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), Creepmoss.CreepmossLevel.MEDIUM);
    public static final RegistryObject<Block> DEEP_DIRT = registerSimpleBlock("deep_dirt", () -> new Block(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.DIRT).strength(0.5F).sound(SoundType.GRAVEL)));
    public static final RegistryObject<Block> DEEP_DIRT_PATH = registerPathBlock("deep_dirt_path", () -> new PathBlock(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.DIRT).strength(0.5F).sound(SoundType.GRAVEL), DEEP_DIRT.get(), modLoc("deep_dirt")));
    public static final RegistryObject<Block> GRASSY_COARSE_DEEP_DIRT = registerBlock("grassy_coarse_deep_dirt", () -> new Block(BlockBehaviour.Properties.of(Material.GRASS, MaterialColor.DIRT).strength(0.5F).sound(SoundType.GRAVEL)));
    public static final RegistryObject<Block> GRASSY_COARSE_DEEP_DIRT_PATH = registerBlock("grassy_coarse_deep_dirt_path", () -> new PathBlock(BlockBehaviour.Properties.of(Material.GRASS, MaterialColor.DIRT).strength(0.5F).sound(SoundType.GRAVEL), GRASSY_COARSE_DEEP_DIRT.get(), modLoc("deep_dirt")));
    public static final RegistryObject<Block> PEBBLED_DEEP_DIRT = registerSimpleBlock("pebbled_deep_dirt", () -> new Block(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.DIRT).strength(0.5F).sound(SoundType.GRAVEL)));
    public static final RegistryObject<Block> PEBBLED_DEEP_DIRT_PATH = registerPathBlock("pebbled_deep_dirt_path", () -> new PathBlock(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.DIRT).strength(0.5F).sound(SoundType.GRAVEL), PEBBLED_DEEP_DIRT.get(), modLoc("deep_dirt")));
    public static final RegistryObject<Block> COARSE_DEEP_DIRT = registerSimpleBlock("coarse_deep_dirt", () -> new Block(BlockBehaviour.Properties.of(Material.DIRT).strength(0.5F).sound(SoundType.GRAVEL)));
    public static final RegistryObject<Block> COARSE_DEEP_DIRT_PATH = registerPathBlock("coarse_deep_dirt_path", () -> new PathBlock(BlockBehaviour.Properties.of(Material.DIRT).strength(0.5F).sound(SoundType.GRAVEL), COARSE_DEEP_DIRT.get(), modLoc("deep_dirt")));
    public static final RegistryObject<Block> ROCKY_DEEP_DIRT = registerSimpleBlock("rocky_deep_dirt", () -> new Block(BlockBehaviour.Properties.of(Material.DIRT).strength(0.5F).sound(SoundType.GRAVEL)));
    public static final RegistryObject<Block> COBBLED_DEEP_DIRT = registerSimpleBlock("cobbled_deep_dirt", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(0.6F).sound(SoundType.STONE)));
    public static final RegistryObject<Block> DEEP_GRASSY_DIRT = registerBlock("deep_grassy_dirt", () -> new Block(BlockBehaviour.Properties.of(Material.GRASS, MaterialColor.DIRT).strength(0.5F).sound(SoundType.GRAVEL)));
    public static final RegistryObject<Block> DEEP_DIRTY_GRASS = registerBlock("deep_dirty_grass", () -> new Block(BlockBehaviour.Properties.of(Material.GRASS, MaterialColor.DIRT).strength(0.5F).sound(SoundType.GRAVEL)));
    public static final RegistryObject<Block> DEEP_GRASS_BLOCK = registerBlock("deep_grass_block", () -> new DeepGrassBlock(BlockBehaviour.Properties.of(Material.GRASS).randomTicks().strength(0.6F).sound(SoundType.GRASS)));
    public static final RegistryObject<Block> SKULL_SAND = registerSimpleBlock("skull_sand", () -> new Block(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.DIRT).strength(0.5F).sound(SoundType.GRAVEL)));

    public static final RegistryObject<Block> LINES_STONE_COLUMN = registerBlock("lines_stone_column", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> PILLAR_STONE_COLUMN = registerBlock("pillar_stone_column", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> SMOOTH_STONE_COLUMN = registerBlock("smooth_stone_column", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> CHISELED_STONE_COLUMN = registerBlock("chiseled_stone_column", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> SKELETON_CARVED_STONE_COLUMN = registerBlock("skeleton_carved_stone_column", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> GROOVED_POLISHED_GRANITE_COLUMN = registerBlock("grooved_polished_granite_column", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> SKELETON_CARVED_GRANITE_COLUMN = registerBlock("skeleton_carved_granite_column", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));

    public static final BuildingBlockHelper STONE_TILES = registerDirtyBuildingBlock("stone_tiles", () -> new DirtyBlock(Dirty.DirtLevel.UNAFFECTED, BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), Dirty.DirtLevel.UNAFFECTED);
    public static final RegistryObject<Block> DEEP_STONE_TILES_PATH = registerPathBlock("deep_stone_tiles_path", () -> new DirtyPathBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F), STONE_TILES.getBlock().get(), modLoc("deep_dirt"), Dirty.DirtLevel.UNAFFECTED));
    public static final BuildingBlockHelper DIRTY_STONE_TILES = registerDirtyBuildingBlock("dirty_stone_tiles", () -> new DirtyBlock(Dirty.DirtLevel.MEDIUM, BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), Dirty.DirtLevel.MEDIUM);
    public static final RegistryObject<Block> DEEP_DIRTY_STONE_TILES_PATH = registerPathBlock("deep_dirty_stone_tiles_path", () -> new DirtyPathBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F), DIRTY_STONE_TILES.getBlock().get(), modLoc("deep_dirt"), Dirty.DirtLevel.MEDIUM));
    public static final BuildingBlockHelper HIGH_DIRTY_STONE_TILES = registerDirtyBuildingBlock("high_dirty_stone_tiles", () -> new DirtyBlock(Dirty.DirtLevel.HIGH, BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), Dirty.DirtLevel.HIGH);
    public static final RegistryObject<Block> DEEP_HIGH_DIRTY_STONE_TILES_PATH = registerPathBlock("deep_high_dirty_stone_tiles_path", () -> new DirtyPathBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F), HIGH_DIRTY_STONE_TILES.getBlock().get(), modLoc("deep_dirt"), Dirty.DirtLevel.HIGH));
    public static final BuildingBlockHelper DETAILED_STONE_TILES = registerDirtyBuildingBlock("detailed_stone_tiles", () -> new DirtyBlock(Dirty.DirtLevel.UNAFFECTED, BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), Dirty.DirtLevel.UNAFFECTED);
    public static final RegistryObject<Block> DEEP_DETAILED_STONE_TILES_PATH = registerPathBlock("deep_detailed_stone_tiles_path", () -> new DirtyPathBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F), DETAILED_STONE_TILES.getBlock().get(), modLoc("deep_dirt"), Dirty.DirtLevel.UNAFFECTED));
    public static final BuildingBlockHelper DIRTY_DETAILED_STONE_TILES = registerDirtyBuildingBlock("dirty_detailed_stone_tiles", () -> new DirtyBlock(Dirty.DirtLevel.MEDIUM, BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), Dirty.DirtLevel.MEDIUM);
    public static final RegistryObject<Block> DEEP_DIRTY_DETAILED_STONE_TILES_PATH = registerPathBlock("deep_dirty_detailed_stone_tiles_path", () -> new DirtyPathBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F), DIRTY_DETAILED_STONE_TILES.getBlock().get(), modLoc("deep_dirt"), Dirty.DirtLevel.MEDIUM));
    public static final BuildingBlockHelper HIGH_DIRTY_DETAILED_STONE_TILES = registerDirtyBuildingBlock("high_dirty_detailed_stone_tiles", () -> new DirtyBlock(Dirty.DirtLevel.HIGH, BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), Dirty.DirtLevel.HIGH);
    public static final RegistryObject<Block> DEEP_HIGH_DIRTY_DETAILED_STONE_TILES_PATH = registerPathBlock("deep_high_dirty_detailed_stone_tiles_path", () -> new DirtyPathBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F), HIGH_DIRTY_DETAILED_STONE_TILES.getBlock().get(), modLoc("deep_dirt"), Dirty.DirtLevel.HIGH));
    public static final BuildingBlockHelper DIRTY_POLISHED_GRANITE = registerDirtyBuildingBlock("dirty_polished_granite", () -> new DirtyBlock(Dirty.DirtLevel.MEDIUM, BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), Dirty.DirtLevel.MEDIUM);

    // Creeper Woods Deco
    public static final RegistryObject<Block> FULL_GLOWING_MUSHROOM = registerBlock("full_glowing_mushroom", () -> new GlowingMushroomBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_ORANGE)
            .lightLevel(block -> GlowingMushroomBlock.isSqueezed(block) ? 4 + 2 * block.getValue(GlowingMushroomBlock.MUSHROOMS) : 2 + 2 * block.getValue(GlowingMushroomBlock.MUSHROOMS)).sound(SoundType.SLIME_BLOCK).noOcclusion()));
    public static final RegistryObject<Block> GLOWING_MUSHROOM = registerBlock("glowing_mushroom", () -> new MushroomBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_ORANGE).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).lightLevel(lightLevel -> 3).hasPostProcess(BlocksInit::always), () -> {
        return null;
    }));
    public static final RegistryObject<Block> GRAVE = registerBlock("grave", () -> new CreeperGraveBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).noOcclusion()));
    public static final RegistryObject<Block> MOSSY_GRAVE = registerBlock("mossy_grave", () -> new CreeperGraveBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).noOcclusion()));
    // Creepy Crypt Deco
    public static final RegistryObject<Block> CREEPY_SARCOPHAGUS = registerBlock("creepy_sarcophagus", () -> new CreepySarcophagusBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).noOcclusion()));
    public static final RegistryObject<Block> CHANDELIER = registerBlock("chandelier", () -> new ChandelierBlock(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(1.5F, 6.0F).noOcclusion()));

    // Spider Caves
    public static final RegistryObject<Block> SPIDER_EGG = registerBlock("spider_egg", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));

    // Soggy Swamp
    // soggy_oak
    public static final RegistryObject<Block> SOGGY_OAK_LOG = registerBlock("soggy_oak_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> SOGGY_OAK_WOOD = registerBlock("soggy_oak_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> SOGGY_OAK_LEAVES = registerBlock("soggy_oak_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(BlocksInit::ocelotOrParrot).isSuffocating(BlocksInit::never).isViewBlocking(BlocksInit::never));
    public static final RegistryObject<Block> SOGGY_OAK_SAPLING = registerBlock("soggy_oak_sapling", () -> new WaterloggedSaplingBlock(new SoggyOakTreeGrower(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> SOGGY_OAK_PLANKS = registerBlock("soggy_oak_planks", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(2.0F, 3.0F).sound(SoundType.WOOD)));


    private static RegistryObject<Block> registerBlock(String id, Supplier<Block> sup) {
        BLOCK_IDS.add(id);
        RegistryObject<Block> blockRegistryObject = BLOCKS.register(id, sup);
        registerBlockItem(id, blockRegistryObject, blockSupplier -> new BlockItem(blockSupplier.get(), new Item.Properties().tab(DungeonsWorld.TAB)));
        return blockRegistryObject;
    }

    private static RegistryObject<Block> registerChestBlock(String id, Supplier<Block> sup, DungeonsChestType chestType) {
        BLOCK_IDS.add(id);
        RegistryObject<Block> blockRegistryObject = BLOCKS.register(id, sup);
        registerBlockItem(id, blockRegistryObject, blockSupplier -> new DungeonsChestBlockItem(blockSupplier.get(), new Item.Properties().tab(DungeonsWorld.TAB), chestType));
        CHEST_TYPES.put(chestType, blockRegistryObject);
        return blockRegistryObject;
    }

    private static RegistryObject<Block> registerSimpleBlock(String id, Supplier<Block> sup) {
        RegistryObject<Block> singleBlock = registerBlock(id, sup);
        SINGLE_BLOCKS.add(singleBlock);
        return singleBlock;
    }

    private static RegistryObject<Block> registerPathBlock(String id, Supplier<Block> sup) {
        RegistryObject<Block> singleBlock = registerBlock(id, sup);
        PATH_BLOCKS.add(singleBlock);
        return singleBlock;
    }

    private static RegistryObject<Block> registerRottenBlock(String id, Supplier<Block> sup) {
        RegistryObject<Block> rottenBlock = registerBlock(id, sup);
        ROTTEN_BLOCKS.add(rottenBlock);
        return rottenBlock;
    }

    private static RegistryObject<Block> registerSpecialClassBlock(String id, Supplier<Block> sup) {
        RegistryObject<Block> specialClassBlock = registerBlock(id, sup);
        SPECIAL_CLASS_BLOCKS.add(specialClassBlock);
        return specialClassBlock;
    }

    private static BuildingBlockHelper registerBuildingBlock(String id, Supplier<Block> sup) {
        RegistryObject<Block> primaryBlock = registerBlock(id, sup);
        BuildingBlockHelper buildingBlockHelper = new BuildingBlockHelper.Builder()
                .withBlockId(id).withBlock(primaryBlock)
                .withSlab(registerBlock(id + SLAB_ID, () -> new SlabBlock(BlockBehaviour.Properties.copy(primaryBlock.get()))))
                .withStairs(registerBlock(id + STAIRS_ID, () -> new StairBlock(() -> primaryBlock.get().defaultBlockState(), BlockBehaviour.Properties.copy(primaryBlock.get()))))
                .withButton(registerBlock(id + BUTTON_ID, () -> new StoneButtonBlock(BlockBehaviour.Properties.copy(primaryBlock.get()).noCollission().strength(0.5F))))
                .withPressurePlate(registerBlock(id + PLATE_ID, () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, BlockBehaviour.Properties.copy(primaryBlock.get()).noCollission().strength(0.5F))))
                .withWall(registerBlock(id + WALL_ID, () -> new WallBlock(BlockBehaviour.Properties.copy(primaryBlock.get()))))
                .createBuildingBlockHelper();
        BUILDING_BLOCK_HELPERS.add(buildingBlockHelper);
        return buildingBlockHelper;
    }

    private static BuildingBlockHelper registerDirtyBuildingBlock(String id, Supplier<Block> sup, Dirty.DirtLevel dirtLevel) {
        RegistryObject<Block> primaryBlock = registerBlock(id, sup);
        BuildingBlockHelper buildingBlockHelper = new BuildingBlockHelper.Builder()
                .withBlockId(id).withBlock(primaryBlock)
                .withSlab(registerBlock(id + SLAB_ID, () -> new DirtySlabBlock(dirtLevel, BlockBehaviour.Properties.copy(primaryBlock.get()))))
                .withStairs(registerBlock(id + STAIRS_ID, () -> new DirtyStairsBlock(dirtLevel, () -> primaryBlock.get().defaultBlockState(), BlockBehaviour.Properties.copy(primaryBlock.get()))))
                .withButton(registerBlock(id + BUTTON_ID, () -> new StoneButtonBlock(BlockBehaviour.Properties.copy(primaryBlock.get()).noCollission().strength(0.5F))))
                .withPressurePlate(registerBlock(id + PLATE_ID, () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, BlockBehaviour.Properties.copy(primaryBlock.get()).noCollission().strength(0.5F))))
                .withWall(registerBlock(id + WALL_ID, () -> new WallBlock(BlockBehaviour.Properties.copy(primaryBlock.get()))))
                .createBuildingBlockHelper();
        BUILDING_BLOCK_HELPERS.add(buildingBlockHelper);
        return buildingBlockHelper;
    }

    private static BuildingBlockHelper registerCreepmossBuildingBlock(String id, Supplier<Block> sup, Creepmoss.CreepmossLevel creepmossLevel) {
        RegistryObject<Block> primaryBlock = registerBlock(id, sup);
        BuildingBlockHelper buildingBlockHelper = new BuildingBlockHelper.Builder()
                .withBlockId(id).withBlock(primaryBlock)
                .withSlab(registerBlock(id + SLAB_ID, () -> new CreepmossSlabBlock(creepmossLevel, BlockBehaviour.Properties.copy(primaryBlock.get()))))
                .withStairs(registerBlock(id + STAIRS_ID, () -> new CreepmossStairsBlock(creepmossLevel, () -> primaryBlock.get().defaultBlockState(), BlockBehaviour.Properties.copy(primaryBlock.get()))))
                .withButton(registerBlock(id + BUTTON_ID, () -> new StoneButtonBlock(BlockBehaviour.Properties.copy(primaryBlock.get()).noCollission().strength(0.5F))))
                .withPressurePlate(registerBlock(id + PLATE_ID, () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, BlockBehaviour.Properties.copy(primaryBlock.get()).noCollission().strength(0.5F))))
                .withWall(registerBlock(id + WALL_ID, () -> new WallBlock(BlockBehaviour.Properties.copy(primaryBlock.get()))))
                .createBuildingBlockHelper();
        BUILDING_BLOCK_HELPERS.add(buildingBlockHelper);
        return buildingBlockHelper;
    }


    public static void initRenderTypes(){
        ROTTEN_BLOCKS.forEach(block ->  ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.translucent()));
        ItemBlockRenderTypes.setRenderLayer(DEEP_GRASS_BLOCK.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(DEEP_GRASSY_DIRT.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(DEEP_DIRTY_GRASS.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GRASSY_COARSE_DEEP_DIRT.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GRASSY_COARSE_DEEP_DIRT_PATH.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GLOWING_MUSHROOM.get(), RenderType.cutout());
        //RenderTypeLookup.setRenderLayer(CHANDELIER.get(), RenderType.translucent());
    }

    private static boolean always(BlockState p_235426_0_, BlockGetter p_235426_1_, BlockPos p_235426_2_) {
        return true;
    }


    private static Boolean ocelotOrParrot(BlockState p_50822_, BlockGetter p_50823_, BlockPos p_50824_, EntityType<?> p_50825_) {
        return (boolean)(p_50825_ == EntityType.OCELOT || p_50825_ == EntityType.PARROT);
    }

    private static boolean never(BlockState p_50806_, BlockGetter p_50807_, BlockPos p_50808_) {
        return false;
    }
}
