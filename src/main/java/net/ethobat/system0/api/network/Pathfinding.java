package net.ethobat.system0.api.network;

import net.ethobat.system0.api.network.physical.INetConnection;
import net.ethobat.system0.api.network.physical.INetConnector;

import java.util.HashMap;
import java.util.HashSet;

public final class Pathfinding {

    public NetworkPath dijkstra(INetConnector start, INetConnector end) {
        NetworkPath.Builder builder = new NetworkPath.Builder();

        final HashMap<INetConnector,Float> distances = new HashMap<>();
        final HashSet<INetConnector> visitedNodes = new HashSet<>(start.getConnectedNodes());
        HashSet<INetConnection> unexploredConnections;
        INetConnector currentNode = start;
        float newDistanceFromStart;

        distances.put(start, 0F);

        unexploredConnections = new HashSet<INetConnection>(currentNode.getConnections());
        unexploredConnections.removeAll(visitedNodes);
        for (INetConnection cxn : unexploredConnections) {
            newDistanceFromStart = distances.get(currentNode) + 1F/cxn.getBandwidth();
            if (newDistanceFromStart < distances.get(cxn.getTarget())) {
                distances.put(cxn.getTarget(), newDistanceFromStart);
            }
            visitedNodes.add(currentNode);
            // TODO TODO TODO

            distances.put(cxn.getTarget(), Math.min(distances.get(cxn.getTarget()), 1F/cxn.getBandwidth()));
        }

        return builder.build();
    }

}
