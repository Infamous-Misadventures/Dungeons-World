package com.infamous.dungeons_world.tileentity;

import com.infamous.dungeons_world.client.renderer.ChestAtlas;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.state.properties.ChestType;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class DungeonsChestTileEntity extends ChestTileEntity {
    RenderMaterial materialLeft;
    RenderMaterial materialRight;
    RenderMaterial materialSingle;

    public DungeonsChestTileEntity() {
        this(ModTileEntityTypes.CHEST.get());
    }

    public DungeonsChestTileEntity(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
    }

    public DungeonsChestTileEntity(DungeonsChestType chestType) {
        this(ModTileEntityTypes.CHEST.get());
        setRenderMaterial(chestType);
    }

    public RenderMaterial getMaterial(ChestType type) {
        switch(type) {
            case LEFT:
                return materialLeft == null ? Atlases.CHEST_LOCATION_LEFT : materialLeft;
            case RIGHT:
                return materialRight == null ? Atlases.CHEST_LOCATION_RIGHT : materialRight;
            case SINGLE:
            default:
                return materialSingle == null ? Atlases.CHEST_LOCATION : materialSingle;
        }
    }

    public void setRenderMaterial(DungeonsChestType chestType) {
        materialLeft = ChestAtlas.CHEST_LEFT_MATERIALS.getOrDefault(chestType, Atlases.CHEST_LOCATION_LEFT);
        materialRight = ChestAtlas.CHEST_RIGHT_MATERIALS.getOrDefault(chestType, Atlases.CHEST_LOCATION_RIGHT);
        materialSingle = ChestAtlas.CHEST_MATERIALS.getOrDefault(chestType, Atlases.CHEST_LOCATION);
    }

    @Override
    public TileEntityType<?> getType() {
        return ModTileEntityTypes.CHEST.get();
    }
}