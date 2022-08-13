package net.ethobat.system0.api.network.abstracted;

import net.ethobat.system0.api.nbt.NBTHandler;
import net.minecraft.nbt.NbtCompound;

import java.util.UUID;

public class AbstractedDrain implements IAbstractedNetworkMember,INetworkTerminal {

    public Network network;

    public final UUID uuid;

    public AbstractedDrain(Network network, UUID uuid) {
        this.network = network;
        this.uuid = uuid;
    }

    public AbstractedDrain(UUID uuid) {
        this(null, uuid);
    }


    public UUID getUUID() {
        return this.uuid;
    }

    @Override
    public Network getNetwork() {
        return null;
    }

    // NBT stuff

    public static AbstractedDrain fromNBT(NbtCompound nbt) {
        return new AbstractedDrain(
                NBTHandler.getUUID(nbt, "uuid")
        );
    }

    public NbtCompound toNBT() {
        NbtCompound nbt = new NbtCompound();
        NBTHandler.genericPut(nbt, "uuid", this.uuid);
        return nbt;
    }

}
