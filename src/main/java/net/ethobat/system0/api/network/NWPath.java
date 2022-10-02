package net.ethobat.system0.api.network;

import net.ethobat.system0.api.energy.EnergyTypeMap;
import net.ethobat.system0.api.nbt.NBTHandler;
import net.minecraft.nbt.NbtCompound;

import java.util.HashMap;
import java.util.UUID;

// HashMap of network jumps.
public class NWPath {

    public final Network NETWORK;

    public final NWSource SOURCE;
    public final NWDrain DRAIN;

    public final HashMap<NWConnection, EnergyTypeMap> JUMPS;

    public NWPath(Network network, UUID source, UUID drain, HashMap<NWConnection,EnergyTypeMap> jumps) {
        this.NETWORK = network;
        this.SOURCE = this.NETWORK.getSource(source);
        this.DRAIN = this.NETWORK.getDrain(drain);
        this.JUMPS = jumps;
    }

//    public NWPath build(IncompleteNetworkPath path) {
//        return new NWPath(path.NETWORK, path.SOURCE.getUUID(), path.DRAIN.getUUID(), path.JUMPS);
//    }

    // NBT stuff

    /*
        {
            "source" : UUID,
            "drain" : UUID,
            "jumps" : {
                TRN-UUID-01 : {
                    RCV-UUID-01 : ENERGY-TYPE-MAP,
                    RCV-UUID-02 : ENERGY-TYPE-MAP,
                    ...
                },
                TRN-UUID-02 : { ... },
                ...
            }
        }
     */

    public NbtCompound toNBT() {
        NbtCompound ret = new NbtCompound();
        NBTHandler.genericPut(ret, "source", this.SOURCE.getUUID());
        NBTHandler.genericPut(ret, "drain", this.DRAIN.getUUID());

        NbtCompound jumpsNBT = new NbtCompound();
        for (NWConnection cxn : this.JUMPS.keySet()) {

            String transmitterKey = cxn.TRANSMITTER.getUUID().toString();
            String receiverKey = cxn.RECEIVER.getUUID().toString();

            if (!jumpsNBT.getKeys().contains(transmitterKey)) { // If there is no sub-key in "jumps" for this transmitter...
                NBTHandler.genericPut(jumpsNBT, transmitterKey, new NbtCompound()); // ... create an empty one
            }

            NBTHandler.genericPut(jumpsNBT.getCompound(transmitterKey), receiverKey, this.JUMPS.get(cxn));

        }
        NBTHandler.genericPut(ret, "jumps", jumpsNBT);

        return ret;
    }

    public static NWPath fromNBT(Network network, NbtCompound nbt) {
        HashMap<NWConnection, EnergyTypeMap> jumps = new HashMap<>();
        NbtCompound jumpsNBT = nbt.getCompound("jumps");
        for (String transmitterKey : jumpsNBT.getKeys()) {
            NbtCompound cxnsNBT = jumpsNBT.getCompound(transmitterKey);
            for (String receiverKey : cxnsNBT.getKeys()) {
                jumps.put(new NWConnection(
                        network,
                        network.getNode(UUID.fromString(transmitterKey)),
                        network.getNode(UUID.fromString(receiverKey))
                ), NBTHandler.getEnergyTypeMap(cxnsNBT, receiverKey));
            }
        }
        return new NWPath(
                network,
                NBTHandler.getUUID(nbt, "source"),
                NBTHandler.getUUID(nbt, "drain"),
                jumps
        );
    }

}
