package com.infamous.dungeons_world.items;

import com.infamous.dungeons_world.DungeonsWorld;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DungeonsWorld.MODID);
    public static final List<String> ITEM_IDS = new ArrayList<>();

    public static RegistryObject<BlockItem> registerBlockItem(String id, RegistryObject<Block> block, Function<Supplier<Block>, BlockItem> itemCreatorFunction){
        return ITEMS.register(id,  () -> itemCreatorFunction.apply(block));
    }
}
