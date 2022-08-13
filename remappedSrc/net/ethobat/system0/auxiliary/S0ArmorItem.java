package net.ethobat.system0.auxiliary;

import net.ethobat.system0.System0;
import net.ethobat.system0.registry.S0Registrar;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;

public class S0ArmorItem extends ArmorItem implements IS0Item {

    public final String NAME;
    public boolean HAS_TOOLTIP = false;

    public S0ArmorItem(ArmorMaterial material, EquipmentSlot slot, String registryName) {
        this(material, slot, IS0Item.s(), registryName);
    }

    public S0ArmorItem(ArmorMaterial material, EquipmentSlot slot, Settings settings, String registryName) {
        super(material, slot, settings.group(System0.ITEM_GROUP_ARMOR).maxCount(1));
        this.NAME = registryName;
        S0Registrar.register(this, registryName);
    }

    @Override
    public void giveTooltip() {
        this.HAS_TOOLTIP = true;
    }
}
