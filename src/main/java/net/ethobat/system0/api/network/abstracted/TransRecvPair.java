package net.ethobat.system0.api.network.abstracted;

import net.minecraft.util.Pair;

import java.util.UUID;

// "Transmitter-Receiver Pair."
public final class TransRecvPair extends Pair<UUID,UUID> {

    public TransRecvPair(UUID transmitter, UUID receiver) {
        super(transmitter, receiver);
    }

    public TransRecvPair(AbstractedNode transmitter, AbstractedNode receiver) {
        super(transmitter.getUUID(), receiver.getUUID());
    }

    public UUID getTransmitter() {
        return this.getLeft();
    }

    public UUID getReceiver() {
        return this.getRight();
    }

}
