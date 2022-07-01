package net.ethobat.system0.items.cards;

import net.ethobat.system0.System0;
import net.ethobat.system0.registry.S0Items;
import net.minecraft.item.Item;

public class TarotCard extends AugmentCard {

    public TarotCard(String registryName) {
        super(new Item.Settings().group(System0.ITEM_GROUP_COMPONENTS), registryName);
    }

    public static class TarotLock extends TarotCard {

        public enum Formats {
            DISKS, CONDUITS, VESSELS, SWORDS
        }

        public enum Orders {
            CROWN(-3), WISH(-2), MASK(-1),
            CIPHER(0),
            ACE(1), DEUCE(2), TRIPLEX(3),
            TETRAD(4), LUSTRUM(5), SEMESTER(6),
            HEBDOMAD(7), OCTAVE(8), ENNEAD(9);
            final int NUMBER;
            Orders(int number) {
                this.NUMBER = number;
            }

        }

        public TarotLock(Formats format, Orders order) {
            super("card_tarot_lock_"+format.name().toLowerCase()+"_"+order.name().toLowerCase());
        }

    }

    public static class TarotRiddle extends TarotCard {

        public TarotRiddle(int number) {
            super("card_tarot_riddle_"+String.valueOf(number));
        }
    }

    public static class TarotEnigma extends TarotCard {

        public TarotEnigma(String name) {
            super("card_tarot_enigma_"+name);
        }

    }

}
