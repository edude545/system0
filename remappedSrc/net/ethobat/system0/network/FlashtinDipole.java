package net.ethobat.system0.network;

import net.ethobat.system0.api.energy.EnergyType;
import net.ethobat.system0.api.network.physical.be.AbstractBETransceiverDeprecated;
import net.ethobat.system0.auxiliary.S0Block;
import net.ethobat.system0.registry.S0BlockEntityTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
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

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new net.ethobat.system0.network.FlashtinDipole.BE(pos, state);
    }

//    @Override
//    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
//        return BE::tick;
//    }

    public static class BE extends AbstractBETransceiverDeprecated {

        public BE(BlockPos pos, BlockState state) {
            super(S0BlockEntityTypes.FLASHTIN_DIPOLE, pos, state);
        }

        public int getRange() {
            return 16;
        }
        public float getPenetration() {
            return 0;
        }
        public long getBandwidthUp() {
            return 16;
        }

        public long getBandwidthDown() {
            return 64;
        }
        public float getSensitivity() {
            return 1.5f;
        }



        public long transmitEnergy(long amt, EnergyType type) {
            return 0;
        } // TEMP
        public long occupyBandwidth(long amt) {
            return 0;
        } // TEMP

    }

}