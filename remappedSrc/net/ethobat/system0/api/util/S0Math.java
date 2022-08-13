package net.ethobat.system0.api.util;

public class S0Math {

    public static int roundToNearestMultiple(int n, int m) {
        return m * Math.round(n/m);
    }

    public static int floorToNearestMultiple(int n, int m) {
        return (int) (m * Math.floor(n/m));
    }

}
