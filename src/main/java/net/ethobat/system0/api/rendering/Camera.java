package net.ethobat.system0.api.rendering;

import net.minecraft.client.gl.VertexBuffer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;

public class Camera {

    public Vec3d pos;

    public float fov;

    public float pitch;
    public float yaw;

    public Camera(Vec3d pos, float fov, float pitch, float yaw) {
        this.pos = pos;
        this.fov = fov;
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public MatrixStack getMatrices() {
        MatrixStack stack = new MatrixStack();
        S03DMath.rotateX(stack, this.yaw);
        S03DMath.rotateY(stack, this.pitch);
        stack.push();
        stack.translate(-this.pos.x, -this.pos.y, -this.pos.z);
        return stack;
    }

}
