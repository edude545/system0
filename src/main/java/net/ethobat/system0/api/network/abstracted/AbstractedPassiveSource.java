package net.ethobat.system0.api.network.abstracted;

import net.ethobat.system0.api.energy.EnergyType;
import net.ethobat.system0.api.energy.EnergyTypeMap;
import net.ethobat.system0.api.nbt.NBTHandler;
import net.minecraft.nbt.NbtCompound;

import java.util.UUID;

public class AbstractedPassiveSource extends AbstractedSource {

    private EnergyTypeMap potential;

    public AbstractedPassiveSource(UUID uuid, EnergyTypeMap potential) {
        super(uuid);
        this.potential = potential;
    }

    // Create a new AbstractedPassiveSource that provides a single energy type.
    public static AbstractedPassiveSource mono(Network network, EnergyType energyType, long potential) {
        AbstractedPassiveSource ret = new AbstractedPassiveSource(
                UUID.randomUUID(),
                EnergyTypeMap.singleType(energyType, potential)
        );
        ret.subscribe(network);
        return ret;
    }

    @Override
    public EnergyTypeMap getPotentialEnergy() {
        return this.potential;
    }

    private void setPotentialEnergy(EnergyTypeMap potential) {
        this.potential = potential;
    }

    //
    // NBT
    //

    public NbtCompound toNBT() {
        NbtCompound nbt = new NbtCompound();
        NBTHandler.genericPut(nbt, NBT_UUID_KEY, this.uuid);
        NBTHandler.genericPut(nbt, NBT_ACTIVE_KEY, false);
        NBTHandler.genericPut(nbt, NBT_POTENTIAL_KEY, this.potential);
        NBTHandler.genericPut(nbt, NBT_CHANNELS_KEY, this.getChannels());
        return nbt;
    }

    public static AbstractedPassiveSource fromNBT(NbtCompound nbt) {
        AbstractedPassiveSource ret = new AbstractedPassiveSource(
                NBTHandler.getUUID(nbt, NBT_UUID_KEY),
                NBTHandler.getEnergyTypeMap(nbt, NBT_POTENTIAL_KEY)
        );
        ret.setChannels(NBTHandler.getEnergyTypeMap(nbt, NBT_CHANNELS_KEY));
        return ret;
    }

}
