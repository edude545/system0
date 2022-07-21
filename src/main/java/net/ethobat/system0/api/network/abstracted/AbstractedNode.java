package net.ethobat.system0.api.network.abstracted;

import net.ethobat.system0.api.nbt.NBTHandler;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

import java.util.UUID;

public class AbstractedNode implements IAbstractedNetworkMember {

    public Network network;

    private final UUID uuid;
    private final BlockPos pos;

    private final long bandwidth;
    private final int range;
    private final float sensitivity;

    public AbstractedNode(UUID uuid, BlockPos pos, long bandwidth, int range, float sensitivity) {
        this.uuid = uuid;
        this.pos = pos;
        this.bandwidth = bandwidth;
        this.range = range;
        this.sensitivity = sensitivity;
    }

    public boolean tryConnectionTo(AbstractedNode receiver) {
        return this.network.tryConnection(this, receiver);
    }
    public boolean tryConnectionFrom(AbstractedNode transmitter) {
        return this.network.tryConnection(transmitter, this);
    }

    public AbstractedConnection getConnectionTo(UUID receiver) {
        return this.getNetwork().getConnection(this.getUUID(), receiver);
    }
    public AbstractedConnection getConnectionFrom(UUID transmitter) {
        return this.getNetwork().getConnection(transmitter, this.getUUID());
    }

    @Override
    public Network getNetwork() {
        return this.network;
    }
    public UUID getUUID() {
        return this.uuid;
    }
    public BlockPos getPos() {
        return this.pos;
    }
    public long getBandwidth() {
        return this.bandwidth;
    }
    public int getRange() {
        return this.range;
    }
    public float getSensitivity() {
        return this.sensitivity;
    }

    // NBT stuff

    public static AbstractedNode fromNBT(NbtCompound nbt) {
        return new AbstractedNode(
                NBTHandler.getUUID(nbt, "uuid"),
                NBTHandler.getBlockPos(nbt, "pos"),
                NBTHandler.getLong(nbt, "bandwidth"),
                NBTHandler.getInt(nbt, "range"),
                NBTHandler.getFloat(nbt, "sensitivity")
        );
    }

    public NbtCompound toNBT() {
        NbtCompound nbt = new NbtCompound();
        NBTHandler.genericPut(nbt, "uuid", this.uuid);
        NBTHandler.genericPut(nbt, "pos", this.pos);
        NBTHandler.genericPut(nbt, "bandwidth", this.bandwidth);
        NBTHandler.genericPut(nbt, "range", this.range);
        NBTHandler.genericPut(nbt, "sensitivity", this.sensitivity);
        return nbt;
    }

}