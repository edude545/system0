package net.ethobat.system0.auxiliary;

import net.ethobat.system0.System0;
import net.ethobat.system0.api.item.CapacitorItem;

public class S0CapacitorItem extends CapacitorItem implements IS0Item {

    public S0CapacitorItem(Settings settings, long maxAmount) {
        super(settings.group(System0.ITEM_GROUP_COMPONENTS).maxCount(1), maxAmount);
    }

    public S0CapacitorItem(long maxAmount) {
        this(IS0Item.s(), maxAmount);
    }

    @Override
    public void giveTooltip() {
        // TODO
    }

}
