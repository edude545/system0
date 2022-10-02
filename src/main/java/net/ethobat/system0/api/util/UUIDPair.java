package net.ethobat.system0.api.util;

import net.ethobat.system0.api.network.NWNode;

import java.util.UUID;

public final class UUIDPair {

    public final UUID FIRST;
    public final UUID SECOND;

    public UUIDPair(UUID first, UUID second) {
        this.FIRST = first;
        this.SECOND = second;
    }

    public UUIDPair(NWNode transmitter, NWNode receiver) {
        this(transmitter.getUUID(), receiver.getUUID());
    }

    public boolean has(UUID uuid) {
        return this.FIRST.equals(uuid) || this.SECOND.equals(uuid);
    }

    public String toString() {
        return this.FIRST.toString() + " " + this.SECOND.toString();
    }

    public static UUIDPair fromString(String str) {
        String[] arr = str.split(" ");
        return new UUIDPair(UUID.fromString(arr[0]), UUID.fromString(arr[1]));
    }

}
