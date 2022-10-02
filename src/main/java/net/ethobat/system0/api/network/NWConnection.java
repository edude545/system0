package net.ethobat.system0.api.network;

import net.ethobat.system0.api.energy.EnergyTypeMap;
import net.ethobat.system0.api.nbt.NBTHandler;
import net.minecraft.nbt.NbtCompound;

import java.util.UUID;

public class NWConnection {

    public static final String NBT_TRANSMITTER_KEY = "transmitter";
    public static final String NBT_RECEIVER_KEY = "receiver";

    public static final String NBT_CHANNELS_KEY = "channels";

    public final Network NETWORK;

    public final NWNode TRANSMITTER;
    public final NWNode RECEIVER;

    // Bandwidth of the connection.
    private final long BANDWIDTH;

    // Represents energy flowing through the connection each tick.
    public EnergyTypeMap channels;

    public NWConnection(Network network, NWNode transmitter, NWNode receiver) {
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

    public static NWConnection fromNBT(Network network, NbtCompound nbt) {
        NWNode transmitter = network.getNode(NBTHandler.getUUID(nbt, NBT_TRANSMITTER_KEY));
        NWNode receiver = network.getNode(NBTHandler.getUUID(nbt, NBT_RECEIVER_KEY));
        if (transmitter != null && receiver != null) { // Connection data may be corrupted and refer to nodes that no longer exist. If so, return null.
            NWConnection ret = new NWConnection(
                    network,
                    transmitter,
                    receiver
            );
            ret.channels = NBTHandler.getEnergyTypeMap(nbt, NBT_CHANNELS_KEY);
            return ret;
        }
        return null;
    }

}
