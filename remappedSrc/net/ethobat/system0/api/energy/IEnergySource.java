package net.ethobat.system0.api.energy;

public interface IEnergySource {

    // Add energy to the source. Returns the amount of energy that couldn't be added.
    long put(long amt);

    // Draw energy from the source. Returns the amount of energy drawn.
    long draw(long amt);

    EnergyType getEnergyType();

}
