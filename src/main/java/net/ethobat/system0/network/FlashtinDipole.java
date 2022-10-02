package net.ethobat.system0.network;

import net.ethobat.system0.api.energy.EnergyType;
import net.ethobat.system0.api.network.AbstractNWMember;
import net.ethobat.system0.api.network.NWNode;
import net.ethobat.system0.auxiliary.S0NetworkBlock;
import net.ethobat.system0.registry.S0BlockEntityTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class FlashtinDipole extends S0NetworkBlock {

    public static final DirectionProperty FACING = DirectionProperty.of("south");

    public FlashtinDipole() {
        super(Settings.of(Material.DECORATION).noCollision().nonOpaque(), "flashtin_dipole");
        setDefaultState(this.getStateManager().getDefaultState().with(Properties.FACING, Direction.SOUTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(Properties.FACING, ctx.getSide());
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new BE(blockPos, blockState);
    }


    public static class BE extends S0NetworkBlock.BE {

        public BE(BlockPos pos, BlockState state) {
            super(S0BlockEntityTypes.FLASHTIN_DIPOLE, pos, state);
        }

        @Override
        public AbstractNWMember createNetworkMember() {
            return new NWNode(this.pos, 8, 12, 1.0f);
        }

    }

}