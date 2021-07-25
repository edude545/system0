package net.ethobat.system0.api.energy.energyamount;

import org.apache.commons.lang3.NotImplementedException;

import java.math.BigInteger;

public class InfiniteEnergyAmount extends AbstractEnergyAmount {

    public static InfiniteEnergyAmount INSTANCE = new InfiniteEnergyAmount();

    public int intValue() {
        throw new NotImplementedException("");
    }
    public BigInteger bigIntValue() {
        throw new NotImplementedException("");
    }

    public AbstractEnergyAmount sum(BigIntEnergyAmount amt) {
        return INSTANCE;
    }
    public AbstractEnergyAmount sum(IntEnergyAmount amt) {
        return INSTANCE;
    }

    public AbstractEnergyAmount mulFloor(double m) {
        if (m == 0) {
            return IntEnergyAmount.ZERO;
        } else {
            return INSTANCE;
        }
    }

    @Override
    public AbstractEnergyAmount min(BigIntEnergyAmount amt) {
        return amt;
    }

    @Override
    public AbstractEnergyAmount min(IntEnergyAmount amt) {
        return amt;
    }

    @Override
    public boolean isZero() {
        return false;
    }

}
