package net.ethobat.system0.api.gui.widgets;

import net.ethobat.system0.api.gui.WidgetedScreen;
import net.ethobat.system0.api.gui.WidgetedScreenHandler;
import net.ethobat.system0.api.rendering.S0Drawing;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.Identifier;

public class WSlot extends GUIWidget {

    public static final Identifier TEXTURE_NORMAL = new Identifier("system0", "textures/gui/widget/slot.png");
    public static final Identifier TEXTURE_OUTPUT = new Identifier("system0", "textures/gui/widget/slot_26.png");

    public int index;

    public Inventory inv;
    public boolean isOutput;

    public WSlot(String name, int screenX, int screenY, Inventory inv, int index) {
        super(name, screenX, screenY);
        this.inv = inv;
        this.index = index;
        this.isOutput = false;
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

        if (this.isOutput) {
            S0Drawing.prepareTexture(TEXTURE_OUTPUT);
            DrawableHelper.drawTexture(matrices, this.screenX+screen.getX()-3, this.screenY+screen.getY()-3, 0, 0, 24, 24, 24, 24);
        } else {
            S0Drawing.prepareTexture(TEXTURE_NORMAL);
            DrawableHelper.drawTexture(matrices, this.screenX+screen.getX(), this.screenY+screen.getY(), 0, 0, 18, 18, 18, 18);
        }
    }

    // Inventory-based widgets don't need to be synced between server & client.
    @Override
    public void updateFromNBT(NbtCompound nbt) {}

    public WSlot setOutput() {
        this.isOutput = true;
        return this;
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
