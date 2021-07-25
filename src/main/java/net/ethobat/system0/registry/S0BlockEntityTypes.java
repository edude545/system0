package net.ethobat.system0.registry;

import net.ethobat.system0.auxiliary.S0BlockEntity;
import net.ethobat.system0.machinery.DebugMachine;
import net.ethobat.system0.machinery.FirstArcanumExoticizer;
import net.ethobat.system0.network.FlashtinDipole;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;

public final class S0BlockEntityTypes {

    // Machinery
    public static final BlockEntityType<DebugMachine.BE> DEBUG_MACHINE =
            type("debug_machine", DebugMachine.BE::new, S0Blocks.DEBUG_MACHINE);
    public static final BlockEntityType<FirstArcanumExoticizer.BE> FIRST_ARCANUM_EXOTICIZER =
            type("first_arcanum_exoticizer", FirstArcanumExoticizer.BE::new, S0Blocks.FIRST_ARCANUM_EXOTICIZER);

    // Network
    public static final BlockEntityType<FlashtinDipole.BE> FLASHTIN_DIPOLE =
            type("flashtin_dipole", FlashtinDipole.BE::new, S0Blocks.FLASHTIN_DIPOLE);

    public static void init() {}

    // just for use in this class, for brevity
    private static <T extends S0BlockEntity> BlockEntityType<T> type(String name, FabricBlockEntityTypeBuilder.Factory<T> factory, Block block) {
        BlockEntityType<T> t = FabricBlockEntityTypeBuilder.create(factory, block).build(null);
        S0Registrar.register(t, name);
        return t;
    }

}
