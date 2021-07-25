package net.ethobat.system0.auxiliary;

import net.ethobat.system0.System0;
import net.ethobat.system0.auxiliary.S0Item;
import net.minecraft.item.Item;

public class MaterialItem extends S0Item {

    public MaterialItem(String registryName) {
        this(registryName, new Item.Settings());
    }

    public MaterialItem(String registryName, Settings settings) {
        super(settings.group(System0.ITEM_GROUP_CRAFTING), registryName);
    }
}
