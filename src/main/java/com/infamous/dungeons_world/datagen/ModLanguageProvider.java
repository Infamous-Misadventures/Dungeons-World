package com.infamous.dungeons_world.datagen;

import com.infamous.dungeons_world.DungeonsWorld;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.LanguageProvider;

import static com.infamous.dungeons_world.blocks.ModBlocks.BLOCK_IDS;
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
