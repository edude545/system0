package net.ethobat.system0.api.network;

import net.ethobat.system0.api.savedata.LevelNetworks;
import net.minecraft.util.math.BlockPos;

import java.util.UUID;

public abstract class AbstractNWMember {

    public static final String NBT_UUID_KEY = "uuid";
    public static final String NBT_POS_KEY = "pos";

    public Network network;
    public UUID uuid;
    public BlockPos pos;

    public AbstractNWMember(UUID uuid, BlockPos pos) {
        this.uuid = uuid;
        this.pos = pos;
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public Network getNetwork() {
        return this.network;
    }

    public BlockPos getPos() {
        return this.pos;
    }

    public void onSubscribe(Network network) {
        this.network = network;
    }

    public void delete() {
        this.network.delete(this);
    }

}
