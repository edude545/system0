package net.ethobat.system0.api.math;

import me.x150.renderer.renderer.util.CameraContext3D;
import net.ethobat.system0.api.util.S0Math;
import net.minecraft.util.math.Vec3d;

// Uses radians
public class Polar3D {

    public double radius;
    public double inclination;
    public double azimuth;

    public Polar3D(double radius, double inclination, double azimuth) {
        this.radius = radius;
        this.inclination = inclination;
        this.azimuth = azimuth;
    }

    public void wrapAngles() {
        if (inclination < 0) {
            inclination = -inclination;
            azimuth += Math.PI;
        }
        inclination = S0Math.wrapCycle(inclination, Math.PI);
        azimuth = S0Math.wrapRadians(azimuth);
    }

    public static Polar3D fromDegrees(double radius, double inclination, double azimuth) {
        return new Polar3D(radius,
                Math.toRadians(inclination),
                Math.toRadians(azimuth));
    }

    public static Polar3D fromCartesian(double x, double y, double z) {
        double radius;
        double inclination;
        double azimuth;

        // Formulae from en.wikipedia.org/wiki/Spherical_coordinate_system#Cartesian_coordinates
        // This page uses X and Y as the horizontal axes - Y and Z are swapped here, because Y should be vertical.
        radius = Math.sqrt(x*x + y*y + z*z);
        inclination = Math.acos(y / radius);
        if (x > 0) {
            azimuth = Math.atan(z/x);
        } else if (x < 0 && z >= 0) {
            azimuth = Math.atan(z/x) + Math.PI;
        } else if (x < 0 && z < 0) {
            azimuth = Math.atan(z/x) - Math.PI;
        } else if (x == 0 && z > 0) {
            azimuth = Math.PI / 2;
        } else if (x == 0 && z < 0) {
            azimuth = -Math.PI / 2;
        } else {
            azimuth = 0; // Undefined; value doesn't matter.
        }

        return new Polar3D(radius, inclination, azimuth);
    }

    public static Polar3D fromCartesian(Vec3d vec) {
        return fromCartesian(vec.x, vec.y, vec.z);
    }

    public Vec3d toCartesian() {
        return new Vec3d(
                this.radius * Math.cos(this.azimuth) * Math.sin(this.inclination),
                this.radius * Math.cos(this.inclination),
                this.radius * Math.sin(this.azimuth) * Math.sin(this.inclination)
        );
    }

    public static Polar3D cameraLookDirection(CameraContext3D camera) {
        return fromDegrees(1, camera.getPitch()+90, camera.getYaw()+90);
    }

}
