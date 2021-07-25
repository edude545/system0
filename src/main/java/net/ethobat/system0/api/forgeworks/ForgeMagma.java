package net.ethobat.system0.api.forgeworks;

import net.ethobat.system0.api.util.S0Math;

public class ForgeMagma {

    public final float AMOUNT;
    public final float HEAT;
    public final float DENSITY;
    public final float PURITY;
    public final float CHARGE;

    public ForgeMagma() {
        this(0, 0, 0, 0, 0);
    }

    public ForgeMagma(float amount, float heat, float density, float purity, float charge) {
        this.AMOUNT = amount;
        this.HEAT = heat;
        this.DENSITY = density;
        this.PURITY = purity;
        this.CHARGE = charge;
    }

    public ForgeMagma getAverage(ForgeMagma magma) {
        float amt1 = this.AMOUNT; float amt2 = magma.AMOUNT;
        return new ForgeMagma(
                amt1 + amt2,
                avg(amt1, this.HEAT, amt2, this.HEAT),
                avg(amt1, this.DENSITY, amt2, this.DENSITY),
                avg(amt1, this.PURITY, amt2, this.PURITY),
                avg(amt1, this.CHARGE, amt2, this.CHARGE)
        );
    }

    private float avg(float amt1, float prop1, float amt2, float prop2) {
        return (amt1*prop1 + amt2*prop2) / amt1+amt2;
    }

}
