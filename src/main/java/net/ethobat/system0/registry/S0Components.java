package net.ethobat.system0.registry;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.level.LevelComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.level.LevelComponentInitializer;
import net.ethobat.system0.api.savedata.Networks;

public final class S0Components implements LevelComponentInitializer {

    public static final ComponentKey<Networks> NETWORKS =
            ComponentRegistry.getOrCreate(S0Registrar.id("networks"), Networks.class);

    @Override
    public void registerLevelComponentFactories(LevelComponentFactoryRegistry registry) {
        registry.register(NETWORKS, Networks::new);
    }

    public static void init() {

    }

}
