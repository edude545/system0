package net.ethobat.system0.api.network;

public interface IAntenna extends INetConnector {

    // Transmission
    long occupyBandwidth(long amt);

}
