package com.infamous.dungeons_world.datagen;

import com.infamous.dungeons_world.blocks.BuildingBlockHelper;
import com.infamous.dungeons_world.blocks.LinkedFenceBlock;
import com.infamous.dungeons_world.blocks.PathBlock;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.infamous.dungeons_world.DungeonsWorld.MODID;
import static com.infamous.dungeons_world.blocks.ModBlocks.*;
import static net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock.FACE;
import static net.minecraft.world.level.block.HorizontalDirectionalBlock.FACING;
import static net.minecraftforge.client.model.generators.ModelProvider.BLOCK_FOLDER;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        BUILDING_BLOCK_HELPERS.forEach(this::registerBuildingBlockHelper);
        SINGLE_BLOCKS.forEach(this::registerSingleBlock);
        PATH_BLOCKS.forEach(this::registerPathBlock);
        ROTTEN_BLOCKS.forEach(this::registerRottenBlock);
        handleSpecialClassBlocks();
        registerColumnBlock(PILLAR_STONE_COLUMN);
        registerColumnBlock(LINES_STONE_COLUMN, new ResourceLocation(MODID, ModelProvider.BLOCK_FOLDER + "/stone_column_top"));
        registerColumnBlock(SPIDER_EGG);
        registerColumnBlock(GROOVED_POLISHED_GRANITE_COLUMN, new ResourceLocation(MODID, ModelProvider.BLOCK_FOLDER + "/polished_granite_column_top"));
        registerColumnBlock(SMOOTH_STONE_COLUMN, new ResourceLocation(MODID, ModelProvider.BLOCK_FOLDER + "/stone_column_top"));
        registerColumnBlock(CHISELED_STONE_COLUMN);
        registerColumnBlock(SKELETON_CARVED_STONE_COLUMN, new ResourceLocation(MODID, ModelProvider.BLOCK_FOLDER + "/stone_column_top"));
        registerColumnBlock(SKELETON_CARVED_GRANITE_COLUMN, new ResourceLocation(MODID, ModelProvider.BLOCK_FOLDER + "/polished_granite_column_top"));
        registerCrossBlock(GLOWING_MUSHROOM);
    }

    private void handleSpecialClassBlocks() {
        SPECIAL_CLASS_BLOCKS.forEach(block -> {
            if(block.get() instanceof LinkedFenceBlock){
                this.registerLinkedFenceBlock(block.get());
            }
        });
    }

    private void registerBuildingBlockHelper(BuildingBlockHelper buildingBlockHelper){
        if(buildingBlockHelper.getColor() == null){
            registerBuildingBlock(buildingBlockHelper);
        }else{
            //registerColoredBlock(buildingBlockHelper);
            // Do Nothing for now
        }
    }

    private void registerSingleBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get());
    }

    private void registerPathBlock(RegistryObject<Block> blockRegistryObject) {
        PathBlock block = (PathBlock) blockRegistryObject.get();
        ResourceLocation sideTexture = block.getSideBlock();
        BlockModelBuilder models = models().singleTexture(getBlockRegistryName(block).getPath(), modLoc(BLOCK_FOLDER + "/abstract_path"), "top", blockLoc(getBlockRegistryName(block.getUnshoveled())))
                .texture("side", blockLoc(sideTexture));
        simpleBlock(block, models);
    }

    private void registerRottenBlock(RegistryObject<Block> blockRegistryObject) {
        Block block = blockRegistryObject.get();
        ResourceLocation planksTexture = new ResourceLocation("minecraft", BLOCK_FOLDER + "/" + getBlockRegistryName(block).getPath().replaceFirst("rotten_", ""));
        simpleBlock(block, models().singleTexture(getBlockRegistryName(block).getPath(), modLoc(BLOCK_FOLDER + "/rotten_abstract_planks"), "planks", planksTexture));
    }

    private void registerLinkedFenceBlock(Block block) {
        String suffix = "log";
        if(block.defaultBlockState().getMaterial().equals(Material.NETHER_WOOD)){
            suffix = "stem";
        }
        ResourceLocation logTexture = new ResourceLocation("minecraft", BLOCK_FOLDER + "/" + getBlockRegistryName(block).getPath().replaceFirst("linked_fence", suffix));
        ResourceLocation logTopTexture = new ResourceLocation("minecraft", BLOCK_FOLDER + "/" + getBlockRegistryName(block).getPath().replaceFirst("linked_fence", suffix + "_top"));
        BlockModelBuilder model = models().withExistingParent(getBlockRegistryName(block).getPath(), modLoc(BLOCK_FOLDER + "/abstract_linked_fence"))
                .texture("texture", logTexture)
                .texture("texture_top", logTopTexture);
        getVariantBuilder(block).forAllStatesExcept(state -> {
            int xRot = 0;
            int yRot = ((int) state.getValue(LinkedFenceBlock.FACING).toYRot());
            boolean isOpen = state.getValue(LinkedFenceBlock.OPEN);
            yRot %= 360;
            return ConfiguredModel.builder().modelFile(isOpen ? model : model)
                    .rotationX(xRot)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerColumnBlock(RegistryObject<Block> blockRegistryObject) {
        logBlock((RotatedPillarBlock) blockRegistryObject.get());
    }

    private void registerColumnBlock(RegistryObject<Block> blockRegistryObject, Block alternateEnd){
        axisBlock((RotatedPillarBlock) blockRegistryObject.get(), blockTexture(blockRegistryObject.get()), blockTexture(alternateEnd));
    }

    private void registerColumnBlock(RegistryObject<Block> blockRegistryObject, ResourceLocation alternateEnd){
        axisBlock((RotatedPillarBlock) blockRegistryObject.get(), blockTexture(blockRegistryObject.get()), alternateEnd);
    }

    private void registerCrossBlock(RegistryObject<Block> block) {
        String id = block.getId().getPath();
        ResourceLocation texture = modBlockLoc(id);
        BlockModelBuilder blockModel = models().withExistingParent(id, "block/cross").texture("cross", texture);
        getVariantBuilder(block.get())
                .partialState().setModels(new ConfiguredModel(blockModel));
    }

    private void registerBuildingBlock(BuildingBlockHelper blockHelper) {
        ResourceLocation texture = modBlockLoc(blockHelper.getBlockId());
        BlockModelBuilder model = models().withExistingParent(blockHelper.getId(), "cube_all").texture("particle", texture).texture("all", texture);
        simpleBlock(blockHelper.getBlock().get(), model);
        slabBlock((SlabBlock) blockHelper.getSlab().get(), getBlockRegistryName(blockHelper.getBlock().get()), texture);
        stairsBlock((StairBlock)  blockHelper.getStairs().get(), texture);
        wallBlock((WallBlock) blockHelper.getWall().get(), texture);
        models().wallInventory(getBlockRegistryName(blockHelper.getWall().get()).getPath() + "_inventory", texture);
        buttonBlock(blockHelper);
        pressurePlateBlock(blockHelper);
    }

    private void buttonBlock(BuildingBlockHelper blockHelper) {
        getVariantBuilder(blockHelper.getButton().get()).forAllStates(state -> {
            ModelFile buttonModel = buttonModel(blockHelper, state.getValue(ButtonBlock.POWERED));
            AttachFace face = state.getValue(FACE);
            Direction dir = state.getValue(FACING);
            int xrot = 0;
            if (face == AttachFace.CEILING) {
                dir = dir.getOpposite();
                xrot = 180;
            } else if (face == AttachFace.WALL) {
                xrot = 270;
            }
            return ConfiguredModel.builder().modelFile(buttonModel)
                    .rotationY((int) dir.toYRot())
                    .rotationX(xrot)
                    .uvLock(face == AttachFace.WALL).build();
        });
        buttonInventory(blockHelper);
    }

    private ModelFile buttonModel(BuildingBlockHelper blockId, boolean powered) {
        String suffix = "button" + (powered ? "_pressed" : "");
        return models().singleTexture(getBlockRegistryName(blockId.getButton().get()).getPath() + "_" + suffix, mcLoc("block/" + suffix), "texture", modLoc("block/" + blockId.getBlockId()));
    }

    private void buttonInventory(BuildingBlockHelper blockHelper) {
        String suffix = "button_inventory";
        models().singleTexture(blockHelper.getBlockId() + "_" + suffix, mcLoc("block/" + suffix), "texture", modLoc("block/" + blockHelper.getBlockId()));
    }

    private void pressurePlateBlock(BuildingBlockHelper blockHelper) {
        getVariantBuilder(blockHelper.getPressurePlate().get()).forAllStates(state -> ConfiguredModel.builder().modelFile(pressurePlateModel(blockHelper, state.getValue(PressurePlateBlock.POWERED))).build());
    }

    private ModelFile pressurePlateModel(BuildingBlockHelper blockHelper, boolean powered) {
        String modelName = "pressure_plate" + (powered ? "_down" : "");
        String parentName = modelName + (powered ? "" : "_up");
        return models().withExistingParent(blockHelper.getBlockId() + "_" + modelName, mcLoc("block/" + parentName))
                .texture("texture", modLoc("block/" + blockHelper.getBlockId()));
    }

    private void registerColoredBlock(BuildingBlockHelper blockHelper) {
        ResourceLocation texture = modBlockLoc(blockHelper.getBlockId());
        BlockModelBuilder model = models().withExistingParent(blockHelper.getId(), modBlockLoc("slime_block")).texture("particle", texture).texture("texture", texture);
        simpleBlock(blockHelper.getBlock().get(), model);
        modSlabBlock((SlabBlock) blockHelper.getSlab().get(), getBlockRegistryName(blockHelper.getBlock().get()), texture, "colored_slab");
        modStairsBlock((StairBlock)  blockHelper.getStairs().get(), texture, "colored_stairs");
        modWallBlock((WallBlock) blockHelper.getWall().get(), texture, "colored_wall");
        modButtonBlock(blockHelper, "colored_button", texture);
        modPressurePlateBlock(blockHelper, "colored_pressure_plate", texture);
    }

    private void modSlabBlock(SlabBlock block, ResourceLocation doubleslab, ResourceLocation texture, String parent) {
        String name = getBlockRegistryName(block).getPath();
        BlockModelBuilder bottomModel = models().withExistingParent(name, modBlockLoc(parent)).texture("texture", texture);
        BlockModelBuilder topModel = models().withExistingParent(name + "_top", modBlockLoc(parent + "_top")).texture("texture", texture);
        slabBlock(block, bottomModel, topModel, models().getExistingFile(doubleslab));
    }

    private void modStairsBlock(StairBlock block, ResourceLocation texture, String parent) {
        String name = getBlockRegistryName(block).getPath();
        BlockModelBuilder standardModel = models().withExistingParent(name, modBlockLoc(parent)).texture("texture", texture);
        BlockModelBuilder innerModel = models().withExistingParent(name + "_inner", modBlockLoc(parent + "_inner")).texture("texture", texture);
        BlockModelBuilder outerModel = models().withExistingParent(name + "_outer", modBlockLoc(parent + "_outer")).texture("texture", texture);
        stairsBlock(block, standardModel, innerModel, outerModel);
    }

    private void modWallBlock(WallBlock block, ResourceLocation texture, String parent) {
        String name = getBlockRegistryName(block).getPath();
        BlockModelBuilder post = models().withExistingParent(name, modBlockLoc(parent + "_post")).texture("wall", texture);
        BlockModelBuilder side = models().withExistingParent(name + "_inner", modBlockLoc(parent + "_side")).texture("wall", texture);
        BlockModelBuilder sideTall = models().withExistingParent(name + "_outer", modBlockLoc(parent + "_side_tall")).texture("wall", texture);
        wallBlock(block, post, side, sideTall);
        models().withExistingParent(name + "_inventory", modBlockLoc(parent + "_inventory")).texture("wall", texture);
    }

    private void modButtonBlock(BuildingBlockHelper blockHelper, String parent, ResourceLocation texture) {
        getVariantBuilder(blockHelper.getButton().get()).forAllStates(state -> {
            ModelFile buttonModel = modButtonModel(blockHelper, state.getValue(ButtonBlock.POWERED), parent, texture);
            AttachFace face = state.getValue(FACE);
            Direction dir = state.getValue(FACING);
            int xrot = 0;
            if (face == AttachFace.CEILING) {
                dir = dir.getOpposite();
                xrot = 180;
            } else if (face == AttachFace.WALL) {
                xrot = 270;
            }
            return ConfiguredModel.builder().modelFile(buttonModel)
                    .rotationY((int) dir.toYRot())
                    .rotationX(xrot)
                    .uvLock(face == AttachFace.WALL).build();
        });
        modButtonInventory(blockHelper, parent);
    }

    private ModelFile modButtonModel(BuildingBlockHelper blockHelper, boolean powered, String parent, ResourceLocation texture) {
        String suffix = powered ? "_pressed" : "";
        return models().singleTexture(getBlockRegistryName(blockHelper.getButton().get()).getPath() + suffix, modBlockLoc(parent + suffix), "texture", texture);
    }

    private void modButtonInventory(BuildingBlockHelper blockHelper, String parent) {
        String suffix = "_inventory";
        models().singleTexture(getBlockRegistryName(blockHelper.getButton().get()).getPath() + suffix, modBlockLoc(parent + suffix), "texture", modBlockLoc(blockHelper.getBlockId()));
    }

    private void modPressurePlateBlock(BuildingBlockHelper blockHelper, String parent, ResourceLocation texture) {
        getVariantBuilder(blockHelper.getPressurePlate().get()).forAllStates(state -> ConfiguredModel.builder().modelFile(modPressurePlateModel(blockHelper, state.getValue(PressurePlateBlock.POWERED), parent, texture)).build());
    }

    private ModelFile modPressurePlateModel(BuildingBlockHelper blockHelper, boolean powered, String parent, ResourceLocation texture) {
        String suffix = powered ? "_down" : "";
        String parentName = parent + suffix + (powered ? "" : "_up");
        return models().withExistingParent(getBlockRegistryName(blockHelper.getPressurePlate().get()).getPath() + suffix, modBlockLoc(parentName))
                .texture("texture", texture);
    }

    public ResourceLocation modBlockLoc(String name) {
        return modLoc(BLOCK_FOLDER + "/" + name);
    }
    public ResourceLocation blockLoc(ResourceLocation resourceLocation) {
        return new ResourceLocation(resourceLocation.getNamespace(), BLOCK_FOLDER + "/" + resourceLocation.getPath());
    }

    private ResourceLocation extend(ResourceLocation rl, String suffix) {
        return new ResourceLocation(rl.getNamespace(), rl.getPath() + suffix);
    }

    public ResourceLocation getBlockRegistryName(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }
}