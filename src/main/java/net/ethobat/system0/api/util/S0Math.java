package net.ethobat.system0.api.util;

public class S0Math {

    public static final double TAU = Math.PI*2;

    public static double wrapCycle(double d, double period) {
        return d - (d % period) * period;
    }

    public static int roundToNearestMultiple(int n, int m) {
        return m * Math.round(n/m);
    }

    public static int floorToNearestMultiple(int n, int m) {
        return (int) (m * Math.floor(n/m));
    }

}
