package com.infamous.dungeons_world.world.gen.provider;

import com.infamous.dungeons_world.DungeonsWorld;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProviderType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockstateProviders {
    public static final DeferredRegister<BlockStateProviderType<?>> BLOCK_STATE_PROVIDERS = DeferredRegister.create(ForgeRegistries.BLOCK_STATE_PROVIDER_TYPES, DungeonsWorld.MODID);

    public static final RegistryObject<BlockStateProviderType<?>> KEY_SUPPLIER = BLOCK_STATE_PROVIDERS.register("key_supplier", () -> new BlockStateProviderType<>(KeySupplierBockStateProvider.CODEC));
}
