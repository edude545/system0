package net.ethobat.system0.api.network.abstracted;

import net.ethobat.system0.api.energy.EnergyTypeMap;

import java.util.HashMap;
import java.util.UUID;

// Class for incomplete network paths, like during editing
public class IncompleteNetworkPath {

    public final Network NETWORK;

    public AbstractedSource SOURCE;
    public AbstractedDrain DRAIN;

    public final HashMap<AbstractedConnection, EnergyTypeMap> JUMPS;

    public IncompleteNetworkPath(Network network) {
        this.NETWORK = network;
        this.JUMPS = new HashMap<>();
    }

    public void addJump(UUID start, UUID end) {
        this.JUMPS.put(this.NETWORK.getConnection(start, end), new EnergyTypeMap());
    }

    public void setJumpChannels(AbstractedConnection connection, EnergyTypeMap channels) {
        this.JUMPS.put(connection, channels);
    }

}
