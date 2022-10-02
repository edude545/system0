package net.ethobat.system0.api.network;

import net.ethobat.system0.api.energy.EnergyTypeMap;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

import java.util.UUID;

public abstract class NWSource extends AbstractNWMember {

    public static final String NBT_ACTIVE_KEY = "active";
    public static final String NBT_CHANNELS_KEY = "channels";
    public static final String NBT_POTENTIAL_KEY = "potential";

    public EnergyTypeMap channels;

    public NWSource(UUID uuid, BlockPos pos) {
        super(uuid, pos);
        this.channels = new EnergyTypeMap();
    }

    //
    // Network methods
    //

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
        assert (this instanceof NWPassiveSource || this instanceof NWActiveSource);
        if (this instanceof NWPassiveSource) {
            return ((NWPassiveSource) this).toNBT();
        } else {
            return ((NWActiveSource) this).toNBT();
        }
    }

    public static NWSource fromNBT(NbtCompound nbt) {
        NWSource ret;
        if (nbt.getBoolean(NBT_ACTIVE_KEY)) {
            ret = NWActiveSource.fromNBT(nbt);
        } else {
            ret = NWPassiveSource.fromNBT(nbt);
        }
        return ret;
    }

}
