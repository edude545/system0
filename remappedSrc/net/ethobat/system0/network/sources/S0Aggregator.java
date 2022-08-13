package net.ethobat.system0.network.sources;

import net.ethobat.system0.api.network.abstracted.AbstractedSource;
import net.ethobat.system0.auxiliary.S0Block;
import net.ethobat.system0.auxiliary.S0BlockEntity;
import net.ethobat.system0.network.S0NetworkBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.listener.GameEventListener;
import org.jetbrains.annotations.Nullable;

public abstract class S0Aggregator extends S0NetworkBlock {

    public S0Aggregator(AbstractBlock.Settings settings, String registryName) {
        super(settings, registryName);
    }

    // There is no need for basic aggregators to tick. They simply provide a fixed amount of energy as a SimpleMonoSource to the network.
    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return null;
    }

    public static abstract class BE extends S0NetworkBlock.BE {

        public <T extends S0Block> BE(BlockEntityType<?> type, BlockPos pos, BlockState state) {
            super(type, pos, state);
        }

    }


}
