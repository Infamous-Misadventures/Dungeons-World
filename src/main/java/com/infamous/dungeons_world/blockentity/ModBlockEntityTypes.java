package com.infamous.dungeons_world.blockentity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.infamous.dungeons_world.DungeonsWorld.MODID;
import static com.infamous.dungeons_world.blocks.ModBlocks.*;

public class ModBlockEntityTypes {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);


    public static final RegistryObject<BlockEntityType<DungeonsChestBlockEntity>> CHEST = BLOCK_ENTITY_TYPES.register("chest",
            () -> BlockEntityType.Builder.of(DungeonsChestBlockEntity::new,
                    COMMON_CHEST.get(),
                    FANCY_CHEST.get(),
                    OBSIDIAN_CHEST.get()
            ).build(null));
}
