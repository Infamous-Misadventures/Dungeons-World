package com.infamous.dungeons_world.state;

import net.minecraft.util.IStringSerializable;

import java.util.Arrays;

public enum BlockPart  implements IStringSerializable  {
    COMPLETE(-1, "complete"),
    PART_0(0, "part_0"),
    PART_1(1, "part_1"),
    PART_2(2, "part_2"),
    PART_3(3, "part_3"),
    PART_4(4, "part_4"),
    PART_5(5, "part_5"),
    PART_6(6, "part_6"),
    PART_7(7, "part_7"),
    PART_8(8, "part_8"),
    PART_9(9, "part_9"),
    PART_10(10, "part_10"),
    PART_11(11, "part_11"),
    PART_12(12, "part_12"),
    PART_13(13, "part_13"),
    PART_14(14, "part_14"),
    PART_15(15, "part_15"),
    PART_16(16, "part_16"),
    PART_17(17, "part_17"),
    PART_18(18, "part_18"),
    PART_19(19, "part_19"),
    PART_20(20, "part_20"),
    PART_21(21, "part_21"),
    PART_22(22, "part_22"),
    PART_23(23, "part_23"),
    PART_24(24, "part_24"),
    PART_25(25, "part_25"),
    PART_26(26, "part_26");

    private final int partNumber;
    private final String name;

    BlockPart(int partNumber, String name) {
        this.partNumber = partNumber;
        this.name = name;
    }

    public int getPartNumber() {
        return partNumber;
    }

    public static BlockPart getByPartNumber(int partNumber) {
        return Arrays.stream(BlockPart.values()).filter(blockPart -> blockPart.getPartNumber() == partNumber).findFirst().orElseThrow(() -> new RuntimeException("Attempted to get BlockPart bigger than available."));
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
