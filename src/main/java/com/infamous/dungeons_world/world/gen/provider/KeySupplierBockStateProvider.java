package com.infamous.dungeons_world.world.gen.provider;

import com.infamous.dungeons_world.DungeonsWorld;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.BlockStateProviderType;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

public class KeySupplierBockStateProvider extends BlockStateProvider {
  public static final Codec<KeySupplierBockStateProvider> CODEC = ResourceLocation.CODEC.fieldOf("key").xmap(KeySupplierBockStateProvider::new, (provider) -> provider.key).codec();

  protected final ResourceLocation key;
  protected BlockState state = null;

  public KeySupplierBockStateProvider(String namespace, String path) {
    this(new ResourceLocation(namespace, path));
  }

  public KeySupplierBockStateProvider(ResourceLocation key) {
    this.key = key;
  }

  @Override
  protected BlockStateProviderType<?> type(){
    return ModBlockstateProviders.KEY_SUPPLIER.get();
  }

  @Override
  public BlockState getState(Random randomIn, BlockPos blockPosIn) {
    if (state == null) {
      Block block = ForgeRegistries.BLOCKS.getValue(key);
      if (block == null) {
        DungeonsWorld.LOGGER.error("Block couldn't be located for key: " + key);
        state = Blocks.AIR.defaultBlockState();
      } else {
        state = block.defaultBlockState();
      }
    }

    return state;
  }
}