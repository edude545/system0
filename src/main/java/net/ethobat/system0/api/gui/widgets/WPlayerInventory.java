package net.ethobat.system0.api.gui.widgets;

import net.ethobat.system0.api.gui.WidgetedScreen;
import net.ethobat.system0.api.gui.WidgetedScreenHandler;
import net.ethobat.system0.api.rendering.S0Drawing;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.inventory.Inventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;

public class WPlayerInventory extends GUIWidget {

    private WSlotGrid main;
    private WSlotGrid hotbar;

    public WPlayerInventory(String name, int x, int y, Inventory inv) {
        super(name, x, y);
        this.main = new WSlotGrid("main", this.screenX, this.screenY+69, 9, inv, 0, 9);
        this.hotbar = new WSlotGrid("hotbar", this.screenX, this.screenY+11, 9, inv, 9, 27);
    }

    @Override
    public void draw(WidgetedScreen<?> screen, MatrixStack matrices, int mouseX, int mouseY) {
        this.main.draw(screen, matrices, mouseX, mouseY);
        this.hotbar.draw(screen, matrices, mouseX, mouseY);
        S0Drawing.drawText(matrices, Text.translatable("container.inventory"), this.screenX+screen.getX()+2, this.screenY+screen.getY());
    }

    // Inventory-based widgets don't need to be synced between server & client.
    @Override
    public void updateFromNBT(NbtCompound nbt) {}

    @Override
    public void onAdd(WidgetedScreenHandler handler) {
        super.onAdd(handler);
        this.main.onAdd(handler);
        this.hotbar.onAdd(handler);
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }


}
