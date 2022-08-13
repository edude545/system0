package net.ethobat.system0.mixin;

import net.ethobat.system0.items.equipment.Mitten;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    // getStackInHand returns empty if the player is holding a mitten, simulating interacting with blocks with an empty hand.
    @Inject(method = "getStackInHand", at=@At("RETURN"), cancellable = true)
    public void getStackInHand(Hand hand, CallbackInfoReturnable<ItemStack> cir) {
        if (cir.getReturnValue().getItem() instanceof Mitten) {
            cir.setReturnValue(ItemStack.EMPTY);
        }
    }

}
