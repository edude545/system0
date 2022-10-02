package net.ethobat.system0.api.network;

import net.ethobat.system0.api.nbt.NBTHandler;
import net.ethobat.system0.api.util.UUIDPair;
import net.minecraft.nbt.NbtCompound;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

/*
Each network consists of a set of the following: transmitters, receivers, sources, drains, connections, and NetworkPaths.
All links from transmitters to receivers that can possibly exist (given location & antenna properties) are called AbstractConnections.

Energy flows through the network in the form of NetworkPaths. These are peer-to-peer links from AbstractedSources to AbstractedDrains.
NetworkPaths themselves consist of a set of jumps from transmitters to receivers, occupying a certain amount of bandwidth with each jump.
NetworkPaths may split and rejoin any number of times.
Sources and drains may be members of multiple NetworkPaths. However, there can only be one NWPath for each source/drain pair.
*/
public class Network {

    public static final String NBT_UUID_KEY = "uuid";
    public static final String NBT_NODES_KEY = "nodes";
    public static final String NBT_SOURCES_KEY = "sources";
    public static final String NBT_DRAINS_KEY = "drains";
    public static final String NBT_CONNECTIONS_KEY = "connections";
    public static final String NBT_PATHS_KEY = "paths";

    public final UUID uuid;

    public HashMap<UUID, NWNode> nodes = new HashMap<>();

    public HashMap<UUID, NWSource> sources = new HashMap<>();
    public HashMap<UUID, NWDrain> drains = new HashMap<>();

    private HashMap<UUIDPair, NWConnection> connections = new HashMap<>();

    //private HashMap<UUIDPair, NWPath> paths = new HashMap<>();

    public Network(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public NWNode getNode(UUID uuid) {
        return this.nodes.get(uuid);
    }
    public Collection<NWNode> getNodes() {
        return this.nodes.values();
    }

    public NWSource getSource(UUID uuid) {
        return this.sources.get(uuid);
    }
    public Collection<NWSource> getSources() {
        return this.sources.values();
    }

    public NWDrain getDrain(UUID uuid) {
        return this.drains.get(uuid);
    }
    public Collection<NWDrain> getDrains() {
        return this.drains.values();
    }

    public NWConnection getConnection(UUID transmitter, UUID receiver) {
        return this.getConnection(new UUIDPair(transmitter, receiver));
    }
    public NWConnection getConnection(UUIDPair pair) {
        return this.connections.get(pair);
    }
    public Collection<NWConnection> getConnections() {
        return this.connections.values();
    }

//    public NWPath getPath(UUID source, UUID drain) {
//        return this.getPath(new UUIDPair(source, drain));
//    }

//    public NWPath getPath(UUIDPair pair) {
//        return this.paths.get(pair);
//    }

    // Methods used to add new elements to the network. *Not* used in reading network data from NBT.

    public void register(AbstractNWMember member) {
        if (member instanceof NWNode) {
            this.registerNode((NWNode) member);
            member.onSubscribe(this);
        } else if (member instanceof NWSource) {
            this.registerSource((NWSource) member);
            member.onSubscribe(this);
        } else if (member instanceof NWDrain) {
            this.registerDrain((NWDrain) member);
            member.onSubscribe(this);
        }
    }

    public void registerNode(NWNode node) {
        this.nodes.put(node.getUUID(), node);
        for (UUID otherNode : this.nodes.keySet()) {
            this.tryConnection(node, otherNode);
            this.tryConnection(otherNode, node);
        }
    }

    public void registerSource(NWSource source) {
        this.sources.put(source.getUUID(), source);
    }

    public void registerDrain(NWDrain drain) {
        this.drains.put(drain.getUUID(), drain);
    }

    public void delete(AbstractNWMember member) {
        if (member instanceof NWNode) {
            this.deleteNode((NWNode) member);
        } else if (member instanceof NWSource) {
            this.deleteSource((NWSource) member);
        } else if (member instanceof NWDrain) {
            this.deleteDrain((NWDrain) member);
        }
    }

    public void deleteNode(NWNode node) {
        this.nodes.remove(node.uuid);
        HashSet<UUIDPair> deleteKeySet = new HashSet<>();
        for (UUIDPair pair : this.connections.keySet()) {
            if (pair.has(node.uuid)) {
                deleteKeySet.add(pair);
            }
        }
        for (UUIDPair pair : deleteKeySet) {
            this.connections.remove(pair);
        }
    }

    // TODO
    public void deleteSource(NWSource node) {
        this.sources.remove(node.uuid);
    }

    // TODO
    public void deleteDrain(NWDrain node) {
        this.drains.remove(node.uuid);
    }

    //

    // Establishes a connection if possible.
    public boolean tryConnection(NWNode transmitter, NWNode receiver) {
        // TODO: Penetration
        if (transmitter.getPos().isWithinDistance(receiver.getPos(), transmitter.getRange() * receiver.getSensitivity())) {
            this.connections.put(new UUIDPair(transmitter, receiver), new NWConnection(this, transmitter, receiver));
            return true;
        }
        return false;
    }

    public boolean tryConnection(UUID transmitter, UUID receiver) {
        return this.tryConnection(this.getNode(transmitter), this.getNode(receiver));
    }

    public boolean tryConnection(UUID transmitter, NWNode receiver) {
        return this.tryConnection(this.getNode(transmitter), receiver);
    }

    public boolean tryConnection(NWNode transmitter, UUID receiver) {
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
                NWNode node = NWNode.fromNBT(nodesNBT.getCompound(key));
                net.nodes.put(uuid, node);
                node.network = net;
            }
        }
        if (nbt.contains(NBT_SOURCES_KEY)) {
            NbtCompound sourcesNBT = nbt.getCompound(NBT_SOURCES_KEY);
            for (String key : sourcesNBT.getKeys()) {
                UUID uuid = UUID.fromString(key);
                NWSource source = NWSource.fromNBT(sourcesNBT.getCompound(key));
                net.sources.put(uuid, source);
                source.network = net;
            }
        }
        if (nbt.contains(NBT_DRAINS_KEY)) {
            NbtCompound drainsNBT = nbt.getCompound(NBT_DRAINS_KEY);
            for (String key : drainsNBT.getKeys()) {
                UUID uuid = UUID.fromString(key);
                NWDrain drain = NWDrain.fromNBT(drainsNBT.getCompound(key));
                net.drains.put(uuid, drain);
                drain.network = net;
            }
        }
        if (nbt.contains(NBT_CONNECTIONS_KEY)) {
            NbtCompound connectionsNBT = nbt.getCompound(NBT_CONNECTIONS_KEY);
            for (String key : connectionsNBT.getKeys()) {
                NWConnection connection = NWConnection.fromNBT(net, connectionsNBT.getCompound(key));
                if (connection != null) {
                    net.connections.put(UUIDPair.fromString(key), connection);
                }
            }
        }
//        if (nbt.contains(NBT_PATHS_KEY)) {
//            NbtCompound pathsNBT = nbt.getCompound(NBT_PATHS_KEY);
//            for (String key : pathsNBT.getKeys()) {
//                net.paths.put(
//                        UUIDPair.fromString(key),
//                        NWPath.fromNBT(net, pathsNBT.getCompound(key))
//                );
//            }
//        }

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

//        tmp = new NbtCompound();
//        for (UUIDPair pair : this.paths.keySet()) {
//            tmp.put(pair.toString(), this.getPath(pair).toNBT());
//        }
//        nbt.put(NBT_PATHS_KEY, tmp);

        return nbt;
    }

    public String toString() {
        return "Network{UUID=" + this.uuid.toString() + "}";
    }

}