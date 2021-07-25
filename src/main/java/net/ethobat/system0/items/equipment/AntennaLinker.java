package net.ethobat.system0.items.equipment;

import net.ethobat.system0.System0;
import net.ethobat.system0.api.util.MessageHandler;
import net.ethobat.system0.api.network.IReceiverAntenna;
import net.ethobat.system0.api.network.ITransmitterAntenna;
import net.ethobat.system0.auxiliary.S0Item;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

public class AntennaLinker extends S0Item {

    private ITransmitterAntenna transmitter;

    public AntennaLinker() {
        super(new Settings().group(System0.ITEM_GROUP_TOOLS).maxCount(1), "antenna_linker");
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext ctx) {
        World world = ctx.getWorld();
        BlockEntity be = world.getBlockEntity(ctx.getBlockPos());
        if (be instanceof ITransmitterAntenna && this.transmitter == null) {
            this.transmitter = (ITransmitterAntenna) be;
            MessageHandler.say("Transmitter selected", ctx.getPlayer());
        } else if (be instanceof IReceiverAntenna && this.transmitter != null && this.transmitter.attemptConnection((IReceiverAntenna) be)) {
            this.transmitter = null;
            MessageHandler.say("Connection established!", ctx.getPlayer());
        }
        return ActionResult.PASS;
    }

}
