package net.ethobat.system0.api.energy.energyamount;

import java.math.BigDecimal;
import java.math.BigInteger;

// Value should ALWAYS be greater than 2^31-1 (max int).
public class BigIntEnergyAmount extends AbstractEnergyAmount {

    public final BigInteger VALUE;

    public BigIntEnergyAmount(BigInteger val) {
        this.VALUE = val;
    }

    public int intValue() {
        return this.VALUE.intValue();
    }

    public BigInteger bigIntValue() {
        return this.VALUE;
    }

    public AbstractEnergyAmount sum(BigIntEnergyAmount amt) {
        return sumAsBigInt(amt);
    }
    public AbstractEnergyAmount sum(IntEnergyAmount amt) {
        return sumAsBigInt(amt);
    }

    public AbstractEnergyAmount min(BigIntEnergyAmount amt) {
        return new BigIntEnergyAmount(this.VALUE.min(amt.VALUE));
    }
    public AbstractEnergyAmount min(IntEnergyAmount amt) {
        return new BigIntEnergyAmount(this.VALUE.min(BigInteger.valueOf(amt.VALUE)));
    }

    public AbstractEnergyAmount mulFloor(double m) {
        return new BigIntEnergyAmount(new BigDecimal(this.VALUE).multiply(new BigDecimal(m)).toBigInteger());
    }

    @Override
    public boolean isZero() {
        return false;
    }

}
