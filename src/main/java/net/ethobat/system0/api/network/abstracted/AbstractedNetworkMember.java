package net.ethobat.system0.api.network.abstracted;

import net.ethobat.system0.api.savedata.Networks;

import java.util.UUID;

public abstract class AbstractedNetworkMember implements IAbstractedNetworkMember {

    public static final String NBT_UUID_KEY = "uuid";
    public static final String NBT_NETWORK_KEY = "network";

    public Network network;
    public final UUID uuid;

    public AbstractedNetworkMember(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public Network getNetwork() {
        return this.network;
    }

    @Override
    public void subscribe(UUID uuid) {
        this.subscribe(Networks.getNetwork(uuid));
    }

}
