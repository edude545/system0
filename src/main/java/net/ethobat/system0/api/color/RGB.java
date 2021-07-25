package net.ethobat.system0.api.color;

import net.minecraft.util.math.MathHelper;

public class RGB {

    // test
    public final int RED;
    public final int GREEN;
    public final int BLUE;

    public RGB(int red, int green, int blue) {
        this.RED = red;
        this.GREEN = green;
        this.BLUE = blue;
    }

    public int asInt() {
        return MathHelper.packRgb(this.RED/255, this.GREEN/255, this.BLUE/255);
    }

}
