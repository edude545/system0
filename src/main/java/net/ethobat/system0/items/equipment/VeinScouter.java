package net.ethobat.system0.items.equipment;

import net.ethobat.system0.System0;
import net.ethobat.system0.auxiliary.S0Item;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Wearable;

public class VeinScouter extends S0Item implements Wearable {

    public VeinScouter() {
        super(new Item.Settings().group(System0.ITEM_GROUP_TOOLS).maxCount(1), "vein_scouter");
    }

    public EquipmentSlot getSlotType() {
        return EquipmentSlot.HEAD;
    }

}
