package net.ethobat.system0.api.rendering;

import com.mojang.blaze3d.systems.RenderSystem;
import me.x150.renderer.renderer.Renderer3d;
import me.x150.renderer.renderer.color.Color;
import me.x150.renderer.renderer.util.CameraContext3D;
import net.ethobat.system0.api.color.RGB;
import net.minecraft.client.gl.VertexBuffer;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import org.lwjgl.opengl.GL11;

public class Line implements I3DBody {

    public final Vec3d pos1;
    public final Vec3d pos2;
    public final Color color;

    public Line(Vec3d pos1, Vec3d pos2, Color color) {
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.color = color;
    }

    public Line(BlockPos pos1, BlockPos pos2, Color color) {
        this(S03DMath.blockPosToVec3dCenter(pos1), S03DMath.blockPosToVec3dCenter(pos2), color);
    }

    @Override
    public void render(CameraContext3D camera) {
        render(camera, this.pos1, this.pos2, this.color);
    }

    public static void render(CameraContext3D camera, Vec3d pos1, Vec3d pos2, Color color) {
        Renderer3d.renderLine(pos1, pos2, color).drawWithoutVboWith3DContext(camera);
    }

}
