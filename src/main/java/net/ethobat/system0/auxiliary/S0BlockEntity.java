package net.ethobat.system0.auxiliary;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public abstract class S0BlockEntity extends BlockEntity {

    public <T extends S0Block> S0BlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public void onRemoved() {

    }

}
