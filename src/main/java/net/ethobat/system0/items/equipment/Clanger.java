package net.ethobat.system0.items.equipment;

import net.ethobat.system0.System0;
import net.ethobat.system0.auxiliary.S0Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class Clanger extends S0Item {

    public Clanger() {
        super(new Settings().group(System0.ITEM_GROUP_TOOLS).maxCount(1), "clanger");
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity pe, Hand hand) {
        pe.playSound(SoundEvents.BLOCK_ANVIL_PLACE, 1.0F, 1.5F * System0.random.nextFloat());
        return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, pe.getStackInHand(hand));
    }

}
