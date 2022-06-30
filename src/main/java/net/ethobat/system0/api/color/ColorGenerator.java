package net.ethobat.system0.api.color;

import java.util.Random;

public class ColorGenerator {

    private static final Random random = new Random();

    public static IColor getRandom() {
        return new RGB(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    public static IColor getRandomSaturatedBright() {
        int r = 0; int g = 0; int b = 0;
        int primary = random.nextInt(3);
        int secondary = random.nextBoolean() ? primary+1 : primary-1;
        if (secondary == -1) {secondary = 3;}
        else if (secondary == 3) {secondary = 0;}
        switch (primary) {
            case 0: r = 255;
            case 1: g = 255;
            case 2: b = 255;
        }
        switch (secondary) {
            case 0: r = random.nextInt(256);
            case 1: g = random.nextInt(256);
            case 2: b = random.nextInt(256);
        }
        return new RGB(r,g,b);
    }

}
