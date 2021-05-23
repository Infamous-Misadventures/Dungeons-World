package com.infamous.dungeons_world.datagen;

import com.infamous.dungeons_world.DungeonsWorld;
import com.infamous.dungeons_world.blocks.BuildingBlockHelper;
import com.infamous.dungeons_world.blocks.ModBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockTagsProvider extends BlockTagsProvider {

    public ModBlockTagsProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, DungeonsWorld.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        ModBlocks.BUILDING_BLOCK_HELPERS.forEach(this::addBlocksToTags);
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