package com.infamous.dungeons_world.client.renderer.entity;

import com.infamous.dungeons_world.entity.item.ShieldItemFrameEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import mod.patrigan.slimierslimes.SlimierSlimes;
import mod.patrigan.slimierslimes.entities.AbstractSlimeEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.SlimeGelLayer;
import net.minecraft.client.renderer.entity.model.SlimeModel;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import static com.infamous.dungeons_world.DungeonsWorld.MODID;

public class ShieldItemFrameEntityRenderer extends EntityRenderer<ShieldItemFrameEntity> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(MODID, "textures/entity/common_slime.png");

    @Override
    public void render(ShieldItemFrameEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        this.shadowRadius = 0.25F * (float)entityIn.getSize();
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    protected void scale(ShieldItemFrameEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        float f = 0.999F;
        matrixStackIn.scale(f, f, f);
        matrixStackIn.translate(0.0D, (double)0.001F, 0.0D);
        float f1 = (float)entitylivingbaseIn.getSize();
        float f2 = MathHelper.lerp(partialTickTime, entitylivingbaseIn.prevSquishFactor, entitylivingbaseIn.squishFactor) / (f1 * 0.5F + 1.0F);
        float f3 = 1.0F / (f2 + 1.0F);
        matrixStackIn.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
    }

    @Override
    public ResourceLocation getTextureLocation(ShieldItemFrameEntity p_110775_1_) {
        return TEXTURE;
    }
}
