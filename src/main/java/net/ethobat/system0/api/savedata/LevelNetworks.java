package net.ethobat.system0.api.savedata;

import net.ethobat.system0.api.network.Network;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.world.WorldProperties;
import net.minecraft.world.level.ServerWorldProperties;

import java.util.HashMap;
import java.util.UUID;

public class LevelNetworks implements INetworksComponent {

    public static LevelNetworks INSTANCE;

    public final WorldProperties worldProperties;

    private final HashMap<UUID, Network> networks;

    public LevelNetworks(WorldProperties worldProperties) {
        // CC-API Level components are instantiated ClientWorld.Properties on the client, and LevelProperties (impl. of ServerWorldProperties) on the server.
        // INSTANCE should be null on the client. On the server, INSTANCE should point to the server LevelNetworks component.
        if (worldProperties instanceof ServerWorldProperties) {
            INSTANCE = this;
        }
        this.worldProperties = worldProperties;
        this.networks = new HashMap<>();
    }

    @Override
    public HashMap<UUID, Network> getNetworkMap() {
        return this.networks;
    }

    @Override
    public void putNetwork(Network network) {
        this.networks.put(network.uuid, network);
    }

    @Override
    public Network getNetwork(UUID uuid) {
        return this.networks.getOrDefault(uuid, null);
    }

}
