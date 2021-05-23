package com.infamous.dungeons_world.datagen;

import com.infamous.dungeons_world.blocks.BuildingBlockHelper;
import net.minecraft.data.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import java.util.function.Consumer;

import static com.infamous.dungeons_world.DungeonsWorld.MODID;
import static com.infamous.dungeons_world.blocks.ModBlocks.BUILDING_BLOCK_HELPERS;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation modRecipe(String id){
        return new ResourceLocation(MODID, id);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        BUILDING_BLOCK_HELPERS.forEach(buildingBlockHelper -> buildingBlockRecipes(buildingBlockHelper, consumer));
    }

    private void buildingBlockRecipes(BuildingBlockHelper blockHelper, Consumer<IFinishedRecipe> consumer){
        ShapedRecipeBuilder.shaped(blockHelper.getSlab().get(), 6).define('#', blockHelper.getBlock().get())
                .pattern("###")
                .unlockedBy("has_" + blockHelper.getId(), has(blockHelper.getBlock().get())).save(consumer);
        ShapedRecipeBuilder.shaped(blockHelper.getStairs().get(), 4).define('#', blockHelper.getBlock().get())
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .unlockedBy("has_" +  blockHelper.getId(), has(blockHelper.getBlock().get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(blockHelper.getButton().get()).requires(blockHelper.getBlock().get())
                .unlockedBy("has_" +  blockHelper.getId(), has(blockHelper.getBlock().get())).save(consumer);
        ShapedRecipeBuilder.shaped(blockHelper.getPressurePlate().get())
                .define('#', blockHelper.getBlock().get())
                .pattern("##")
                .unlockedBy("has_" +  blockHelper.getId(), has(blockHelper.getBlock().get())).save(consumer);
        ShapedRecipeBuilder.shaped(blockHelper.getWall().get(), 6).define('#', blockHelper.getBlock().get())
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_" + blockHelper.getId(), has(blockHelper.getBlock().get())).save(consumer);
        stoneCutterRecipes(blockHelper, consumer);
    }

    private void stoneCutterRecipes(BuildingBlockHelper blockHelper, Consumer<IFinishedRecipe> consumer) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(blockHelper.getBlock().get()), blockHelper.getStairs().get()).unlocks("has_" + blockHelper.getId(), has(blockHelper.getBlock().get()))
                .save(consumer, modRecipe(blockHelper.getId() + "_stairs_from_" + blockHelper.getId() + "_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(blockHelper.getBlock().get()), blockHelper.getSlab().get(), 2).unlocks("has_" + blockHelper.getId(), has(blockHelper.getBlock().get()))
                .save(consumer, modRecipe(blockHelper.getId() + "_slab_from_" + blockHelper.getId() + "_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(blockHelper.getBlock().get()), blockHelper.getWall().get()).unlocks("has_" + blockHelper.getId(), has(blockHelper.getBlock().get()))
                .save(consumer, modRecipe(blockHelper.getId() + "_wall_from_" + blockHelper.getId() + "_stonecutting"));
    }

}