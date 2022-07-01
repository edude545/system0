package net.ethobat.system0.api.gui.widgets;

import com.mojang.blaze3d.systems.RenderSystem;
import net.ethobat.system0.api.gui.WidgetedScreen;
import net.ethobat.system0.api.gui.WidgetedScreenHandler;
import net.ethobat.system0.api.visuals.S0Renderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

// Widgets exist on the ScreenHandler. HandledScreens render from the client ScreenHandler.
public abstract class GUIWidget extends DrawableHelper {

    public String name;
    public int screenX;
    public int screenY;

    public GUIWidget(String name, int x, int y) {
        this.name = name;
        this.screenX = x;
        this.screenY = y;
    }

    public String getName() {
        return this.name;
    }

    public abstract void draw(WidgetedScreen<?> screen, MatrixStack matrices, int mouseX, int mouseY);

    public boolean isMouseOver(WidgetedScreen<?> screen, int mouseX, int mouseY) {
        return screen.isPointWithinBounds(this.screenX, this.screenY, this.getWidth(), this.getHeight(), mouseX, mouseY);
    }
    public void drawMouseoverTooltip(MatrixStack matrices, WidgetedScreen<?> screen, int mouseX, int mouseY) {}

    public void highlightIfMouseover(WidgetedScreen<?> screen, MatrixStack matrices, int mouseX, int mouseY) {
        if (screen.isPointWithinBounds(this.screenX, this.screenY, this.getWidth(), this.getHeight(), mouseX, mouseY)) {
            this.highlight(screen, matrices);
        }
    }
    public void highlight(WidgetedScreen<?> screen, MatrixStack matrices) {
        S0Renderer.drawGradient(matrices, screen.getX() + this.screenX + 1, screen.getY() + this.screenY + 1, this.getWidth() - 1, this.getHeight() - 1, -2130706433);
    }

    public abstract void updateFromNBT(NbtCompound nbt);

    public void onAdd(WidgetedScreenHandler handler) {

    }

    public abstract int getWidth();
    public abstract int getHeight();

}