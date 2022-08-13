package net.ethobat.system0.auxiliary;

import net.ethobat.system0.api.gui.WidgetedScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import org.jetbrains.annotations.Nullable;

// ScreenHandlers exist to sync the GUI between the client and server. Has stuff like interactive slots/buttons.
public abstract class S0ScreenHandler extends WidgetedScreenHandler {

    public S0ScreenHandler(@Nullable ScreenHandlerType<?> type, int syncID) {
        super(type, syncID);
    }

//    public S0ScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
//        super(syncId, playerInventory, buf);
//    }

    public abstract Inventory getInventory();

    // Shift + Player Inv Slot
    @Override
    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.getInventory().size()) {
                if (!this.insertItem(originalStack, this.getInventory().size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.getInventory().size(), false)) {
                return ItemStack.EMPTY;
            }
            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        return newStack;
    }

    protected void placePlayerInvSlots(PlayerInventory playerInv, int sx, int sy) {
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.addSlot(new Slot(playerInv, x + y * 9 + 9, (x * 18)+sx, (y * 18)+sy));
            }
        }
    }

    protected void placePlayerHotbarSlots(PlayerInventory playerInv, int sx, int sy) {
        for (int x = 0; x < 9; ++x) {
            this.addSlot(new Slot(playerInv, x, (x * 18)+sx, sy));
        }
    }

    protected void place3x3GridSlots(Inventory inv, int sx, int sy) {
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 3; ++x) {
                this.addSlot(new Slot(inv, x + y * 3, (x * 18)+sx, (y * 18)+sy));
            }
        }
    }

}
