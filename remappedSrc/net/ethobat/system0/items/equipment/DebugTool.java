package net.ethobat.system0.items.equipment;

import net.ethobat.system0.System0;
import net.ethobat.system0.api.util.MessageHandler;
import net.ethobat.system0.auxiliary.S0Item;
import net.ethobat.system0.machinery.DebugMachine;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class DebugTool extends S0Item {

    private static PlayerEntity player;

    public DebugTool() {
        super(new Settings().group(System0.ITEM_GROUP_TOOLS).maxCount(1), "debug_tool");
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (!user.world.isClient()) {
            MessageHandler.say(entity.getName() + " @ " + entity.getBlockPos().toShortString(), user);
            MessageHandler.say("Health: "+ entity.getHealth(), user);
            MessageHandler.say("Armor: "+ entity.getArmor() + "/" + entity.getMaxHealth(), user);
            //MessageHandler.say("Attributes: "+entity.getAttributes().toString(), user);
        }
        return super.useOnEntity(stack, user, entity, hand);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        player = context.getPlayer();
//        if (world.isClient()) {
//            PlayerEntity pe = context.getPlayer();
//            if (pe == null) {return ActionResult.PASS;}
//            Block block = world.getBlockState(context.getBlockPos()).getBlock();
//            MessageHandler.displayActionBarMessage(block.getName(), pe);
//            if (block instanceof IS0Block) {
//                //MessageHandler.displayActionBarMessage(block.type, pe);
//                MessageHandler.displayActionBarMessage("This is an S0 block!", pe);
//            }
//        }
        DebugMachine.BE be = (DebugMachine.BE) world.getBlockEntity(context.getBlockPos());
        if (be != null) {
            say("Server?: "+String.valueOf(!world.isClient())+"\nPower: "+String.valueOf(be.skeinPower.amt)+"\n"+String.valueOf(context.getWorld().getTime()));
        }
        return ActionResult.PASS;
    }

    private void say(String msg) {
        MessageHandler.say(msg, player);
    }

}
