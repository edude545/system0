package net.ethobat.system0.api.network.abstracted;

import net.ethobat.system0.api.energy.EnergyTypeMap;

import java.util.HashMap;

// HashMap of network jumps.
public class NetworkPath {

    public final Network NETWORK;

    public final HashMap<AbstractedConnection, EnergyTypeMap> JUMPS;

    public NetworkPath(Network network, HashMap<AbstractedConnection,EnergyTypeMap> jumps) {
        this.NETWORK = network;
        this.JUMPS = jumps;
    }

    public NetworkPath build(PartialNetworkPath path) {
        return new NetworkPath(path.NETWORK, path.JUMPS);
    }

}
