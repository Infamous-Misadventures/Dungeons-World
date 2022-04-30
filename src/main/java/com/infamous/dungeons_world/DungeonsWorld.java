package com.infamous.dungeons_world;

import com.infamous.dungeons_world.biomes.ModBiomes;
import com.infamous.dungeons_world.blocks.ModBlocks;
import com.infamous.dungeons_world.client.renderer.tileentity.DungeonsChestBlockEntityRenderer;
import com.infamous.dungeons_world.entity.ModEntityTypes;
import com.infamous.dungeons_world.items.ModItems;
import com.infamous.dungeons_world.particles.ModParticleTypes;
import com.infamous.dungeons_world.blockentity.ModBlockEntityTypes;
import com.infamous.dungeons_world.world.gen.feature.ModConfiguredFeatures;
import com.infamous.dungeons_world.world.gen.feature.ModFeatures;
import com.infamous.dungeons_world.world.gen.processors.ModProcessors;
import com.infamous.dungeons_world.world.gen.provider.ModBlockstateProviders;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(DungeonsWorld.MODID)
public class DungeonsWorld {
    public static final String MODID = "dungeons_world";
    public static final Logger LOGGER = LogManager.getLogger();

    public DungeonsWorld() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::doClientStuff);
        ModBlocks.BLOCKS.register(modEventBus);
        ModEntityTypes.ENTITY_TYPES.register(modEventBus);
        ModBlockEntityTypes.BLOCK_ENTITY_TYPES.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModBlockstateProviders.BLOCK_STATE_PROVIDERS.register(modEventBus);
        ModBiomes.BIOMES.register(modEventBus);
        ModFeatures.FEATURES.register(modEventBus);
        ModParticleTypes.PARTICLES.register((modEventBus));

        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
    }

    public static final CreativeModeTab TAB = new CreativeModeTab(MODID) {
        @Override
        public ItemStack makeIcon(){
            return new ItemStack(ModBlocks.FANCY_CHEST.get());
        }
    };

    private void setup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            ModConfiguredFeatures.registerConfiguredFeatures();
            ModProcessors.init();
            ModBiomes.initBiomes();
        });
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        ModBlocks.initRenderTypes();
        BlockEntityRenderers.register(ModBlockEntityTypes.CHEST.get(), DungeonsChestBlockEntityRenderer::new);
    }
}
