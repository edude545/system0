package net.ethobat.system0.api.network.abstracted;

import net.ethobat.system0.api.energy.EnergyTypeMap;
import net.ethobat.system0.api.nbt.NBTHandler;
import net.minecraft.nbt.NbtCompound;

import java.util.UUID;

public abstract class AbstractedSource implements IAbstractedNetworkMember,INetworkTerminal {

    public static final String NBT_UUID_KEY = "uuid";
    public static final String NBT_CHANNELS_KEY = "channels";
    public static final String NBT_ACTIVE_KEY = "active";
    public static final String NBT_POTENTIAL_KEY = "potential";
    public static final String NBT_BLOCK_ENTITY_POS_KEY = "blockEntityPos";

    public Network network;

    public final UUID uuid;

    private EnergyTypeMap channels;

    public AbstractedSource(UUID uuid) {
        this.uuid = uuid;
        this.channels = new EnergyTypeMap();
    }

    public AbstractedSource(Network network, UUID uuid) {
        this(uuid);
        this.network = network;
    }

    public AbstractedSource subscribe(Network network) {
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

    // NBT stuff

    public static AbstractedSource fromNBT(NbtCompound nbt) {
        AbstractedSource ret;
        UUID uuid = NBTHandler.getUUID(nbt, NBT_UUID_KEY);
        EnergyTypeMap channels = NBTHandler.getEnergyTypeMap(nbt, NBT_CHANNELS_KEY);
        if (!nbt.getBoolean(NBT_ACTIVE_KEY)) { // if reading an active source...
            ret = new AbstractedPassiveSource(uuid, NBTHandler.getEnergyTypeMap(nbt, NBT_POTENTIAL_KEY));
        } else {
            ret = new AbstractedActiveSource(uuid, NBTHandler.getBlockPos(nbt, NBT_BLOCK_ENTITY_POS_KEY));
        }
        ret.setChannels(channels);
        return ret;
    }

    public NbtCompound toNBT() {
        NbtCompound ret = new NbtCompound();
        NBTHandler.genericPut(ret, NBT_UUID_KEY, this.uuid);
        NBTHandler.genericPut(ret, NBT_CHANNELS_KEY, this.channels);
        return ret;
    }

}
