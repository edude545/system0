package net.ethobat.system0.mixin;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.mob.ShulkerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ShulkerEntity.class)
public interface ShulkerEntityMixin {

    // Used in Crowbar
    @Accessor("COVERED_ARMOR_BONUS")
     EntityAttributeModifier getCoveredArmorBonus();

}
