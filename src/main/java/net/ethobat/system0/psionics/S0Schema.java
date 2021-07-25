package net.ethobat.system0.psionics;

import net.ethobat.system0.api.psionics.PsionicSchema;
import net.ethobat.system0.registry.S0Registrar;

public class S0Schema extends PsionicSchema {

    public S0Schema(String name) {
        super(name);
        S0Registrar.register(this, name);
    }

}
