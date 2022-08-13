package net.ethobat.system0.items.equipment;

import com.google.common.collect.Multimap;
import net.ethobat.system0.System0;
import net.ethobat.system0.api.util.MessageHandler;
import net.ethobat.system0.auxiliary.S0Item;
import net.ethobat.system0.mixin.ShulkerEntityMixin;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.event.GameEvent;

public class Crowbar extends S0ToolItem {

    public Crowbar() {
        super(new Settings().maxCount(1).group(System0.ITEM_GROUP_TOOLS), "crowbar", new ToolMaterial());
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return super.getAttributeModifiers(slot);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (entity instanceof ShulkerEntity) {
            NbtCompound nbt = new NbtCompound();
            //entity.writeCustomDataToNbt(nbt);
            //MessageHandler.displayActionBarMessage(String.valueOf(nbt.getByte("Peek")), user);

            nbt.putByte("Peek", (byte) 30);
            entity.readCustomDataFromNbt(nbt);

            entity.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).removeModifier(((ShulkerEntityMixin)entity).getCoveredArmorBonus());
            entity.playSound(SoundEvents.BLOCK_CHEST_OPEN, 0.6F, 1.2F);
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    public static class ToolMaterial implements net.minecraft.item.ToolMaterial {

        @Override
        public int getDurability() {
            return 0;
        }

        @Override
        public float getMiningSpeedMultiplier() {
            return 0;
        }

        @Override
        public float getAttackDamage() {
            return 7;
        }

        @Override
        public int getMiningLevel() {
            return 0;
        }

        @Override
        public int getEnchantability() {
            return 0;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return null;
        }

    }

}
