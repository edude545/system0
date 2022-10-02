package net.ethobat.system0.auxiliary;

import net.ethobat.system0.api.network.AbstractNWMember;
import net.ethobat.system0.items.equipment.NetworkPDA;
import net.ethobat.system0.registry.S0Components;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public abstract class S0NetworkBlock extends S0BlockWithEntity {

    public S0NetworkBlock(Settings settings, String registryName) {
        super(settings, registryName);
    }

    @Override
    public void onPlaced(World world, BlockPos blockPos, BlockState blockState, @Nullable LivingEntity livingEntity, ItemStack itemStack) {
        super.onPlaced(world, blockPos, blockState, livingEntity, itemStack);
        if (!world.isClient()) {
            BE blockEntity = (BE) world.getBlockEntity(blockPos);
            assert blockEntity != null;
            if (livingEntity instanceof PlayerEntity) {
                blockEntity.subscribe(NetworkPDA.getPlayersDefaultNetwork((PlayerEntity) livingEntity));
            }
        }
    }

    // Only active sources need to tick.
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return null;
    }

    public static abstract class BE extends S0BlockEntity {

        private final AbstractNWMember networkMember;

        public <T extends S0Block> BE(BlockEntityType<?> type, BlockPos pos, BlockState state) {
            super(type, pos, state);
            this.networkMember = this.createNetworkMember();
        }

        // Serverside methods
        public abstract AbstractNWMember createNetworkMember();

        public AbstractNWMember getNetworkMember() {
            return this.networkMember;
        }

        @Override
        public void onRemoved() {
            super.onRemoved();
            if (!this.world.isClient() && this.networkMember.network != null) {
                this.networkMember.delete();
            }
        }

        public void subscribe(UUID networkID) {
            S0Components.LEVEL_NETWORKS.get(this.world.getLevelProperties()).safeRegister(networkID, this.getNetworkMember());
        }

    }

}
