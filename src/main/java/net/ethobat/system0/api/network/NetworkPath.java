package net.ethobat.system0.api.network;

import net.ethobat.system0.api.network.physical.INetConnection;

import java.util.ArrayList;
import java.util.Collections;

public class NetworkPath {

    public final INetConnection[] CONNECTIONS;

    NetworkPath(INetConnection[] connections) {
        this.CONNECTIONS = connections;
    }

    public INetConnection[] getConnections() {
        return this.CONNECTIONS;
    }

    public int getLength() {
        return this.getConnections().length;
    }

    public long getBandwidthThrough() {
        ArrayList<Long> bandwidths = new ArrayList<>();
        for (INetConnection cxn : this.getConnections()) {
            bandwidths.add(cxn.getBandwidth());
        }
        return Collections.min(bandwidths);
    }

    public static class Builder {

        private final ArrayList<INetConnection> CONNECTIONS;

        public Builder() {
            this.CONNECTIONS = new ArrayList<>();
        }

        public INetConnection add(INetConnection connection) {
            this.CONNECTIONS.add(connection);
            return connection;
        }

        public NetworkPath build() {
            return new NetworkPath((INetConnection[]) this.CONNECTIONS.toArray());
        }

    }

}
