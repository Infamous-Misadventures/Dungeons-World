package com.infamous.dungeons_world.state;

import net.minecraft.util.StringRepresentable;
import net.minecraft.core.BlockPos;

import java.util.Arrays;

public enum BlockPart  implements StringRepresentable  {
    COMPLETE("complete", "complete", new BlockPos(-1, -1, -1)),
    PART_000("000", "part_000", new BlockPos(0, 0, 0)),
    PART_001("001", "part_001", new BlockPos(0, 0, 1)),
    PART_002("002", "part_002", new BlockPos(0, 0, 2)),
    PART_010("010", "part_010", new BlockPos(0, 1, 0)),
    PART_011("011", "part_011", new BlockPos(0, 1, 1)),
    PART_012("012", "part_012", new BlockPos(0, 1, 2)),
    PART_020("020", "part_020", new BlockPos(0, 2, 0)),
    PART_021("021", "part_021", new BlockPos(0, 2, 1)),
    PART_022("022", "part_022", new BlockPos(0, 2, 2)),
    PART_100("100", "part_100", new BlockPos(1, 0, 0)),
    PART_101("101", "part_101", new BlockPos(1, 0, 1)),
    PART_102("102", "part_102", new BlockPos(1, 0, 2)),
    PART_110("110", "part_110", new BlockPos(1, 1, 0)),
    PART_111("111", "part_111", new BlockPos(1, 1, 1)),
    PART_112("112", "part_112", new BlockPos(1, 1, 2)),
    PART_120("120", "part_120", new BlockPos(1, 2, 0)),
    PART_121("121", "part_121", new BlockPos(1, 2, 1)),
    PART_122("122", "part_122", new BlockPos(1, 2, 2)),
    PART_200("200", "part_200", new BlockPos(2, 0, 0)),
    PART_201("201", "part_201", new BlockPos(2, 0, 1)),
    PART_202("202", "part_202", new BlockPos(2, 0, 2)),
    PART_210("210", "part_210", new BlockPos(2, 1, 0)),
    PART_211("211", "part_211", new BlockPos(2, 1, 1)),
    PART_212("212", "part_212", new BlockPos(2, 1, 2)),
    PART_220("220", "part_220", new BlockPos(2, 2, 0)),
    PART_221("221", "part_221", new BlockPos(2, 2, 1)),
    PART_222("222", "part_222", new BlockPos(2, 2, 2));

    private final String partNumber;
    private final String name;
    private BlockPos blockPos;

    BlockPart(String partNumber, String name, BlockPos blockPos) {
        this.partNumber = partNumber;
        this.name = name;
        this.blockPos = blockPos;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public static BlockPart getByPartNumber(String partNumber) {
        return Arrays.stream(BlockPart.values()).filter(blockPart -> blockPart.getPartNumber() == partNumber).findFirst().orElseThrow(() -> new RuntimeException("Attempted to get nonExistant part:"+partNumber));
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
