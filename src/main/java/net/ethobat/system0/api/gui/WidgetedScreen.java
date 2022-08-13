package net.ethobat.system0.api.gui;

import net.ethobat.system0.api.gui.widgets.GUIWidget;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;

public abstract class WidgetedScreen<SH extends WidgetedScreenHandler> extends HandledScreen<SH> {

    public WidgetedScreen(SH handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    public void drawWidgets(MatrixStack matrices, int mouseX, int mouseY) {
        for (GUIWidget widget : this.getScreenHandler().getWidgets()) {
            widget.draw(this, matrices, mouseX, mouseY);
        }
    }

    public void drawWidgetTooltips(MatrixStack matrices, int mouseX, int mouseY) {
        for (GUIWidget widget : this.getScreenHandler().getWidgets()) {
            if (widget.isMouseOver(this, mouseX, mouseY)) {
                widget.drawMouseoverTooltip(matrices, this, mouseX, mouseY);
            }
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        super.render(matrices, mouseX, mouseY, delta);
    }

    // Access widener
    public boolean isPointWithinBounds(int x, int y, int width, int height, double pointX, double pointY) {
        return super.isPointWithinBounds(x, y, width, height, pointX, pointY);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        //this.getScreenHandler().mouseClicked(this, mouseX, mouseY, MouseButton.fromInt(i));
        GUINetworkingHandler.sendMouseInteractPacket(mouseX, mouseY, mouseButton, false);
        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int mouseButton) {
        //this.getScreenHandler().mouseReleased(this, mouseX, mouseY, MouseButton.fromInt(i));#
        GUINetworkingHandler.sendMouseInteractPacket(mouseX, mouseY, mouseButton, true);
        return super.mouseReleased(mouseX, mouseY, mouseButton);
    }

    // Position of WidgetedScreen on the display
    public int getX() { return this.x; }
    public int getY() { return this.y; }

}
