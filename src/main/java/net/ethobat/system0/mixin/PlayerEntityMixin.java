package net.ethobat.system0.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    @Shadow private ItemStack selectedItem;

    @Shadow public abstract void playSound(SoundEvent event, SoundCategory category, float volume, float pitch);

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("HEAD"), method = "jump()V")
    public void jump(CallbackInfo info) {
//        System.out.println("Player jumped!");
//        System.out.println(this.selectedItem);
//        this.playSound(SoundEvents.BLOCK_ANVIL_PLACE, 0.5F, 1.5F * System0.random.nextFloat());
    }

}
