package net.ethobat.system0.api.gui.widgets;

import net.ethobat.system0.api.gui.WidgetedScreen;
import net.ethobat.system0.api.gui.WidgetedScreenHandler;
import net.ethobat.system0.api.rendering.S0Drawing;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.nbt.NbtCompound;

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
        S0Drawing.fillRectangle(matrices, screen.getX() + this.screenX + 1, screen.getY() + this.screenY + 1, this.getWidth() - 1, this.getHeight() - 1, -2130706433);
    }

    private void drawBevel(WidgetedScreen<?> screen, MatrixStack matrices, boolean inward) {
        int sx = this.screenX+screen.getX()-1;
        int sy = this.screenY+screen.getY()-1;
        int ex = sx+this.getWidth()+2;
        int ey = sy+this.getHeight()+2;
        int width = this.getWidth()+2;
        int height = this.getHeight()+2;
        int inner = inward ? 0xFF373737 : 0xFFFFFFFF;
        int mid = 0xFF757475;
        int outer = inward ? 0xFFFFFFFF : 0xFF373737;
        S0Drawing.fillRectangle(matrices, sx, sy, width-1, 1, inner);
        S0Drawing.fillRectangle(matrices, sx, sy, 1, height-1, inner);
        S0Drawing.fillRectangle(matrices, sx+width-1, sy, 1, 1, mid);
        S0Drawing.fillRectangle(matrices, sx, sy+height-1, 1, 1, mid);
        S0Drawing.fillRectangle(matrices, sx+1, sy+height-1, width-1, 1, outer);
        S0Drawing.fillRectangle(matrices, sx+width-1, sy+1, 1, height-1, outer);
    }

    public void drawInwardBevel(WidgetedScreen<?> screen, MatrixStack matrices) {
        this.drawBevel(screen, matrices, true);
    }

    public void drawOutwardBevel(WidgetedScreen<?> screen, MatrixStack matrices) {
        this.drawBevel(screen, matrices, false);
    }

    // GUIWidgets should have a method like this, that writes widget NBT under the given name.
    // This is called on the server, and the clientside widget is updated by GUIWidget#updateFromNBT.
//    public static void writeNBT(NbtCompound nbt, String name, other args...) {
//        NbtCompound widgetNBT = new NbtCompound();
//        // ...
//        NBTHandler.writeWidgetNBT(nbt, name, widgetNBT);
//    }

    public abstract void updateFromNBT(NbtCompound nbt);

    public void onAdd(WidgetedScreenHandler handler) {

    }

    public abstract int getWidth();
    public abstract int getHeight();

}