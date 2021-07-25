package net.ethobat.system0.api.network;

import net.ethobat.system0.auxiliary.S0Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractBEReceiver extends AbstractBEAntenna implements IReceiverAntenna {

    private final Set<INetConnection> transmitters;

    public <T extends S0Block> AbstractBEReceiver(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.transmitters = new HashSet<INetConnection>();
    }

}
