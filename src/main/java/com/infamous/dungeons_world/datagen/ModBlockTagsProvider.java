package com.infamous.dungeons_world.datagen;

import com.infamous.dungeons_world.DungeonsWorld;
import com.infamous.dungeons_world.blocks.BuildingBlockHelper;
import com.infamous.dungeons_world.blocks.ModBlocks;
import com.infamous.dungeons_world.tags.ModTags;
import net.minecraft.block.material.Material;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import static com.infamous.dungeons_world.blocks.ModBlocks.DEEP_DIRT;
import static com.infamous.dungeons_world.blocks.ModBlocks.DEEP_GRASS_BLOCK;
import static net.minecraft.block.Blocks.RED_MUSHROOM;

public class ModBlockTagsProvider extends BlockTagsProvider {

    public ModBlockTagsProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, DungeonsWorld.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        ModBlocks.BUILDING_BLOCK_HELPERS.forEach(this::addBlocksToTags);
        this.tag(ModTags.Blocks.DIRT).add(DEEP_DIRT.get());
        this.tag(ModTags.Blocks.DIRT).add(DEEP_GRASS_BLOCK.get());
    }

    private void addBlocksToTags(BuildingBlockHelper blockHelper) {
        this.tag(BlockTags.SLABS).add(blockHelper.getSlab().get());
        this.tag(BlockTags.STAIRS).add(blockHelper.getStairs().get());
        this.tag(BlockTags.BUTTONS).add(blockHelper.getButton().get());
        this.tag(BlockTags.PRESSURE_PLATES).add(blockHelper.getPressurePlate().get());
        this.tag(BlockTags.WALLS).add(blockHelper.getWall().get());
        if(Material.STONE.equals(blockHelper.getBlock().get().defaultBlockState().getMaterial())) {
            this.tag(BlockTags.STONE_PRESSURE_PLATES).add(blockHelper.getPressurePlate().get());
        }else{
            this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(blockHelper.getPressurePlate().get());
        }
    }
}