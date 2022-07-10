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

    private HashMap<UUID,AbstractedTransmitter> transmitters;
    private HashMap<UUID,AbstractedReceiver> receivers;

    private HashMap<UUID,AbstractedSource> sources;
    private HashMap<UUID,AbstractedDrain> drains;

    private HashMap<TransRecvPair,AbstractedConnection> connections;

    private HashMap<SourceDrainPair,NetworkPath> paths;

    public UUID getUUID() {
        return this.uuid;
    }

    public AbstractedTransmitter getTransmitter(UUID uuid) {
        return this.transmitters.get(uuid);
    }
    public AbstractedReceiver getReceiver(UUID uuid) {
        return this.receivers.get(uuid);
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

    // Establishes a connection if possible.
    public boolean tryConnection(AbstractedTransmitter start, AbstractedReceiver end) {
        // TODO: Penetration
        if (start.getPos().isWithinDistance(end.getPos(), start.getRange()*end.getSensitivity())) {
            this.connections.put(new TransRecvPair(start, end), new AbstractedConnection(start, end));
            return true;
        }
        return false;
    }

    public boolean tryConnection(UUID start, UUID end) {
        return this.tryConnection(this.getTransmitter(start), this.getReceiver(end));
    }

    // ~~~~~~~~~~~
    // NBT stuff
    // TODO: Save+load paths
    // ~~~~~~~~~~~
    public static Network fromNBT(NbtCompound nbt) {
        Network network = new Network();

        HashMap<UUID,AbstractedTransmitter> transmitters = new HashMap<>();
        HashMap<UUID,AbstractedReceiver> receivers = new HashMap<>();

        NbtCompound transmittersNBT = NBTHandler.getCompound(nbt, "transmitters");
        NbtCompound receiversNBT = NBTHandler.getCompound(nbt, "receivers");

        for (String key : transmittersNBT.getKeys()) {
            transmitters.put(UUID.fromString(key), AbstractedTransmitter.fromNBT(transmittersNBT.getCompound(key)));
        }
        for (String key : receiversNBT.getKeys()) {
            receivers.put(UUID.fromString(key), AbstractedReceiver.fromNBT(receiversNBT.getCompound(key)));
        }

        network.uuid = NBTHandler.getUUID(nbt, "uuid");
        network.transmitters = transmitters;
        network.receivers = receivers;
        return network;
    }

    public NbtCompound toNBT() {
        NbtCompound nbt = new NbtCompound();

        NBTHandler.genericPut(nbt, "uuid", this.uuid);

        NbtCompound tmp = new NbtCompound();
        for (UUID uuid : this.transmitters.keySet()) {
            tmp.put(uuid.toString(), this.getTransmitter(uuid).toNBT());
        }
        nbt.put("transmitters", tmp);

        tmp = new NbtCompound();
        for (UUID uuid : this.receivers.keySet()) {
            tmp.put(uuid.toString(), this.getReceiver(uuid).toNBT());
        }
        nbt.put("receivers", tmp);

        return nbt;
    }

}
