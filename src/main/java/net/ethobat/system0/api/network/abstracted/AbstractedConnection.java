package net.ethobat.system0.api.network.abstracted;

import net.ethobat.system0.api.energy.EnergyTypeMap;
import net.ethobat.system0.api.nbt.NBTHandler;
import net.minecraft.nbt.NbtCompound;

import java.util.HashMap;

public class AbstractedConnection {

    public static final String NBT_TRANSMITTER_KEY = "transmitter";
    public static final String NBT_RECEIVER_KEY = "receiver";

    public static final String NBT_CHANNELS_KEY = "channels";

    public final Network NETWORK;

    public final AbstractedNode TRANSMITTER;
    public final AbstractedNode RECEIVER;

    // Bandwidth of the connection.
    private final long BANDWIDTH;

    // Represents energy flowing through the connection each tick.
    public EnergyTypeMap channels;

    public AbstractedConnection(Network network, AbstractedNode transmitter, AbstractedNode receiver) {
        this.NETWORK = network;
        this.TRANSMITTER = transmitter;
        this.RECEIVER = receiver;
        this.BANDWIDTH = Math.min(transmitter.getBandwidth(), receiver.getBandwidth());

        this.channels = new EnergyTypeMap();
    }

    public Network getNetwork() {
        return this.NETWORK;
    }

    public long getBandwidth() {
        return this.BANDWIDTH;
    }

    //
    // NBT
    //

    public NbtCompound toNBT() {
        NbtCompound nbt = new NbtCompound();
        NBTHandler.genericPut(nbt, NBT_CHANNELS_KEY, this.channels);
        NBTHandler.genericPut(nbt, "transmitter", this.TRANSMITTER.getUUID());
        NBTHandler.genericPut(nbt, "receiver", this.RECEIVER.getUUID());
        return nbt;
    }

    public static AbstractedConnection fromNBT(Network network, NbtCompound nbt) {
        AbstractedConnection ret = new AbstractedConnection(
                network,
                network.getNode(nbt.getUuid(NBT_TRANSMITTER_KEY)),
                network.getNode(nbt.getUuid(NBT_RECEIVER_KEY))
        );
        ret.channels = NBTHandler.getEnergyTypeMap(nbt, NBT_CHANNELS_KEY);
        return ret;
    }

}
