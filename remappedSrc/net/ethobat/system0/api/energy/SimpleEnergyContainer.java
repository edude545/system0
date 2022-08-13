package net.ethobat.system0.api.energy;

public class SimpleEnergyContainer implements IEnergyContainer {

    public long amt;
    public final long maxAmt;
    public EnergyType energyType;

    public SimpleEnergyContainer(long maxAmt, EnergyType energyType) {
        this.amt = 0;
        this.maxAmt = maxAmt;
        this.energyType = energyType;
    }

    public SimpleEnergyContainer(long maxAmt) {
        this(maxAmt, null);
    }

    @Override
    public long getAmount() {
        return this.amt;
    }

    @Override
    public long getMaxAmount() {
        return this.maxAmt;
    }

    @Override
    public EnergyType getEnergyType() {
        return this.energyType;
    }

    @Override
    public float getFillRatio() {
        return this.amt / (float) this.maxAmt;
    }

    @Override
    public long put(long amt) {
        long diff = maxAmt - amt;
        if (diff < amt) {
            this.amt += diff;
            return amt - diff;
        } else {
            this.amt += amt;
            return 0;
        }
    }

    @Override
    public long draw(long amt) {
        long amtAfter = this.amt - amt;
        if (amtAfter < 0) {
            long drawn = this.amt;
            this.amt = 0;
            return drawn;
        } else {
            this.amt = amtAfter;
            return amt;
        }
    }
}
