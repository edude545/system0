package net.ethobat.system0.api.rendering;

import me.x150.renderer.renderer.color.Color;
import me.x150.renderer.renderer.util.CameraContext3D;
import net.ethobat.system0.api.math.VecMath;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class Crosshairs implements I3DBody {

    public Vec3d pos;
    public Vec3d direction;

    public Crosshairs(Vec3d pos, Vec3d direction) {
        this.pos = pos;
        this.direction = direction;
    }

    public Crosshairs(BlockPos pos, Vec3d direction) {
        this(S03DMath.blockPosToVec3d(pos), direction);
    }

    @Override
    public void render(CameraContext3D camera) {
        render(camera, this.pos, this.direction);
    }

    public static void render(CameraContext3D camera, Vec3d pos, Vec3d direction) {
        Vec3d ud = VecMath.rotate(direction, Math.PI/2, 0);
        Vec3d lr = direction.crossProduct(ud).normalize();

        ud = ud.multiply(2); lr = lr.multiply(5);

        Line.render(camera, pos.add(direction), pos.add(direction.negate()), Color.RED);
        Line.render(camera, pos.add(ud), pos.add(ud.negate()), Color.GREEN);
        Line.render(camera, pos.add(lr), pos.add(lr.negate()), Color.BLUE);
    }

}
