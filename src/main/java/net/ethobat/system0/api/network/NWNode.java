package net.ethobat.system0.api.network;

import net.ethobat.system0.api.nbt.NBTHandler;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

import java.util.UUID;

public class NWNode extends AbstractNWMember {

    public static final String NBT_POS_KEY = "pos";
    public static final String NBT_BANDWIDTH_KEY = "bandwidth";
    public static final String NBT_RANGE_KEY = "range";
    public static final String NBT_SENSITIVITY_KEY = "sensitivity";

    private final long bandwidth;
    private final int range;
    private final float sensitivity;

    public NWNode(UUID uuid, BlockPos pos, long bandwidth, int range, float sensitivity) {
        super(uuid,pos);
        this.bandwidth = bandwidth;
        this.range = range;
        this.sensitivity = sensitivity;
    }

    public NWNode(BlockPos pos, long bandwidth, int range, float sensitivity) {
        this(UUID.randomUUID(), pos, bandwidth, range, sensitivity);
    }

    public boolean tryConnectionTo(NWNode receiver) {
        return this.network.tryConnection(this, receiver);
    }
    public boolean tryConnectionFrom(NWNode transmitter) {
        return this.network.tryConnection(transmitter, this);
    }

    public NWConnection getConnectionTo(UUID receiver) {
        return this.getNetwork().getConnection(this.getUUID(), receiver);
    }
    public NWConnection getConnectionFrom(UUID transmitter) {
        return this.getNetwork().getConnection(transmitter, this.getUUID());
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

    public static NWNode fromNBT(NbtCompound nbt) {
        return new NWNode(
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