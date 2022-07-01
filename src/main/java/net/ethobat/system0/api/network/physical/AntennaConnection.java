package net.ethobat.system0.api.network.physical;

// One-way connection of transmitter to receiver.
// Two connected transceivers will each have a connection targeting each other.
public class AntennaConnection implements INetConnection {

    private final IReceiverAntenna receiver;
    private final long bandwidth;

    AntennaConnection(ITransmitterAntenna transmitter, IReceiverAntenna receiver) {
        this.receiver = receiver;
        this.bandwidth = transmitter.getEffectiveBandwidth(receiver);
    }

    @Override
    public INetConnector getTarget() {
        return this.receiver;
    }

    @Override
    public long getBandwidth() {
        return this.bandwidth;
    }

}
