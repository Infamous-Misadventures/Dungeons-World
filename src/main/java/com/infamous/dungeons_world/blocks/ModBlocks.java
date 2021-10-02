package com.infamous.dungeons_world.blocks;

import com.infamous.dungeons_world.DungeonsWorld;
import com.infamous.dungeons_world.client.renderer.tileentity.itemstackrenderer.ModISTERs;
import com.infamous.dungeons_world.tileentity.DungeonsChestType;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.infamous.dungeons_world.blocks.BuildingBlockHelper.*;
import static com.infamous.dungeons_world.items.ModItems.registerBlockItem;
import static com.infamous.dungeons_world.tileentity.DungeonsChestType.*;
import static net.minecraft.block.Blocks.*;
import static net.minecraft.tags.BlockTags.PLANKS;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DungeonsWorld.MODID);
    public static final List<String> BLOCK_IDS = new ArrayList<>();
    public static final List<BuildingBlockHelper> BUILDING_BLOCK_HELPERS = new ArrayList();
    public static final List<RegistryObject<Block>> SINGLE_BLOCKS = new ArrayList();
    public static final List<RegistryObject<Block>> ROTTEN_BLOCKS = new ArrayList();
    public static final Map<DungeonsChestType, RegistryObject<Block>> CHEST_TYPES = new HashMap<>();

    //All Blocks.OAK_PLANKS, Blocks.SPRUCE_PLANKS, Blocks.BIRCH_PLANKS, Blocks.JUNGLE_PLANKS, Blocks.ACACIA_PLANKS, Blocks.DARK_OAK_PLANKS, Blocks.CRIMSON_PLANKS, Blocks.WARPED_PLANKS
    public static final RegistryObject<Block> COMMON_CHEST = registerChestBlock("common_chest", () -> new DungeonsChestBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD), COMMON), COMMON);
    public static final RegistryObject<Block> FANCY_CHEST = registerChestBlock("fancy_chest", () -> new DungeonsChestBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD), FANCY), FANCY);
    public static final RegistryObject<Block> OBSIDIAN_CHEST = registerChestBlock("obsidian_chest", () -> new DungeonsChestBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD), DungeonsChestType.OBSIDIAN), DungeonsChestType.OBSIDIAN);
    public static final RegistryObject<Block> ROTTEN_OAK_PLANKS =  registerRottenBlock("rotten_oak_planks", () -> new Block(AbstractBlock.Properties.copy(OAK_PLANKS)));
    public static final RegistryObject<Block> ROTTEN_SPRUCE_PLANKS =  registerRottenBlock("rotten_spruce_planks", () -> new Block(AbstractBlock.Properties.copy(SPRUCE_PLANKS)));
    public static final RegistryObject<Block> ROTTEN_BIRCH_PLANKS =  registerRottenBlock("rotten_birch_planks", () -> new Block(AbstractBlock.Properties.copy(BIRCH_PLANKS)));
    public static final RegistryObject<Block> ROTTEN_JUNGLE_PLANKS =  registerRottenBlock("rotten_jungle_planks", () -> new Block(AbstractBlock.Properties.copy(JUNGLE_PLANKS)));
    public static final RegistryObject<Block> ROTTEN_ACACIA_PLANKS =  registerRottenBlock("rotten_acacia_planks", () -> new Block(AbstractBlock.Properties.copy(ACACIA_PLANKS)));
    public static final RegistryObject<Block> ROTTEN_DARK_OAK_PLANKS =  registerRottenBlock("rotten_dark_oak_planks", () -> new Block(AbstractBlock.Properties.copy(DARK_OAK_PLANKS)));
    public static final RegistryObject<Block> ROTTEN_CRIMSON_PLANKS =  registerRottenBlock("rotten_crimson_planks", () -> new Block(AbstractBlock.Properties.copy(CRIMSON_PLANKS)));
    public static final RegistryObject<Block> ROTTEN_WARPED_PLANKS =  registerRottenBlock("rotten_warped_planks", () -> new Block(AbstractBlock.Properties.copy(WARPED_PLANKS)));

    //Creeper Woods && Creepy Crypts
    public static final BuildingBlockHelper LOW_CREEPMOSS_STONE = registerCreepmossBuildingBlock("low_creepmoss_stone", () -> new CreepmossBlock(Creepmoss.CreepmossLevel.LOW, AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), Creepmoss.CreepmossLevel.LOW);
    public static final BuildingBlockHelper MEDIUM_CREEPMOSS_STONE = registerCreepmossBuildingBlock("medium_creepmoss_stone", () -> new CreepmossBlock(Creepmoss.CreepmossLevel.MEDIUM, AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), Creepmoss.CreepmossLevel.MEDIUM);
    public static final BuildingBlockHelper HIGH_CREEPMOSS_STONE = registerCreepmossBuildingBlock("high_creepmoss_stone", () -> new CreepmossBlock(Creepmoss.CreepmossLevel.HIGH, AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), Creepmoss.CreepmossLevel.HIGH);
    public static final BuildingBlockHelper LOW_CREEPMOSS_COBBLESTONE = registerCreepmossBuildingBlock("low_creepmoss_cobblestone", () -> new CreepmossBlock(Creepmoss.CreepmossLevel.LOW, AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), Creepmoss.CreepmossLevel.LOW);
    public static final BuildingBlockHelper MEDIUM_CREEPMOSS_COBBLESTONE = registerCreepmossBuildingBlock("medium_creepmoss_cobblestone", () -> new CreepmossBlock(Creepmoss.CreepmossLevel.MEDIUM, AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), Creepmoss.CreepmossLevel.MEDIUM);
    public static final BuildingBlockHelper HIGH_CREEPMOSS_COBBLESTONE = registerCreepmossBuildingBlock("high_creepmoss_cobblestone", () -> new CreepmossBlock(Creepmoss.CreepmossLevel.HIGH, AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), Creepmoss.CreepmossLevel.HIGH);
    public static final BuildingBlockHelper LOW_CREEPMOSS_STONE_BRICKS = registerCreepmossBuildingBlock("low_creepmoss_stone_bricks", () -> new CreepmossBlock(Creepmoss.CreepmossLevel.LOW, AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), Creepmoss.CreepmossLevel.LOW);
    public static final BuildingBlockHelper MEDIUM_CREEPMOSS_STONE_BRICKS = registerCreepmossBuildingBlock("medium_creepmoss_stone_bricks", () -> new CreepmossBlock(Creepmoss.CreepmossLevel.MEDIUM, AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), Creepmoss.CreepmossLevel.MEDIUM);
    public static final BuildingBlockHelper MEDIUM_CREEPMOSS_CRACKED_STONE_BRICKS = registerCreepmossBuildingBlock("medium_creepmoss_cracked_stone_bricks", () -> new CreepmossBlock(Creepmoss.CreepmossLevel.MEDIUM, AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), Creepmoss.CreepmossLevel.MEDIUM);
    public static final BuildingBlockHelper LOW_CREEPMOSS_CHISELED_STONE_BRICKS = registerCreepmossBuildingBlock("low_creepmoss_chiseled_stone_bricks", () -> new CreepmossBlock(Creepmoss.CreepmossLevel.LOW, AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), Creepmoss.CreepmossLevel.LOW);
    public static final BuildingBlockHelper LOW_CREEPMOSS_POLISHED_ANDESITE = registerCreepmossBuildingBlock("low_creepmoss_polished_andesite", () -> new CreepmossBlock(Creepmoss.CreepmossLevel.LOW, AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), Creepmoss.CreepmossLevel.LOW);
    public static final BuildingBlockHelper MEDIUM_CREEPMOSS_POLISHED_ANDESITE = registerCreepmossBuildingBlock("medium_creepmoss_polished_andesite", () -> new CreepmossBlock(Creepmoss.CreepmossLevel.MEDIUM, AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), Creepmoss.CreepmossLevel.MEDIUM);
    public static final RegistryObject<Block> DEEP_DIRT = registerSimpleBlock("deep_dirt", () -> new Block(AbstractBlock.Properties.of(Material.DIRT, MaterialColor.DIRT).strength(0.5F).sound(SoundType.GRAVEL)));
    public static final RegistryObject<Block> PEBBLED_DEEP_DIRT = registerSimpleBlock("pebbled_deep_dirt", () -> new Block(AbstractBlock.Properties.of(Material.DIRT, MaterialColor.DIRT).strength(0.5F).sound(SoundType.GRAVEL)));
    public static final RegistryObject<Block> ROCKY_DEEP_DIRT = registerSimpleBlock("rocky_deep_dirt", () -> new Block(AbstractBlock.Properties.of(Material.DIRT).strength(0.5F).sound(SoundType.GRAVEL)));
    public static final RegistryObject<Block> COBBLED_DEEP_DIRT = registerSimpleBlock("cobbled_deep_dirt", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(0.6F).sound(SoundType.STONE)));
    public static final RegistryObject<Block> DEEP_GRASSY_DIRT = registerBlock("deep_grassy_dirt", () -> new Block(AbstractBlock.Properties.of(Material.GRASS, MaterialColor.DIRT).strength(0.5F).sound(SoundType.GRAVEL)));
    public static final RegistryObject<Block> DEEP_DIRTY_GRASS = registerBlock("deep_dirty_grass", () -> new Block(AbstractBlock.Properties.of(Material.GRASS, MaterialColor.DIRT).strength(0.5F).sound(SoundType.GRAVEL)));
    public static final RegistryObject<Block> DEEP_GRASS_BLOCK = registerBlock("deep_grass_block", () -> new DeepGrassBlock(AbstractBlock.Properties.of(Material.GRASS).randomTicks().strength(0.6F).sound(SoundType.GRASS)));

    public static final RegistryObject<Block> LINES_STONE_COLUMN = registerBlock("lines_stone_column", () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> SMOOTH_STONE_COLUMN = registerBlock("smooth_stone_column", () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> SKELETON_CARVED_STONE_COLUMN = registerBlock("skeleton_carved_stone_column", () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> GROOVED_POLISHED_GRANITE_COLUMN = registerBlock("grooved_polished_granite_column", () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));

    public static final BuildingBlockHelper STONE_TILES = registerDirtyBuildingBlock("stone_tiles", () -> new DirtyBlock(Dirty.DirtLevel.UNAFFECTED, AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), Dirty.DirtLevel.UNAFFECTED);
    public static final BuildingBlockHelper DIRTY_STONE_TILES = registerDirtyBuildingBlock("dirty_stone_tiles", () -> new DirtyBlock(Dirty.DirtLevel.MEDIUM, AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), Dirty.DirtLevel.MEDIUM);
    public static final BuildingBlockHelper HIGH_DIRTY_STONE_TILES = registerDirtyBuildingBlock("high_dirty_stone_tiles", () -> new DirtyBlock(Dirty.DirtLevel.HIGH, AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), Dirty.DirtLevel.HIGH);
    public static final BuildingBlockHelper DETAILED_STONE_TILES = registerDirtyBuildingBlock("detailed_stone_tiles", () -> new DirtyBlock(Dirty.DirtLevel.UNAFFECTED, AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), Dirty.DirtLevel.UNAFFECTED);
    public static final BuildingBlockHelper DIRTY_DETAILED_STONE_TILES = registerDirtyBuildingBlock("dirty_detailed_stone_tiles", () -> new DirtyBlock(Dirty.DirtLevel.MEDIUM, AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), Dirty.DirtLevel.MEDIUM);
    public static final BuildingBlockHelper HIGH_DIRTY_DETAILED_STONE_TILES = registerDirtyBuildingBlock("high_dirty_detailed_stone_tiles", () -> new DirtyBlock(Dirty.DirtLevel.HIGH, AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)), Dirty.DirtLevel.HIGH);

    public static final RegistryObject<Block> FULL_GLOWING_MUSHROOM = registerBlock("full_glowing_mushroom", () -> new GlowingMushroomBlock(AbstractBlock.Properties.of(Material.PLANT, MaterialColor.COLOR_ORANGE)
            .lightLevel(block -> GlowingMushroomBlock.isSqueezed(block) ? 4 + 2 * block.getValue(GlowingMushroomBlock.MUSHROOMS) : 2 + 2 * block.getValue(GlowingMushroomBlock.MUSHROOMS)).sound(SoundType.SLIME_BLOCK).noOcclusion()));
    public static final RegistryObject<Block> GLOWING_MUSHROOM = registerBlock("glowing_mushroom", () -> new MushroomBlock(AbstractBlock.Properties.of(Material.PLANT, MaterialColor.COLOR_ORANGE).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).lightLevel(lightLevel -> 3).hasPostProcess(ModBlocks::always)));
    public static final RegistryObject<Block> GRAVE = registerBlock("grave", () -> new CreeperGraveBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).noOcclusion()));
    public static final RegistryObject<Block> MOSSY_GRAVE = registerBlock("mossy_grave", () -> new CreeperGraveBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).noOcclusion()));

    //
    /*public static final BuildingBlockHelper CHISELED_STONE_SPIRALS = registerBuildingBlock("chiseled_stone_spirals", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final BuildingBlockHelper CHISELED_STONE_RUNE = registerBuildingBlock("chiseled_stone_rune", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> CREEPER_CRYPT_FACE = registerSingleBlock("creeper_crypt_face", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final BuildingBlockHelper HIGH_DIRTY_STONE_TILES = registerBuildingBlock("high_dirty_stone_tiles", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final BuildingBlockHelper LOW_DIRTY_STONE_TILES = registerBuildingBlock("low_dirty_stone_tiles", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final BuildingBlockHelper CREEPMOSS_STONE_TILES = registerBuildingBlock("creepmoss_stone_tiles", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));*/

    private static RegistryObject<Block> registerBlock(String id, Supplier<Block> sup) {
        BLOCK_IDS.add(id);
        RegistryObject<Block> blockRegistryObject = BLOCKS.register(id, sup);
        registerBlockItem(id, blockRegistryObject, blockSupplier -> new BlockItem(blockSupplier.get(), new Item.Properties().tab(DungeonsWorld.TAB)));
        return blockRegistryObject;
    }

    private static RegistryObject<Block> registerChestBlock(String id, Supplier<Block> sup, DungeonsChestType chestType) {
        BLOCK_IDS.add(id);
        RegistryObject<Block> blockRegistryObject = BLOCKS.register(id, sup);
        registerBlockItem(id, blockRegistryObject, blockSupplier -> new BlockItem(blockSupplier.get(), new Item.Properties().tab(DungeonsWorld.TAB)
                .setISTER(() -> () -> ModISTERs.createChestTileEntity(chestType))));
        CHEST_TYPES.put(chestType, blockRegistryObject);
        return blockRegistryObject;
    }

    private static RegistryObject<Block> registerSimpleBlock(String id, Supplier<Block> sup) {
        RegistryObject<Block> singleBlock = registerBlock(id, sup);
        SINGLE_BLOCKS.add(singleBlock);
        return singleBlock;
    }

    private static RegistryObject<Block> registerRottenBlock(String id, Supplier<Block> sup) {
        RegistryObject<Block> rottenBlock = registerBlock(id, sup);
        ROTTEN_BLOCKS.add(rottenBlock);
        return rottenBlock;
    }

    private static BuildingBlockHelper registerBuildingBlock(String id, Supplier<Block> sup) {
        BuildingBlockHelper buildingBlockHelper = new BuildingBlockHelper.Builder()
                .withBlockId(id).withBlock(registerBlock(id, sup))
                .withSlab(registerBlock(id + SLAB_ID, () -> new SlabBlock(AbstractBlock.Properties.copy(sup.get()).harvestLevel(0).harvestTool(ToolType.PICKAXE))))
                .withStairs(registerBlock(id + STAIRS_ID, () -> new StairsBlock(() -> sup.get().defaultBlockState(), AbstractBlock.Properties.copy(sup.get()).harvestLevel(0).harvestTool(ToolType.PICKAXE))))
                .withButton(registerBlock(id + BUTTON_ID, () -> new StoneButtonBlock(AbstractBlock.Properties.copy(sup.get()).harvestLevel(0).harvestTool(ToolType.PICKAXE).noCollission().strength(0.5F))))
                .withPressurePlate(registerBlock(id + PLATE_ID, () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, AbstractBlock.Properties.copy(sup.get()).harvestLevel(0).harvestTool(ToolType.PICKAXE).noCollission().strength(0.5F))))
                .withWall(registerBlock(id + WALL_ID, () -> new WallBlock(AbstractBlock.Properties.copy(sup.get()).harvestLevel(0).harvestTool(ToolType.PICKAXE))))
                .createBuildingBlockHelper();
        BUILDING_BLOCK_HELPERS.add(buildingBlockHelper);
        return buildingBlockHelper;
    }

    private static BuildingBlockHelper registerDirtyBuildingBlock(String id, Supplier<Block> sup, Dirty.DirtLevel dirtLevel) {
        BuildingBlockHelper buildingBlockHelper = new BuildingBlockHelper.Builder()
                .withBlockId(id).withBlock(registerBlock(id, sup))
                .withSlab(registerBlock(id + SLAB_ID, () -> new DirtySlabBlock(dirtLevel, AbstractBlock.Properties.copy(sup.get()).harvestLevel(0).harvestTool(ToolType.PICKAXE))))
                .withStairs(registerBlock(id + STAIRS_ID, () -> new DirtyStairsBlock(dirtLevel, () -> sup.get().defaultBlockState(), AbstractBlock.Properties.copy(sup.get()).harvestLevel(0).harvestTool(ToolType.PICKAXE))))
                .withButton(registerBlock(id + BUTTON_ID, () -> new StoneButtonBlock(AbstractBlock.Properties.copy(sup.get()).harvestLevel(0).harvestTool(ToolType.PICKAXE).noCollission().strength(0.5F))))
                .withPressurePlate(registerBlock(id + PLATE_ID, () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, AbstractBlock.Properties.copy(sup.get()).harvestLevel(0).harvestTool(ToolType.PICKAXE).noCollission().strength(0.5F))))
                .withWall(registerBlock(id + WALL_ID, () -> new WallBlock(AbstractBlock.Properties.copy(sup.get()).harvestLevel(0).harvestTool(ToolType.PICKAXE))))
                .createBuildingBlockHelper();
        BUILDING_BLOCK_HELPERS.add(buildingBlockHelper);
        return buildingBlockHelper;
    }

    private static BuildingBlockHelper registerCreepmossBuildingBlock(String id, Supplier<Block> sup, Creepmoss.CreepmossLevel creepmossLevel) {
        BuildingBlockHelper buildingBlockHelper = new BuildingBlockHelper.Builder()
                .withBlockId(id).withBlock(registerBlock(id, sup))
                .withSlab(registerBlock(id + SLAB_ID, () -> new CreepmossSlabBlock(creepmossLevel, AbstractBlock.Properties.copy(sup.get()).harvestLevel(0).harvestTool(ToolType.PICKAXE))))
                .withStairs(registerBlock(id + STAIRS_ID, () -> new CreepmossStairsBlock(creepmossLevel, () -> sup.get().defaultBlockState(), AbstractBlock.Properties.copy(sup.get()).harvestLevel(0).harvestTool(ToolType.PICKAXE))))
                .withButton(registerBlock(id + BUTTON_ID, () -> new StoneButtonBlock(AbstractBlock.Properties.copy(sup.get()).harvestLevel(0).harvestTool(ToolType.PICKAXE).noCollission().strength(0.5F))))
                .withPressurePlate(registerBlock(id + PLATE_ID, () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, AbstractBlock.Properties.copy(sup.get()).harvestLevel(0).harvestTool(ToolType.PICKAXE).noCollission().strength(0.5F))))
                .withWall(registerBlock(id + WALL_ID, () -> new WallBlock(AbstractBlock.Properties.copy(sup.get()).harvestLevel(0).harvestTool(ToolType.PICKAXE))))
                .createBuildingBlockHelper();
        BUILDING_BLOCK_HELPERS.add(buildingBlockHelper);
        return buildingBlockHelper;
    }


    public static void initRenderTypes(){
        ROTTEN_BLOCKS.forEach(block ->  RenderTypeLookup.setRenderLayer(block.get(), RenderType.translucent()));
        RenderTypeLookup.setRenderLayer(DEEP_GRASS_BLOCK.get(), RenderType.translucent());
        RenderTypeLookup.setRenderLayer(DEEP_GRASSY_DIRT.get(), RenderType.translucent());
        RenderTypeLookup.setRenderLayer(DEEP_DIRTY_GRASS.get(), RenderType.translucent());
        RenderTypeLookup.setRenderLayer(GLOWING_MUSHROOM.get(), RenderType.cutout());
    }

    private static boolean always(BlockState p_235426_0_, IBlockReader p_235426_1_, BlockPos p_235426_2_) {
        return true;
    }
}
