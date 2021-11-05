package com.infamous.dungeons_world.state;

import com.google.common.collect.Lists;
import net.minecraft.state.EnumProperty;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BlockPartProperty extends EnumProperty<BlockPart> {
    protected BlockPartProperty(String propertyName, Collection<BlockPart> blockParts) {
        super(propertyName, BlockPart.class, blockParts);
    }

    public static BlockPartProperty create(String propertyName, Predicate<BlockPart> blockPartPredicate) {
        return create(propertyName, Arrays.stream(BlockPart.values()).filter(blockPartPredicate).collect(Collectors.toList()));
    }

    public static BlockPartProperty create(String propertyName, BlockPart... blockParts) {
        return create(propertyName, Lists.newArrayList(blockParts));
    }

    public static BlockPartProperty create(String propertyName, Collection<BlockPart> blockParts) {
        return new BlockPartProperty(propertyName, blockParts);
    }
}