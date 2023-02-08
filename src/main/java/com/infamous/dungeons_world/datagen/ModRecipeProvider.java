package com.infamous.dungeons_world.datagen;

import com.infamous.dungeons_world.blocks.BuildingBlockHelper;
import net.minecraft.data.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;

import static com.infamous.dungeons_world.DungeonsWorld.MODID;
import static com.infamous.dungeons_world.init.BlocksInit.BUILDING_BLOCK_HELPERS;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ResourceLocation modRecipe(String id){
        return new ResourceLocation(MODID, id);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        BUILDING_BLOCK_HELPERS.forEach(buildingBlockHelper -> buildingBlockRecipes(buildingBlockHelper, consumer));
    }

    private void buildingBlockRecipes(BuildingBlockHelper blockHelper, Consumer<FinishedRecipe> consumer){
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

    private void stoneCutterRecipes(BuildingBlockHelper blockHelper, Consumer<FinishedRecipe> consumer) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(blockHelper.getBlock().get()), blockHelper.getStairs().get()).unlockedBy("has_" + blockHelper.getId(), has(blockHelper.getBlock().get()))
                .save(consumer, modRecipe(blockHelper.getId() + "_stairs_from_" + blockHelper.getId() + "_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(blockHelper.getBlock().get()), blockHelper.getSlab().get(), 2).unlockedBy("has_" + blockHelper.getId(), has(blockHelper.getBlock().get()))
                .save(consumer, modRecipe(blockHelper.getId() + "_slab_from_" + blockHelper.getId() + "_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(blockHelper.getBlock().get()), blockHelper.getWall().get()).unlockedBy("has_" + blockHelper.getId(), has(blockHelper.getBlock().get()))
                .save(consumer, modRecipe(blockHelper.getId() + "_wall_from_" + blockHelper.getId() + "_stonecutting"));
    }

}