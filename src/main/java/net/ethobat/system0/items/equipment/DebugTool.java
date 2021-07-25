package net.ethobat.system0.items.equipment;

import net.ethobat.system0.System0;
import net.ethobat.system0.api.util.MessageHandler;
import net.ethobat.system0.auxiliary.IS0Block;
import net.ethobat.system0.auxiliary.S0Block;
import net.ethobat.system0.auxiliary.S0Item;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

public class DebugTool extends S0Item {

    public DebugTool() {
        super(new Settings().group(System0.ITEM_GROUP_TOOLS).maxCount(1), "debug_tool");
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        if (world.isClient()) {
            PlayerEntity pe = context.getPlayer();
            if (pe == null) {return ActionResult.PASS;}
            Block block = world.getBlockState(context.getBlockPos()).getBlock();
            MessageHandler.displayActionBarMessage(block.getName(), pe);
            if (block instanceof IS0Block) {
                //MessageHandler.displayActionBarMessage(block.type, pe);
                MessageHandler.displayActionBarMessage(new LiteralText("This is an S0 block!"), pe);
            }
        }
        return ActionResult.PASS;
    }

}
