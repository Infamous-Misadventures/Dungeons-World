package com.infamous.dungeons_world;

import com.infamous.dungeons_world.biomes.ModBiomes;
import com.infamous.dungeons_world.blocks.ModBlocks;
import com.infamous.dungeons_world.client.renderer.tileentity.DungeonsChestTileEntityRenderer;
import com.infamous.dungeons_world.items.ModItems;
import com.infamous.dungeons_world.particles.ModParticleTypes;
import com.infamous.dungeons_world.structures.ModConfiguredStructures;
import com.infamous.dungeons_world.structures.ModStructures;
import com.infamous.dungeons_world.tileentity.ModTileEntityTypes;
import com.infamous.dungeons_world.world.gen.feature.ModConfiguredFeatures;
import com.infamous.dungeons_world.world.gen.feature.ModFeatures;
import com.infamous.dungeons_world.world.gen.processors.ModProcessors;
import com.infamous.dungeons_world.world.gen.provider.ModBlockstateProviders;
import com.infamous.dungeons_world.world.surfacebuilder.ModSurfaceBuilders;
import com.mojang.serialization.Codec;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Mod(DungeonsWorld.MODID)
public class DungeonsWorld {
    public static final String MODID = "dungeons_world";
    public static final Logger LOGGER = LogManager.getLogger();

    public DungeonsWorld() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::doClientStuff);
        ModBlocks.BLOCKS.register(modEventBus);
        ModTileEntityTypes.TILE_ENTITY_TYPES.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModBlockstateProviders.BLOCK_STATE_PROVIDERS.register(modEventBus);
        ModBiomes.BIOMES.register(modEventBus);
        ModFeatures.FEATURES.register(modEventBus);
        ModStructures.STRUCTURES.register(modEventBus);
        ModSurfaceBuilders.SURFACE_BUILDERS.register(modEventBus);
        ModParticleTypes.PARTICLES.register((modEventBus));
        modEventBus.addListener(this::setup);

        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        forgeBus.addListener(EventPriority.NORMAL, this::addDimensionalSpacing);

        forgeBus.addListener(EventPriority.HIGH, this::biomeModification);
    }

    public static final ItemGroup TAB = new ItemGroup(MODID) {
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
            ModStructures.setupStructures();
            ModConfiguredStructures.registerConfiguredStructures();
            ModBiomes.initBiomes();
        });
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        ModBlocks.initRenderTypes();
        ClientRegistry.bindTileEntityRenderer(ModTileEntityTypes.CHEST.get(), DungeonsChestTileEntityRenderer::new);
    }


    /**
     * This is the event you will use to add anything to any biome.
     * This includes spawns, changing the biome's looks, messing with its surfacebuilders,
     * adding carvers, spawning new features... etc
     *
     * Here, we will use this to add our structure to all biomes.
     */
    public void biomeModification(final BiomeLoadingEvent event) {
        /*
         * Add our structure to all biomes including other modded biomes.
         * You can skip or add only to certain biomes based on stuff like biome category,
         * temperature, scale, precipitation, mod id, etc. All kinds of options!
         *
         * You can even use the BiomeDictionary as well! To use BiomeDictionary, do
         * RegistryKey.getOrCreateKey(Registry.BIOME_KEY, event.getName()) to get the biome's
         * registrykey. Then that can be fed into the dictionary to get the biome's types.
         */
        if(event.getName().equals(Biomes.PLAINS.getRegistryName())) {
            event.getGeneration().getStructures().add(() -> ModConfiguredStructures.CONFIGURED_DUNGEONS_BLACKSMITH);
        }
    }

    /**
     * Will go into the world's chunkgenerator and manually add our structure spacing.
     * If the spacing is not added, the structure doesn't spawn.
     *
     * Use this for dimension blacklists for your structure.
     * (Don't forget to attempt to remove your structure too from the map if you are blacklisting that dimension!)
     * (It might have your structure in it already.)
     *
     * Basically use this to make absolutely sure the chunkgenerator can or cannot spawn your structure.
     */
    private static Method GETCODEC_METHOD;
    public void addDimensionalSpacing(final WorldEvent.Load event) {
        if(event.getWorld() instanceof ServerWorld){
            ServerWorld serverWorld = (ServerWorld)event.getWorld();

            /*
             * Skip Terraforged's chunk generator as they are a special case of a mod locking down their chunkgenerator.
             * They will handle your structure spacing for your if you add to WorldGenRegistries.NOISE_GENERATOR_SETTINGS in your structure's registration.
             * This here is done with reflection as this tutorial is not about setting up and using Mixins.
             * If you are using mixins, you can call the codec method with an invoker mixin instead of using reflection.
             */
            try {
                if(GETCODEC_METHOD == null) GETCODEC_METHOD = ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "func_230347_a_");
                ResourceLocation cgRL = Registry.CHUNK_GENERATOR.getKey((Codec<? extends ChunkGenerator>) GETCODEC_METHOD.invoke(serverWorld.getChunkSource().generator));
                if(cgRL != null && cgRL.getNamespace().equals("terraforged")) return;
            }
            catch(Exception e){
                DungeonsWorld.LOGGER.error("Was unable to check if " + serverWorld.dimension().location() + " is using Terraforged's ChunkGenerator.");
            }

            /*
             * Prevent spawning our structure in Vanilla's superflat world as
             * people seem to want their superflat worlds free of modded structures.
             * Also that vanilla superflat is really tricky and buggy to work with in my experience.
             */
            if(serverWorld.getChunkSource().getGenerator() instanceof FlatChunkGenerator &&
                    serverWorld.dimension().equals(World.OVERWORLD)){
                return;
            }

            /*
             * putIfAbsent so people can override the spacing with dimension datapacks themselves if they wish to customize spacing more precisely per dimension.
             * Requires AccessTransformer  (see resources/META-INF/accesstransformer.cfg)
             *
             * NOTE: if you add per-dimension spacing configs, you can't use putIfAbsent as WorldGenRegistries.NOISE_GENERATOR_SETTINGS in FMLCommonSetupEvent
             * already added your default structure spacing to some dimensions. You would need to override the spacing with .put(...)
             * And if you want to do dimension blacklisting, you need to remove the spacing entry entirely from the map below to prevent generation safely.
             */
            Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(serverWorld.getChunkSource().generator.getSettings().structureConfig());
            tempMap.putIfAbsent(ModStructures.CW_RUINED_TOWER.get(), DimensionStructuresSettings.DEFAULTS.get(ModStructures.CW_RUINED_TOWER.get()));
            tempMap.putIfAbsent(ModStructures.CW_ANCIENT_TRUNK.get(), DimensionStructuresSettings.DEFAULTS.get(ModStructures.CW_ANCIENT_TRUNK.get()));
            tempMap.putIfAbsent(ModStructures.CW_CREEPER_HEAD.get(), DimensionStructuresSettings.DEFAULTS.get(ModStructures.CW_CREEPER_HEAD.get()));
            tempMap.putIfAbsent(ModStructures.CW_ENDERMAN_HEAD.get(), DimensionStructuresSettings.DEFAULTS.get(ModStructures.CW_ENDERMAN_HEAD.get()));
            tempMap.putIfAbsent(ModStructures.CREEPY_CRYPT.get(), DimensionStructuresSettings.DEFAULTS.get(ModStructures.CREEPY_CRYPT.get()));
            tempMap.putIfAbsent(ModStructures.ILLAGER_CARAVAN.get(), DimensionStructuresSettings.DEFAULTS.get(ModStructures.ILLAGER_CARAVAN.get()));
            tempMap.putIfAbsent(ModStructures.DUNGEONS_BLACKSMITH.get(), DimensionStructuresSettings.DEFAULTS.get(ModStructures.DUNGEONS_BLACKSMITH.get()));
            serverWorld.getChunkSource().generator.getSettings().structureConfig = tempMap;
        }
    }
}
