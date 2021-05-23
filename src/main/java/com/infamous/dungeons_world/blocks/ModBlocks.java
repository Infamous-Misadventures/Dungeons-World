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
    public static final BuildingBlockHelper CW_STONE_BRICKS = registerBuildingBlock("cw_stone_bricks", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final BuildingBlockHelper CRACKED_CW_STONE_BRICKS = registerBuildingBlock("cracked_cw_stone_bricks", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final BuildingBlockHelper CHISELED_CW_STONE_BRICKS = registerBuildingBlock("chiseled_cw_stone_bricks", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final BuildingBlockHelper LOW_MOSSY_CW_STONE = registerBuildingBlock("low_mossy_cw_stone", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final BuildingBlockHelper MEDIUM_MOSSY_CW_STONE = registerBuildingBlock("medium_mossy_cw_stone", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final BuildingBlockHelper HIGH_MOSSY_CW_STONE = registerBuildingBlock("high_mossy_cw_stone", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final BuildingBlockHelper POLISHED_CW_STONE = registerBuildingBlock("polished_cw_stone", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final BuildingBlockHelper CRACKED_POLISHED_CW_STONE = registerBuildingBlock("cracked_polished_cw_stone", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final BuildingBlockHelper CW_COBBLESTONE = registerBuildingBlock("cw_cobblestone", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final BuildingBlockHelper MOSSY_CW_COBBLESTONE = registerBuildingBlock("mossy_cw_cobblestone", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> CW_DIRT = registerSingleBlock("cw_dirt", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> MOSSY_CW_DIRT = registerSingleBlock("mossy_cw_dirt", () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));

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
