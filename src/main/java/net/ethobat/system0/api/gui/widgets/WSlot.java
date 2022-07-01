package net.ethobat.system0.api.gui.widgets;

import net.ethobat.system0.api.gui.WidgetedScreen;
import net.ethobat.system0.api.gui.WidgetedScreenHandler;
import net.ethobat.system0.api.visuals.S0Renderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.Identifier;

public class WSlot extends GUIWidget {

    public int index;

    public Inventory inv;
    public boolean isOutput = false;

    public WSlot(String name, int screenX, int screenY, Inventory inv, int index) {
        super(name, screenX, screenY);
        this.inv = inv;
        this.index = index;
    }

    @Override
    public void onAdd(WidgetedScreenHandler handler) {
        super.onAdd(handler);
        handler.addSlot(this.asSlot());
    }

    @Override
    public int getWidth() {
        return 18;
    }

    @Override
    public int getHeight() {
        return 18;
    }

    @Override
    public void draw(WidgetedScreen<?> screen, MatrixStack matrices, int mouseX, int mouseY) {
        S0Renderer.prepareTexture(this.getTexture());
        DrawableHelper.drawTexture(matrices, this.screenX+screen.getX(), this.screenY+screen.getY(), 0, 0, 18, 18, 18, 18);
    }

    // Inventory-based widgets don't need to be synced between server & client.
    @Override
    public void updateFromNBT(NbtCompound nbt) {}

    public WSlot setOutput() {
        this.isOutput = true;
        return this;
    }

    public Identifier getTexture() {
        return new Identifier("system0", "textures/gui/widget/slot.png");
    }

    public Slot asSlot() {
        if (this.isOutput) {
            return new OutputSlot(this.inv, this.index, this.screenX+1, this.screenY+1);
        } else {
            return new Slot(this.inv, this.index, this.screenX+1, this.screenY+1);
        }
    }

    static class OutputSlot extends Slot {

        public OutputSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        @Override
        public boolean canInsert(ItemStack stack) {
            return false;
        }

    }

}
