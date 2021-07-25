package net.ethobat.system0.api.item;

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
        return S0Registry.ENERGY_TYPE.get(
                new Identifier(stack.getTag().getCompound("system0").getString("energyType"))
        )
                .getPrimaryColor().asInt();
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        return super.getItemBarStep(stack);
    }

}
