package com.infamous.dungeons_world.datagen;

import com.infamous.dungeons_world.structures.ModStructures;
import net.minecraft.data.loot.ChestLootTables;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.functions.EnchantWithLevels;
import net.minecraft.loot.functions.ExplorationMap;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.loot.functions.SetNBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.storage.MapDecoration;

import java.util.function.BiConsumer;

import static com.infamous.dungeons_gear.registry.ItemRegistry.*;
import static com.infamous.dungeons_world.DungeonsWorld.MODID;

public class ModChestLootTables extends ChestLootTables {

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
                        withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
                                .add(ItemLootEntry.lootTableItem(Items.BOOK).setWeight(5).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(10)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(Items.IRON_INGOT).setWeight(40).apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F))))
                                .add(ItemLootEntry.lootTableItem(Items.EMERALD).setWeight(20))
                                .add(ItemLootEntry.lootTableItem(Items.POTION).setWeight(20).apply(getHealingPotionSetNBT()))
                                .add(ItemLootEntry.lootTableItem(Items.BREAD).setWeight(20))
                                .add(ItemLootEntry.lootTableItem(Items.EXPERIENCE_BOTTLE).setWeight(10))
                        ));
        consumer.accept(FANCY,
                LootTable.lootTable().
                        withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
                                .add(ItemLootEntry.lootTableItem(Items.BOOK).setWeight(25).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(20)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(Items.IRON_INGOT).setWeight(30).apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F))))
                                .add(ItemLootEntry.lootTableItem(Items.EMERALD).setWeight(30).apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F))))
                                .add(ItemLootEntry.lootTableItem(Items.POTION).setWeight(20).apply(getHealingPotionSetNBT()))
                                .add(ItemLootEntry.lootTableItem(Items.BREAD).setWeight(10))
                                .add(ItemLootEntry.lootTableItem(Items.EXPERIENCE_BOTTLE).setWeight(15))
                        ));
        consumer.accept(OBSIDIAN,
                LootTable.lootTable().
                        withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
                                .add(ItemLootEntry.lootTableItem(Items.BOOK).setWeight(35).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(30)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(Items.IRON_INGOT).setWeight(10).apply(SetCount.setCount(RandomValueRange.between(1.0F, 6.0F))))
                                .add(ItemLootEntry.lootTableItem(Items.EMERALD).setWeight(25).apply(SetCount.setCount(RandomValueRange.between(1.0F, 5.0F))))
                                .add(ItemLootEntry.lootTableItem(Items.POTION).setWeight(20).apply(getHealingPotionSetNBT()))
                                .add(ItemLootEntry.lootTableItem(Items.EXPERIENCE_BOTTLE).setWeight(10))
                        ));
        creeperWoods(consumer);
    }

    private void createForDungeon(BiConsumer<ResourceLocation, LootTable.Builder> consumer, String dungeonName) {
        consumer.accept(new ResourceLocation(MODID, "chests/"+dungeonName+"/common"),
                LootTable.lootTable().
                        withPool(LootPool.lootPool().setRolls(RandomValueRange.between(4.0F, 8.0F))
                                .add(TableLootEntry.lootTableReference(COMMON).setWeight(COMMON_WEIGHT))));
        consumer.accept(new ResourceLocation(MODID, "chests/"+dungeonName+"/fancy"),
                LootTable.lootTable().
                        withPool(LootPool.lootPool().setRolls(RandomValueRange.between(5.0F, 8.0F))
                                .add(TableLootEntry.lootTableReference(FANCY).setWeight(FANCY_WEIGHT))
                                .add(ItemLootEntry.lootTableItem(Items.MAP).setWeight(10).apply(ExplorationMap.makeExplorationMap().setDestination(ModStructures.CW_RUINED_TOWER.get()).setMapDecoration(MapDecoration.Type.RED_X).setZoom((byte)1).setSkipKnownStructures(false)))
                        ));
        consumer.accept(new ResourceLocation(MODID, "chests/"+dungeonName+"/obsidian"),
                LootTable.lootTable().
                        withPool(LootPool.lootPool().setRolls(RandomValueRange.between(5.0F, 10.0F))
                                .add(TableLootEntry.lootTableReference(OBSIDIAN).setWeight(OBSIDIAN_WEIGHT))
                                .add(ItemLootEntry.lootTableItem(Items.MAP).setWeight(20).apply(ExplorationMap.makeExplorationMap().setDestination(ModStructures.CW_RUINED_TOWER.get())))
                        ));
    }

    private void creeperWoods(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
        createForDungeon(consumer, "creeper_woods");
        consumer.accept(new ResourceLocation(DGMODID, "chests/creeper_woods/common"),
                LootTable.lootTable().
                        withPool(LootPool.lootPool().setRolls(RandomValueRange.between(1.0F, 1.0F))
                                .add(ItemLootEntry.lootTableItem(SWORD.get()).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(HAWKBRAND.get()).setWeight(1))
                                .add(ItemLootEntry.lootTableItem(DIAMOND_SWORD.get()).setWeight(1))
                                .add(ItemLootEntry.lootTableItem(AXE.get()).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(FIREBRAND.get()).setWeight(1))
                                .add(ItemLootEntry.lootTableItem(HIGHLAND_AXE.get()).setWeight(1))
                                .add(ItemLootEntry.lootTableItem(SPEAR.get()).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(FORTUNE_SPEAR.get()).setWeight(1))
                                .add(ItemLootEntry.lootTableItem(WHISPERING_SPEAR.get()).setWeight(1))
                                .add(ItemLootEntry.lootTableItem(BONEBOW.get()).setWeight(1))
                                .add(ItemLootEntry.lootTableItem(HAUNTED_BOW.get()).setWeight(1))
                                .add(ItemLootEntry.lootTableItem(TWIN_BOW.get()).setWeight(1))
                                .add(ItemLootEntry.lootTableItem(SOUL_BOW.get()).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(NOCTURNAL_BOW.get()).setWeight(1))
                                .add(ItemLootEntry.lootTableItem(BOW_OF_LOST_SOULS.get()).setWeight(1))
                                //.add(ItemLootEntry.lootTableItem(SHIVERING_BOW.get()).setWeight())
                                .add(ItemLootEntry.lootTableItem(HUNTERS_ARMOR.get()).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(ARCHERS_ARMOR.get()).setWeight(1))
                                .add(ItemLootEntry.lootTableItem(ARCHERS_ARMOR_HOOD.get()).setWeight(1))
                                //.add(ItemLootEntry.lootTableItem(MYSTERY_ARMOR.get()).setWeight())
                                .add(ItemLootEntry.lootTableItem(SOUL_ROBE.get()).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(SOUL_ROBE_HOOD.get()).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(SOULDANCER_ROBE.get()).setWeight(1))
                                .add(ItemLootEntry.lootTableItem(SOULDANCER_ROBE_HOOD.get()).setWeight(1))
                                .add(ItemLootEntry.lootTableItem(WOLF_ARMOR.get()).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(WOLF_ARMOR_HOOD.get()).setWeight(100))
                                .add(ItemLootEntry.lootTableItem(FOX_ARMOR.get()).setWeight(1))
                                .add(ItemLootEntry.lootTableItem(FOX_ARMOR_HOOD.get()).setWeight(1))
                                //.add(ItemLootEntry.lootTableItem(BLACK_WOLF_ARMOR.get()).setWeight())
                                //.add(ItemLootEntry.lootTableItem(BLACK_WOLF_ARMOR_HOOD.get()).setWeight())
                        ));
        consumer.accept(new ResourceLocation(DGMODID, "chests/creeper_woods/fancy"),
                LootTable.lootTable().
                        withPool(LootPool.lootPool().setRolls(RandomValueRange.between(1.0F, 3.0F))
                                .add(ItemLootEntry.lootTableItem(SWORD.get()).setWeight(95).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(15)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(HAWKBRAND.get()).setWeight(5).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(15)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(DIAMOND_SWORD.get()).setWeight(5).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(15)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(AXE.get()).setWeight(95).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(15)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(FIREBRAND.get()).setWeight(5).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(15)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(HIGHLAND_AXE.get()).setWeight(5).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(15)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(SPEAR.get()).setWeight(95).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(15)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(FORTUNE_SPEAR.get()).setWeight(5).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(15)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(WHISPERING_SPEAR.get()).setWeight(5).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(15)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(BONEBOW.get()).setWeight(5).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(15)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(HAUNTED_BOW.get()).setWeight(5).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(15)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(TWIN_BOW.get()).setWeight(5).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(15)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(SOUL_BOW.get()).setWeight(95).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(15)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(NOCTURNAL_BOW.get()).setWeight(5).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(15)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(BOW_OF_LOST_SOULS.get()).setWeight(5).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(15)).allowTreasure()))
                                //.add(ItemLootEntry.lootTableItem(SHIVERING_BOW.get()).setWeight().apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(15)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(HUNTERS_ARMOR.get()).setWeight(95).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(15)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(ARCHERS_ARMOR.get()).setWeight(5).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(15)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(ARCHERS_ARMOR_HOOD.get()).setWeight(5).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(15)).allowTreasure()))
                                //.add(ItemLootEntry.lootTableItem(MYSTERY_ARMOR.get()).setWeight().apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(15)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(SOUL_ROBE.get()).setWeight(95).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(15)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(SOUL_ROBE_HOOD.get()).setWeight(95).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(15)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(SOULDANCER_ROBE.get()).setWeight(5).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(15)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(SOULDANCER_ROBE_HOOD.get()).setWeight(5).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(15)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(WOLF_ARMOR.get()).setWeight(95).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(15)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(WOLF_ARMOR_HOOD.get()).setWeight(95).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(15)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(FOX_ARMOR.get()).setWeight(5).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(15)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(FOX_ARMOR_HOOD.get()).setWeight(5).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(15)).allowTreasure()))
                                //.add(ItemLootEntry.lootTableItem(BLACK_WOLF_ARMOR.get()).setWeight().apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(15)).allowTreasure()))
                                //.add(ItemLootEntry.lootTableItem(BLACK_WOLF_ARMOR_HOOD.get()).setWeight().apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(15)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(TORMENT_QUIVER.get()).setWeight(95))
                                .add(ItemLootEntry.lootTableItem(TASTY_BONE.get()).setWeight(95))
                                .add(ItemLootEntry.lootTableItem(DEATH_CAP_MUSHROOM.get()).setWeight(95))
                                .add(ItemLootEntry.lootTableItem(BOOTS_OF_SWIFTNESS.get()).setWeight(95))
                        ));
        consumer.accept(new ResourceLocation(DGMODID, "chests/creeper_woods/obsidian"),
                LootTable.lootTable().
                        withPool(LootPool.lootPool().setRolls(RandomValueRange.between(2.0F, 4.0F))
                                .add(ItemLootEntry.lootTableItem(SWORD.get()).setWeight(90).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(30)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(HAWKBRAND.get()).setWeight(10).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(30)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(DIAMOND_SWORD.get()).setWeight(10).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(30)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(AXE.get()).setWeight(90).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(30)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(FIREBRAND.get()).setWeight(10).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(30)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(HIGHLAND_AXE.get()).setWeight(10).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(30)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(SPEAR.get()).setWeight(90).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(30)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(FORTUNE_SPEAR.get()).setWeight(10).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(30)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(WHISPERING_SPEAR.get()).setWeight(10).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(30)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(BONEBOW.get()).setWeight(10).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(30)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(HAUNTED_BOW.get()).setWeight(10).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(30)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(TWIN_BOW.get()).setWeight(10).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(30)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(SOUL_BOW.get()).setWeight(90).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(30)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(NOCTURNAL_BOW.get()).setWeight(10).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(30)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(BOW_OF_LOST_SOULS.get()).setWeight(10).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(30)).allowTreasure()))
                                //.add(ItemLootEntry.lootTableItem(SHIVERING_BOW.get()).setWeight().apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(30)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(HUNTERS_ARMOR.get()).setWeight(90).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(30)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(ARCHERS_ARMOR.get()).setWeight(10).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(30)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(ARCHERS_ARMOR_HOOD.get()).setWeight(10).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(30)).allowTreasure()))
                                //.add(ItemLootEntry.lootTableItem(MYSTERY_ARMOR.get()).setWeight().apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(30)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(SOUL_ROBE.get()).setWeight(90).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(30)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(SOUL_ROBE_HOOD.get()).setWeight(90).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(30)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(SOULDANCER_ROBE.get()).setWeight(10).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(30)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(SOULDANCER_ROBE_HOOD.get()).setWeight(10).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(30)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(WOLF_ARMOR.get()).setWeight(90).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(30)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(WOLF_ARMOR_HOOD.get()).setWeight(90).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(30)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(FOX_ARMOR.get()).setWeight(10).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(30)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(FOX_ARMOR_HOOD.get()).setWeight(10).apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(30)).allowTreasure()))
                                //.add(ItemLootEntry.lootTableItem(BLACK_WOLF_ARMOR.get()).setWeight().apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(30)).allowTreasure()))
                                //.add(ItemLootEntry.lootTableItem(BLACK_WOLF_ARMOR_HOOD.get()).setWeight().apply(EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(30)).allowTreasure()))
                                .add(ItemLootEntry.lootTableItem(TORMENT_QUIVER.get()).setWeight(90))
                                .add(ItemLootEntry.lootTableItem(TASTY_BONE.get()).setWeight(90))
                                .add(ItemLootEntry.lootTableItem(DEATH_CAP_MUSHROOM.get()).setWeight(90))
                                .add(ItemLootEntry.lootTableItem(BOOTS_OF_SWIFTNESS.get()).setWeight(90))
                        ));
    }

    private LootFunction.Builder<?> getHealingPotionSetNBT(){
        return SetNBT.setTag(Util.make(new CompoundNBT(), (p_218607_0_) -> {
            p_218607_0_.putString("Potion", "minecraft:healing");
        }));
    }

}