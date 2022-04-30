package com.infamous.dungeons_world.datagen;

import com.infamous.dungeons_world.DungeonsWorld;
import com.infamous.dungeons_world.blocks.BuildingBlockHelper;
import com.infamous.dungeons_world.blocks.ModBlocks;
import net.minecraft.world.level.material.Material;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import static com.infamous.dungeons_world.blocks.ModBlocks.*;

public class ModBlockTagsProvider extends BlockTagsProvider {

    public ModBlockTagsProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, DungeonsWorld.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        ModBlocks.BUILDING_BLOCK_HELPERS.forEach(this::addBlocksToTags);
        this.tag(BlockTags.DIRT).add(DEEP_DIRT.get());
        this.tag(BlockTags.DIRT).add(PEBBLED_DEEP_DIRT.get());
        this.tag(BlockTags.DIRT).add(DEEP_GRASSY_DIRT.get());
        this.tag(BlockTags.DIRT).add(DEEP_DIRTY_GRASS.get());
        this.tag(BlockTags.DIRT).add(DEEP_GRASS_BLOCK.get());
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

    private void addToolTags(BuildingBlockHelper blockHelper) {
        this.tag(BlockTags.SLABS).add(blockHelper.getSlab().get());
        this.tag(BlockTags.STAIRS).add(blockHelper.getStairs().get());
        this.tag(BlockTags.BUTTONS).add(blockHelper.getButton().get());
        this.tag(BlockTags.PRESSURE_PLATES).add(blockHelper.getPressurePlate().get());
        this.tag(BlockTags.WALLS).add(blockHelper.getWall().get());
        if(Material.STONE.equals(blockHelper.getBlock().get().defaultBlockState().getMaterial()) || Material.METAL.equals(blockHelper.getBlock().get().defaultBlockState().getMaterial())) {
            this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(blockHelper.getBlock().get());
            this.tag(BlockTags.STONE_PRESSURE_PLATES).add(blockHelper.getPressurePlate().get());
            this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(blockHelper.getSlab().get());
            this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(blockHelper.getStairs().get());
            this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(blockHelper.getWall().get());
            this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(blockHelper.getButton().get());
            this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(blockHelper.getPressurePlate().get());
        }else {
            this.tag(BlockTags.MINEABLE_WITH_AXE).add(blockHelper.getBlock().get());
            this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(blockHelper.getPressurePlate().get());
            this.tag(BlockTags.MINEABLE_WITH_AXE).add(blockHelper.getSlab().get());
            this.tag(BlockTags.MINEABLE_WITH_AXE).add(blockHelper.getStairs().get());
            this.tag(BlockTags.MINEABLE_WITH_AXE).add(blockHelper.getWall().get());
            this.tag(BlockTags.MINEABLE_WITH_AXE).add(blockHelper.getButton().get());
            this.tag(BlockTags.MINEABLE_WITH_AXE).add(blockHelper.getPressurePlate().get());
        }
    }
}