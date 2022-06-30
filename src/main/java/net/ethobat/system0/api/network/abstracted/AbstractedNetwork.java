package net.ethobat.system0.api.network.abstracted;

import net.ethobat.system0.api.nbt.NBTHandler;
import net.minecraft.nbt.NbtCompound;

import java.util.HashMap;
import java.util.UUID;

public class AbstractedNetwork {

    private UUID uuid;
    private HashMap<UUID,AbstractedTransmitter> transmitters;
    private HashMap<UUID,AbstractedReceiver> receivers;

    public AbstractedTransmitter getTransmitter(UUID uuid) {
        return this.transmitters.get(uuid);
    }
    public AbstractedReceiver getReceiver(UUID uuid) {
        return this.receivers.get(uuid);
    }

    public static AbstractedNetwork fromNBT(NbtCompound nbt) {
        AbstractedNetwork absnet = new AbstractedNetwork();

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

        absnet.uuid = NBTHandler.getUUID(nbt, "uuid");
        absnet.transmitters = transmitters;
        absnet.receivers = receivers;
        return absnet;
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
