package net.ethobat.system0.api.gui.widgets;

import net.ethobat.system0.api.gui.WidgetedScreen;
import net.ethobat.system0.api.visuals.S0Renderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

public abstract class PowerBarVert extends PowerBar {

    public PowerBarVert(String name, int x, int y) {
        super(name, x, y);
    }

    @Override
    public void draw(WidgetedScreen<?> screen, MatrixStack matrices, int mouseX, int mouseY) {
        S0Renderer.prepareTexture(this.getTextureEmpty());
        DrawableHelper.drawTexture(matrices, this.screenX+screen.getX(), this.screenY+screen.getY(), 0f, 0f, this.getWidth(), this.getHeight(), this.getWidth(), this.getHeight());
        S0Renderer.prepareTexture(this.getTextureFull());
        int fillHeight = this.getRoundedFillAmount(this.getHeight());
        int gapHeight = this.getHeight() - fillHeight;
        DrawableHelper.drawTexture(matrices, this.screenX+screen.getX(), this.screenY+screen.getY()+gapHeight, 0f, gapHeight, this.getWidth(), fillHeight, this.getWidth(), this.getHeight());
        this.highlightIfMouseover(screen, matrices, mouseX, mouseY);
    }

}
