package net.ethobat.system0.api.rendering;

import com.mojang.blaze3d.systems.RenderSystem;
import me.x150.renderer.renderer.Renderer3d;
import me.x150.renderer.renderer.color.Color;
import me.x150.renderer.renderer.util.CameraContext3D;
import net.ethobat.system0.api.math.Polar3D;
import net.ethobat.system0.api.math.VecMath;
import net.fabricmc.loader.impl.lib.sat4j.core.Vec;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

import java.util.HashSet;

public class VirtualSpace {

    private Vec3d cameraFocus; // Used as the focal point for zooming and rotating. Translated along with the camera when the view is panned.
    private float zoom; // Distance of camera position from cameraFocus.
    private static final float ZOOM_MINIMUM = -8;
    public final CameraContext3D camera;

    public final HashSet<I3DBody> bodies = new HashSet<>();

    public VirtualSpace(Vec3d cameraFocus) {
        this.cameraFocus = cameraFocus;
        this.zoom = -5;
        this.camera = CameraContext3D.builder().position(this.cameraFocus.add(new Vec3d(0,3,this.zoom))).fov(120f).build();
        this.camera.faceTowards(this.cameraFocus);
    }

    public void addObject(I3DBody body) {
        this.bodies.add(body);
    }

    public void render(int x, int y, int width, int height, int mouseX, int mouseY) {
        RenderSystem.disableDepthTest();

        this.camera.use(()-> {
            for (I3DBody body : this.bodies) {
                body.render(this.camera);
            }
            Vec3d vec = VecMath.rotate(new Vec3d(8,0,0), Oscillator.range(1000,0,1), 0);
            FilledCuboid.renderCentered(this.camera, vec, new Vec3d(1,1,1), Color.ORANGE);
            Crosshairs.render(this.camera, new Vec3d(0,0,0), new Vec3d(1,0,0));
            }, x, y, x+width, y+height);

    }

    public void wipe() {
        this.bodies.clear();
    }

    public void nudgeCamera(Vec3d nudge) {
        this.cameraFocus = this.cameraFocus.add(nudge);
        this.camera.setPosition(this.camera.getPosition().add(nudge));
    }

    public Vec3d getCameraLookDirection() {
//        return Polar3D.fromDegrees(1, this.camera.getPitch()+90, this.camera.getYaw()).toCartesian();
        return Polar3D.cameraLookDirection(this.camera).toCartesian();
    }

    // Move the position of the camera by the given distance away from the camera focus.
    // i.e. positive values move the camera away, negative values move it closer.
    // TODO: minimum zoom level
    public void zoomCamera(float diff) {
        float newZoom = this.zoom + diff;
        if (newZoom < ZOOM_MINIMUM) {
            diff = ZOOM_MINIMUM - this.zoom;
            this.zoom = ZOOM_MINIMUM;
        } else {
            this.zoom = newZoom;
        }
        this.camera.setPosition(this.camera.getPosition().add(
                this.camera.getPosition().subtract(this.cameraFocus) // Get the vector from the focus to the camera...
                        .normalize() // ...get the direction vector...
                        .multiply(diff)) // ... then multiply that by the new zoom factor, then add it to the camera position.
        );
    }

    // Rotate the camera by the given values in radians, centered on cameraFocus.
    // This method uses spherical coordinates to change the inclination and azimuth of the camera directly.
    // The radius is always equal to the zoom factor.
    public void rotateCamera(double pitch, double yaw) {
        Vec3d relPos = this.camera.getPosition().subtract(this.cameraFocus);

        Polar3D polar = Polar3D.fromCartesian(relPos);
        polar.azimuth += yaw;
        polar.inclination += pitch;

        // Inclination is the angle subtended by the coordinate and the y-axis. Camera pitch, however, is the angle from the x-z plane.

        // Don't allow the user to rotate the camera close to 90 degrees in either direction.
        // With a camera pitch of exactly +/-90 degrees, the azimuth would be undefined, and Polar3D would default to 0, snapping the camera to a fixed yaw.
        // Ensuring the camera never looks directly down or up preserves the azimuth.
        if (polar.inclination < Math.PI/2) {
            polar.inclination = Math.max(polar.inclination, 0.00390625);
        } else {
            polar.inclination = Math.min(polar.inclination, Math.PI-0.00390625);
        }

        this.camera.setPosition(this.cameraFocus.add(polar.toCartesian()));
        this.camera.faceTowards(this.cameraFocus);
    }

    // Set rotation in radians.
    public void setCameraRotation(double pitch, double yaw) {
        this.rotateCamera((pitch-this.camera.getPitch())*Math.PI/180, (yaw-this.camera.getYaw())*Math.PI/180);
    }

    public void setYaw(double yaw) {
        this.rotateCamera(0, (yaw-this.camera.getYaw())*Math.PI/180);
    }

}
