package net.ethobat.system0.api.psionics;

import net.ethobat.system0.registry.S0Registrar;

public class PsionicSchema {

    public final String NAME;

    public PsionicSchema(String name) {
        this.NAME = name;
    }

    public PsionicSchema register() {
        S0Registrar.register(this, this.NAME);
        return this;
    }

}
