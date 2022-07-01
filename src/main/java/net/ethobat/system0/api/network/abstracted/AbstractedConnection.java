package net.ethobat.system0.api.network.abstracted;

import net.ethobat.system0.api.energy.EnergyTypeMap;

public class AbstractedConnection {

    public final AbstractedTransmitter transmitter;
    public final AbstractedReceiver receiver;

    // Highest potential bandwidth of the connection, assuming the nodes have no other connections.
    private final long potentialBandwidth;
    // Effective bandwidth of the connection. May be recalculated if the nodes establishe other connections.
    private long bandwidth;

    // Represents energy flowing through the connection each tick.
    public final EnergyTypeMap channels;

    public AbstractedConnection(AbstractedTransmitter transmitter, AbstractedReceiver receiver) {
        this.transmitter = transmitter;
        this.receiver = receiver;
        this.potentialBandwidth = Math.min(transmitter.getBandwidth(), receiver.getBandwidth());

        this.channels = new EnergyTypeMap();
    }

}
