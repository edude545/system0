package net.ethobat.system0.api.rendering;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class S0Renderer extends DrawableHelper {

    public static void prepareTexture(Identifier textureID) {
        //RenderSystem.setShader(GameRenderer::getPositionTexShader); // This is already present in DrawableHelper#drawTexturedQuad
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, textureID);
    }

    public static void drawGradient(MatrixStack matrices, int x, int y, int width, int height, int color) {
        RenderSystem.disableDepthTest();
        RenderSystem.colorMask(true, true, true, false);
        DrawableHelper.fill(matrices, x, y, x + width, y + height, color);
        RenderSystem.colorMask(true, true, true, true);
        RenderSystem.enableDepthTest();
    }

}
