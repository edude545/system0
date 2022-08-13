package net.ethobat.system0.api.network.abstracted;

import net.ethobat.system0.api.nbt.NBTHandler;
import net.ethobat.system0.api.util.UUIDPair;
import net.minecraft.nbt.NbtCompound;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

/*
Each network consists of a set of the following: transmitters, receivers, sources, drains, connections, and NetworkPaths.
All links from transmitters to receivers that can possibly exist (given location & antenna properties) are called AbstractConnections.

Energy flows through the network in the form of NetworkPaths. These are peer-to-peer links from AbstractedSources to AbstractedDrains.
NetworkPaths themselves consist of a set of jumps from transmitters to receivers, occupying a certain amount of bandwidth with each jump.
NetworkPaths may split and rejoin any number of times.
Sources and drains may be members of multiple NetworkPaths. However, there can only be one NetworkPath for each source/drain pair.
*/
public class Network {

    public static final String NBT_UUID_KEY = "uuid";
    public static final String NBT_NODES_KEY = "nodes";
    public static final String NBT_SOURCES_KEY = "sources";
    public static final String NBT_DRAINS_KEY = "drains";
    public static final String NBT_CONNECTIONS_KEY = "connections";
    public static final String NBT_PATHS_KEY = "paths";

    public final UUID uuid;

    public HashMap<UUID, AbstractedNode> nodes;

    public HashMap<UUID, AbstractedSource> sources;
    public HashMap<UUID, AbstractedDrain> drains;

    private HashMap<UUIDPair, AbstractedConnection> connections;

    private HashMap<UUIDPair, NetworkPath> paths;

    public Network(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public AbstractedNode getNode(UUID uuid) {
        return this.nodes.get(uuid);
    }

    public AbstractedSource getSource(UUID uuid) {
        return this.sources.get(uuid);
    }

    public AbstractedDrain getDrain(UUID uuid) {
        return this.drains.get(uuid);
    }

    public AbstractedConnection getConnection(UUID transmitter, UUID receiver) {
        return this.getConnection(new UUIDPair(transmitter, receiver));
    }
    public AbstractedConnection getConnection(UUIDPair pair) {
        return this.connections.get(pair);
    }

    public Collection<AbstractedConnection> getConnections() {
        return this.connections.values();
    }

    public NetworkPath getPath(UUID source, UUID drain) {
        return this.getPath(new UUIDPair(source, drain));
    }

    public NetworkPath getPath(UUIDPair pair) {
        return this.paths.get(pair);
    }

    // Methods used to add new elements to the network. *Not* used in reading network data from NBT.

    public void register(IAbstractedNetworkMember member) {
        if (member instanceof AbstractedNode) {
            this.registerNode((AbstractedNode) member);
        } else if (member instanceof AbstractedSource) {
            this.registerSource((AbstractedSource) member);
        } else if (member instanceof AbstractedDrain) {
            this.registerDrain((AbstractedDrain) member);
        }
    }

    public void registerNode(AbstractedNode node) {
        this.nodes.put(node.getUUID(), node);
        for (UUID otherNode : this.nodes.keySet()) {
            this.tryConnection(node, otherNode);
            this.tryConnection(otherNode, node);
        }
    }

    public void registerSource(AbstractedSource source) {
        this.sources.put(source.getUUID(), source);
    }

    public void registerDrain(AbstractedDrain drain) {
        this.drains.put(drain.getUUID(), drain);
    }

    //

    // Establishes a connection if possible.
    public boolean tryConnection(AbstractedNode transmitter, AbstractedNode receiver) {
        // TODO: Penetration
        if (transmitter.getPos().isWithinDistance(receiver.getPos(), transmitter.getRange() * receiver.getSensitivity())) {
            this.connections.put(new UUIDPair(transmitter, receiver), new AbstractedConnection(this, transmitter, receiver));
            return true;
        }
        return false;
    }

    public boolean tryConnection(UUID transmitter, UUID receiver) {
        return this.tryConnection(this.getNode(transmitter), this.getNode(receiver));
    }

    public boolean tryConnection(UUID transmitter, AbstractedNode receiver) {
        return this.tryConnection(this.getNode(transmitter), receiver);
    }

    public boolean tryConnection(AbstractedNode transmitter, UUID receiver) {
        return this.tryConnection(transmitter, this.getNode(receiver));
    }

    //
    // NBT
    //

    public static Network fromNBT(NbtCompound nbt) {
        Network net = new Network(NBTHandler.getUUID(nbt, NBT_UUID_KEY));

        if (nbt.contains(NBT_NODES_KEY)) {
            NbtCompound nodesNBT = nbt.getCompound(NBT_NODES_KEY);
            for (String key : nodesNBT.getKeys()) {
                UUID uuid = UUID.fromString(key);
                net.nodes.put(uuid, AbstractedNode.fromNBT(nodesNBT.getCompound(key)).silentSubscribe(net));
            }
        }
        if (nbt.contains(NBT_SOURCES_KEY)) {
            NbtCompound sourcesNBT = nbt.getCompound(NBT_SOURCES_KEY);
            for (String key : sourcesNBT.getKeys()) {
                UUID uuid = UUID.fromString(key);
                net.sources.put(uuid, AbstractedSource.fromNBT(sourcesNBT.getCompound(key)));
            }
        }
        if (nbt.contains(NBT_DRAINS_KEY)) {
            NbtCompound drainsNBT = nbt.getCompound(NBT_DRAINS_KEY);
            for (String key : drainsNBT.getKeys()) {
                UUID uuid = UUID.fromString(key);
                net.drains.put(uuid, AbstractedDrain.fromNBT(drainsNBT.getCompound(key)).silentSubscribe(net));
            }
        }
        if (nbt.contains(NBT_CONNECTIONS_KEY)) {
            NbtCompound connectionsNBT = nbt.getCompound(NBT_CONNECTIONS_KEY);
            for (String key : connectionsNBT.getKeys()) {
                net.connections.put(
                    UUIDPair.fromString(key),
                    AbstractedConnection.fromNBT(net, connectionsNBT.getCompound(key))
                );
            }
        }
        if (nbt.contains(NBT_PATHS_KEY)) {
            NbtCompound pathsNBT = nbt.getCompound(NBT_PATHS_KEY);
            for (String key : pathsNBT.getKeys()) {
                net.paths.put(
                        UUIDPair.fromString(key),
                        NetworkPath.fromNBT(net, pathsNBT.getCompound(key))
                );
            }
        }

        return net;
    }

    public NbtCompound toNBT() {
        NbtCompound nbt = new NbtCompound();
        NBTHandler.genericPut(nbt, NBT_UUID_KEY, this.uuid);

        NbtCompound tmp = new NbtCompound();
        for (UUID uuid : this.nodes.keySet()) {
            tmp.put(uuid.toString(), this.getNode(uuid).toNBT());
        }
        nbt.put(NBT_NODES_KEY, tmp);

        tmp = new NbtCompound();
        for (UUID uuid : this.sources.keySet()) {
            tmp.put(uuid.toString(), this.getSource(uuid).autoToNBT());
        }
        nbt.put(NBT_SOURCES_KEY, tmp);

        tmp = new NbtCompound();
        for (UUID uuid : this.drains.keySet()) {
            tmp.put(uuid.toString(), this.getDrain(uuid).toNBT());
        }
        nbt.put(NBT_DRAINS_KEY, tmp);

        tmp = new NbtCompound();
        for (UUIDPair pair : this.connections.keySet()) {
            tmp.put(pair.toString(), this.getConnection(pair).toNBT());
        }
        nbt.put(NBT_CONNECTIONS_KEY, tmp);

        tmp = new NbtCompound();
        for (UUIDPair pair : this.paths.keySet()) {
            tmp.put(pair.toString(), this.getPath(pair).toNBT());
        }
        nbt.put(NBT_PATHS_KEY, tmp);

        return nbt;
    }

}