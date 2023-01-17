package com.infamous.dungeons_world.init;

import com.infamous.dungeons_world.loot.DungeonsChestLootModifier;
import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.infamous.dungeons_world.DungeonsWorld.MODID;


public class GlobalLootModifierInit {

    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, MODID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> DUNGEONS_LOOT_ADDITIONS =
            LOOT_MODIFIER_SERIALIZERS.register("dungeons_chest_loot", DungeonsChestLootModifier.CODEC);

}