package net.ethobat.system0.registry;

import net.ethobat.system0.System0;
import net.ethobat.system0.api.color.RGB;
import net.ethobat.system0.api.energy.EnergyType;

public final class S0EnergyTypes {

    //quick dimension ideas to write down: sandstorm world, lush alien planet with big weird jellies,
    //claustrophobic cave world with tight winding chasms, colourful coral world,
    //magnetic desert with world height "soaked stone" pillars and multicoloured types of magnetic rock
    public static final EnergyType GREENULAR            = r("greenular", 255, 255, 255);
    public static final EnergyType SKEINTILLATING       = r("skeintillating", 217, 161, 0);
    public static final EnergyType BLAKRONIC            = r("blakronic", 255, 255, 255);
    public static final EnergyType PETRIONIC            = r("petrionic", 255, 255, 255);
    public static final EnergyType TIDALIZING           = r("tidalizing", 255, 255, 255);
    public static final EnergyType CHORROMATIC          = r("chorromatic", 255, 255, 255);
    public static final EnergyType GLIMMERAL            = r("glimmeral", 255, 255, 255);
    public static final EnergyType LODESTIC             = r("lodestic", 255, 255, 255);
    public static final EnergyType ANBARIC              = r("anbaric", 255, 255, 255);
    public static final EnergyType CUSHASTIC            = r("cushastic", 255, 255, 255);
    public static final EnergyType FULVINANT            = r("fulvinant", 255, 255, 255);
    public static final EnergyType MONSTROTIC           = r("monstrotic", 255, 255, 255);
    public static final EnergyType HARLEQUIOUS          = r("harlequious", 255, 255, 255);
    public static final EnergyType LIMINESQUE           = r("liminesque", 20, 20, 20);
    public static final EnergyType MNEMOLINE            = r("mnemoline", 255, 255, 255);
    public static final EnergyType PHANTASMAGORIC       = r("phantasmagoric", 255, 255, 255);

    private static EnergyType r(String name, int r, int g, int b) {
        EnergyType ret = new EnergyType(S0Registrar.id(name), new RGB(r,g,b));
        S0Registrar.register(ret, name);
        return ret;
    }

    public static void init() {

    }

}
