package com.infamous.dungeons_world.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.RegistryObject;

import static com.infamous.dungeons_world.DungeonsWorld.MODID;

public class BuildingBlockHelper {
    public static final String SLAB_ID = "_slab";
    public static final String STAIRS_ID = "_stairs";
    public static final String BUTTON_ID = "_button";
    public static final String PLATE_ID = "_pressure_plate";
    public static final String WALL_ID = "_wall";

    private String blockId;
    private Integer color = null;
    private RegistryObject<Block> block;
    private RegistryObject<Block> slab;
    private RegistryObject<Block> stairs;
    private RegistryObject<Block> button;
    private RegistryObject<Block> pressurePlate;
    private RegistryObject<Block> wall;

    public BuildingBlockHelper(String blockId, Integer color, RegistryObject<Block> block, RegistryObject<Block> slab, RegistryObject<Block> stairs, RegistryObject<Block> button, RegistryObject<Block> pressurePlate, RegistryObject<Block> wall) {
        this.blockId = blockId;
        this.color = color;
        this.block = block;
        this.slab = slab;
        this.stairs = stairs;
        this.button = button;
        this.pressurePlate = pressurePlate;
        this.wall = wall;
    }

    public BuildingBlockHelper(String blockId, RegistryObject<Block> block, RegistryObject<Block> slab, RegistryObject<Block> stairs, RegistryObject<Block> button, RegistryObject<Block> pressurePlate, RegistryObject<Block> wall) {
        this.blockId = blockId;
        this.block = block;
        this.slab = slab;
        this.stairs = stairs;
        this.button = button;
        this.pressurePlate = pressurePlate;
        this.wall = wall;
    }

    public String getBlockId() {
        return blockId;
    }

    public String getId() {
        if(color == null) {
            return blockId;
        }else{
            return color + "_" + blockId;
        }
    }

    public RegistryObject<Block> getBlock() {
        return block;
    }

    public RegistryObject<Block> getSlab() {
        return slab;
    }

    public RegistryObject<Block> getStairs() {
        return stairs;
    }

    public RegistryObject<Block> getButton() {
        return button;
    }

    public RegistryObject<Block> getPressurePlate() {
        return pressurePlate;
    }

    public RegistryObject<Block> getWall() {
        return wall;
    }

    public ResourceLocation getResourceLocation(){
        return new ResourceLocation(MODID, blockId);
    }

    public Integer getColor() {
        return color;
    }

    public static class Builder {

        private String blockId;
        private Integer color;
        private RegistryObject<Block> block;
        private RegistryObject<Block> slab;
        private RegistryObject<Block> stairs;
        private RegistryObject<Block> button;
        private RegistryObject<Block> pressurePlate;
        private RegistryObject<Block> wall;

        public Builder withBlockId(String blockId) {
            this.blockId = blockId;
            return this;
        }

        public Builder withColor(Integer color) {
            this.color = color;
            return this;
        }

        public Builder withBlock(RegistryObject<Block> block) {
            this.block = block;
            return this;
        }

        public Builder withSlab(RegistryObject<Block> slab) {
            this.slab = slab;
            return this;
        }

        public Builder withStairs(RegistryObject<Block> stairs) {
            this.stairs = stairs;
            return this;
        }

        public Builder withButton(RegistryObject<Block> button) {
            this.button = button;
            return this;
        }

        public Builder withPressurePlate(RegistryObject<Block> pressurePlate) {
            this.pressurePlate = pressurePlate;
            return this;
        }

        public Builder withWall(RegistryObject<Block> wall) {
            this.wall = wall;
            return this;
        }

        public BuildingBlockHelper createBuildingBlockHelper() {
            return new BuildingBlockHelper(blockId, color, block, slab, stairs, button, pressurePlate, wall);
        }

    }


}
