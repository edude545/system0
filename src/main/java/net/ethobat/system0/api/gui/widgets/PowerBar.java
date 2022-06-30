package net.ethobat.system0.api.gui.widgets;

import net.ethobat.system0.api.energy.EnergyType;
import net.ethobat.system0.api.energy.IEnergyContainer;
import net.ethobat.system0.api.gui.WidgetedScreen;
import net.ethobat.system0.api.nbt.NBTHandler;
import net.ethobat.system0.api.registry.S0Registry;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.List;

public abstract class PowerBar extends GUIWidget {

    public long amount;
    public long maxAmount;
    public EnergyType energyType;

    public PowerBar(String name, int x, int y) {
        super(name, x, y);
        this.amount = 0;
        this.maxAmount = 0;
        this.energyType = null;
    }

    public abstract Identifier getTextureEmpty();
    public abstract Identifier getTextureFull();

    public int getRoundedFillAmount(int length) {
        return (int) Math.ceil((this.amount / (float) this.maxAmount) * length);
    }

    public static void writeNBT(NbtCompound nbt, String name, IEnergyContainer container) {
        NbtCompound widgetNBT = new NbtCompound();
        widgetNBT.putLong("amount", container.getAmount());
        widgetNBT.putLong("maxAmount", container.getMaxAmount());
        EnergyType et = container.getEnergyType();
        widgetNBT.putString("energyType", et==null ? "" : et.getRegistryID().toString());
        NBTHandler.writeWidgetNBT(nbt, name, widgetNBT);
    }

    @Override
    public void updateFromNBT(NbtCompound nbt) {
        this.amount = nbt.getLong("amount");
        this.maxAmount = nbt.getLong("maxAmount");
        String str = nbt.getString("energyType");
        this.energyType = str.equals("") ? null : S0Registry.ENERGY_TYPE.get(str);
    }

    @Override
    public void drawMouseoverTooltip(MatrixStack matrices, WidgetedScreen<?> screen, int mouseX, int mouseY) {
        super.drawMouseoverTooltip(matrices, screen, mouseX, mouseY);
        screen.renderTooltip(matrices,
                List.of(
                        this.energyType.getTranslatedName().append(" Energy").formatted(Formatting.GOLD, Formatting.BOLD),//.formatted(this.energyType.FORMAT_COLOR_CODE, Formatting.BOLD),
                        new LiteralText("- - - - - - - - - - -").formatted(Formatting.DARK_GRAY),
                        new LiteralText(String.valueOf(this.amount)),
                        new LiteralText("---"),
                        new LiteralText(String.valueOf(this.maxAmount))
                ), mouseX, mouseY);
    }

}
