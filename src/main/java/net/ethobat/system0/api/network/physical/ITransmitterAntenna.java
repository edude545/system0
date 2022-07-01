package net.ethobat.system0.api.network.physical;

import net.ethobat.system0.api.energy.EnergyType;

public interface ITransmitterAntenna extends IAntenna {

    // Antenna info
    int getRange();             // Distance in blocks. Multiplied by receiver's sensitivity to get effective range.
    float getPenetration();
    long getBandwidthUp();

    // Networking
    void addConnection(IReceiverAntenna receiver);
    boolean attemptConnection(IReceiverAntenna receiver);

    // Telemetry
    boolean canConnectTo(IReceiverAntenna receiver);
    long getEffectiveBandwidth(IReceiverAntenna receiver); // should assume the given receiver is within range
    long getEffectiveBandwidth(IReceiverAntenna receiver, double coeff); // should assume the given receiver is within range
    int getEffectiveRange(IReceiverAntenna receiver);
    boolean canReach(IReceiverAntenna receiver);
    double getAttenuationCoefficient(IReceiverAntenna receiver);

    // Transmission
    long transmitEnergy(long amt, EnergyType type);

}
