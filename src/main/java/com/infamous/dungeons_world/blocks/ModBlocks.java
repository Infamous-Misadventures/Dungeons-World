package com.infamous.dungeons_world.blocks;

import com.infamous.dungeons_world.DungeonsWorld;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static com.infamous.dungeons_world.blocks.BuildingBlockHelper.*;
import static com.infamous.dungeons_world.items.ModItems.registerBlockItem;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DungeonsWorld.MODID);
    public static final List<String> BLOCK_IDS = new ArrayList<>();
    public static final List<BuildingBlockHelper> BUILDING_BLOCK_HELPERS = new ArrayList();
    public static final List<RegistryObject<Block>> SINGLE_BLOCKS = new ArrayList();

    //Creeper Woods
    public static final BuildingBlockHelper CREEPMOSS_CREEPER_WOODS_STONE_BRICKS = registerBuildingBlock("creepmoss_creeper_woods_stone_bricks", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final BuildingBlockHelper CRACKED_CREEPMOSS_STONE_BRICKS = registerBuildingBlock("cracked_creepmoss_stone_bricks", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final BuildingBlockHelper CHISELED_CREEPMOSS_STONE_BRICKS = registerBuildingBlock("chiseled_creepmoss_stone_bricks", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final BuildingBlockHelper LOW_CREEPMOSS_STONE = registerBuildingBlock("low_creepmoss_stone", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final BuildingBlockHelper MEDIUM_CREEPMOSS_STONE = registerBuildingBlock("medium_creepmoss_stone", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final BuildingBlockHelper HIGH_CREEPMOSS_STONE = registerBuildingBlock("high_creepmoss_stone", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final BuildingBlockHelper POLISHED_CREEPMOSS_STONE = registerBuildingBlock("polished_creepmoss_stone", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final BuildingBlockHelper CRACKED_POLISHED_CREEPMOSS_STONE = registerBuildingBlock("cracked_polished_creepmoss_stone", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final BuildingBlockHelper CREEPER_WOODS_COBBLESTONE = registerBuildingBlock("creeper_woods_cobblestone", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final BuildingBlockHelper CREEPMOSS_COBBLESTONE = registerBuildingBlock("creepmoss_cobblestone", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> CREEPMOSS_DIRT = registerSingleBlock("creepmoss_dirt", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> MOSSY_CREEPMOSS_DIRT = registerSingleBlock("mossy_creepmoss_dirt", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));

    //Creepy Crypts
    public static final BuildingBlockHelper CREEPMOSS_STONE_BRICKS = registerBuildingBlock("creepmoss_stone_bricks", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> CHISELED_STONE_COLUMN = registerBlock("chiseled_stone_column", () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final BuildingBlockHelper CHISELED_STONE_SPIRALS = registerBuildingBlock("chiseled_stone_spirals", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final BuildingBlockHelper CHISELED_STONE_RUNE = registerBuildingBlock("chiseled_stone_rune", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> CREEPER_CRYPT_FACE = registerSingleBlock("creeper_crypt_face", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final BuildingBlockHelper HIGH_DIRTY_STONE_TILES = registerBuildingBlock("high_dirty_stone_tiles", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final BuildingBlockHelper LOW_DIRTY_STONE_TILES = registerBuildingBlock("low_dirty_stone_tiles", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final BuildingBlockHelper CREEPMOSS_STONE_TILES = registerBuildingBlock("creepmoss_stone_tiles", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));



    private static RegistryObject<Block> registerBlock(String id, Supplier<Block> sup) {
        BLOCK_IDS.add(id);
        RegistryObject<Block> blockRegistryObject = BLOCKS.register(id, sup);
        registerBlockItem(id, blockRegistryObject, blockSupplier -> new BlockItem(blockSupplier.get(), new Item.Properties().tab(DungeonsWorld.TAB)));
        return blockRegistryObject;
    }

    private static RegistryObject<Block> registerSingleBlock(String id, Supplier<Block> sup) {
        RegistryObject<Block> singleBlock = registerBlock(id, sup);
        SINGLE_BLOCKS.add(singleBlock);
        return singleBlock;
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
}
