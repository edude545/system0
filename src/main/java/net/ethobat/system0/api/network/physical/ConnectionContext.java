package net.ethobat.system0.api.network.physical;

import net.minecraft.world.World;

public class ConnectionContext {

    public final IAntennaDeprecated TARGET;
    public final World WORLD;

    ConnectionContext(IAntennaDeprecated target) {
        this.TARGET = target;
        this.WORLD = this.TARGET.getWorld();
    }

}
