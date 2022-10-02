package net.ethobat.system0.api.network;

import net.ethobat.system0.api.nbt.NBTHandler;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

import java.util.UUID;

public class NWDrain extends AbstractNWMember {

    public NWDrain(UUID uuid, BlockPos pos) {
        super(uuid, pos);
    }
    public NWDrain(BlockPos pos) {
        super(UUID.randomUUID(), pos);
    }

    //
    // NBT
    //

    public static NWDrain fromNBT(NbtCompound nbt) {
        return new NWDrain(
                NBTHandler.getUUID(nbt, NBT_UUID_KEY),
                NBTHandler.getBlockPos(nbt, NBT_POS_KEY)
        );
    }

    public NbtCompound toNBT() {
        NbtCompound nbt = new NbtCompound();
        NBTHandler.genericPut(nbt, NBT_UUID_KEY, this.uuid);
        NBTHandler.genericPut(nbt, NBT_POS_KEY, this.pos);
        return nbt;
    }

}
