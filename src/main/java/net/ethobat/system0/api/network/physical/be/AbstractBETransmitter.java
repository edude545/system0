package net.ethobat.system0.api.network.physical.be;

import net.ethobat.system0.api.network.physical.INetConnection;
import net.ethobat.system0.api.network.physical.IReceiverAntenna;
import net.ethobat.system0.api.network.physical.ITransmitterAntenna;
import net.ethobat.system0.api.util.WorldReader;
import net.ethobat.system0.auxiliary.S0Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractBETransmitter extends AbstractBEAntenna implements ITransmitterAntenna {

    private final Set<INetConnection> receivers;

    public <T extends S0Block> AbstractBETransmitter(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.receivers = new HashSet<INetConnection>();
    }

    @Override
    public void addConnection(IReceiverAntenna receiver) {
        this.receivers.add((INetConnection) receiver);
    }

    @Override
    public boolean attemptConnection(IReceiverAntenna receiver) {
        if (this.canConnectTo(receiver)) {
            this.addConnection(receiver);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Set<INetConnection> getConnections() {
        return this.receivers;
    }

    @Override
    public boolean canConnectTo(IReceiverAntenna receiver) {
        //return this.canReach(receiver) && !this.getEffectiveBandwidth(receiver).isZero();
        return this.canReach(receiver) && !(this.getEffectiveBandwidth(receiver) == 0);
    }

    @Override
    public long getEffectiveBandwidth(IReceiverAntenna receiver) {
        return this.getEffectiveBandwidth(receiver, this.getAttenuationCoefficient(receiver));
    }

    @Override
    public long getEffectiveBandwidth(IReceiverAntenna receiver, double coeff) {
        //return this.getBandwidthUp().min(receiver.getBandwidthDown()).mulFloor(coeff);
        return (long) Math.floor(Math.min(this.getBandwidthUp(), receiver.getBandwidthDown()) * coeff);
    }

    @Override
    public int getEffectiveRange(IReceiverAntenna receiver) {
        return (int) Math.floor(this.getRange() * receiver.getSensitivity());
    }
    @Override
    public boolean canReach(IReceiverAntenna receiver) {
        return this.getPos().isWithinDistance(receiver.getPos(), this.getEffectiveRange(receiver));
    }

    @Override
    public double getAttenuationCoefficient(IReceiverAntenna receiver) {
        return WorldReader.getAttenuationCoefficient(this.getPos(), receiver.getPos());
    }

}
