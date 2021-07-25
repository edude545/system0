package net.ethobat.system0.registry;

import net.ethobat.system0.api.energy.EnergyType;

public final class S0EnergyTypes {

    //quick dimension ideas to write down: sandstorm world, lush alien planet with big weird jellies,
    //claustrophobic cave world with tight winding chasms, colourful coral world,
    //magnetic desert with world height "soaked stone" pillars and multicoloured types of magnetic rock
    public static final EnergyType GREENULAR            = r("greenular");
    public static final EnergyType SKEINTILLATING       = r("skeintillating");
    public static final EnergyType BLAKRONIC            = r("blakronic");
    public static final EnergyType PETRIONIC            = r("petrionic");
    public static final EnergyType TIDALIZING           = r("tidalizing");
    public static final EnergyType CHORROMATIC          = r("chorromatic");
    public static final EnergyType GLIMMERAL            = r("glimmeral");
    public static final EnergyType LODESTIC             = r("lodestic");
    public static final EnergyType ANBARIC              = r("anbaric");
    public static final EnergyType CUSHASTIC            = r("cushastic");
    public static final EnergyType FULVINANT            = r("fulvinant");
    public static final EnergyType MONSTROTIC           = r("monstrotic");
    public static final EnergyType HARLEQUIOUS          = r("harlequious");
    public static final EnergyType LIMINESQUE           = r("liminesque");
    public static final EnergyType MNEMOLINE            = r("mnemoline");
    public static final EnergyType PHANTASMAGORIC       = r("phantasmagoric");

    private static EnergyType r(String name) {
        EnergyType ret = new EnergyType(name, primary_color);
        S0Registrar.register(ret, name);
        return ret;
    }

    public static void init() {

    }

}
