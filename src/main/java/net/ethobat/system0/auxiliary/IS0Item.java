package net.ethobat.system0.auxiliary;

import net.minecraft.item.Item;

public interface IS0Item {

    void giveTooltip();

    static Item.Settings s() {
        return new Item.Settings();
    }

}
