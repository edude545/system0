package net.ethobat.system0.api.network;

import net.ethobat.system0.api.energy.EnergyTypeMap;
import net.ethobat.system0.api.nbt.NBTHandler;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

import java.util.UUID;

public class NWActiveSource extends NWSource {

    public BlockPos blockEntityPos;

    public NWActiveSource(UUID uuid, BlockPos blockEntityPos) {
        super(uuid, blockEntityPos);
        this.blockEntityPos = blockEntityPos;
    }

    public NWActiveSource(BlockPos blockEntityPos) {
        this(UUID.randomUUID(), blockEntityPos);
    }

    @Override
    public EnergyTypeMap getPotentialEnergy() {
        return null;
    }

    public void setBlockEntityPos(BlockPos pos) {
        this.blockEntityPos = pos;
    }

    //
    // NBT
    //

    public NbtCompound toNBT() {
        NbtCompound nbt = new NbtCompound();
        NBTHandler.genericPut(nbt, NBT_UUID_KEY, this.uuid);
        NBTHandler.genericPut(nbt, NBT_POS_KEY, this.pos);
        NBTHandler.genericPut(nbt, NBT_ACTIVE_KEY, true);
        NBTHandler.genericPut(nbt, NBT_CHANNELS_KEY, this.getChannels());
        return nbt;
    }

    public static NWActiveSource fromNBT(NbtCompound nbt) {
        NWActiveSource ret = new NWActiveSource(
                NBTHandler.getUUID(nbt, NBT_UUID_KEY),
                NBTHandler.getBlockPos(nbt, NBT_POS_KEY)
        );
        ret.setChannels(NBTHandler.getEnergyTypeMap(nbt, NBT_CHANNELS_KEY));
        return ret;
    }

}
