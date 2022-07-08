package net.ethobat.system0.api.network.abstracted;

import net.ethobat.system0.api.nbt.NBTHandler;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

import java.util.UUID;

public class AbstractedReceiver extends AbstractedNode {

    private final float sensitivity;

    public float getSensitivity() {
        return this.sensitivity;
    }

    public AbstractedReceiver(UUID uuid, BlockPos pos, long bandwidth, float sensitivity) {
        super(uuid, pos, bandwidth);
        this.sensitivity = sensitivity;
    }

    public AbstractedConnection getConnection(UUID transmitter) {
        return this.getNetwork().getConnection(transmitter, this.getUUID());
    }

    public static AbstractedReceiver fromNBT(NbtCompound nbt) {
        return new AbstractedReceiver(
                NBTHandler.getUUID(nbt, "uuid"),
                NBTHandler.getBlockPos(nbt, "pos"),
                NBTHandler.getLong(nbt, "bandwidth"),
                NBTHandler.getFloat(nbt, "sensitivity")
        );
    }

    @Override
    public NbtCompound toNBT() {
        NbtCompound nbt = new NbtCompound();
        NBTHandler.genericPut(nbt, "sensitivity", this.sensitivity);
        return nbt;
    }

}