package net.ethobat.system0.api.network.abstracted;

import net.ethobat.system0.api.energy.EnergyTypeMap;
import net.ethobat.system0.api.nbt.NBTHandler;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

import java.util.UUID;

public class AbstractedActiveSource extends AbstractedSource {

    public BlockPos blockEntityPos;

    public AbstractedActiveSource(UUID uuid, BlockPos blockEntityPos) {
        super(uuid);
        this.blockEntityPos = blockEntityPos;
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
        NBTHandler.genericPut(nbt, NBT_ACTIVE_KEY, true);
        NBTHandler.genericPut(nbt, NBT_BLOCK_ENTITY_POS_KEY, this.blockEntityPos);
        NBTHandler.genericPut(nbt, NBT_CHANNELS_KEY, this.getChannels());
        return nbt;
    }

    public static AbstractedActiveSource fromNBT(NbtCompound nbt) {
        AbstractedActiveSource ret = new AbstractedActiveSource(
                NBTHandler.getUUID(nbt, NBT_UUID_KEY),
                NBTHandler.getBlockPos(nbt, NBT_BLOCK_ENTITY_POS_KEY)
        );
        ret.setChannels(NBTHandler.getEnergyTypeMap(nbt, NBT_CHANNELS_KEY));
        return ret;
    }

}
