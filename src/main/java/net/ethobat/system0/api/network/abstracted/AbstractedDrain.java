package net.ethobat.system0.api.network.abstracted;

import net.ethobat.system0.api.nbt.NBTHandler;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;

import java.util.UUID;

public class AbstractedDrain extends AbstractedNetworkMember {

    public AbstractedDrain(UUID uuid) {
        super(uuid);
    }

    @Override
    public void subscribe(Network network) {
        this.network = network;
    }

    public AbstractedDrain silentSubscribe(Network network) {
        this.network = network;
        return this;
    }

    //
    // NBT
    //

    public NbtCompound toNBT() {
        NbtCompound nbt = new NbtCompound();
        NBTHandler.genericPut(nbt, NBT_UUID_KEY, this.uuid);
        return nbt;
    }

    public static AbstractedDrain fromNBT(NbtCompound nbt) {
        return new AbstractedDrain(
                NBTHandler.getUUID(nbt, NBT_UUID_KEY)
        );
    }

}
