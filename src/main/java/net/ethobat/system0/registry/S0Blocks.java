package net.ethobat.system0.registry;

import net.ethobat.system0.auxiliary.S0Block;
import net.ethobat.system0.block.*;
import net.ethobat.system0.network.sources.DebugEnergySource;
import net.ethobat.system0.machinery.DebugMachine;
import net.ethobat.system0.machinery.FirstArcanumExoticizer;
import net.ethobat.system0.network.sources.S0BasicAggregator;
import net.ethobat.system0.network.sources.TrickleAggregator;
import net.ethobat.system0.network.FlashtinDipole;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;

public final class S0Blocks {

    public static final S0Block ROUGH_ROCK = new S0Block(FabricBlockSettings.copy(Blocks.STONE), "rough_rock");
    public static final StorageBlock STORAGE_BLOCK = new StorageBlock("storage_block");
    public static final S0Block INDIGONITE_TRIM = new S0Block(FabricBlockSettings.copy(Blocks.IRON_BLOCK), "indigonite_trim");
    public static final GlaucousGourd GLAUCOUS_GOURD = new GlaucousGourd(); // "glaucous_gourd"
    public static final S0Block ICON_TROPHY = new S0Block(FabricBlockSettings.copy(Blocks.IRON_BLOCK), "icon_trophy");

    // Aggregators
    public static final S0BasicAggregator TRICKLE_AGGREGATOR = new TrickleAggregator("trickle_aggregator");

    // Machinery
    public static final DebugEnergySource DEBUG_ENERGY_SOURCE = new DebugEnergySource(); // "debug_energy_source"
    public static final DebugMachine DEBUG_MACHINE = new DebugMachine(); // "debug_machine"
    public static final FirstArcanumExoticizer FIRST_ARCANUM_EXOTICIZER = new FirstArcanumExoticizer(); // "first_arcanum_exoticizer"

    // Network
    public static final FlashtinDipole FLASHTIN_DIPOLE = new FlashtinDipole(); // "flashtin_dipole"

    static {
        GLAUCOUS_GOURD.getItem().giveTooltip();
    }

    public static void init() {

    }

}
