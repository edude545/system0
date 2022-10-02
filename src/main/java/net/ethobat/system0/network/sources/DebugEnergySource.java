package net.ethobat.system0.network.sources;

import net.ethobat.system0.api.energy.EnergyTypeMap;
import net.ethobat.system0.api.network.AbstractNWMember;
import net.ethobat.system0.api.network.NWPassiveSource;
import net.ethobat.system0.api.network.NWSource;
import net.ethobat.system0.api.network.Network;
import net.ethobat.system0.auxiliary.S0Block;
import net.ethobat.system0.registry.S0BlockEntityTypes;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class DebugEnergySource extends S0Aggregator {

    public DebugEnergySource() {
        super(FabricBlockSettings.copy(Blocks.IRON_BLOCK), "debug_energy_source");
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BE(pos, state);
    }

    public static class BE extends S0Aggregator.BE {

        public <T extends S0Block> BE(BlockPos pos, BlockState state) {
            super(S0BlockEntityTypes.DEBUG_ENERGY_SOURCE, pos, state);
        }

        @Override
        public AbstractNWMember createNetworkMember() {
            return new NWPassiveSource(this.pos, EnergyTypeMap.maxAll());
        }
    }

}
