package net.ethobat.system0.api.network.physical;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Set;

public interface INetConnector {

    boolean isOnline();
    boolean setOnline(boolean b);

    Set<INetConnection> getConnections();
    Set<INetConnector> getConnectedNodes();

    World getWorld();
    BlockPos getPos();

}
