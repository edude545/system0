package net.ethobat.system0.api.network.abstracted;

import net.minecraft.util.Pair;

import java.util.UUID;

public final class SourceDrainPair extends Pair<UUID,UUID> {

    public SourceDrainPair(UUID source, UUID drain) {
        super(source, drain);
    }

    public UUID getSource() {
        return this.getLeft();
    }

    public UUID getDrain() {
        return this.getRight();
    }

}
