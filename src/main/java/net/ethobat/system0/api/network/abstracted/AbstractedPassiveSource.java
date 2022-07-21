package net.ethobat.system0.api.network.abstracted;

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
