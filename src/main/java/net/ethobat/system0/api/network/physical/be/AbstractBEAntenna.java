package net.ethobat.system0.api.network.physical.be;

import net.ethobat.system0.api.network.physical.IAntenna;
import net.ethobat.system0.api.network.physical.INetConnection;
import net.ethobat.system0.api.network.physical.INetConnector;
import net.ethobat.system0.auxiliary.S0Block;
import net.ethobat.system0.auxiliary.S0BlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractBEAntenna extends S0BlockEntity implements IAntenna {

    protected boolean online;

    @Override
    public Set<INetConnector> getConnectedNodes() {
        HashSet<INetConnector> nodes = new HashSet<>();
        for (INetConnection cxn : this.getConnections()) {
            nodes.add(cxn.getTarget());
        }
        return nodes;
    }

    public <T extends S0Block> AbstractBEAntenna(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public boolean isOnline() {
        return this.online;
    }

    @Override
    public boolean setOnline(boolean b) {
        return this.online = b;
    }

}
