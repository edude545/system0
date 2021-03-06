package net.ethobat.system0.mixin;

import net.ethobat.system0.api.energy.IItemEnergyContainer;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Shadow public abstract NbtCompound getOrCreateTag();

    // "Unexpected error" here
    @Inject(at = @At("TAIL"), method = "<init>(Lnet/minecraft/item/ItemConvertible;I)V")
    public void ItemStack(ItemConvertible itemConv, int count, CallbackInfo ci) {
        if (itemConv != null) {
            if (itemConv.asItem() instanceof IItemEnergyContainer) {
//                CapacitorItem.setEnergyType((ItemStack) (Object) this, S0EnergyTypes.ANBARIC);
//                CapacitorItem.setEnergy((ItemStack) (Object) this, 170_000);
//                System.out.println("INITIALIZING!!! :)");
            }
        }
    }

}
