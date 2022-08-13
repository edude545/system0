package net.ethobat.system0.network.sources;

import net.ethobat.system0.api.energy.EnergyType;
import net.ethobat.system0.api.network.abstracted.AbstractedPassiveSource;
import net.ethobat.system0.api.network.abstracted.Network;
import net.ethobat.system0.auxiliary.S0Block;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

import java.util.UUID;

// Class for basic aggregators with fixed influx and single energy type.
public abstract class S0BasicAggregator extends S0Aggregator {

    public S0BasicAggregator(String registryName) {
        super(FabricBlockSettings.copy(Blocks.IRON_BLOCK), registryName);
    }

    public static class BE extends S0Aggregator.BE {

        public EnergyType energyType;
        public long potentialEnergy;

        public <T extends S0Block> BE(BlockEntityType<?> type, BlockPos pos, BlockState state, EnergyType energyType, long potentialEnergy) {
            super(type, pos, state);
            this.energyType = energyType;
            this.potentialEnergy = potentialEnergy;
        }

        @Override
        public void subscribe(Network network) {
            network.registerSource(AbstractedPassiveSource.mono(this.energyType, this.potentialEnergy));
        }

    }

}
