package net.ethobat.system0.api.rendering;

public class Oscillator {

    public static double osc(int period) {
        return (System.currentTimeMillis() % period) / (double) period;
    }

    public static double range(int period, double low, double high) {
        return (osc(period) * (high-low)) + low;
    }

    public static double loop(int period, double low, double high) {
        if ((System.currentTimeMillis() % period) < period/2) {
            return 2*range(period, low, high);
        } else {
            return 2*range(period, high, low);
        }
    }

}
