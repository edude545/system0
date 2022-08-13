package net.ethobat.system0.api.network;

import java.util.UUID;

public class NetworkID {

    public UUID uuid;

    public NetworkID() {
        this(UUID.randomUUID());
    }

    public NetworkID(UUID uuid) {
        this.uuid = uuid;
    }

    public String toString() {
        return this.uuid.toString();
    }

    public static NetworkID fromString(String str) {
        return new NetworkID(UUID.fromString(str));
    }

}
