package net.ethobat.system0.api.savedata;

import net.ethobat.system0.api.network.abstracted.Network;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.PersistentState;

import java.util.HashMap;
import java.util.UUID;

// ONE OF THESE EXISTS per world (dimension? - not sure how PersistentState works yet) and takes the form of a group of network connector metadata.
// When a network connector is placed, it tries to connect to other connectors in range, checking through this list.
// This means potential connectees don't have to be loaded.
// The abstract network also tracks all potential and current connections through the network, from sources to drains.
public class WorldAbstractedNetsState extends PersistentState {

    public HashMap<UUID, Network> networks;

    public WorldAbstractedNetsState(HashMap<UUID, Network> networks) {
        this.networks = networks;
    }

    public WorldAbstractedNetsState() {
        this.networks = new HashMap<>();
    }

    public static WorldAbstractedNetsState fromNBT(NbtCompound nbt) {
        WorldAbstractedNetsState ret = new WorldAbstractedNetsState();
        for (String uuid : nbt.getKeys()) {
            ret.networks.put(UUID.fromString(uuid), Network.fromNBT(nbt.getCompound(uuid)));
        }
        return ret;
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        for (UUID uuid : this.networks.keySet()) {
            nbt.put(uuid.toString(), this.networks.get(uuid).toNBT());
        }
        return nbt;
    }

    public void createNetwork(UUID uuid) {
        this.networks.put(uuid, new Network());
    }

}
