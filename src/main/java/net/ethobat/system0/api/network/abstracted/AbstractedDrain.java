package net.ethobat.system0.api.network.abstracted;

import java.util.UUID;

public abstract class AbstractedDrain implements IAbstractedNetworkMember,INetworkTerminal {

    public final UUID uuid;

    public AbstractedDrain(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUUID() {
        return this.uuid;
    }

}
