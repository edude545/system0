package net.ethobat.system0.mixin;

import net.minecraft.item.BundleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BundleItem.class)
public class debugmixin {

    private static int n = 0;

    @Inject(at = @At("TAIL"), method = "<init>")
    public void BundleItem(Item.Settings settings, CallbackInfo ci) {
        System.out.println("Registered bundle item " + String.valueOf(n));
        new Exception("BundleItem registered!").printStackTrace();
        n++;
    }

    @Inject(at = @At("TAIL"), method = "isItemBarVisible")
    public void isItemBarVisible(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        new Exception("Here!").printStackTrace();
    }

}