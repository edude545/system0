package net.ethobat.system0.api.gui;

import net.ethobat.system0.api.gui.widgets.GUIWidget;
import net.ethobat.system0.api.registry.S0Registry;
import net.ethobat.system0.gui.widgets.WSlotGrid;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;

public abstract class WidgetedScreenHandler extends ScreenHandler {

    public final HashSet<GUIWidget> widgets = new HashSet<>();

    protected WidgetedScreenHandler(@Nullable ScreenHandlerType<?> type, int syncId) {
        super(type, syncId);
    }

    public WidgetedScreenHandler(int syncID, PlayerInventory playerInv, PacketByteBuf buf) {
        super(null, syncID);
        this.updateScreenHandlerFromNBT(buf.readNbt());
    }

    // Move to a new class outside system0.api
    protected void placeSlots() {
        for (GUIWidget widget : this.widgets) {
            if (widget instanceof WSlotGrid) {
                for (WSlotGrid.Slot slot : ((WSlotGrid) widget).slots) {
                    this.addSlot(new Slot(((WSlotGrid) widget).inv, slot.index, slot.screenX, slot.screenY));
                }
            }
        }
    }

    public NbtCompound writeToNBT(NbtCompound nbt) {
        NbtList nbtWidgetList = new NbtList();
        for (GUIWidget widget : widgets) {
            nbtWidgetList.add(widget.writeToNBT(new NbtCompound()));
        }
        nbt.put("widgets", nbtWidgetList);
        return nbt;
    }

    // { "widgets" :
    //   {
    //      "system0:slot_grid" : [{...}, {...}, {...}],
    //      "system0:power_bar_medium_vert" : [{...}]
    //   }
    // }
    public void updateScreenHandlerFromNBT(NbtCompound nbt) {
        NbtCompound nbtWidgetsCompound = nbt.getCompound("widgets");
        for (String widgetID : nbtWidgetsCompound.getKeys()) {
            for (Object nbtWidget : nbtWidgetsCompound.getList(widgetID, NbtElement.LIST_TYPE).toArray()) {
                try {
                    this.widgets.add(
                            S0Registry.WIDGETS.get(new Identifier(widgetID))
                                    .getConstructor(NbtCompound.class)
                                    .newInstance((NbtCompound) nbtWidget)
                    );
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace(); // fuuuuuuuuuuuuuck
                }
            }
        }
    }

}
