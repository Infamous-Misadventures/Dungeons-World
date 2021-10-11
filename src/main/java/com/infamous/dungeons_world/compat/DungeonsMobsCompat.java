package com.infamous.dungeons_world.compat;

import com.infamous.dungeons_world.DungeonsWorld;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = DungeonsWorld.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DungeonsMobsCompat {
    public static final String DUNGEONS_MOBS_MOD_ID = "dungeons_mobs";
    private static boolean IS_LOADED = false;
    private static Supplier<EntityType<?>> ENCHANTER = () -> EntityType.ZOMBIE;

    @SubscribeEvent
    public static void onInterMod(InterModProcessEvent event){
        if(ModList.get().isLoaded(DUNGEONS_MOBS_MOD_ID)){
            IS_LOADED = true;
            ENCHANTER = () -> getRegisteredEntity("enchanter");
        }
    }

    private static EntityType<?> getRegisteredEntity(String entity) {
        return ForgeRegistries.ENTITIES.getValue(getDungeonsMobsResource(entity));
    }

    public static boolean isLoaded(){
        return IS_LOADED;
    }

    public static Supplier<EntityType<?>> getEnchanter() {
        return ENCHANTER;
    }

    private static ResourceLocation getDungeonsMobsResource(String resourceId) {
        return new ResourceLocation(DUNGEONS_MOBS_MOD_ID, resourceId);
    }
}
