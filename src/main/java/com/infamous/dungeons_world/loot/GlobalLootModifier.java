package com.infamous.dungeons_world.loot;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

import static com.infamous.dungeons_world.DungeonsWorld.MODID;


public class GlobalLootModifier {

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class GlobalLootModifierRegistry {

        @SubscribeEvent
        public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
            event.getRegistry().register(
                    new DungeonsChestLootModifier.Serializer().setRegistryName(new ResourceLocation(MODID, "dungeons_chest_loot"))
            );
        }
    }
}