package net.ethobat.system0.registry;

import net.ethobat.system0.System0;
import net.ethobat.system0.api.energy.EnergyType;
import net.ethobat.system0.api.gui.GUINetworkingHandler;
import net.ethobat.system0.api.gui.widgets.GUIWidget;
import net.ethobat.system0.api.psionics.PsionicSchema;
import net.ethobat.system0.api.registry.S0Registry;
import net.ethobat.system0.auxiliary.S0BlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class S0Registrar {

    // Registered on both sides.
    public static void init() {
        S0BlockEntityTypes.init();
        S0Blocks.init();
        S0Commands.init();
        S0EnergyTypes.init();
        S0Items.init();
        S0Progressions.init();
        S0Schemas.init();
        S0ScreenHandlerTypes.init();

        // Part of S0's API
        GUINetworkingHandler.init();
    }

    // Registered on the client only.
    public static void initClient() {
        S0Screens.init();
    }

    // Registered on the server only.
    public static void initServer() {

    }

    // for brevity
    public static Identifier id(String name) {
        return new Identifier(System0.MOD_ID, name);
    }

    // Register item
    public static Item register(Item item, String name) {
        return Registry.register(Registry.ITEM, id(name), item);
    }

    // Register block
    public static Block register(Block block, String name) {
        return Registry.register(Registry.BLOCK, id(name), block);
    }

    // Register block entity type
    public static BlockEntityType<?> register(BlockEntityType<?> type, String name) {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, id(name), type);
    }

    // Register psionic ability
    public static PsionicSchema register(PsionicSchema schema, String name) {
        return S0Registry.SCHEMA.register(schema, id(name));
    }

    // Register energy type
    public static EnergyType register(EnergyType energyType, String name) {
        return S0Registry.ENERGY_TYPE.register(energyType, id(name));
    }

    // Register widget
    public static Class<? extends GUIWidget> register(Class<? extends GUIWidget> cls, String name) {
        return S0Registry.WIDGETS.register(cls, id(name));
    }

//    // Register screen handler type
//    public static <SH extends S0ScreenHandler> ScreenHandlerType<SH> register(ScreenHandlerType<SH> type, String name) {
//        return ScreenHandlerRegistry.registerSimple(id(name), type:n);
//    }

}