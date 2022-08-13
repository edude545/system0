package net.ethobat.system0.api.rendering;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gl.VertexBuffer;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

import java.util.HashSet;

public class VirtualSpace {

    public final Camera camera = new Camera(new Vec3d(0,0,0), 90f, 0f, 0f);

    public final HashSet<I3DBody> bodies = new HashSet<I3DBody>();

    public VirtualSpace() {

    }

    public void addObject(I3DBody body) {
        this.bodies.add(body);
    }

    public void render() {
        MatrixStack stack = this.camera.getMatrices();
        VertexBuffer vbo = new VertexBuffer();

        vbo.bind();
        for (I3DBody body : this.bodies) {
            BufferBuilder.BuiltBuffer bb = body.buildBuffer(Tessellator.getInstance().getBuffer());
            vbo.upload(bb);
        }

        RenderSystem.disableCull();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);

        boolean rendersThroughWalls = false;
        RenderSystem.depthFunc(rendersThroughWalls ? GL11.GL_ALWAYS : GL11.GL_LEQUAL);

        vbo.draw(stack.peek().getPositionMatrix(), RenderSystem.getProjectionMatrix(), GameRenderer.getPositionColorShader());

        VertexBuffer.unbind();

        stack.pop();

        RenderSystem.disableBlend();
        RenderSystem.enableCull();
        RenderSystem.depthFunc(GL11.GL_LEQUAL);
    }

    public void wipe() {
        this.bodies.clear();
    }

}
