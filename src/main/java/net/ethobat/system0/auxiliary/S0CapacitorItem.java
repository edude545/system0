package net.ethobat.system0.auxiliary;

import net.ethobat.system0.System0;
import net.ethobat.system0.api.item.CapacitorItem;
import net.ethobat.system0.registry.S0Registrar;

public class S0CapacitorItem extends CapacitorItem implements IS0Item {

    public final String NAME;

    public S0CapacitorItem(Settings settings, String registryName, long maxAmount, long ioRate) {
        super(settings.group(System0.ITEM_GROUP_COMPONENTS).maxCount(1), maxAmount, ioRate);
        this.NAME = registryName;
        S0Registrar.register(this, registryName);
    }

    public S0CapacitorItem(String registryName, long maxAmount, long ioRate) {
        this(IS0Item.s(), registryName, maxAmount, ioRate);
    }

    @Override
    public void giveTooltip() {
        // TODO
    }

}
