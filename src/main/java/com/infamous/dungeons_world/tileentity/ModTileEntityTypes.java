package com.infamous.dungeons_world.tileentity;

import com.infamous.dungeons_world.blocks.ModBlocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.infamous.dungeons_world.DungeonsWorld.MODID;
import static com.infamous.dungeons_world.blocks.ModBlocks.*;

public class ModTileEntityTypes {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MODID);


    public static final RegistryObject<TileEntityType<DungeonsChestTileEntity>> CHEST = TILE_ENTITY_TYPES.register("chest",
            () -> TileEntityType.Builder.of(DungeonsChestTileEntity::new,
                    COMMON_CHEST.get(),
                    FANCY_CHEST.get(),
                    OBSIDIAN_CHEST.get()
            ).build(null));
}
