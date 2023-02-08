package com.infamous.dungeons_world.datagen;

import com.infamous.dungeons_world.DungeonsWorld;
import net.minecraft.world.level.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.LanguageProvider;

import static com.infamous.dungeons_world.world.biomes.ModBiomes.BIOME_NAMES;
import static com.infamous.dungeons_world.init.BlocksInit.BLOCK_IDS;
import static com.infamous.dungeons_world.items.ModItems.ITEM_IDS;
import static net.minecraftforge.registries.ForgeRegistries.BLOCKS;
import static net.minecraftforge.registries.ForgeRegistries.ITEMS;

public class ModLanguageProvider extends LanguageProvider {

    public ModLanguageProvider(DataGenerator gen, String locale) {
        super(gen, DungeonsWorld.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        BLOCK_IDS.forEach(this::addBlock);
        ITEM_IDS.forEach(this::addItem);
        add("itemGroup.dungeons_world", "Dungeons World");
        addConfigOptions();
        addTips();
        addBiomes();
    }

    private void addBiomes() {
        for (String biomeName : BIOME_NAMES) {
            add("biome.dungeons_world."+biomeName, getNameFromId(biomeName));
        }
    }

    private void addTips() {
        add("dungeons_world.tip.creepy_crypt_location", "The Creepy Crypt can be found in the Creeper Woods!");
    }

    private void addConfigOptions() {
    }

    private void addBlock(String blockId) {
        Block block = BLOCKS.getValue(new ResourceLocation(DungeonsWorld.MODID, blockId));
        add(block, getNameFromId(blockId));
    }

    private void addItem(String itemId) {
        Item item = ITEMS.getValue(new ResourceLocation(DungeonsWorld.MODID, itemId));
        add(item, getNameFromId(itemId));
    }

    private String getNameFromId(String idString) {
        StringBuilder sb = new StringBuilder();
        for(String word : idString.toLowerCase().split("_") )
        {
            sb.append(word.substring(0,1).toUpperCase() );
            sb.append(word.substring(1) );
            sb.append(" ");
        }
        return sb.toString().trim();
    }
}
