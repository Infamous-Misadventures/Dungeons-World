package com.infamous.dungeons_world.loot;

import com.google.gson.JsonObject;
import com.infamous.dungeons_world.mixin.LootContextAccessor;
import com.infamous.dungeons_world.tileentity.DungeonsChestType;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

import java.util.*;

import static com.infamous.dungeons_world.DungeonsWorld.MODID;

public class DungeonsChestLootModifier extends LootModifier {

    private static String DGMODID = "dungeons_gear";

    // Need to map loottables by hand to the vanilla structure that our structure is based on. (usually...)
    private static final List<ResourceLocation> TABLE_LIST = createList();

    private static List<ResourceLocation> createList() {
        List<ResourceLocation> tableList = new ArrayList<>();
        Arrays.stream(DungeonsChestType.values()).forEach(dungeonsChestType -> {
            tableList.add(new ResourceLocation(MODID, "chests/creeper_woods/"+dungeonsChestType.name().toLowerCase()));
        });
        return tableList;
    }

    public DungeonsChestLootModifier(final ILootCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        //if(!RepurposedStructures.RSModdedLootConfig.importModdedItems.get() || isInBlacklist(context.getQueriedLootTableId()))
           // return generatedLoot; // easier blacklist for users

        if(!TABLE_LIST.contains(context.getQueriedLootTableId()))
            return generatedLoot; // Safety net

        ResourceLocation tableToImportLoot = new ResourceLocation(DGMODID, context.getQueriedLootTableId().getPath());
        // Generate random loot from the dungeons gear tables
        LootContext newContext = copyLootContextWithNewQueryID(context, tableToImportLoot);
        List<ItemStack> newlyGeneratedLoot = context.getLootTable(tableToImportLoot).getRandomItems(newContext);

        // Add Dungeons Gear loot to Dungeons World Chest
        generatedLoot.addAll(newlyGeneratedLoot);
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<DungeonsChestLootModifier> {
        @Override
        public DungeonsChestLootModifier read(ResourceLocation location, JsonObject object, ILootCondition[] conditions) {
            return new DungeonsChestLootModifier(conditions);
        }

        @Override
        public JsonObject write(DungeonsChestLootModifier instance) {
            return this.makeConditions(instance.conditions);
        }
    }

    protected static LootContext copyLootContextWithNewQueryID(LootContext oldLootContext, ResourceLocation newQueryID){
        LootContext newContext = new LootContext.Builder(oldLootContext).create(LootParameterSets.CHEST);
        ((LootContextAccessor)newContext).dungeonsgear_setQueriedLootTableId(newQueryID);
        return newContext;
    }
}