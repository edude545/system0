package net.ethobat.system0.api.network;

import net.minecraft.world.World;

public class ConnectionContext {

    public final IAntenna TARGET;
    public final World WORLD;

    ConnectionContext(IAntenna target) {
        this.TARGET = target;
        this.WORLD = this.TARGET.getWorld();
    }

}
