package net.ethobat.system0.api.math;

import net.minecraft.util.math.Vec2f;

public class Polar2D {

    public double radius;
    public double inclination;

    public Polar2D(double radius, double inclination) {
        this.radius = radius;
        this.inclination = inclination;
    }

    public static Polar2D fromCartesian(double x, double y) {
        double radius = Math.sqrt(x*x + y*y);
        double inclination;
        if (x != 0) {
            inclination = Math.atan(y/x);
        } else {
            inclination = 0;
        }
        return new Polar2D(radius, inclination);
    }

    public double getCartesianX() {
        return this.radius * Math.cos(this.inclination);
    }

    public double getCartesianY() {
        return this.radius * Math.sin(this.inclination);
    }

}
