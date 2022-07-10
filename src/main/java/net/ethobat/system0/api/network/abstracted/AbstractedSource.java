package net.ethobat.system0.api.network.abstracted;

import net.ethobat.system0.api.energy.EnergyTypeMap;

import java.util.UUID;

public abstract class AbstractedSource implements IAbstractedNetworkMember,INetworkTerminal {

    public final UUID uuid;

    public AbstractedSource(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUUID() {
        return this.uuid;
    }

    // Returns the highest amount of energy the source can possibly provide.
    public abstract EnergyTypeMap getPotentialEnergy();

    // Returns the amount of energy the source is currently providing to the network.
    public abstract EnergyTypeMap getChannels();

}
