package com.infamous.dungeons_world.compat;

import com.infamous.dungeons_world.DungeonsWorld;
import net.minecraft.world.entity.EntityType;
import net.minecraft.resources.ResourceLocation;
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
    private static Supplier<EntityType<?>> NECROMANCER = () -> EntityType.ZOMBIE;

    @SubscribeEvent
    public static void onInterMod(InterModProcessEvent event){
        if(ModList.get().isLoaded(DUNGEONS_MOBS_MOD_ID)){
            IS_LOADED = true;
            ENCHANTER = () -> getRegisteredEntity("enchanter");
            NECROMANCER = () -> getRegisteredEntity("necromancer");
        }
    }

    private static EntityType<?> getRegisteredEntity(String entity) {
        return ForgeRegistries.ENTITY_TYPES.getValue(getDungeonsMobsResource(entity));
    }

    public static boolean isLoaded(){
        return IS_LOADED;
    }

    public static Supplier<EntityType<?>> getEnchanter() {
        return ENCHANTER;
    }

    public static Supplier<EntityType<?>> getNecromancer() {
        return NECROMANCER;
    }

    private static ResourceLocation getDungeonsMobsResource(String resourceId) {
        return new ResourceLocation(DUNGEONS_MOBS_MOD_ID, resourceId);
    }
}
