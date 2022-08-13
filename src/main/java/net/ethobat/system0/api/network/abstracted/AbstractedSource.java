package net.ethobat.system0.api.network.abstracted;

import net.ethobat.system0.api.energy.EnergyTypeMap;
import net.ethobat.system0.api.nbt.NBTHandler;
import net.minecraft.nbt.NbtCompound;

import java.util.UUID;

public abstract class AbstractedSource extends AbstractedNetworkMember {

    public static final String NBT_ACTIVE_KEY = "active";
    public static final String NBT_CHANNELS_KEY = "channels";
    public static final String NBT_POTENTIAL_KEY = "potential";
    public static final String NBT_BLOCK_ENTITY_POS_KEY = "blockEntityPos";

    public EnergyTypeMap channels;

    public AbstractedSource(UUID uuid) {
        super(uuid);
        this.channels = new EnergyTypeMap();
    }

    //
    // Network methods
    //

    @Override
    public void subscribe(Network network) {
        this.network = network;
    }

    public AbstractedSource silentSubscribe(Network network) {
        this.network = network;
        return this;
    }

    @Override
    public Network getNetwork() {
        return this.network;
    }

    public UUID getUUID() {
        return this.uuid;
    }

    // Returns the highest amount of energy the source can possibly provide.
    public abstract EnergyTypeMap getPotentialEnergy();

    // Returns the amount of energy the source is currently providing to the network.
    public EnergyTypeMap getChannels() {
        return this.channels;
    }

    public void setChannels(EnergyTypeMap channels) {
        this.channels = channels;
    }

    //
    // NBT
    //

    public NbtCompound autoToNBT() {
        assert (this instanceof AbstractedPassiveSource || this instanceof AbstractedActiveSource);
        if (this instanceof AbstractedPassiveSource) {
            return ((AbstractedPassiveSource) this).toNBT();
        } else {
            return ((AbstractedActiveSource) this).toNBT();
        }
    }

    public static AbstractedSource fromNBT(NbtCompound nbt) {
        AbstractedSource ret;
        if (nbt.getBoolean(NBT_ACTIVE_KEY)) {
            ret = AbstractedActiveSource.fromNBT(nbt);
        } else {
            ret = AbstractedPassiveSource.fromNBT(nbt);
        }
        return ret;
    }

}
