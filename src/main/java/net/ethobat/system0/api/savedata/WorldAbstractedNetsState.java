package net.ethobat.system0.api.savedata;

import net.ethobat.system0.api.network.abstracted.AbstractedNetwork;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;

import java.util.HashMap;
import java.util.UUID;

// ONE OF THESE EXISTS per world and takes the form of a group of network connector metadata.
// When a network connector is placed, it tries to connect to other connectors in range, checking through this list.
// This means potential connectees don't have to be loaded
public class WorldAbstractedNetsState extends PersistentState {

    public HashMap<UUID, AbstractedNetwork> networks;

    public WorldAbstractedNetsState(HashMap<UUID, AbstractedNetwork> networks) {
        this.networks = networks;
    }

    public WorldAbstractedNetsState() {
        this.networks = new HashMap<>();
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {

    }

}
