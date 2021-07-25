package net.ethobat.system0.api.energy.energyamount;

import java.math.BigInteger;

public class IntEnergyAmount extends AbstractEnergyAmount {

    public final Integer VALUE;

    public static final IntEnergyAmount ZERO = new IntEnergyAmount(0);

    public IntEnergyAmount(int val) {
        this.VALUE = val;
    }

    public int intValue() {
        return this.VALUE;
    }

    public BigInteger bigIntValue() {
        return BigInteger.valueOf(this.VALUE);
    }

    public AbstractEnergyAmount sum(BigIntEnergyAmount amt) {
        return this.sumAsBigInt(amt);
    }
    public AbstractEnergyAmount sum(IntEnergyAmount amt) {
        return this.sumAsInt(amt);
    }
    public AbstractEnergyAmount sum(InfiniteEnergyAmount amt) {
        return InfiniteEnergyAmount.INSTANCE;
    }

    public AbstractEnergyAmount min(BigIntEnergyAmount amt) {
        return new BigIntEnergyAmount(BigInteger.valueOf(this.VALUE).min(amt.VALUE));
    }
    public AbstractEnergyAmount min(IntEnergyAmount amt) {
        return new IntEnergyAmount(Math.min(this.VALUE, amt.VALUE));
    }

    public AbstractEnergyAmount mulFloor(double m) {
        return new IntEnergyAmount((int) Math.floor(this.VALUE * m));
    }

    @Override
    public boolean isZero() {
        return this.VALUE == 0;
    }

}