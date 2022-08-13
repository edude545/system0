package net.ethobat.system0.api.network.abstracted;

import net.ethobat.system0.api.nbt.NBTHandler;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
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

    private UUID uuid;

    public HashMap<UUID,AbstractedNode> nodes;

    public HashMap<UUID,AbstractedSource> sources;
    public HashMap<UUID,AbstractedDrain> drains;

    private HashMap<TransRecvPair,AbstractedConnection> connections;

    private HashMap<SourceDrainPair,NetworkPath> paths;

    public static final String NBT_UUID_KEY = "uuid";
    public static final String NBT_NODES_KEY = "nodes";
    public static final String NBT_SOURCES_KEY = "sources";
    public static final String NBT_DRAINS_KEY = "drains";

    // getters

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
        return this.connections.get(new TransRecvPair(transmitter,receiver));
    }

    public NetworkPath getPath(UUID source, UUID drain) {
        return this.paths.get(new SourceDrainPair(source,drain));
    }

    //

    // Methods used to add new elements to the network. *Not* used in reading network data from NBT.

    public void register(IAbstractedNetworkMember member) {
             if (member instanceof AbstractedNode           )   { this.registerNode         ( (AbstractedNode       ) member);  }
        else if (member instanceof AbstractedSource         )   { this.registerSource       ( (AbstractedSource     ) member);  }
        else if (member instanceof AbstractedDrain          )   { this.registerDrain        ( (AbstractedDrain      ) member);  }
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
        if (transmitter.getPos().isWithinDistance(receiver.getPos(), transmitter.getRange()*receiver.getSensitivity())) {
            this.connections.put(new TransRecvPair(transmitter, receiver), new AbstractedConnection(transmitter, receiver));
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

    // ~~~~~~~~~~~
    // NBT stuff
    // TODO: Save+load paths
    // ~~~~~~~~~~~
    public static Network fromNBT(NbtCompound nbt) {
        Network network = new Network();

        HashMap<UUID,AbstractedNode> nodes = new HashMap<>();
        HashMap<UUID,AbstractedSource> sources = new HashMap<>();
        HashMap<UUID,AbstractedDrain> drains = new HashMap<>();

        NbtCompound nodesNBT = NBTHandler.getCompound(nbt, NBT_NODES_KEY);
        NbtCompound sourcesNBT = NBTHandler.getCompound(nbt, NBT_SOURCES_KEY);
        NbtCompound drainsNBT = NBTHandler.getCompound(nbt, NBT_DRAINS_KEY);

        for (String key : nodesNBT.getKeys()) {
            nodes.put(UUID.fromString(key), AbstractedNode.fromNBT(nodesNBT.getCompound(key)));
        }
        for (String key : sourcesNBT.getKeys()) {
            sources.put(UUID.fromString(key), AbstractedSource.fromNBT(sourcesNBT.getCompound(key)));
        }
        for (String key : drainsNBT.getKeys()) {
            drains.put(UUID.fromString(key), AbstractedDrain.fromNBT(drainsNBT.getCompound(key)));
        }

        network.uuid = NBTHandler.getUUID(nbt, NBT_UUID_KEY);
        network.nodes = nodes;
        network.sources = sources;
        network.drains = drains;
        return network;
    }

    public NbtCompound toNBT() {
        NbtCompound nbt = new NbtCompound();

        NBTHandler.genericPut(nbt, NBT_UUID_KEY, this.uuid);

        NbtCompound tmp = new NbtCompound();
        for (UUID uuid : this.nodes.keySet()) {
            tmp.put(uuid.toString(), this.getNode(uuid).toNBT());
        }
        nbt.put(NBT_NODES_KEY, tmp);

        return nbt;
    }

}
