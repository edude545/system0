package net.ethobat.system0.api.network.abstracted;

import net.ethobat.system0.api.energy.EnergyType;
import net.ethobat.system0.api.energy.EnergyTypeMap;

public class SimpleSource extends AbstractedSource {

    private final EnergyType type;
    private final long potentialEnergy;

    // Amount of occupied energy.
    private final long usedEnergy;

    public SimpleSource(EnergyType type, long potentialEnergy) {
        this.type = type;
        this.potentialEnergy = potentialEnergy;
        this.usedEnergy = 0;
    }

    @Override
    public EnergyTypeMap getPotentialEnergy() {
        return EnergyTypeMap.singleType(this.type, this.potentialEnergy);
    }

    @Override
    public EnergyTypeMap getChannels() {
        return EnergyTypeMap.singleType(this.type, this.usedEnergy);
    }

}
