package net.ethobat.system0.api.color;

import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class RGB implements IColor {

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
        return MathHelper.packRgb(this.RED/255f, this.GREEN/255f, this.BLUE/255f);
    }

    public Formatting getFormatCode() {
        return Formatting.byColorIndex(this.asInt());
    }

}
