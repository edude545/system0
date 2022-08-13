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
        return (AbstractedPassiveSource) AbstractedPassiveSource.mono(energyType, potential).subscribe(network);
    }
    public static AbstractedPassiveSource mono(EnergyType energyType, long potential) {
        return new AbstractedPassiveSource(UUID.randomUUID(), EnergyTypeMap.singleType(energyType, potential));
    }

    @Override
    public EnergyTypeMap getPotentialEnergy() {
        return this.potential;
    }

    public NbtCompound toNBT() {
        NbtCompound ret = super.toNBT();
        NBTHandler.genericPut(ret, NBT_ACTIVE_KEY, false);
        NBTHandler.genericPut(ret, NBT_POTENTIAL_KEY, this.potential);
        return ret;
    }

}
