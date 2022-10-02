package net.ethobat.system0.api.gui;

import net.ethobat.system0.api.gui.widgets.GUIWidget;
import net.ethobat.system0.api.gui.widgets.IWClickable;
import net.ethobat.system0.api.gui.widgets.IWScrollable;
import net.ethobat.system0.api.nbt.NBTHandler;
import net.ethobat.system0.api.util.MouseButton;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;

public abstract class WidgetedScreenHandler extends ScreenHandler {

    public static final String WIDGET_NBT_KEY = "widgets";

    public final boolean isClient;
    public final HashMap<String, GUIWidget> widgets = new HashMap<>();

    // Server constructor
    public WidgetedScreenHandler(@Nullable ScreenHandlerType<?> type, int syncID, boolean isClient) {
        super(type, syncID);
        this.isClient = isClient;
    }

//    // Client constructor example - don't uncomment
//    public WidgetedScreenHandler(int syncID, PlayerInventory playerInv, PacketByteBuf buf) {
//        super(null, syncID);
//        this.updateWidgetsFromNBT(buf.readNbt());
//    }

//    // Need to move to a new class outside system0.api
//    protected void placeSlots() {
//        for (GUIWidget widget : this.widgets) {
//            if (widget instanceof WSlotGrid) {
//                for (WSlotGrid.SGSlot slot : ((WSlotGrid) widget).slots) {
//                    this.addSlot(new SGSlot(((WSlotGrid) widget).inv, slot.index, slot.screenX, slot.screenY));
//                }
//            }
//        }
//    }

    public void addWidget(GUIWidget widget) {
        this.widgets.put(widget.getName(), widget);
        widget.onAdd(this);
    }

    public GUIWidget getWidget(String name) {
        return this.widgets.get(name);
    }

    public HashMap<String,GUIWidget> getWidgetsMap() {
        return this.widgets;
    }

    public Collection<GUIWidget> getWidgets() {
        return this.widgets.values();
    }

    // workaround; ScreenHandler#addSlot is protected and can't be accessed from GUIWidget#onAdd
    @Override
    public Slot addSlot(Slot slot) {
        return super.addSlot(slot);
    }

    // Example buf widget NBT:
    // {
    //      "widgets" : {
    //          "playerHotbar" : {...},
    //          "playerInv" : {...},
    //          "machineInv" : {...},
    //          "skeintillatingPowerMain" : {...},
    //          "greenularPowerOptional" : {...}
    //      }
    // }
    public void updateWidgetsFromNBT(NbtCompound nbt) {
        nbt = nbt.getCompound(NBTHandler.WIDGET_NBT_KEY);
        for (String name : this.widgets.keySet()) {
            this.widgets.get(name).updateFromNBT(nbt.getCompound(name));
        }
    }

    public void mouseInteract(int screenPosX, int screenPosY, double mouseX, double mouseY, MouseButton button, boolean released) {
        for (GUIWidget widget : this.getWidgets()) {
            if (widget instanceof IWClickable && widget.isPointWithinBounds(screenPosX, screenPosY, (int) mouseX, (int) mouseY)) {
                ((IWClickable)widget).onMouseInteract(mouseX, mouseY, button, released);
            }
        }
    }

    public void mouseScroll(int screenPosX, int screenPosY, double mouseX, double mouseY, double direction) {
        for (GUIWidget widget : this.getWidgets()) {
            if (widget instanceof IWScrollable && widget.isPointWithinBounds(screenPosX, screenPosY, (int) mouseX, (int) mouseY)) {
                ((IWScrollable)widget).onMouseScroll(mouseX, mouseY, direction);
            }
        }
    }

    // Old code: this used reflection to access fields directly, rather than the widget map.
//    public void updateWidgetsFromBuf(PacketByteBuf buf) {
//        Field field;
//        GUIWidget fieldValue;
//        NbtCompound widgetNBT;
//        NbtCompound allWidgetNBT = buf.readNbt().getCompound(WIDGET_NBT_KEY);
//        for (String widgetName : allWidgetNBT.getKeys()) {
//            widgetNBT = (NbtCompound) allWidgetNBT.get(widgetName);
//            try {
//                field = this.getClass().getField(widgetName);
//                fieldValue = ((GUIWidget) field.get(this));
//                if (fieldValue == null) {
//                    field.set(this, field.getType().getConstructor(NbtCompound.class).newInstance(widgetNBT)); // Construct a new widget from NBT
//                } else {
//                    fieldValue.updateFromNBT(widgetNBT); // Update existing widget with NBT
//                }
//            } catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InstantiationException | InvocationTargetException e) {
//                e.printStackTrace();
//            }
//        }
//    }

}
