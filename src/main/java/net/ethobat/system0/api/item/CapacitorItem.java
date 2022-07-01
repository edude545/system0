package net.ethobat.system0.api.item;

import net.ethobat.system0.api.color.ColorGenerator;
import net.ethobat.system0.api.energy.EnergyType;
import net.ethobat.system0.api.energy.IItemEnergyContainer;
import net.ethobat.system0.api.nbt.NBTHandler;
import net.ethobat.system0.api.registry.S0Registry;
import net.ethobat.system0.registry.S0EnergyTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;

public class CapacitorItem extends ComponentItem implements IItemEnergyContainer {

    public final long maxAmount;

    public CapacitorItem(Settings settings, long maxAmount) {
        super(settings);
        this.maxAmount = maxAmount;
    }

    public static EnergyType setEnergyType(ItemStack stack, EnergyType energyType) {
        NBTHandler.putNBTForItem(stack.getOrCreateTag(), "energyType", energyType.getRegistryID().toString());
        return energyType;
    }

    public static EnergyType getEnergyType(ItemStack stack) {
        String key = NBTHandler.getString(stack.getTag(), "energyType");
        if (stack.hasTag()) {
            if (S0Registry.ENERGY_TYPE.hasKey(key)) {
                return S0Registry.ENERGY_TYPE.get(key);
            }
        }
        return null;
    }

    public static long setEnergy(ItemStack stack, long n) {
        NBTHandler.putNBT(stack.getOrCreateTag(), "energy", n);
        return n;
    }

    public static long getEnergy(ItemStack stack) {
        if (stack.hasTag()) {
            return NBTHandler.getLong(stack.getTag(), "energy");
        } else {
            return 0;
        }
    }

    public static long addEnergy(ItemStack stack, long n) {
        return setEnergy(stack, getEnergy(stack)+n);
    }

    public static float getFillRatio(ItemStack stack) {
        CapacitorItem instance = (CapacitorItem) stack.getItem();
        return getEnergy(stack) / (float) instance.getMaxEnergy();
    }

    @Override
    public long getMaxEnergy() {
        return this.maxAmount;
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        //System.out.println("Checking color...");
        if (stack.hasTag()) {
            //System.out.println("...found a color!");
            EnergyType energyType = getEnergyType(stack);
            if (energyType != null) {
                return energyType.getPrimaryColor().asInt();
            }
        }
        //System.out.println("...picking random color.");
        return ColorGenerator.getRandomSaturatedBright().asInt();
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        //return -1;
        //System.out.println(String.valueOf(getEnergy(stack)));
        return (int) Math.min(13 * getFillRatio(stack), 13);

        //return 13;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        CapacitorItem.setEnergyType(context.getStack(), S0EnergyTypes.SKEINTILLATING);
        CapacitorItem.setEnergy(context.getStack(), 170_000);
        return ActionResult.SUCCESS;
    }
}
