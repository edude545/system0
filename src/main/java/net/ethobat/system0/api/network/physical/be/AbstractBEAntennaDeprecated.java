package net.ethobat.system0.api.network.physical.be;

import net.ethobat.system0.api.network.physical.IAntennaDeprecated;
import net.ethobat.system0.api.network.physical.INetConnection;
import net.ethobat.system0.api.network.physical.INetConnector;
import net.ethobat.system0.auxiliary.S0Block;
import net.ethobat.system0.auxiliary.S0BlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

import java.util.HashSet;
import java.util.Set;

// TODO: Integrate all this shit with the abstract network system. Get rid of connection tracking on block entities.

public abstract class AbstractBEAntennaDeprecated extends S0BlockEntity implements IAntennaDeprecated {

    protected boolean online;

    public <T extends S0Block> AbstractBEAntennaDeprecated(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public Set<INetConnector> getConnectedNodes() {
        HashSet<INetConnector> nodes = new HashSet<>();
        for (INetConnection cxn : this.getConnections()) {
            nodes.add(cxn.getTarget());
        }
        return nodes;
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
