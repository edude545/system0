package net.ethobat.system0.network;

import net.ethobat.system0.api.network.WorldNetworks;
import net.ethobat.system0.api.network.abstracted.AbstractedSource;
import net.ethobat.system0.api.network.abstracted.IAbstractedNetworkMember;
import net.ethobat.system0.api.network.abstracted.Network;
import net.ethobat.system0.auxiliary.S0Block;
import net.ethobat.system0.auxiliary.S0BlockEntity;
import net.ethobat.system0.auxiliary.S0BlockWithEntity;
import net.ethobat.system0.items.equipment.NetworkPDA;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.listener.GameEventListener;
import org.jetbrains.annotations.Nullable;

public abstract class S0NetworkBlock extends S0BlockWithEntity {

    public S0NetworkBlock(Settings settings, String registryName) {
        super(settings, registryName);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        if (placer instanceof PlayerEntity) {           // If placed by a player...
            Network network = NetworkPDA.getPlayersDefaultNetwork((PlayerEntity) placer);
            if (network != null) {
                ((BE)getBlockEntity(world, pos))        // ...get this block's block entity...
                        .subscribe(network);            // ... and have it subscribe to the player's default network
            }
        }
    }

    // Most network blocks don't need to tick - all the logic happens in the abstract network.
    // Only stuff like Artery Aggregators, which require active upkeep, need to tick.
    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return null;
    }

    public abstract static class BE extends S0BlockEntity {

        public <T extends S0Block> BE(BlockEntityType<?> type, BlockPos pos, BlockState state) {
            super(type, pos, state);
        }

        public abstract void subscribe(Network network);

    }

}
