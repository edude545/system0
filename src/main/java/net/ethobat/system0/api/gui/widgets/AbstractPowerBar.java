package net.ethobat.system0.api.gui.widgets;

import net.ethobat.system0.api.energy.EnergyType;
import net.minecraft.util.Identifier;

public abstract class AbstractPowerBar extends GUIWidget {

    public long amt;
    public long maxAmt;
    public EnergyType energyType;

    public AbstractPowerBar(int x, int y, long amt, long maxAmt, EnergyType energyType) {
        super(x, y);
        this.amt = amt;
        this.maxAmt = maxAmt;
        this.energyType = energyType;
    }

    public abstract Identifier getTextureEmpty();
    public abstract Identifier getTextureFull();

    public float getFillAmount() {
        return ((float)this.amt) / this.maxAmt;
    }

    public int getRoundedFillAmount(int length) {
        return (int) Math.ceil(this.getFillAmount() * length);
    }

}
