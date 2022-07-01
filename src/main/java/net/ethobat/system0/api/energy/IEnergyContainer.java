package net.ethobat.system0.api.energy;

public interface IEnergyContainer extends IEnergySource {

    long getAmount();

    long getMaxAmount();

    float getFillRatio();

}
