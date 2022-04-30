package com.infamous.dungeons_world.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

import java.util.Iterator;

public class BlockHelper {

    public static final BlockState getStateWithProperties(Block block, BlockState state) {
        BlockState blockState = block.defaultBlockState();
        Iterator var3 = state.getBlock().getStateDefinition().getProperties().iterator();

        while(var3.hasNext()) {
            Property<?> property = (Property)var3.next();
            if (blockState.getProperties().contains(property)) {
                blockState = copyProperty(state, blockState, property);
            }
        }

        return blockState;
    }

    private static <T extends Comparable<T>> BlockState copyProperty(BlockState source, BlockState target, Property<T> property) {
        return target.setValue(property, source.getValue(property));
    }

}
