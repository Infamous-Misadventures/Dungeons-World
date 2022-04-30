package com.infamous.dungeons_world.client.particles;

import net.minecraft.client.particle.*;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.SimpleParticleType;

public class GlowingMushroomParticle  extends TextureSheetParticle {
    private GlowingMushroomParticle(ClientLevel p_i232403_1_, double p_i232403_2_, double p_i232403_4_, double p_i232403_6_) {
        super(p_i232403_1_, p_i232403_2_, p_i232403_4_, p_i232403_6_, 0.0D, 0.0D, 0.0D);
        this.xd *= 0.2F;
        this.yd *= 0.2F;
        this.zd *= 0.2F;
        //his.yd = (double)(this.random.nextFloat() * 0.4F + 0.05F);
        this.quadSize *= this.random.nextFloat() * 2.0F + 0.4F;
        this.lifetime = (int)(50.0D / (Math.random() * 0.8D + 0.2D));
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public int getLightColor(float p_189214_1_) {
        return 240;
    }

    public float getQuadSize(float p_217561_1_) {
        return this.quadSize;
    }

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        float f = (float)this.age / (float)this.lifetime;

        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            this.move(this.xd, this.yd, this.zd);
            this.xd *= 0.999D;
            this.yd *= 0.999D;
            this.zd *= 0.999D;
            if(this.age % 3 == 0){
                this.xd *= -1;
                this.yd *= -1;
                this.zd *= -1;
            }
        }
    }

    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public Factory(SpriteSet p_i50495_1_) {
            this.sprite = p_i50495_1_;
        }

        public Particle createParticle(SimpleParticleType p_199234_1_, ClientLevel p_199234_2_, double p_199234_3_, double p_199234_5_, double p_199234_7_, double p_199234_9_, double p_199234_11_, double p_199234_13_) {
            GlowingMushroomParticle glowingMushroomParticle = new GlowingMushroomParticle(p_199234_2_, p_199234_3_, p_199234_5_, p_199234_7_);
            glowingMushroomParticle.pickSprite(this.sprite);
            glowingMushroomParticle.setColor(238/256F, 193/256F, 86/256F);
            return glowingMushroomParticle;
        }
    }
}