package net.ethobat.system0.registry;

import net.ethobat.system0.api.item.CapacitorItem;
import net.ethobat.system0.auxiliary.S0ArmorItem;
import net.ethobat.system0.auxiliary.S0CapacitorItem;
import net.ethobat.system0.items.cards.TarotCard;
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
    public static final S0Item RAW_INDIGONITE = new MaterialItem("raw_indigonite");
    public static final S0Item INDIGO_1 = new MaterialItem("indigo_1");
    public static final S0Item INDIGO_2 = new MaterialItem("indigo_2");
    public static final S0Item INDIGO_3 = new MaterialItem("indigo_3");
    public static final S0Item INDIGO_4 = new MaterialItem("indigo_4");
    public static final S0Item INDIGO_1_SHEET = new MaterialItem("indigo_1_sheet");
    public static final S0Item INDIGO_2_SHEET = new MaterialItem("indigo_2_sheet");
    public static final S0Item INDIGO_3_SHEET = new MaterialItem("indigo_3_sheet");
    public static final S0Item INDIGO_4_SHEET = new MaterialItem("indigo_4_sheet");

    public static final S0Item GLAUCOUS_GOURD_SLICE = new MaterialItem("glaucous_gourd_slice");
    public static final S0Item SYMVINYL_SHEET = new MaterialItem("symvinyl_sheet");
    public static final S0Item PARA_TRILLIONS = new MaterialItem("para_trillions");
    public static final S0Item NEXXINE_SYMBOL = new MaterialItem("nexxine_symbol");
    public static final MaterialItem FLASHTIN_ANTENNA = new MaterialItem("flashtin_antenna");

    // Decorations

    // Networks

    // Psionics

    // Tools
    public static final Clanger CLANGER = new Clanger();
    public static final DebugTool DEBUG_TOOL = new DebugTool();
    public static final AntennaLinker ANTENNA_LINKER = new AntennaLinker();
    public static final VeinScouter VEIN_SCOUTER = new VeinScouter();
    public static final NetworkPDA NETWORK_PDA = new NetworkPDA();
    public static final Crowbar CROWBAR = new Crowbar();

    // Components
    public static final CapacitorItem ORGIMEX_DUAL_CELL = new S0CapacitorItem(240_000);

    public static class AugmentCards {
        public static class Tarot {
            public static class Locks {
                public static class Disks {
                    public static TarotCard.TarotLock CROWN     = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.DISKS,TarotCard.TarotLock.Orders.CROWN);
                    public static TarotCard.TarotLock WISH      = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.DISKS,TarotCard.TarotLock.Orders.WISH);
                    public static TarotCard.TarotLock MASK      = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.DISKS,TarotCard.TarotLock.Orders.MASK);
                    public static TarotCard.TarotLock CIPHER    = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.DISKS,TarotCard.TarotLock.Orders.CIPHER);
                    public static TarotCard.TarotLock ACE       = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.DISKS,TarotCard.TarotLock.Orders.ACE);
                    public static TarotCard.TarotLock DEUCE     = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.DISKS,TarotCard.TarotLock.Orders.DEUCE);
                    public static TarotCard.TarotLock TRIPLEX   = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.DISKS,TarotCard.TarotLock.Orders.TRIPLEX);
                    public static TarotCard.TarotLock TETRAD    = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.DISKS,TarotCard.TarotLock.Orders.TETRAD);
                    public static TarotCard.TarotLock LUSTRUM   = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.DISKS,TarotCard.TarotLock.Orders.LUSTRUM);
                    public static TarotCard.TarotLock SEMESTER  = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.DISKS,TarotCard.TarotLock.Orders.SEMESTER);
                    public static TarotCard.TarotLock HEBDOMAD  = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.DISKS,TarotCard.TarotLock.Orders.HEBDOMAD);
                    public static TarotCard.TarotLock OCTAVE    = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.DISKS,TarotCard.TarotLock.Orders.OCTAVE);
                    public static TarotCard.TarotLock ENNEAD    = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.DISKS,TarotCard.TarotLock.Orders.ENNEAD);
                }
                public static class Conduits {
                    public static TarotCard.TarotLock CROWN     = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.CONDUITS,TarotCard.TarotLock.Orders.CROWN);
                    public static TarotCard.TarotLock WISH      = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.CONDUITS,TarotCard.TarotLock.Orders.WISH);
                    public static TarotCard.TarotLock MASK      = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.CONDUITS,TarotCard.TarotLock.Orders.MASK);
                    public static TarotCard.TarotLock CIPHER    = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.CONDUITS,TarotCard.TarotLock.Orders.CIPHER);
                    public static TarotCard.TarotLock ACE       = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.CONDUITS,TarotCard.TarotLock.Orders.ACE);
                    public static TarotCard.TarotLock DEUCE     = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.CONDUITS,TarotCard.TarotLock.Orders.DEUCE);
                    public static TarotCard.TarotLock TRIPLEX   = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.CONDUITS,TarotCard.TarotLock.Orders.TRIPLEX);
                    public static TarotCard.TarotLock TETRAD    = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.CONDUITS,TarotCard.TarotLock.Orders.TETRAD);
                    public static TarotCard.TarotLock LUSTRUM   = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.CONDUITS,TarotCard.TarotLock.Orders.LUSTRUM);
                    public static TarotCard.TarotLock SEMESTER  = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.CONDUITS,TarotCard.TarotLock.Orders.SEMESTER);
                    public static TarotCard.TarotLock HEBDOMAD  = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.CONDUITS,TarotCard.TarotLock.Orders.HEBDOMAD);
                    public static TarotCard.TarotLock OCTAVE    = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.CONDUITS,TarotCard.TarotLock.Orders.OCTAVE);
                    public static TarotCard.TarotLock ENNEAD    = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.CONDUITS,TarotCard.TarotLock.Orders.ENNEAD);
                }
                public static class Vessels {
                    public static TarotCard.TarotLock CROWN     = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.VESSELS,TarotCard.TarotLock.Orders.CROWN);
                    public static TarotCard.TarotLock WISH      = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.VESSELS,TarotCard.TarotLock.Orders.WISH);
                    public static TarotCard.TarotLock MASK      = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.VESSELS,TarotCard.TarotLock.Orders.MASK);
                    public static TarotCard.TarotLock CIPHER    = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.VESSELS,TarotCard.TarotLock.Orders.CIPHER);
                    public static TarotCard.TarotLock ACE       = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.VESSELS,TarotCard.TarotLock.Orders.ACE);
                    public static TarotCard.TarotLock DEUCE     = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.VESSELS,TarotCard.TarotLock.Orders.DEUCE);
                    public static TarotCard.TarotLock TRIPLEX   = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.VESSELS,TarotCard.TarotLock.Orders.TRIPLEX);
                    public static TarotCard.TarotLock TETRAD    = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.VESSELS,TarotCard.TarotLock.Orders.TETRAD);
                    public static TarotCard.TarotLock LUSTRUM   = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.VESSELS,TarotCard.TarotLock.Orders.LUSTRUM);
                    public static TarotCard.TarotLock SEMESTER  = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.VESSELS,TarotCard.TarotLock.Orders.SEMESTER);
                    public static TarotCard.TarotLock HEBDOMAD  = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.VESSELS,TarotCard.TarotLock.Orders.HEBDOMAD);
                    public static TarotCard.TarotLock OCTAVE    = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.VESSELS,TarotCard.TarotLock.Orders.OCTAVE);
                    public static TarotCard.TarotLock ENNEAD    = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.VESSELS,TarotCard.TarotLock.Orders.ENNEAD);
                }
                public static class Swords {
                    public static TarotCard.TarotLock CROWN     = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.SWORDS,TarotCard.TarotLock.Orders.CROWN);
                    public static TarotCard.TarotLock WISH      = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.SWORDS,TarotCard.TarotLock.Orders.WISH);
                    public static TarotCard.TarotLock MASK      = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.SWORDS,TarotCard.TarotLock.Orders.MASK);
                    public static TarotCard.TarotLock CIPHER    = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.SWORDS,TarotCard.TarotLock.Orders.CIPHER);
                    public static TarotCard.TarotLock ACE       = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.SWORDS,TarotCard.TarotLock.Orders.ACE);
                    public static TarotCard.TarotLock DEUCE     = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.SWORDS,TarotCard.TarotLock.Orders.DEUCE);
                    public static TarotCard.TarotLock TRIPLEX   = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.SWORDS,TarotCard.TarotLock.Orders.TRIPLEX);
                    public static TarotCard.TarotLock TETRAD    = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.SWORDS,TarotCard.TarotLock.Orders.TETRAD);
                    public static TarotCard.TarotLock LUSTRUM   = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.SWORDS,TarotCard.TarotLock.Orders.LUSTRUM);
                    public static TarotCard.TarotLock SEMESTER  = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.SWORDS,TarotCard.TarotLock.Orders.SEMESTER);
                    public static TarotCard.TarotLock HEBDOMAD  = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.SWORDS,TarotCard.TarotLock.Orders.HEBDOMAD);
                    public static TarotCard.TarotLock OCTAVE    = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.SWORDS,TarotCard.TarotLock.Orders.OCTAVE);
                    public static TarotCard.TarotLock ENNEAD    = new TarotCard.TarotLock(TarotCard.TarotLock.Formats.SWORDS,TarotCard.TarotLock.Orders.ENNEAD);
                }
            }
            public static class Riddles {
                public static TarotCard.TarotRiddle AVATAR          = new TarotCard.TarotRiddle(0);
                public static TarotCard.TarotRiddle WIZARD          = new TarotCard.TarotRiddle(1);
                public static TarotCard.TarotRiddle OPERATOR        = new TarotCard.TarotRiddle(2);
                public static TarotCard.TarotRiddle SCULPTOR        = new TarotCard.TarotRiddle(3);
                public static TarotCard.TarotRiddle SUPERUSER       = new TarotCard.TarotRiddle(4);
                public static TarotCard.TarotRiddle TRINITY         = new TarotCard.TarotRiddle(5);
                public static TarotCard.TarotRiddle LOVERS          = new TarotCard.TarotRiddle(6);
                public static TarotCard.TarotRiddle VICTOR          = new TarotCard.TarotRiddle(7);
                public static TarotCard.TarotRiddle MACHINE         = new TarotCard.TarotRiddle(8);
                public static TarotCard.TarotRiddle SEER            = new TarotCard.TarotRiddle(9);
                public static TarotCard.TarotRiddle BREADTH         = new TarotCard.TarotRiddle(10);
                public static TarotCard.TarotRiddle ENERGY          = new TarotCard.TarotRiddle(11);
                public static TarotCard.TarotRiddle MARTYR          = new TarotCard.TarotRiddle(12);
                public static TarotCard.TarotRiddle MUTATION        = new TarotCard.TarotRiddle(13);
                public static TarotCard.TarotRiddle ART             = new TarotCard.TarotRiddle(14);
                public static TarotCard.TarotRiddle CAPRICIOUS      = new TarotCard.TarotRiddle(15);
                public static TarotCard.TarotRiddle TOWER           = new TarotCard.TarotRiddle(16);
                public static TarotCard.TarotRiddle SIGNALS         = new TarotCard.TarotRiddle(17);
                public static TarotCard.TarotRiddle GAPS            = new TarotCard.TarotRiddle(18);
                public static TarotCard.TarotRiddle RIFTS           = new TarotCard.TarotRiddle(19);
                public static TarotCard.TarotRiddle ABSOLUTION      = new TarotCard.TarotRiddle(20);
                public static TarotCard.TarotRiddle GALAXY          = new TarotCard.TarotRiddle(21);
            }
            public static class Enigmas {
                public static TarotCard.TarotEnigma EDGE    = new TarotCard.TarotEnigma("edge");
                public static TarotCard.TarotEnigma NOISE   = new TarotCard.TarotEnigma("noise");
                public static TarotCard.TarotEnigma BLUR    = new TarotCard.TarotEnigma("blur");
                public static TarotCard.TarotEnigma BLEND   = new TarotCard.TarotEnigma("blend");
            }
        }
    }


    public static Item.Settings s() {
        return new Item.Settings();
    }

    static {
        INDIGONITE_COMPOSITE.giveTooltip();
        GLAUCOUS_GOURD_SLICE.giveTooltip();
        FLASHTIN_ANTENNA.giveTooltip();
        SYMVINYL_SHEET.giveTooltip();
        PARA_TRILLIONS.giveTooltip();
        NEXXINE_SYMBOL.giveTooltip();

        //S0Registrar.register(ORGIMEX_DUAL_CELL, "orgimex_dual_cell");
    }

    public static void init() {

    }

}
