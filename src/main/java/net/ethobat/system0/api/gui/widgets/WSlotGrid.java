package net.ethobat.system0.api.gui.widgets;

import net.ethobat.system0.api.gui.WidgetedScreen;
import net.ethobat.system0.api.gui.WidgetedScreenHandler;
import net.ethobat.system0.api.visuals.S0Renderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.inventory.Inventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.Identifier;

import java.util.HashSet;

public class WSlotGrid extends GUIWidget {

    public static final int SLOT_GAP = 2;
    public static final int PART_SLOT_GAP = 1;
    public static final int SLOT_SIZE = 16;

    public int startIndex;
    public int size;

    public Inventory inv;

    public SGSlot[] slots;

    // index is the index of the first slot, size is the number of slots in the slotgrid
    public WSlotGrid(String name, int screenX, int screenY, int columns, Inventory inv, int startIndex, int size) {
        super(name, screenX, screenY);
        this.inv = inv;
        this.slots = new SGSlot[size];
        this.startIndex = startIndex;
        this.size = size;
        int cx = 0;
        int cy = 0;
        int curColumn = 0;
        for (int i = this.startIndex; i < this.startIndex+size; i++) {
            this.slots[i-this.startIndex] = new SGSlot(screenX+cx*SLOT_SIZE+cx*SLOT_GAP, screenY+cy*SLOT_SIZE+cy*SLOT_GAP, i, this);
            curColumn += 1;
            if (curColumn == columns) {
                curColumn = 0;
                cx = 0;
                cy += 1;
            } else {
                cx += 1;
            }
        }
    }

    public WSlotGrid(String name, int screenX, int screenY, int columns, Inventory inv) {
        this(name, screenX, screenY, columns, inv, 0, inv.size());
    }

    @Override
    public void draw(WidgetedScreen<?> screen, MatrixStack matrices, int mouseX, int mouseY) {
        S0Renderer.prepareTexture(SGSlot.texture);
        for (SGSlot slot : this.slots) {
            slot.draw(screen, matrices);
        }
    }

    // Inventory-based widgets don't need to be synced between server & client.
    @Override
    public void updateFromNBT(NbtCompound nbt) {}

    @Override
    public void onAdd(WidgetedScreenHandler handler) {
        super.onAdd(handler);
        for (SGSlot slot : this.slots) {
            handler.addSlot(slot.asSlot());
        }
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    public static class SGSlot {

        // These coordinates are assumed to be bounded by the HandledScreen's backgroundWidth and backgroundHeight.
        public int screenX;
        public int screenY;
        public int index;
        public WSlotGrid slotGrid;

        public static Identifier texture = new Identifier("system0", "textures/gui/widget/slot.png");

        SGSlot(int screenX, int screenY, int index, WSlotGrid slotGrid) {
            this.screenX = screenX;
            this.screenY = screenY;
            this.index = index;
            this.slotGrid = slotGrid;
        }

        public void draw(WidgetedScreen<?> screen, MatrixStack matrices) {
            DrawableHelper.drawTexture(matrices, this.screenX+screen.getX(), this.screenY+screen.getY(), 0, 0, 18, 18, 18, 18);
        }

        public Slot asSlot() {
            return new Slot(this.slotGrid.inv, this.index, this.screenX+1, this.screenY+1);
        }

    }

}
