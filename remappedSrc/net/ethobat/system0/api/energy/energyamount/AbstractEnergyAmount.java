package net.ethobat.system0.api.energy.energyamount;

import java.math.BigInteger;

public abstract class AbstractEnergyAmount {

    public abstract int intValue();
    public abstract BigInteger bigIntValue();

    public abstract AbstractEnergyAmount sum(BigIntEnergyAmount amt);
    public abstract AbstractEnergyAmount sum(IntEnergyAmount amt);
    public AbstractEnergyAmount sum(InfiniteEnergyAmount amt) {
        return InfiniteEnergyAmount.INSTANCE;
    }
    public AbstractEnergyAmount sum(AbstractEnergyAmount amt) {throw new IllegalArgumentException("");}

    public abstract AbstractEnergyAmount mulFloor(double m);
    public AbstractEnergyAmount mul(InfiniteEnergyAmount amt) {
        return InfiniteEnergyAmount.INSTANCE;
    }
    public AbstractEnergyAmount mul(AbstractEnergyAmount amt) {throw new IllegalArgumentException("");}

    public abstract AbstractEnergyAmount min(BigIntEnergyAmount amt);
    public abstract AbstractEnergyAmount min(IntEnergyAmount amt);
    public AbstractEnergyAmount min(InfiniteEnergyAmount amt) {
        return this;
    }
    public AbstractEnergyAmount min(AbstractEnergyAmount amt) {throw new IllegalArgumentException("");}

    public AbstractEnergyAmount sumAsBigInt(AbstractEnergyAmount amt) {
        return new BigIntEnergyAmount(this.bigIntValue().add(amt.bigIntValue()));
    }
    public AbstractEnergyAmount sumAsInt(AbstractEnergyAmount amt) {
        return new IntEnergyAmount(this.intValue() + amt.intValue());
    }

    public abstract boolean isZero();

}
