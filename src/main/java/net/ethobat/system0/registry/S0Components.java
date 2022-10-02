package net.ethobat.system0.registry;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.level.LevelComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.level.LevelComponentInitializer;
import net.ethobat.system0.api.savedata.INetworksComponent;
import net.ethobat.system0.api.savedata.LevelNetworks;

public final class S0Components implements LevelComponentInitializer {

    public static final ComponentKey<INetworksComponent> LEVEL_NETWORKS =
            ComponentRegistry.getOrCreate(S0Registrar.id("level_networks"), INetworksComponent.class);

    @Override
    public void registerLevelComponentFactories(LevelComponentFactoryRegistry registry) {
        registry.register(LEVEL_NETWORKS, LevelNetworks::new);
    }

    public static void init() {

    }

}
