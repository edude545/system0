package net.ethobat.system0.registry;

import net.ethobat.system0.machinery.DebugMachine;
import net.ethobat.system0.machinery.FirstArcanumExoticizer;
import net.ethobat.system0.network.sources.DebugEnergySource;
import net.ethobat.system0.network.sources.S0BasicAggregator;
import net.ethobat.system0.network.sources.TrickleAggregator;
import net.ethobat.system0.network.FlashtinDipole;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;

public final class S0BlockEntityTypes {

    // Aggregators
    public static final BlockEntityType<S0BasicAggregator.BE> TRICKLE_AGGREGATOR =
            type("trickle_aggregator", TrickleAggregator.BE::new, S0Blocks.TRICKLE_AGGREGATOR);
    public static final BlockEntityType<DebugEnergySource.BE> DEBUG_ENERGY_SOURCE =
            type("debug_energy_source", DebugEnergySource.BE::new, S0Blocks.DEBUG_ENERGY_SOURCE);

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
    private static <T extends BlockEntity> BlockEntityType<T> type(String name, FabricBlockEntityTypeBuilder.Factory<T> factory, Block block) {
        BlockEntityType<T> t = FabricBlockEntityTypeBuilder.create(factory, block).build(null);
        S0Registrar.register(t, name);
        return t;
    }

}
