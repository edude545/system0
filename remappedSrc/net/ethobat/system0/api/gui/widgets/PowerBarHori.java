package net.ethobat.system0.api.gui.widgets;

import net.ethobat.system0.api.gui.WidgetedScreen;
import net.ethobat.system0.api.rendering.S0Renderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

public abstract class PowerBarHori extends PowerBar {

    public PowerBarHori(String name, int x, int y) {
        super(name, x, y);
    }

    @Override
    public void draw(WidgetedScreen<?> screen, MatrixStack matrices, int mouseX, int mouseY) {
        S0Renderer.prepareTexture(this.getTextureEmpty());
        DrawableHelper.drawTexture(matrices, this.screenX+screen.getX(), this.screenY+screen.getY(), 0, 0, this.getWidth(), this.getHeight(), this.getWidth(), this.getHeight());
        S0Renderer.prepareTexture(this.getTextureFull());
        int fillDist = this.getRoundedFillAmount(this.getWidth());
        int gapDist = this.getWidth() - fillDist;
        DrawableHelper.drawTexture(matrices, this.screenX+screen.getX()+gapDist, this.screenY+screen.getY(), 0, gapDist, fillDist, this.getHeight(), this.getWidth(), this.getHeight());
        this.highlightIfMouseover(screen, matrices, mouseX, mouseY);
    }

}
