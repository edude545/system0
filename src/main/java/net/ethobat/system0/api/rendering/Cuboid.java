package net.ethobat.system0.api.rendering;

import com.mojang.blaze3d.systems.RenderSystem;
import me.x150.renderer.renderer.RenderAction;
import me.x150.renderer.renderer.Renderer3d;
import me.x150.renderer.renderer.color.Color;
import me.x150.renderer.renderer.util.CameraContext3D;
import net.minecraft.client.render.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class Cuboid implements I3DBody {

    public Vec3d pos;
    public Vec3d dimensions;
    public Color color;

    public Cuboid(Vec3d pos, Vec3d dimensions, Color color) {
        this.pos = pos;
        this.dimensions = dimensions;
        this.color = color;
    }

    public Cuboid(Vec3d pos, Color color) {
        this(pos, new Vec3d(1,1,1), color);
    }

    public Cuboid(BlockPos pos, Color color) {
        this(S03DMath.blockPosToVec3d(pos), color);
    }

    @Override
    public void render(CameraContext3D camera) {
        render(camera, this.pos, this.dimensions, this.color);
    }

    public static void render(CameraContext3D camera, Vec3d pos, Vec3d dimensions, Color color) {
        double x1 = pos.x;
        double y1 = pos.y;
        double z1 = pos.z;
        double x2 = x1 + dimensions.x;
        double y2 = y1 + dimensions.y;
        double z2 = z1 + dimensions.z;

        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        int alpha = color.getAlpha();

        BufferBuilder buffer = Tessellator.getInstance().getBuffer();
        buffer.begin(VertexFormat.DrawMode.DEBUG_LINES, VertexFormats.LINES);

        buffer.vertex(x1, y1, z1).color(red, green, blue, alpha).normal(1.0F, 0.0F, 0.0F).next();
        buffer.vertex(x2, y1, z1).color(red, green, blue, alpha).normal(1.0F, 0.0F, 0.0F).next();
        buffer.vertex(x1, y1, z1).color(red, green, blue, alpha).normal(0.0F, 1.0F, 0.0F).next();
        buffer.vertex(x1, y2, z1).color(red, green, blue, alpha).normal(0.0F, 1.0F, 0.0F).next();

        buffer.vertex(x1, y1, z1).color(red, green, blue, alpha).normal(0.0F, 0.0F, 1.0F).next();
        buffer.vertex(x1, y1, z2).color(red, green, blue, alpha).normal(0.0F, 0.0F, 1.0F).next();
        buffer.vertex(x2, y1, z1).color(red, green, blue, alpha).normal(0.0F, 1.0F, 0.0F).next();
        buffer.vertex(x2, y2, z1).color(red, green, blue, alpha).normal(0.0F, 1.0F, 0.0F).next();

        buffer.vertex(x2, y2, z1).color(red, green, blue, alpha).normal(-1.0F, 0.0F, 0.0F).next();
        buffer.vertex(x1, y2, z1).color(red, green, blue, alpha).normal(-1.0F, 0.0F, 0.0F).next();
        buffer.vertex(x1, y2, z1).color(red, green, blue, alpha).normal(0.0F, 0.0F, 1.0F).next();
        buffer.vertex(x1, y2, z2).color(red, green, blue, alpha).normal(0.0F, 0.0F, 1.0F).next();

        buffer.vertex(x1, y2, z2).color(red, green, blue, alpha).normal(0.0F, -1.0F, 0.0F).next();
        buffer.vertex(x1, y1, z2).color(red, green, blue, alpha).normal(0.0F, -1.0F, 0.0F).next();
        buffer.vertex(x1, y1, z2).color(red, green, blue, alpha).normal(1.0F, 0.0F, 0.0F).next();
        buffer.vertex(x2, y1, z2).color(red, green, blue, alpha).normal(1.0F, 0.0F, 0.0F).next();

        buffer.vertex(x2, y1, z2).color(red, green, blue, alpha).normal(0.0F, 0.0F, -1.0F).next();
        buffer.vertex(x2, y1, z1).color(red, green, blue, alpha).normal(0.0F, 0.0F, -1.0F).next();
        buffer.vertex(x1, y2, z2).color(red, green, blue, alpha).normal(1.0F, 0.0F, 0.0F).next();
        buffer.vertex(x2, y2, z2).color(red, green, blue, alpha).normal(1.0F, 0.0F, 0.0F).next();

        buffer.vertex(x2, y1, z2).color(red, green, blue, alpha).normal(0.0F, 1.0F, 0.0F).next();
        buffer.vertex(x2, y2, z2).color(red, green, blue, alpha).normal(0.0F, 1.0F, 0.0F).next();
        buffer.vertex(x2, y2, z1).color(red, green, blue, alpha).normal(0.0F, 0.0F, 1.0F).next();
        buffer.vertex(x2, y2, z2).color(red, green, blue, alpha).normal(0.0F, 0.0F, 1.0F).next();

        new RenderAction(buffer.end(), GameRenderer.getRenderTypeLinesShader()).drawWithoutVboWith3DContext(camera);
    }

    public static void renderCentered(CameraContext3D camera, Vec3d pos, Vec3d dimensions, Color color) {
        render(camera, pos.subtract(dimensions.x/2, dimensions.y/2, dimensions.z/2), dimensions, color);
    }

}
