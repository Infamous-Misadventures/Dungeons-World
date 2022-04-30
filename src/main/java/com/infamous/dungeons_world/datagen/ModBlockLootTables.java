package com.infamous.dungeons_world.datagen;

import com.infamous.dungeons_world.blocks.*;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;
import java.util.stream.Collectors;

import static com.infamous.dungeons_world.blocks.ModBlocks.*;

public class ModBlockLootTables extends BlockLoot {

    private static final LootItemCondition.Builder SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))));
    private static final LootItemCondition.Builder NO_SILK_TOUCH = SILK_TOUCH.invert();
    @Override
    protected void addTables() {
        ModBlocks.BUILDING_BLOCK_HELPERS.forEach(this::buildBlockLootTables);
        ModBlocks.SINGLE_BLOCKS.forEach(block -> this.add(block.get(), BlockLoot::createSingleItemTable));
        ModBlocks.PATH_BLOCKS.forEach(pathBlock -> this.add(pathBlock.get(), block -> createSingleItemTableWithSilkTouch(block, ((PathBlock) block).getUnshoveled())));
        ModBlocks.ROTTEN_BLOCKS.forEach(block -> this.add(block.get(), BlockLoot::createSingleItemTable));
        handleSpecialClassBlocks();
        this.add(COMMON_CHEST.get(), BlockLoot::createSingleItemTable);
        this.add(FANCY_CHEST.get(), BlockLoot::createSingleItemTable);
        this.add(OBSIDIAN_CHEST.get(), BlockLoot::createSingleItemTable);
        this.add(LINES_STONE_COLUMN.get(), BlockLoot::createSingleItemTable);
        this.add(PILLAR_STONE_COLUMN.get(), BlockLoot::createSingleItemTable);
        this.add(GROOVED_POLISHED_GRANITE_COLUMN.get(), BlockLoot::createSingleItemTable);
        this.add(SMOOTH_STONE_COLUMN.get(), BlockLoot::createSingleItemTable);
        this.add(CHISELED_STONE_COLUMN.get(), BlockLoot::createSingleItemTable);
        this.add(SKELETON_CARVED_STONE_COLUMN.get(), BlockLoot::createSingleItemTable);
        this.add(SKELETON_CARVED_GRANITE_COLUMN.get(), BlockLoot::createSingleItemTable);
        this.add(GRASSY_COARSE_DEEP_DIRT.get(), block -> createSingleItemTableWithSilkTouch(block, GRASSY_COARSE_DEEP_DIRT.get()));
        this.add(GRASSY_COARSE_DEEP_DIRT_PATH.get(), block -> createSingleItemTableWithSilkTouch(block, GRASSY_COARSE_DEEP_DIRT.get()));
        this.add(DEEP_GRASSY_DIRT.get(), block -> createSingleItemTableWithSilkTouch(block, DEEP_DIRT.get()));
        this.add(DEEP_DIRTY_GRASS.get(), block -> createSingleItemTableWithSilkTouch(block, DEEP_DIRT.get()));
        this.add(DEEP_GRASS_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, DEEP_DIRT.get()));
        this.add(FULL_GLOWING_MUSHROOM.get(), block -> LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(applyExplosionDecay(FULL_GLOWING_MUSHROOM.get(), LootItem.lootTableItem(block).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GlowingMushroomBlock.MUSHROOMS, 2)))).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GlowingMushroomBlock.MUSHROOMS, 3)))).apply(SetItemCountFunction.setCount(ConstantValue.exactly(4)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GlowingMushroomBlock.MUSHROOMS, 4))))))));
        this.add(GLOWING_MUSHROOM.get(), BlockLoot::createSingleItemTable);
        this.add(GRAVE.get(), BlockLoot::createSingleItemTable);
        this.add(MOSSY_GRAVE.get(), BlockLoot::createSingleItemTable);
        this.add(COMMON_URN.get(), BlockLoot::createSingleItemTable);
        this.add(CREEPY_SARCOPHAGUS.get(), ModBlockLootTables.createMultiPartSingleItemTable(CREEPY_SARCOPHAGUS.get()));
        this.add(CHANDELIER.get(), ModBlockLootTables.createMultiPartSingleItemTable(CHANDELIER.get()));
        this.add(SPIDER_EGG.get(), BlockLoot::createSingleItemTable);

    }

    private void handleSpecialClassBlocks() {
        SPECIAL_CLASS_BLOCKS.forEach(block -> {
            if(block.get() instanceof LinkedFenceBlock){
                this.add(block.get(), BlockLoot::createSingleItemTable);
            }
        });
    }

    private void buildBlockLootTables(BuildingBlockHelper buildingBlockHelper) {
        if(buildingBlockHelper.getBlock().get() instanceof Creepmoss) {
            registerCreepmossBuildingBlockLootTable(buildingBlockHelper);
        }else {
            registerDroppingSelfBuildingBlockHelper(buildingBlockHelper);
        }
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BLOCKS.getEntries().stream().map(RegistryObject::get).collect(Collectors.toList());
    }

    protected void registerCreepmossBuildingBlockLootTable(BuildingBlockHelper blockHelper) {
        this.add(blockHelper.getBlock().get(), BlockLoot::createSingleItemTable);
        this.add(blockHelper.getSlab().get(), createSlabItemTable(blockHelper.getSlab().get()));
        this.add(blockHelper.getStairs().get(), createSingleItemTable(blockHelper.getStairs().get()));
        this.add(blockHelper.getButton().get(), createSingleItemTable(blockHelper.getButton().get()));
        this.add(blockHelper.getPressurePlate().get(), createSingleItemTable(blockHelper.getPressurePlate().get()));
        this.add(blockHelper.getWall().get(), createSingleItemTable(blockHelper.getWall().get()));
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
        this.registerBuildingBlockLootTable(buildingBlockHelper, BlockLoot::createSingleItemTable);
    }

    protected static LootTable.Builder createMultiPartSingleItemTable(Block block) {
        if(block instanceof MultiPartBlock) {
            MultiPartBlock multiPartBlock = (MultiPartBlock) block;
            return BlockLoot.createSinglePropConditionTable(block, multiPartBlock.getBlockPartProperty(), multiPartBlock.getPlacementBlockPart());
        }else{
            throw new RuntimeException("Creating MultiPartLootTable on a non MultiPart block");
        }
    }
}