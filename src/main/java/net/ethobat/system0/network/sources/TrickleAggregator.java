package net.ethobat.system0.network.sources;

import net.ethobat.system0.api.energy.MageVeins;
import net.ethobat.system0.auxiliary.S0Block;
import net.ethobat.system0.registry.S0BlockEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class TrickleAggregator extends S0BasicAggregator {

    public TrickleAggregator(String registryName) {
        super(registryName);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BE(pos, state);
    }

    public static class BE extends S0BasicAggregator.BE {

        public <T extends S0Block> BE(BlockPos pos, BlockState state) {
            super(S0BlockEntityTypes.TRICKLE_AGGREGATOR, pos, state, MageVeins.getEnergyType(pos), MageVeins.getPotentialEnergy(pos, 1));
        }
    }

}
