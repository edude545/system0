package net.ethobat.system0.api.network.abstracted;

import net.ethobat.system0.api.energy.EnergyTypeMap;
import net.ethobat.system0.api.nbt.NBTHandler;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

import java.util.UUID;

public class AbstractedActiveSource extends AbstractedSource {

    public BlockPos blockEntityPos;

    public AbstractedActiveSource(UUID uuid, BlockEntity blockEntity) {
        super(uuid);
        this.blockEntityPos = blockEntity.getPos();
    }

    public AbstractedActiveSource(UUID uuid, BlockPos blockPos) {
        super(uuid);
        this.blockEntityPos = blockPos;
    }

    public AbstractedActiveSource(UUID uuid) {
        super(uuid);
    }

    @Override
    public EnergyTypeMap getPotentialEnergy() {
        return null;
    }

    public NbtCompound toNBT() {
        NbtCompound ret = super.toNBT();
        NBTHandler.genericPut(ret, NBT_ACTIVE_KEY, true);
        NBTHandler.genericPut(ret, NBT_BLOCK_ENTITY_POS_KEY, this.blockEntityPos);
        return ret;
    }

}
