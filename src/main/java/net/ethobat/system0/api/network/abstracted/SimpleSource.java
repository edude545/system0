package net.ethobat.system0.api.network.abstracted;

import net.ethobat.system0.api.energy.EnergyType;
import net.ethobat.system0.api.energy.EnergyTypeMap;

public class SimpleSource extends AbstractedSource {

    private final EnergyType type;
    private final long amt;

    private final EnergyTypeMap channels;

    public SimpleSource(EnergyType type, long amt) {
        this.type = type;
        this.amt = amt;
        this.channels = EnergyTypeMap.singleType(this.type, 0);
    }

    public EnergyTypeMap getPotentialEnergy() {
        return EnergyTypeMap.singleType(this.type, this.amt);
    }

    public EnergyTypeMap getChannels() {
        return this.channels;
    }

}
