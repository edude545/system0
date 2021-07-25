package net.ethobat.system0.registry;

import net.ethobat.system0.api.item.CapacitorItem;
import net.ethobat.system0.auxiliary.S0ArmorItem;
import net.ethobat.system0.auxiliary.S0CapacitorItem;
import net.ethobat.system0.items.equipment.*;
import net.ethobat.system0.auxiliary.MaterialItem;
import net.ethobat.system0.auxiliary.S0Item;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;

public final class S0Items {

    // Armor
    public static final S0ArmorItem INDIGONITE_HELMET = new S0ArmorItem(S0ArmorMaterials.INDIGONITE, EquipmentSlot.HEAD, "indigonite_helmet");
    public static final S0ArmorItem INDIGONITE_CHESTPLATE = new S0ArmorItem(S0ArmorMaterials.INDIGONITE, EquipmentSlot.CHEST, "indigonite_chestplate");
    public static final S0ArmorItem INDIGONITE_LEGGINGS = new S0ArmorItem(S0ArmorMaterials.INDIGONITE, EquipmentSlot.LEGS, "indigonite_leggings");
    public static final S0ArmorItem INDIGONITE_BOOTS = new S0ArmorItem(S0ArmorMaterials.INDIGONITE, EquipmentSlot.FEET, "indigonite_boots");

    // Crafting
    public static final S0Item INDIGONITE_COMPOSITE = new MaterialItem("indigonite_composite");
    public static final S0Item GLAUCOUS_GOURD_SLICE = new MaterialItem("glaucous_gourd_slice");
    public static final S0Item SYMVINYL_SHEET = new MaterialItem("symvinyl_sheet");
    public static final S0Item PARA_TRILLIONS = new MaterialItem("para_trillions");
    public static final S0Item NEXXINE_SYMBOL = new MaterialItem("nexxine_symbol");
    public static final MaterialItem FLASHTIN_ANTENNA = new MaterialItem("flashtin_antenna");

    // Decoration

    // Networks

    // Psionics

    // Tools
    public static final Clanger CLANGER = new Clanger();
    public static final DebugTool DEBUG_TOOL = new DebugTool();
    public static final AntennaLinker ANTENNA_LINKER = new AntennaLinker();
    public static final VeinScouter VEIN_SCOUTER = new VeinScouter();
    public static final NetworkPDA NETWORK_PDA = new NetworkPDA();

    // Components
    public static final CapacitorItem ORGIMEX_DUAL_CELL = new S0CapacitorItem(240_000);

    public static Item.Settings s() {
        return new Item.Settings();
    }

    static {
        INDIGONITE_COMPOSITE.giveTooltip();
        GLAUCOUS_GOURD_SLICE.giveTooltip();
        FLASHTIN_ANTENNA.giveTooltip();

        S0Registrar.register(ORGIMEX_DUAL_CELL, "orgimex_dual_cell");
    }

    public static void init() {

    }

}
