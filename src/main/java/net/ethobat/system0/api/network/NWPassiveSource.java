package net.ethobat.system0.api.network;

import net.ethobat.system0.api.energy.EnergyType;
import net.ethobat.system0.api.energy.EnergyTypeMap;
import net.ethobat.system0.api.nbt.NBTHandler;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

import java.util.UUID;

public class NWPassiveSource extends NWSource {

    private EnergyTypeMap potential;

    public NWPassiveSource(UUID uuid, BlockPos pos, EnergyTypeMap potential) {
        super(uuid, pos);
        this.potential = potential;
    }

    public NWPassiveSource(BlockPos pos, EnergyTypeMap potential) {
        this(UUID.randomUUID(), pos, potential);
    }

    // Create a new NWPassiveSource that provides a single energy type.
    public static NWPassiveSource mono(BlockPos pos, EnergyType energyType, long potential) {
        return new NWPassiveSource(
                UUID.randomUUID(),
                pos,
                EnergyTypeMap.singleType(energyType, potential)
        );
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
        NBTHandler.genericPut(nbt, NBT_POS_KEY, this.pos);
        NBTHandler.genericPut(nbt, NBT_ACTIVE_KEY, false);
        NBTHandler.genericPut(nbt, NBT_POTENTIAL_KEY, this.potential);
        NBTHandler.genericPut(nbt, NBT_CHANNELS_KEY, this.getChannels());
        return nbt;
    }

    public static NWPassiveSource fromNBT(NbtCompound nbt) {
        NWPassiveSource ret = new NWPassiveSource(
                NBTHandler.getUUID(nbt, NBT_UUID_KEY),
                NBTHandler.getBlockPos(nbt, NBT_POS_KEY),
                NBTHandler.getEnergyTypeMap(nbt, NBT_POTENTIAL_KEY)
        );
        ret.setChannels(NBTHandler.getEnergyTypeMap(nbt, NBT_CHANNELS_KEY));
        return ret;
    }

}
