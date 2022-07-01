package net.ethobat.system0.api.nbt;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

public class BlockPosNBT {

    public static NbtCompound toNBT(BlockPos pos) {
        NbtCompound nbt = new NbtCompound();
        nbt.putInt("x", pos.getX());
        nbt.putInt("y", pos.getY());
        nbt.putInt("z", pos.getZ());
        return nbt;
    }

}
