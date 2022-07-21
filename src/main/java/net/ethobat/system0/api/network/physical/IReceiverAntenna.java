package net.ethobat.system0.api.network.physical;

public interface IReceiverAntenna extends IAntennaDeprecated {

    // Antenna info
    float getSensitivity();     // Float coefficient. Multiplied by transmitter's range to get effective range.
    long getBandwidthDown();

}
