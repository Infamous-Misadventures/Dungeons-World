package com.infamous.dungeons_world.datagen;

import net.minecraft.data.loot.ChestLoot;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.functions.EnchantWithLevelsFunction;
import net.minecraft.world.level.storage.loot.functions.ExplorationMapFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SetNbtFunction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.Util;
import net.minecraft.world.level.saveddata.maps.MapDecoration;

import java.util.function.BiConsumer;

//import static com.infamous.dungeons_gear.registry.ItemRegistry.*;
import static com.infamous.dungeons_world.DungeonsWorld.MODID;

import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class ModChestLootTables extends ChestLoot {

    private static final ResourceLocation COMMON = new ResourceLocation(MODID, "chests/common");
    private static final int COMMON_WEIGHT = 115;
    private static final ResourceLocation FANCY = new ResourceLocation(MODID, "chests/fancy");
    private static final int FANCY_WEIGHT = 130;
    private static final ResourceLocation OBSIDIAN = new ResourceLocation(MODID, "chests/obsidian");
    private static final int OBSIDIAN_WEIGHT = 100;

    private static String DGMODID = "dungeons_gear";

    @Override
    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
        consumer.accept(COMMON,
                LootTable.lootTable().
                        withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.BOOK).setWeight(5).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(10)).allowTreasure()))
                                .add(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(40).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(Items.EMERALD).setWeight(20))
                                .add(LootItem.lootTableItem(Items.POTION).setWeight(20).apply(getHealingPotionSetNBT()))
                                .add(LootItem.lootTableItem(Items.BREAD).setWeight(20))
                                .add(LootItem.lootTableItem(Items.EXPERIENCE_BOTTLE).setWeight(10))
                        ));
        consumer.accept(FANCY,
                LootTable.lootTable().
                        withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.BOOK).setWeight(25).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(20)).allowTreasure()))
                                .add(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(30).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                                .add(LootItem.lootTableItem(Items.EMERALD).setWeight(30).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(Items.POTION).setWeight(20).apply(getHealingPotionSetNBT()))
                                .add(LootItem.lootTableItem(Items.BREAD).setWeight(10))
                                .add(LootItem.lootTableItem(Items.EXPERIENCE_BOTTLE).setWeight(15))
                        ));
        consumer.accept(OBSIDIAN,
                LootTable.lootTable().
                        withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.BOOK).setWeight(35).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(30)).allowTreasure()))
                                .add(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))))
                                .add(LootItem.lootTableItem(Items.EMERALD).setWeight(25).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))))
                                .add(LootItem.lootTableItem(Items.POTION).setWeight(20).apply(getHealingPotionSetNBT()))
                                .add(LootItem.lootTableItem(Items.EXPERIENCE_BOTTLE).setWeight(10))
                        ));
        creeperWoods(consumer);
    }

    private void createForDungeon(BiConsumer<ResourceLocation, LootTable.Builder> consumer, String dungeonName) {
        consumer.accept(new ResourceLocation(MODID, "chests/"+dungeonName+"/common"),
                LootTable.lootTable().
                        withPool(LootPool.lootPool().setRolls(UniformGenerator.between(4.0F, 8.0F))
                                .add(LootTableReference.lootTableReference(COMMON).setWeight(COMMON_WEIGHT))));
        consumer.accept(new ResourceLocation(MODID, "chests/"+dungeonName+"/fancy"),
                LootTable.lootTable().
                        withPool(LootPool.lootPool().setRolls(UniformGenerator.between(5.0F, 8.0F))
                                .add(LootTableReference.lootTableReference(FANCY).setWeight(FANCY_WEIGHT))
//                                .add(LootItem.lootTableItem(Items.MAP).setWeight(10).apply(ExplorationMapFunction.makeExplorationMap().setDestination(ModStructures.CW_RUINED_TOWER.get()).setMapDecoration(MapDecoration.Type.RED_X).setZoom((byte)1).setSkipKnownStructures(false)))
                        ));
        consumer.accept(new ResourceLocation(MODID, "chests/"+dungeonName+"/obsidian"),
                LootTable.lootTable().
                        withPool(LootPool.lootPool().setRolls(UniformGenerator.between(5.0F, 10.0F))
                                .add(LootTableReference.lootTableReference(OBSIDIAN).setWeight(OBSIDIAN_WEIGHT))
//                                .add(LootItem.lootTableItem(Items.MAP).setWeight(20).apply(ExplorationMapFunction.makeExplorationMap().setDestination(ModStructures.CW_RUINED_TOWER.get())))
                        ));
    }

    private void creeperWoods(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
        createForDungeon(consumer, "creeper_woods");
        consumer.accept(new ResourceLocation(DGMODID, "chests/creeper_woods/common"),
                LootTable.lootTable().
                        withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 1.0F))
//                                .add(LootItem.lootTableItem(SWORD).setWeight(100))
//                                .add(LootItem.lootTableItem(HAWKBRAND.get()).setWeight(1))
//                                .add(LootItem.lootTableItem(DIAMOND_SWORD.get()).setWeight(1))
//                                .add(LootItem.lootTableItem(AXE.get()).setWeight(100))
//                                .add(LootItem.lootTableItem(FIREBRAND.get()).setWeight(1))
//                                .add(LootItem.lootTableItem(HIGHLAND_AXE.get()).setWeight(1))
//                                .add(LootItem.lootTableItem(SPEAR.get()).setWeight(100))
//                                .add(LootItem.lootTableItem(FORTUNE_SPEAR.get()).setWeight(1))
//                                .add(LootItem.lootTableItem(WHISPERING_SPEAR.get()).setWeight(1))
//                                .add(LootItem.lootTableItem(BONEBOW.get()).setWeight(1))
//                                .add(LootItem.lootTableItem(HAUNTED_BOW.get()).setWeight(1))
//                                .add(LootItem.lootTableItem(TWIN_BOW.get()).setWeight(1))
//                                .add(LootItem.lootTableItem(SOUL_BOW.get()).setWeight(100))
//                                .add(LootItem.lootTableItem(NOCTURNAL_BOW.get()).setWeight(1))
//                                .add(LootItem.lootTableItem(BOW_OF_LOST_SOULS.get()).setWeight(1))
//                                //.add(ItemLootEntry.lootTableItem(SHIVERING_BOW.get()).setWeight())
//                                .add(LootItem.lootTableItem(HUNTERS_ARMOR.get()).setWeight(100))
//                                .add(LootItem.lootTableItem(ARCHERS_ARMOR.get()).setWeight(1))
//                                .add(LootItem.lootTableItem(ARCHERS_ARMOR_HOOD.get()).setWeight(1))
//                                //.add(ItemLootEntry.lootTableItem(MYSTERY_ARMOR.get()).setWeight())
//                                .add(LootItem.lootTableItem(SOUL_ROBE.get()).setWeight(100))
//                                .add(LootItem.lootTableItem(SOUL_ROBE_HOOD.get()).setWeight(100))
//                                .add(LootItem.lootTableItem(SOULDANCER_ROBE.get()).setWeight(1))
//                                .add(LootItem.lootTableItem(SOULDANCER_ROBE_HOOD.get()).setWeight(1))
//                                .add(LootItem.lootTableItem(WOLF_ARMOR.get()).setWeight(100))
//                                .add(LootItem.lootTableItem(WOLF_ARMOR_HOOD.get()).setWeight(100))
//                                .add(LootItem.lootTableItem(FOX_ARMOR.get()).setWeight(1))
//                                .add(LootItem.lootTableItem(FOX_ARMOR_HOOD.get()).setWeight(1))
//                                //.add(ItemLootEntry.lootTableItem(BLACK_WOLF_ARMOR.get()).setWeight())
//                                //.add(ItemLootEntry.lootTableItem(BLACK_WOLF_ARMOR_HOOD.get()).setWeight())
                        ));
        consumer.accept(new ResourceLocation(DGMODID, "chests/creeper_woods/fancy"),
                LootTable.lootTable().
                        withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 3.0F))
//                                .add(LootItem.lootTableItem(SWORD.get()).setWeight(95).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(15)).allowTreasure()))
//                                .add(LootItem.lootTableItem(HAWKBRAND.get()).setWeight(5).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(15)).allowTreasure()))
//                                .add(LootItem.lootTableItem(DIAMOND_SWORD.get()).setWeight(5).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(15)).allowTreasure()))
//                                .add(LootItem.lootTableItem(AXE.get()).setWeight(95).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(15)).allowTreasure()))
//                                .add(LootItem.lootTableItem(FIREBRAND.get()).setWeight(5).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(15)).allowTreasure()))
//                                .add(LootItem.lootTableItem(HIGHLAND_AXE.get()).setWeight(5).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(15)).allowTreasure()))
//                                .add(LootItem.lootTableItem(SPEAR.get()).setWeight(95).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(15)).allowTreasure()))
//                                .add(LootItem.lootTableItem(FORTUNE_SPEAR.get()).setWeight(5).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(15)).allowTreasure()))
//                                .add(LootItem.lootTableItem(WHISPERING_SPEAR.get()).setWeight(5).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(15)).allowTreasure()))
//                                .add(LootItem.lootTableItem(BONEBOW.get()).setWeight(5).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(15)).allowTreasure()))
//                                .add(LootItem.lootTableItem(HAUNTED_BOW.get()).setWeight(5).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(15)).allowTreasure()))
//                                .add(LootItem.lootTableItem(TWIN_BOW.get()).setWeight(5).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(15)).allowTreasure()))
//                                .add(LootItem.lootTableItem(SOUL_BOW.get()).setWeight(95).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(15)).allowTreasure()))
//                                .add(LootItem.lootTableItem(NOCTURNAL_BOW.get()).setWeight(5).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(15)).allowTreasure()))
//                                .add(LootItem.lootTableItem(BOW_OF_LOST_SOULS.get()).setWeight(5).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(15)).allowTreasure()))
//                                //.add(ItemLootEntry.lootTableItem(SHIVERING_BOW.get()).setWeight().apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(15)).allowTreasure()))
//                                .add(LootItem.lootTableItem(HUNTERS_ARMOR.get()).setWeight(95).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(15)).allowTreasure()))
//                                .add(LootItem.lootTableItem(ARCHERS_ARMOR.get()).setWeight(5).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(15)).allowTreasure()))
//                                .add(LootItem.lootTableItem(ARCHERS_ARMOR_HOOD.get()).setWeight(5).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(15)).allowTreasure()))
//                                //.add(ItemLootEntry.lootTableItem(MYSTERY_ARMOR.get()).setWeight().apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(15)).allowTreasure()))
//                                .add(LootItem.lootTableItem(SOUL_ROBE.get()).setWeight(95).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(15)).allowTreasure()))
//                                .add(LootItem.lootTableItem(SOUL_ROBE_HOOD.get()).setWeight(95).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(15)).allowTreasure()))
//                                .add(LootItem.lootTableItem(SOULDANCER_ROBE.get()).setWeight(5).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(15)).allowTreasure()))
//                                .add(LootItem.lootTableItem(SOULDANCER_ROBE_HOOD.get()).setWeight(5).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(15)).allowTreasure()))
//                                .add(LootItem.lootTableItem(WOLF_ARMOR.get()).setWeight(95).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(15)).allowTreasure()))
//                                .add(LootItem.lootTableItem(WOLF_ARMOR_HOOD.get()).setWeight(95).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(15)).allowTreasure()))
//                                .add(LootItem.lootTableItem(FOX_ARMOR.get()).setWeight(5).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(15)).allowTreasure()))
//                                .add(LootItem.lootTableItem(FOX_ARMOR_HOOD.get()).setWeight(5).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(15)).allowTreasure()))
//                                //.add(ItemLootEntry.lootTableItem(BLACK_WOLF_ARMOR.get()).setWeight().apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(15)).allowTreasure()))
//                                //.add(ItemLootEntry.lootTableItem(BLACK_WOLF_ARMOR_HOOD.get()).setWeight().apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(15)).allowTreasure()))
//                                .add(LootItem.lootTableItem(TORMENT_QUIVER.get()).setWeight(95))
//                                .add(LootItem.lootTableItem(TASTY_BONE.get()).setWeight(95))
//                                .add(LootItem.lootTableItem(DEATH_CAP_MUSHROOM.get()).setWeight(95))
//                                .add(LootItem.lootTableItem(BOOTS_OF_SWIFTNESS.get()).setWeight(95))
                        ));
        consumer.accept(new ResourceLocation(DGMODID, "chests/creeper_woods/obsidian"),
                LootTable.lootTable().
                        withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 4.0F))
//                                .add(LootItem.lootTableItem(SWORD.get()).setWeight(90).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(30)).allowTreasure()))
//                                .add(LootItem.lootTableItem(HAWKBRAND.get()).setWeight(10).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(30)).allowTreasure()))
//                                .add(LootItem.lootTableItem(DIAMOND_SWORD.get()).setWeight(10).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(30)).allowTreasure()))
//                                .add(LootItem.lootTableItem(AXE.get()).setWeight(90).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(30)).allowTreasure()))
//                                .add(LootItem.lootTableItem(FIREBRAND.get()).setWeight(10).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(30)).allowTreasure()))
//                                .add(LootItem.lootTableItem(HIGHLAND_AXE.get()).setWeight(10).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(30)).allowTreasure()))
//                                .add(LootItem.lootTableItem(SPEAR.get()).setWeight(90).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(30)).allowTreasure()))
//                                .add(LootItem.lootTableItem(FORTUNE_SPEAR.get()).setWeight(10).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(30)).allowTreasure()))
//                                .add(LootItem.lootTableItem(WHISPERING_SPEAR.get()).setWeight(10).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(30)).allowTreasure()))
//                                .add(LootItem.lootTableItem(BONEBOW.get()).setWeight(10).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(30)).allowTreasure()))
//                                .add(LootItem.lootTableItem(HAUNTED_BOW.get()).setWeight(10).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(30)).allowTreasure()))
//                                .add(LootItem.lootTableItem(TWIN_BOW.get()).setWeight(10).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(30)).allowTreasure()))
//                                .add(LootItem.lootTableItem(SOUL_BOW.get()).setWeight(90).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(30)).allowTreasure()))
//                                .add(LootItem.lootTableItem(NOCTURNAL_BOW.get()).setWeight(10).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(30)).allowTreasure()))
//                                .add(LootItem.lootTableItem(BOW_OF_LOST_SOULS.get()).setWeight(10).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(30)).allowTreasure()))
//                                //.add(ItemLootEntry.lootTableItem(SHIVERING_BOW.get()).setWeight().apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(30)).allowTreasure()))
//                                .add(LootItem.lootTableItem(HUNTERS_ARMOR.get()).setWeight(90).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(30)).allowTreasure()))
//                                .add(LootItem.lootTableItem(ARCHERS_ARMOR.get()).setWeight(10).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(30)).allowTreasure()))
//                                .add(LootItem.lootTableItem(ARCHERS_ARMOR_HOOD.get()).setWeight(10).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(30)).allowTreasure()))
//                                //.add(ItemLootEntry.lootTableItem(MYSTERY_ARMOR.get()).setWeight().apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(30)).allowTreasure()))
//                                .add(LootItem.lootTableItem(SOUL_ROBE.get()).setWeight(90).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(30)).allowTreasure()))
//                                .add(LootItem.lootTableItem(SOUL_ROBE_HOOD.get()).setWeight(90).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(30)).allowTreasure()))
//                                .add(LootItem.lootTableItem(SOULDANCER_ROBE.get()).setWeight(10).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(30)).allowTreasure()))
//                                .add(LootItem.lootTableItem(SOULDANCER_ROBE_HOOD.get()).setWeight(10).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(30)).allowTreasure()))
//                                .add(LootItem.lootTableItem(WOLF_ARMOR.get()).setWeight(90).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(30)).allowTreasure()))
//                                .add(LootItem.lootTableItem(WOLF_ARMOR_HOOD.get()).setWeight(90).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(30)).allowTreasure()))
//                                .add(LootItem.lootTableItem(FOX_ARMOR.get()).setWeight(10).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(30)).allowTreasure()))
//                                .add(LootItem.lootTableItem(FOX_ARMOR_HOOD.get()).setWeight(10).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(30)).allowTreasure()))
//                                //.add(ItemLootEntry.lootTableItem(BLACK_WOLF_ARMOR.get()).setWeight().apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(30)).allowTreasure()))
//                                //.add(ItemLootEntry.lootTableItem(BLACK_WOLF_ARMOR_HOOD.get()).setWeight().apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(30)).allowTreasure()))
//                                .add(LootItem.lootTableItem(TORMENT_QUIVER.get()).setWeight(90))
//                                .add(LootItem.lootTableItem(TASTY_BONE.get()).setWeight(90))
//                                .add(LootItem.lootTableItem(DEATH_CAP_MUSHROOM.get()).setWeight(90))
//                                .add(LootItem.lootTableItem(BOOTS_OF_SWIFTNESS.get()).setWeight(90))
                        ));
    }

    private LootItemConditionalFunction.Builder<?> getHealingPotionSetNBT(){
        return SetNbtFunction.setTag(Util.make(new CompoundTag(), (p_218607_0_) -> {
            p_218607_0_.putString("Potion", "minecraft:healing");
        }));
    }

}