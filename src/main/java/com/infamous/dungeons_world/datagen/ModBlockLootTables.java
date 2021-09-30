package com.infamous.dungeons_world.datagen;

import com.infamous.dungeons_world.blocks.BuildingBlockHelper;
import com.infamous.dungeons_world.blocks.GlowingMushroomBlock;
import com.infamous.dungeons_world.blocks.ModBlocks;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.MatchTool;
import net.minecraft.loot.functions.SetCount;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Function;
import java.util.stream.Collectors;

import static com.infamous.dungeons_world.blocks.ModBlocks.*;

public class ModBlockLootTables extends BlockLootTables {

    private static final ILootCondition.IBuilder SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))));
    private static final ILootCondition.IBuilder NO_SILK_TOUCH = SILK_TOUCH.invert();
    @Override
    protected void addTables() {
        ModBlocks.BUILDING_BLOCK_HELPERS.forEach(this::registerDroppingSelfBuildingBlockHelper);
        ModBlocks.SINGLE_BLOCKS.forEach(block -> this.add(block.get(), BlockLootTables::createSingleItemTable));
        this.add(COMMON_CHEST.get(), BlockLootTables::createSingleItemTable);
        this.add(FANCY_CHEST.get(), BlockLootTables::createSingleItemTable);
        this.add(OBSIDIAN_CHEST.get(), BlockLootTables::createSingleItemTable);
        this.add(LINES_STONE_COLUMN.get(), BlockLootTables::createSingleItemTable);
        this.add(GROOVED_POLISHED_GRANITE_COLUMN.get(), BlockLootTables::createSingleItemTable);
        this.add(SMOOTH_STONE_COLUMN.get(), BlockLootTables::createSingleItemTable);
        this.add(SKELETON_CARVED_STONE_COLUMN.get(), BlockLootTables::createSingleItemTable);
        this.add(DEEP_DIRT.get(), BlockLootTables::createSingleItemTable);
        this.add(PEBBLED_DEEP_DIRT.get(), BlockLootTables::createSingleItemTable);
        this.add(ROCKY_DEEP_DIRT.get(), BlockLootTables::createSingleItemTable);
        this.add(COBBLED_DEEP_DIRT.get(), BlockLootTables::createSingleItemTable);
        this.add(DEEP_GRASSY_DIRT.get(), block -> createSingleItemTableWithSilkTouch(block, DEEP_DIRT.get()));
        this.add(DEEP_DIRTY_GRASS.get(), block -> createSingleItemTableWithSilkTouch(block, DEEP_DIRT.get()));
        this.add(DEEP_GRASS_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, DEEP_DIRT.get()));
        this.add(FULL_GLOWING_MUSHROOM.get(), block -> LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(applyExplosionDecay(FULL_GLOWING_MUSHROOM.get(), ItemLootEntry.lootTableItem(block).apply(SetCount.setCount(ConstantRange.exactly(2)).when(BlockStateProperty.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GlowingMushroomBlock.MUSHROOMS, 2)))).apply(SetCount.setCount(ConstantRange.exactly(3)).when(BlockStateProperty.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GlowingMushroomBlock.MUSHROOMS, 3)))).apply(SetCount.setCount(ConstantRange.exactly(4)).when(BlockStateProperty.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GlowingMushroomBlock.MUSHROOMS, 4))))))));
        this.add(GLOWING_MUSHROOM.get(), BlockLootTables::createSingleItemTable);
        this.add(GRAVE.get(), BlockLootTables::createSingleItemTable);
        this.add(MOSSY_GRAVE.get(), BlockLootTables::createSingleItemTable);

    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BLOCKS.getEntries().stream().map(RegistryObject::get).collect(Collectors.toList());
    }

    protected void registerBuildingBlockLootTable(BuildingBlockHelper blockHelper, Function<Block, LootTable.Builder> baseFactory) {
        this.add(blockHelper.getBlock().get(), baseFactory);
        this.add(blockHelper.getSlab().get(), createSlabItemTable(blockHelper.getSlab().get()));
        this.add(blockHelper.getStairs().get(), createSingleItemTable(blockHelper.getStairs().get()));
        this.add(blockHelper.getButton().get(), createSingleItemTable(blockHelper.getButton().get()));
        this.add(blockHelper.getPressurePlate().get(), createSingleItemTable(blockHelper.getPressurePlate().get()));
        this.add(blockHelper.getWall().get(), createSingleItemTable(blockHelper.getWall().get()));
    }

    protected void registerDroppingSelfBuildingBlockHelper(BuildingBlockHelper buildingBlockHelper){
        this.registerBuildingBlockLootTable(buildingBlockHelper, BlockLootTables::createSingleItemTable);
    }
}