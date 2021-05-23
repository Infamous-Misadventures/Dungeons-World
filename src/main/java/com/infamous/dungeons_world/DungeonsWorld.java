package com.infamous.dungeons_world;

import com.infamous.dungeons_world.biomes.ModBiomes;
import com.infamous.dungeons_world.blocks.ModBlocks;
import com.infamous.dungeons_world.items.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.infamous.dungeons_world.Util.ModLoc;

@Mod(DungeonsWorld.MODID)
public class DungeonsWorld {
    public static final String MODID = "dungeons_world";
    public static final Logger LOGGER = LogManager.getLogger();

    public DungeonsWorld() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::setup);
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModBiomes.BIOMES.register(modEventBus);
    }

    public static final ItemGroup TAB = new ItemGroup(MODID) {
        @Override
        public ItemStack makeIcon(){
            return new ItemStack(Items.ACACIA_DOOR);
        }
    };

    private void setup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            BiomeManager.addBiome(
                    BiomeManager.BiomeType.WARM,
                    new BiomeManager.BiomeEntry(
                            RegistryKey.create(Registry.BIOME_REGISTRY, ModLoc("creeper_woods")),
                            5
                    )
            );
        });
    }
}
