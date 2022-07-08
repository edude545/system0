package net.ethobat.system0.api.network.abstracted;

import net.ethobat.system0.api.energy.EnergyTypeMap;

import java.util.HashMap;

public class AbstractedConnection implements IAbstractedNetworkMember {

    public final Network NETWORK;

    public final AbstractedTransmitter TRANSMITTER;
    public final AbstractedReceiver RECEIVER;

    // Bandwidth of the connection.
    private long potentialBandwidth;

    // Represents energy flowing through the connection each tick.
    public EnergyTypeMap channels;

    public HashMap<NetworkPath,EnergyTypeMap> separatedChannels;

    public AbstractedConnection(AbstractedTransmitter transmitter, AbstractedReceiver receiver) {
        this.NETWORK = transmitter.getNetwork();
        this.TRANSMITTER = transmitter;
        this.RECEIVER = receiver;
        this.potentialBandwidth = Math.min(transmitter.getBandwidth(), receiver.getBandwidth());

        this.channels = new EnergyTypeMap();
    }

    @Override
    public Network getNetwork() {
        return this.NETWORK;
    }

    public long getPotentialBandwidth() {
        return this.potentialBandwidth;
    }

}
