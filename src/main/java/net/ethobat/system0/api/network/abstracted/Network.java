package net.ethobat.system0.api.network.abstracted;

import net.ethobat.system0.api.nbt.NBTHandler;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class Network {

    private UUID uuid;
    private HashMap<UUID,AbstractedTransmitter> transmitters;
    private HashMap<UUID,AbstractedReceiver> receivers;
    private HashSet<NetworkPath> paths;

    public AbstractedTransmitter getTransmitter(UUID uuid) {
        return this.transmitters.get(uuid);
    }
    public AbstractedReceiver getReceiver(UUID uuid) {
        return this.receivers.get(uuid);
    }
    public HashMap<Pair<UUID,UUID>,AbstractedConnection> connections;

    public AbstractedConnection getConnection(UUID start, UUID end) {
        return this.connections.get(new Pair<>(start,end));
    }

    public boolean tryConnection(AbstractedTransmitter start, AbstractedReceiver end) {
        // TODO: Penetration
        if (start.getPos().isWithinDistance(end.getPos(), start.getRange()*end.getSensitivity())) {
            this.connections.put(new Pair<>(start.getUUID(), end.getUUID()), new AbstractedConnection(start, end));
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
