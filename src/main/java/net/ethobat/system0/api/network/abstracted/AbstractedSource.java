package net.ethobat.system0.api.network.abstracted;

import net.ethobat.system0.api.energy.EnergyTypeMap;

public abstract class AbstractedSource {

    // Returns the highest amount of energy the source can possibly provide.
    public abstract EnergyTypeMap getPotentialEnergy();

    // Returns the amount of energy the source is currently providing to the network.
    public abstract EnergyTypeMap getChannels();

}
