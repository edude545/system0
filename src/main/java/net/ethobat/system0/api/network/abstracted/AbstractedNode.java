package net.ethobat.system0.api.network.abstracted;

import net.ethobat.system0.api.nbt.NBTHandler;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

import java.util.UUID;

public abstract class AbstractedNode implements IAbstractedNetworkMember {

    public Network network;

    private final UUID uuid;
    private final BlockPos pos;
    private final long bandwidth;

    public UUID getUUID() {
        return this.uuid;
    }
    public BlockPos getPos() {
        return this.pos;
    }
    public long getBandwidth() {
        return this.bandwidth;
    }

    public AbstractedNode(UUID uuid, BlockPos pos, long bandwidth) {
        this.uuid = uuid;
        this.pos = pos;
        this.bandwidth = bandwidth;
    }

    @Override
    public Network getNetwork() {
        return this.network;
    }

    public NbtCompound toNBT() {
        NbtCompound nbt = new NbtCompound();
        NBTHandler.genericPut(nbt, "uuid", this.uuid);
        NBTHandler.genericPut(nbt, "pos", this.pos);
        NBTHandler.genericPut(nbt, "bandwidth", this.bandwidth);
        return nbt;
    }

}