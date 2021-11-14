package com.infamous.dungeons_world.client.renderer.entity;

import com.infamous.dungeons_world.entity.item.ShieldItemFrameEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import static com.infamous.dungeons_world.DungeonsWorld.MODID;

public class ShieldItemFrameEntityRenderer extends EntityRenderer<ShieldItemFrameEntity> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(MODID, "textures/entity/common_slime.png");

    public ShieldItemFrameEntityRenderer(EntityRendererManager p_i46179_1_) {
        super(p_i46179_1_);
    }

    @Override
    public ResourceLocation getTextureLocation(ShieldItemFrameEntity p_110775_1_) {
        return TEXTURE;
    }
}
