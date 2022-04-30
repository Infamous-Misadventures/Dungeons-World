package com.infamous.dungeons_world.items;

import com.infamous.dungeons_world.DungeonsWorld;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DungeonsWorld.MODID);
    public static final List<String> ITEM_IDS = new ArrayList<>();
    private static RegistryObject<Item> CREEPMOSS = registerItem("creepmoss", () -> new Item(new Item.Properties().tab(DungeonsWorld.TAB)));


    private static RegistryObject<Item> registerItem(String itemId, Supplier<Item> itemSupplier){
        ITEM_IDS.add(itemId);
        return ITEMS.register(itemId, itemSupplier);
    }


    public static RegistryObject<BlockItem> registerBlockItem(String id, RegistryObject<Block> block, Function<Supplier<Block>, BlockItem> itemCreatorFunction){
        return ITEMS.register(id,  () -> itemCreatorFunction.apply(block));
    }
}
