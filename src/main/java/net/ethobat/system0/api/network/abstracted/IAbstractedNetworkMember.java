package net.ethobat.system0.api.network.abstracted;

import java.util.UUID;

public interface IAbstractedNetworkMember {

    Network getNetwork();

    UUID getUUID();

    void subscribe(Network network);
    void subscribe(UUID uuid);

}
