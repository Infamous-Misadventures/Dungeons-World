package com.infamous.dungeons_world.datagen;

import com.infamous.dungeons_world.DungeonsWorld;
import com.infamous.dungeons_world.blocks.BuildingBlockHelper;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

import static com.infamous.dungeons_world.blocks.ModBlocks.*;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, DungeonsWorld.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        registerBlockItems();
    }

    private void registerBlockItems() {
        BUILDING_BLOCK_HELPERS.forEach(this::registerBuildingBlockItems);
        SINGLE_BLOCKS.forEach(this::registerSingleBlockItems);
        blockItemModel(CHISELED_STONE_COLUMN.get());
    }

    private void registerSingleBlockItems(RegistryObject<Block> blockRegistryObject) {
        blockItemModel(blockRegistryObject.get());
    }

    private void registerBuildingBlockItems(BuildingBlockHelper blockHelper) {
        blockItemModel(blockHelper.getBlock().get());
        blockItemModel(blockHelper.getStairs().get());
        blockItemModel(blockHelper.getSlab().get());
        blockInventoryModel(blockHelper.getWall().get());
        blockInventoryModel(blockHelper.getButton().get());
        blockItemModel(blockHelper.getPressurePlate().get());
    }

    private void blockItemModel(Block block) {
        String name = block.getRegistryName().getPath();
        getBuilder(name).parent(new ModelFile.UncheckedModelFile(modLoc(ModelProvider.BLOCK_FOLDER + "/" + name)));
    }

    private void blockInventoryModel(Block block) {
        String name = block.getRegistryName().getPath();
        getBuilder(name).parent(new ModelFile.UncheckedModelFile(modLoc(ModelProvider.BLOCK_FOLDER + "/" + name + "_inventory")));
    }

    private void generated(String path, ResourceLocation texture) {
        getBuilder(path).parent(new ModelFile.UncheckedModelFile(mcLoc("item/generated"))).texture("layer0", texture);
    }
}
