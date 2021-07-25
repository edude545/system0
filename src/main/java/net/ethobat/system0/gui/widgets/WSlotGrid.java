package net.ethobat.system0.gui.widgets;

import net.ethobat.system0.api.gui.widgets.GUIWidget;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.inventory.Inventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtLong;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.HashSet;

public class WSlotGrid extends GUIWidget {

    public static final int SLOT_GAP = 2;
    public static final int PART_SLOT_GAP = 1;
    public static final int SLOT_SIZE = 16;

    public HashSet<Slot> slots;
    public BlockEntity be;
    public Inventory inv;

    WSlotGrid(int screenX, int screenY, BlockEntity be, Inventory inv, int columns) {
        super(screenX, screenY);
        this.slots = new HashSet<>();
        this.be = be;
        this.inv = inv;
        int px = PART_SLOT_GAP;
        int py = PART_SLOT_GAP;
        int curColumn = 0;
        for (int index = 0; index < inv.size(); index++) {
            this.slots.add(new Slot(screenX+px, screenY+py, index, this));
            if (curColumn+1 == columns) {
                curColumn = 0;
                px = PART_SLOT_GAP;
                py += SLOT_SIZE + SLOT_GAP;
            } else {
                px += SLOT_SIZE + SLOT_GAP;
            }
            curColumn += 1;
        }
    }

    @Override
    public void draw(MatrixStack matrices) {
        for (Slot slot : this.slots) {
            slot.draw(matrices);
        }
    }

    @Override
    public NbtCompound writeToNBT(NbtCompound nbt) {
        super.writeToNBT(nbt);
        nbt.put("blockEntityPos", NbtLong.of(this.be.getPos().asLong()));
        return nbt;
    }

    @Override
    public void updateFromNBT(NbtCompound nbt) {
        super.updateFromNBT(nbt);
        this.be = MinecraftClient.getInstance().world.getBlockEntity(BlockPos.fromLong(nbt.getLong("blockEntityPos")));
        this.inv = (Inventory) be;
    }

    public static class Slot {

        public int screenX;
        public int screenY;
        public int index;
        public WSlotGrid slotGrid;

        Slot(int screenX, int screenY, int index, WSlotGrid slotGrid) {
            this.screenX = screenX;
            this.screenY = screenY;
            this.index = index;
            this.slotGrid = slotGrid;
        }

        public void draw(MatrixStack matrices) {
            prepareTexture(this.getTexture());
            slotGrid.drawTexture(matrices, this.screenX, this.screenY, 0, 0, 18, 18);
        }

        public Identifier getTexture() {
            return new Identifier("system0", "textures/gui/slot.png");
        }

    }

}
