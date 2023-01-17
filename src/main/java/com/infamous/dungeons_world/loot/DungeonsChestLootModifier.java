package com.infamous.dungeons_world.loot;

import com.google.common.base.Suppliers;
import com.infamous.dungeons_world.blockentity.DungeonsChestType;
import com.infamous.dungeons_world.mixin.LootContextAccessor;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static com.infamous.dungeons_world.DungeonsWorld.MODID;

public class DungeonsChestLootModifier extends LootModifier {

    public static final Supplier<Codec<DungeonsChestLootModifier>> CODEC = Suppliers.memoize(()
            -> RecordCodecBuilder.create(instance -> codecStart(instance).apply(instance, DungeonsChestLootModifier::new)));

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

    public DungeonsChestLootModifier(final LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if(!ModList.get().isLoaded("dungeons_gear"))
           return generatedLoot; // easier blacklist for users

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

    protected static LootContext copyLootContextWithNewQueryID(LootContext oldLootContext, ResourceLocation newQueryID){
        LootContext newContext = new LootContext.Builder(oldLootContext).create(LootContextParamSets.CHEST);
        ((LootContextAccessor)newContext).dungeonsgear_setQueriedLootTableId(newQueryID);
        return newContext;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}