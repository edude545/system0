package net.ethobat.system0.api.energy;

import net.ethobat.system0.api.color.RGB;

public class EnergyType {

    protected final String NAME;
    protected final RGB PRIMARY_COLOR;

    public EnergyType(String name, RGB primaryColor) {
        this.NAME = name;
        this.PRIMARY_COLOR = primaryColor;
    }

    public String getName() {
        return this.NAME;
    }

    public RGB getPrimaryColor() {
        return this.PRIMARY_COLOR;
    }

}
