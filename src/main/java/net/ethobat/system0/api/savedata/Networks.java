package net.ethobat.system0.api.savedata;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.ethobat.system0.api.network.abstracted.IAbstractedNetworkMember;
import net.ethobat.system0.api.network.abstracted.Network;
import net.ethobat.system0.registry.S0Components;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.WorldProperties;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public final class Networks implements AutoSyncedComponent {

    public static Networks instance;

    public final WorldProperties worldProperties;

    private final HashMap<UUID, Network> networks;

    public Networks(WorldProperties worldProperties) {
        Networks.instance = this;
        this.worldProperties = worldProperties;
        this.networks = new HashMap<>();
    }

    public static Network getNetwork(UUID uuid) {
        return Networks.instance.networks.get(uuid);
    }

    public static void safeRegister(Network network, IAbstractedNetworkMember networkMember) {
        if (network != null) {
            network.register(networkMember);
            System.out.println("Registered " + networkMember.getUUID() + " to network " + network.getUUID());
        }
    }

    @Override
    public void readFromNbt(@NotNull NbtCompound tag) {
        for (String key : tag.getKeys()) {
            this.networks.put(UUID.fromString(key), Network.fromNBT(tag.getCompound(key)));
        }
    }

    @Override
    public void writeToNbt(@NotNull NbtCompound tag) {
        for (UUID uuid : this.networks.keySet()) {
            tag.put(uuid.toString(), this.networks.get(uuid).toNBT());
        }
    }

}
