package com.infamous.dungeons_world.tileentity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;

public enum DungeonsChestType {
    COMMON,
    FANCY,
    OBSIDIAN;

    public static final Codec<DungeonsChestType> CODEC = Codec.STRING.flatComapMap(s -> DungeonsChestType.byName(s, null), d -> DataResult.success(d.name()));

    public static DungeonsChestType byName(String input, DungeonsChestType defaultDungeonsChestType) {
        for(DungeonsChestType enumEntry : values()) {
            if (enumEntry.name().equals(input.toUpperCase())) {
                return enumEntry;
            }
        }

        return defaultDungeonsChestType;
    }
}
