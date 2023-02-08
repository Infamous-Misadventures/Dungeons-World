package com.infamous.dungeons_world;

import com.infamous.dungeons_world.blockentity.ModBlockEntityTypes;
import com.infamous.dungeons_world.init.BlocksInit;
import com.infamous.dungeons_world.client.renderer.tileentity.DungeonsChestBlockEntityRenderer;
import com.infamous.dungeons_world.entity.ModEntityTypes;
import com.infamous.dungeons_world.init.ConditionSourceInit;
import com.infamous.dungeons_world.init.GlobalLootModifierInit;
import com.infamous.dungeons_world.items.ModItems;
import com.infamous.dungeons_world.particles.ModParticleTypes;
import com.infamous.dungeons_world.world.biomes.ModBiomes;
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
        BlocksInit.BLOCKS.register(modEventBus);
        ModEntityTypes.ENTITY_TYPES.register(modEventBus);
        ModBlockEntityTypes.BLOCK_ENTITY_TYPES.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModBlockstateProviders.BLOCK_STATE_PROVIDERS.register(modEventBus);
        ModBiomes.BIOMES.register(modEventBus);
        ModFeatures.FEATURES.register(modEventBus);
        ModParticleTypes.PARTICLES.register((modEventBus));
        GlobalLootModifierInit.LOOT_MODIFIER_SERIALIZERS.register(modEventBus);
        ConditionSourceInit.CONDITION_SOURCES.register(modEventBus);

        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
    }

    public static final CreativeModeTab TAB = new CreativeModeTab(MODID) {
        @Override
        public ItemStack makeIcon(){
            return new ItemStack(BlocksInit.FANCY_CHEST.get());
        }
    };

    private void setup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            ModProcessors.init();
            ModBiomes.initBiomes();
        });
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        BlocksInit.initRenderTypes();
        BlockEntityRenderers.register(ModBlockEntityTypes.CHEST.get(), DungeonsChestBlockEntityRenderer::new);
    }
}
