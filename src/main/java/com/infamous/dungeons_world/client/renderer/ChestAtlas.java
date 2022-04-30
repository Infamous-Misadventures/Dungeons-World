package com.infamous.dungeons_world.client.renderer;

import com.infamous.dungeons_world.blockentity.DungeonsChestType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.infamous.dungeons_world.DungeonsWorld.MODID;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ChestAtlas {
    public static final Map<DungeonsChestType, Material> CHEST_MATERIALS = new HashMap<>();
    public static final Map<DungeonsChestType, Material> CHEST_LEFT_MATERIALS = new HashMap<>();
    public static final Map<DungeonsChestType, Material> CHEST_RIGHT_MATERIALS = new HashMap<>();

    private static void prepareMaterials(){
        CHEST_MATERIALS.putAll(Arrays.stream(DungeonsChestType.values()).collect(Collectors.toMap(Function.identity(), chestType -> chestTexture(chestType, ""))));
        CHEST_LEFT_MATERIALS.putAll(Arrays.stream(DungeonsChestType.values()).collect(Collectors.toMap(Function.identity(),  chestType -> chestTexture(chestType, "_left"))));
        CHEST_RIGHT_MATERIALS.putAll(Arrays.stream(DungeonsChestType.values()).collect(Collectors.toMap(Function.identity(),  chestType -> chestTexture(chestType, "_right"))));
    }

    public static Material chestTexture(DungeonsChestType chestType, String suffix) {
        ResourceLocation location = new ResourceLocation(MODID, chestType.name().toLowerCase());
        return new Material(Sheets.CHEST_SHEET, new ResourceLocation(MODID, "entity/chest/" + location.getPath() + suffix));
    }

    @SubscribeEvent
    public static void onTextureStitchedPre(TextureStitchEvent.Pre event) {
        if (event.getAtlas().location().equals(Sheets.CHEST_SHEET)) {
            prepareMaterials();
            CHEST_MATERIALS.values().forEach(m -> event.addSprite(m.texture()));
            CHEST_LEFT_MATERIALS.values().forEach(m -> event.addSprite(m.texture()));
            CHEST_RIGHT_MATERIALS.values().forEach(m -> event.addSprite(m.texture()));
        }
    }
}
