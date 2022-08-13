package net.ethobat.system0.registry;

import net.ethobat.system0.System0;
import net.ethobat.system0.items.equipment.NetworkPDA;
import net.ethobat.system0.machinery.DebugMachine;
import net.ethobat.system0.machinery.FirstArcanumExoticizer;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public final class S0ScreenHandlerTypes {

//    public static final ScreenHandlerType<FirstArcanumExoticizer.SH> FIRST_ARCANUM_EXOTICIZER =
//            ScreenHandlerRegistry.registerExtended(id("first_arcanum_exoticizer"),
//                    ((syncID, inventory, buf) -> new FirstArcanumExoticizer.SH(syncID, inventory, buf)));

    public static final ScreenHandlerType<DebugMachine.SH> DEBUG_MACHINE =
            ScreenHandlerRegistry.registerExtended(id("debug_machine"),
                    ((syncID, inventory, buf) -> new DebugMachine.SH(syncID, inventory, buf)));

    public static final ScreenHandlerType<FirstArcanumExoticizer.SH> FIRST_ARCANUM_EXOTICIZER =
            ScreenHandlerRegistry.registerExtended(id("first_arcanum_exoticizer"),
                    ((syncID, inventory, buf) -> new FirstArcanumExoticizer.SH(syncID, inventory, buf)));

    public static final ScreenHandlerType<NetworkPDA.SH> NETWORK_PDA =
            ScreenHandlerRegistry.registerExtended(id("network_pda"),
                    ((syncID, inventory, buf) -> new NetworkPDA.SH(syncID, inventory, buf)));

    // for brevity
    private static Identifier id(String name) {
        return new Identifier(System0.MOD_ID, name);
    }
    public static void init() {}

}
