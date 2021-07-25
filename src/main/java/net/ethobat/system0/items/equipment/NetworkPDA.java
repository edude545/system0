package net.ethobat.system0.items.equipment;

import net.ethobat.system0.System0;
import net.ethobat.system0.auxiliary.S0Item;

public class NetworkPDA extends S0Item {

    public NetworkPDA() {
        super(new Settings().group(System0.ITEM_GROUP_TOOLS).maxCount(1), "network_pda");
    }

}
