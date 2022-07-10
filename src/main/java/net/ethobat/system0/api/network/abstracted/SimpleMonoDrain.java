package net.ethobat.system0.api.network.abstracted;

import net.ethobat.system0.api.energy.EnergyType;

import java.util.UUID;

public class SimpleMonoDrain extends AbstractedDrain {

    private final EnergyType type;
    private final long influx;

    public SimpleMonoDrain(UUID uuid, EnergyType type, long influx) {
        super(uuid);
        this.type = type;
        this.influx = influx;
    }

}
