package net.ethobat.system0.api.network.abstracted;

import net.ethobat.system0.api.nbt.NBTHandler;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

import java.util.UUID;

public class AbstractedTransmitter extends AbstractedNode {

    private final int range;
    private final float penetration;

    public int getRange() {
        return this.range;
    }
    public float getPenetration() {
        return this.penetration;
    }

    public AbstractedTransmitter(UUID uuid, BlockPos pos, long bandwidth, int range, float penetration) {
        super(uuid, pos, bandwidth);
        this.range = range;
        this.penetration = penetration;
    }

    public static AbstractedTransmitter fromNBT(NbtCompound nbt) {
        return new AbstractedTransmitter(
                NBTHandler.getUUID(nbt, "uuid"),
                NBTHandler.getBlockPos(nbt, "pos"),
                NBTHandler.getLong(nbt, "bandwidth"),
                NBTHandler.getInt(nbt, "range"),
                NBTHandler.getFloat(nbt, "penetration")
        );
    }

    @Override
    public NbtCompound toNBT() {
        NbtCompound nbt = super.toNBT();
        NBTHandler.genericPut(nbt, "range", this.range);
        NBTHandler.genericPut(nbt, "penetration", this.penetration);
        return nbt;
    }

}