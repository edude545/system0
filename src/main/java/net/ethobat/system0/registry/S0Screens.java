package net.ethobat.system0.registry;

import net.ethobat.system0.items.equipment.NetworkPDA;
import net.ethobat.system0.machinery.DebugMachine;
import net.ethobat.system0.machinery.FirstArcanumExoticizer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

public final class S0Screens {

    public static void init() {
        ScreenRegistry.register(S0ScreenHandlerTypes.FIRST_ARCANUM_EXOTICIZER, FirstArcanumExoticizer.HS::new);
        ScreenRegistry.register(S0ScreenHandlerTypes.DEBUG_MACHINE, DebugMachine.HS::new);
        ScreenRegistry.register(S0ScreenHandlerTypes.NETWORK_PDA_VIEWPORT, NetworkPDA.HSViewport::new);
        ScreenRegistry.register(S0ScreenHandlerTypes.NETWORK_PDA_SELECTOR, NetworkPDA.HSSelector::new);
    }

}
