package net.ethobat.system0.api.network.abstracted;

import net.ethobat.system0.api.nbt.NBTHandler;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

import java.util.UUID;

public class AbstractedNode extends AbstractedNetworkMember {

    public static final String NBT_POS_KEY = "pos";
    public static final String NBT_BANDWIDTH_KEY = "bandwidth";
    public static final String NBT_RANGE_KEY = "range";
    public static final String NBT_SENSITIVITY_KEY = "sensitivity";

    private final BlockPos pos;
    private final long bandwidth;
    private final int range;
    private final float sensitivity;

    public AbstractedNode(UUID uuid, BlockPos pos, long bandwidth, int range, float sensitivity) {
        super(uuid);
        this.pos = pos;
        this.bandwidth = bandwidth;
        this.range = range;
        this.sensitivity = sensitivity;
    }

    @Override
    public void subscribe(Network network) {
        this.network = network;
    }

    public AbstractedNode silentSubscribe(Network network) {
        this.network = network;
        return this;
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

    public static AbstractedNode fromNBT(NbtCompound nbt) {
        return new AbstractedNode(
                NBTHandler.getUUID(nbt, NBT_UUID_KEY),
                NBTHandler.getBlockPos(nbt, NBT_POS_KEY),
                NBTHandler.getLong(nbt, NBT_BANDWIDTH_KEY),
                NBTHandler.getInt(nbt, NBT_RANGE_KEY),
                NBTHandler.getFloat(nbt, NBT_SENSITIVITY_KEY)
        );
    }

    public NbtCompound toNBT() {
        NbtCompound nbt = new NbtCompound();
        NBTHandler.genericPut(nbt, NBT_UUID_KEY, this.uuid);
        NBTHandler.genericPut(nbt, NBT_POS_KEY, this.pos);
        NBTHandler.genericPut(nbt, NBT_BANDWIDTH_KEY, this.bandwidth);
        NBTHandler.genericPut(nbt, NBT_RANGE_KEY, this.range);
        NBTHandler.genericPut(nbt, NBT_SENSITIVITY_KEY, this.sensitivity);
        return nbt;
    }

}