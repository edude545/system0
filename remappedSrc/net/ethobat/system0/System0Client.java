package net.ethobat.system0;

import net.ethobat.system0.registry.S0Registrar;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class System0Client implements ClientModInitializer {

    public void onInitializeClient() {
        S0Registrar.initClient();
        System.out.println("System0Client initialized");
    }

}
