package com.infamous.dungeons_world.datagen;

import com.infamous.dungeons_world.blocks.BuildingBlockHelper;
import com.infamous.dungeons_world.blocks.ModBlocks;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.block.Block;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.MatchTool;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Function;
import java.util.stream.Collectors;

import static com.infamous.dungeons_world.blocks.ModBlocks.BLOCKS;

public class ModBlockLootTables extends BlockLootTables {

    private static final ILootCondition.IBuilder SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))));
    private static final ILootCondition.IBuilder NO_SILK_TOUCH = SILK_TOUCH.invert();
    @Override
    protected void addTables() {
        ModBlocks.BUILDING_BLOCK_HELPERS.forEach(this::registerDroppingSelfBuildingBlockHelper);
        ModBlocks.SINGLE_BLOCKS.forEach(block -> this.add(block.get(), BlockLootTables::createSingleItemTable));
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