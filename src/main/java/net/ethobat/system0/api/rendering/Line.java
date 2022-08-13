package net.ethobat.system0.api.rendering;

import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class Line implements I3DBody {

    public final Vec3d pos1;
    public final Vec3d pos2;
    public final int color;

    public Line(Vec3d pos1, Vec3d pos2, int color) {
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.color = color;
    }

    public Line(BlockPos pos1, BlockPos pos2, int color) {
        this(new Vec3d(pos1.getX(), pos1.getY(), pos1.getZ()), new Vec3d(pos2.getX(), pos2.getY(), pos2.getZ()), color);
    }

    @Override
    public BufferBuilder.BuiltBuffer buildBuffer(BufferBuilder bb) {
        bb.begin(VertexFormat.DrawMode.LINES, VertexFormats.LINES);
        bb.vertex(pos1.x, pos1.y, pos1.z).color(this.color).next();
        bb.vertex(pos2.x, pos2.y, pos2.z).color(this.color).next();
        return bb.end();
    }

}
