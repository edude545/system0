package net.ethobat.system0.api.color;

import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class RGB implements IColor {

    public static final RGB BLACK = new RGB(0,0,0);
    public static final RGB WHITE = new RGB(255,255,255);

    public static final RGB RED = new RGB(255,0,0);
    public static final RGB GREEN = new RGB(0,255,0);
    public static final RGB BLUE = new RGB(0,0,255);

    public static final RGB CYAN = new RGB(0,255,255);
    public static final RGB MAGENTA = new RGB(255,0,255);
    public static final RGB YELLOW = new RGB(255,255,0);

    public final int ALPHA;
    public final int R;
    public final int G;
    public final int B;
    public final int INT;
    public final Formatting FORMAT_CODE;

    public RGB(int alpha, int red, int green, int blue) {
        this.ALPHA = alpha;
        this.R = red;
        this.G = green;
        this.B = blue;
        this.INT = rgbToInt(this);
        this.FORMAT_CODE = rgbToFormatCode(this);
    }

    public RGB(int red, int green, int blue) {
        this(0xFF, red, green ,blue);
    }

    public int asInt() {
        return this.INT;
    }

    private static int rgbToInt(RGB rgb) {
        return
                (rgb.R >> 24  & 0xFF) +
                (rgb.G >> 16  & 0xFF) +
                (rgb.B >> 8   & 0xFF) +
                (rgb.ALPHA    & 0xFF);
    }

    private static Formatting rgbToFormatCode(RGB rgb) {
        return Formatting.byColorIndex(rgb.INT);
    }

}
