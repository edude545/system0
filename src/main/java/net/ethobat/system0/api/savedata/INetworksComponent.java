package net.ethobat.system0.api.savedata;

import dev.onyxstudios.cca.api.v3.component.Component;
import net.ethobat.system0.api.network.AbstractNWMember;
import net.ethobat.system0.api.network.Network;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public interface INetworksComponent extends Component {

    HashMap<UUID,Network> getNetworkMap();

    void putNetwork(Network network);
    Network getNetwork(UUID uuid);

    @Override
    default void readFromNbt(@NotNull NbtCompound tag) {
        for (String key : tag.getKeys()) {
            this.putNetwork(Network.fromNBT(tag.getCompound(key)));
        }
    }

    @Override
    default void writeToNbt(@NotNull NbtCompound tag) {
        for (UUID uuid : this.getNetworkMap().keySet()) {
            tag.put(uuid.toString(), this.getNetwork(uuid).toNBT());
        }
    }

    default Network createNetwork() {
        return this.createNetwork(UUID.randomUUID());
    }

    default Network createNetwork(UUID uuid) {
        Network network = new Network(uuid);
        this.putNetwork(network);
        return network;
    }

    default void safeRegister(UUID networkUUID, AbstractNWMember networkMember) {
        Network network = this.getNetwork(networkUUID);
        if (network != null) {
            network.register(networkMember);
            System.out.println("Registered " + networkMember.getUUID() + " to network " + network.getUUID());
        }
    }

}
