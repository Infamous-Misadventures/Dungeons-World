package com.infamous.dungeons_world.entity;

import com.infamous.dungeons_world.entity.item.ShieldItemFrameEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.infamous.dungeons_world.DungeonsWorld.MODID;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, MODID);

    public static final RegistryObject<EntityType<ShieldItemFrameEntity>> SHIELD_ITEM_FRAME = ENTITY_TYPES.register("shield_item_frame", () -> EntityType.Builder.<ShieldItemFrameEntity>of(ShieldItemFrameEntity::new, EntityClassification.MISC).sized(0.5F, 0.5F).clientTrackingRange(10).updateInterval(Integer.MAX_VALUE)
            .build(new ResourceLocation(MODID, "shield_item_frame").toString()));
}
