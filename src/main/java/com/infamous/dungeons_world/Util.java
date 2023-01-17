package com.infamous.dungeons_world;

import net.minecraft.resources.ResourceLocation;

import static com.infamous.dungeons_world.DungeonsWorld.MODID;

public class Util {
    public static ResourceLocation modLoc(String id){
        return new ResourceLocation(MODID,  id);
    }
}
