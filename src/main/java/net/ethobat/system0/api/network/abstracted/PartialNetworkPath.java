package net.ethobat.system0.api.network.abstracted;

import net.ethobat.system0.api.energy.EnergyTypeMap;

import java.util.HashMap;
import java.util.UUID;

// Class for incomplete network paths, like during editing
public class PartialNetworkPath {

    public final Network NETWORK;

    public final HashMap<AbstractedConnection, EnergyTypeMap> JUMPS;

    public PartialNetworkPath(Network network) {
        this.NETWORK = network;
        this.JUMPS = new HashMap<>();
    }

    public void addJump(UUID start, UUID end) {
        this.JUMPS.put(this.NETWORK.getConnection(start, end), 0L);
    }

    public void setJumpBandwidth(AbstractedConnection connection, long bandwidth) {
        this.JUMPS.put(connection, bandwidth);
    }

}
