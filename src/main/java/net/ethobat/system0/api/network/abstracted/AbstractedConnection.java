package net.ethobat.system0.api.network.abstracted;

import net.ethobat.system0.api.energy.EnergyTypeMap;

import java.util.HashMap;

public class AbstractedConnection {

    public final Network NETWORK;

    public final AbstractedTransmitter TRANSMITTER;
    public final AbstractedReceiver RECEIVER;

    // Bandwidth of the connection.
    private final long potentialBandwidth;

    // Represents energy flowing through the connection each tick.
    public EnergyTypeMap channels;

    // Maps NetworkPaths to the bandwidth they occupy on this connection.
    public HashMap<NetworkPath,EnergyTypeMap> separatedChannels;

    public AbstractedConnection(AbstractedTransmitter transmitter, AbstractedReceiver receiver) {
        this.NETWORK = transmitter.getNetwork();
        this.TRANSMITTER = transmitter;
        this.RECEIVER = receiver;
        this.potentialBandwidth = Math.min(transmitter.getBandwidth(), receiver.getBandwidth());

        this.channels = new EnergyTypeMap();
        this.separatedChannels = new HashMap<>();
    }

    public Network getNetwork() {
        return this.NETWORK;
    }

    public long getPotentialBandwidth() {
        return this.potentialBandwidth;
    }

}
