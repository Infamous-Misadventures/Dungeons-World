package com.infamous.dungeons_world.world.gen.provider;

import com.infamous.dungeons_world.DungeonsWorld;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProviderType;
import net.minecraftforge.registries.ForgeRegistries;

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
  public BlockState getState(RandomSource randomIn, BlockPos blockPosIn) {
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