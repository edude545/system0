package net.ethobat.system0.api.item;

import net.ethobat.system0.api.color.RGB;
import net.ethobat.system0.api.energy.IItemEnergyStore;
import net.ethobat.system0.api.nbt.ItemNBTHandler;
import net.ethobat.system0.api.registry.S0Registry;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class CapacitorItem extends ComponentItem implements IItemEnergyStore {

    public final long maxAmount;

    public CapacitorItem(Settings settings, long maxAmount) {
        super(settings);
        this.maxAmount = maxAmount;
    }

    public long getAmount(ItemStack stack) {
        if (stack.hasTag()) {
            return ItemNBTHandler.getEnergy(stack.getTag());
        } else {
            return 0;
        }
    }

    @Override
    public long getMaxAmount() {
        return this.maxAmount;
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        if (stack.hasTag()) {
            return S0Registry.ENERGY_TYPE.get(new Identifier(stack.getTag().getCompound("system0").getString("energyType")))
                    .getPrimaryColor().asInt();
        } else {
            //return new RGB(255,255,255).asInt();
            return RGB.getNewRandomColor().asInt();
        }
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        //Math.min(1 + 12 * getBundleOccupancy(stack) / 64, 13);
        return 12;
    }

}
