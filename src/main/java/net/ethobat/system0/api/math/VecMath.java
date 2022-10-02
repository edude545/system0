package net.ethobat.system0.api.math;

import me.x150.renderer.renderer.util.CameraContext3D;
import net.minecraft.util.math.Vec3d;

public class VecMath {

    public static Vec3d mirror(Vec3d vec, Vec3d focus) {
        return focus.subtract(vec).negate().add(vec);
    }

    public static Vec3d perpendicularUnit(Vec3d vec1, Vec3d vec2) {
        return vec1.crossProduct(vec2).normalize();
    }

    public static Vec3d rotateXZ(Vec3d vec, double yaw) {
        return new Vec3d(
                vec.x * Math.cos(yaw) - vec.z * Math.sin(yaw),
                vec.y,
                vec.z * Math.cos(yaw) + vec.x * Math.sin(yaw)
        );
    }

    public static Vec3d rotateXZ(double x, double z, double yaw) {
        return rotateXZ(new Vec3d(x,0,z), yaw);
    }

    //
    public static Vec3d rotate(Vec3d vec, double dInclination, double dAzimuth) {
        Polar3D polar = Polar3D.fromCartesian(vec);
        polar.inclination += dInclination;
        polar.azimuth += dAzimuth;
        polar.wrapAngles();
        return polar.toCartesian();
    }

    // Used to translate a camera's position in the direction it's looking.
    // +x is down, +y is forward.
    // Inclination is polar - 0 represents a vector pointing directly upwards, and pi is downwards.
    public static Vec3d translateTowards(Vec3d vec, Vec3d trans, double dInclination, double dAzimuth) {
        return vec.add(rotate(trans,dInclination,dAzimuth));
    }

    public static Vec3d translateTowards(Vec3d vec, double dx, double dy, double dz, double dInclination, double dAzimuth) {
        return translateTowards(vec, new Vec3d(dx,dy,dz), dInclination, dAzimuth);
    }

    public static Vec3d translateTowards(CameraContext3D camera, Vec3d trans) {
        Polar3D polar = Polar3D.cameraLookDirection(camera);
        return translateTowards(camera.getPosition(), trans, polar.inclination, polar.azimuth);
    }

}
