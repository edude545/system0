package net.ethobat.system0.api.network;

import net.ethobat.system0.api.network.abstracted.IAbstractedNetworkMember;
import net.ethobat.system0.api.network.abstracted.Network;
import net.ethobat.system0.api.util.MessageHandler;

import java.util.HashMap;
import java.util.UUID;

public final class WorldNetworks {

    private static HashMap<UUID, Network> networks;

    public static void registerNetwork(Network network) {
        networks.put(network.getUUID(), network);
    }

    public static Network getNetwork(UUID uuid) {
        return networks.get(uuid);
    }

    // to be used with NetworkPDA#getPlayersDefaultNetwork
    public static void safeRegister(Network network, IAbstractedNetworkMember networkMember) {
        if (network != null) {
            network.register(networkMember);
            System.out.println("Registered " + networkMember.getUUID() + " to network " + network.getUUID());
        }
    }

}
