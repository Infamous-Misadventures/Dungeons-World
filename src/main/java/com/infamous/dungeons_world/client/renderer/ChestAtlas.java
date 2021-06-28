package com.infamous.dungeons_world.client.renderer;

import com.infamous.dungeons_world.tileentity.DungeonsChestType;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.infamous.dungeons_world.DungeonsWorld.MODID;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ChestAtlas {
    public static final Map<DungeonsChestType, RenderMaterial> CHEST_MATERIALS = Arrays.stream(DungeonsChestType.values()).collect(Collectors.toMap(Function.identity(), chestType -> chestTexture(chestType, "")));
    public static final Map<DungeonsChestType, RenderMaterial> CHEST_LEFT_MATERIALS = Arrays.stream(DungeonsChestType.values()).collect(Collectors.toMap(Function.identity(),  chestType -> chestTexture(chestType, "_left")));
    public static final Map<DungeonsChestType, RenderMaterial> CHEST_RIGHT_MATERIALS = Arrays.stream(DungeonsChestType.values()).collect(Collectors.toMap(Function.identity(),  chestType -> chestTexture(chestType, "_right")));

    public static RenderMaterial chestTexture(DungeonsChestType chestType, String suffix) {
        ResourceLocation location = new ResourceLocation(MODID, chestType.name().toLowerCase());
        return new RenderMaterial(Atlases.CHEST_SHEET, new ResourceLocation(MODID, "entity/chest/" + location.getPath() + suffix));
    }

    @SubscribeEvent
    public static void onTextureStitchedPre(TextureStitchEvent.Pre event) {
        if (event.getMap().location().equals(Atlases.CHEST_SHEET)) {
            CHEST_MATERIALS.values().forEach(m -> event.addSprite(m.texture()));
            CHEST_LEFT_MATERIALS.values().forEach(m -> event.addSprite(m.texture()));
            CHEST_RIGHT_MATERIALS.values().forEach(m -> event.addSprite(m.texture()));
        }
    }
}
