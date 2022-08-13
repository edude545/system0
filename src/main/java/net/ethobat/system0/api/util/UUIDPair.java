package net.ethobat.system0.api.util;

import net.ethobat.system0.api.network.abstracted.AbstractedNode;

import java.util.UUID;

public final class UUIDPair {

    public final UUID FIRST;
    public final UUID SECOND;

    public UUIDPair(UUID first, UUID second) {
        this.FIRST = first;
        this.SECOND = second;
    }

    public UUIDPair(AbstractedNode transmitter, AbstractedNode receiver) {
        this(transmitter.getUUID(), receiver.getUUID());
    }

    public String toString() {
        return this.FIRST.toString() + " " + this.SECOND.toString();
    }

    public static UUIDPair fromString(String str) {
        String[] arr = str.split(" ");
        return new UUIDPair(UUID.fromString(arr[0]), UUID.fromString(arr[1]));
    }

}
