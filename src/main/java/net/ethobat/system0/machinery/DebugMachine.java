package net.ethobat.system0.machinery;

import net.ethobat.system0.api.util.WorldReader;
import net.ethobat.system0.auxiliary.S0Block;
import net.ethobat.system0.auxiliary.S0BlockEntity;
import net.ethobat.system0.auxiliary.S0BlockWithEntity;
import net.ethobat.system0.auxiliary.S0Machine;
import net.ethobat.system0.registry.S0BlockEntityTypes;
import net.ethobat.system0.registry.S0Blocks;
import net.ethobat.system0.registry.S0ScreenHandlerTypes;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DebugMachine extends S0BlockWithEntity {

    public static final DirectionProperty FACING = DirectionProperty.of("south");

    public DebugMachine() {
        super(FabricBlockSettings.copy(Blocks.IRON_BLOCK), "debug_machine");
        setDefaultState(this.getStateManager().getDefaultState().with(Properties.FACING, Direction.SOUTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(Properties.FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BE(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return BE::tick;
    }

    public static class BE extends S0Machine.BE {

        public BE(BlockPos pos, BlockState state) {
            super(S0BlockEntityTypes.DEBUG_MACHINE, pos, state);
        }

        public static <T extends BlockEntity> void tick(World world, BlockPos pos, BlockState state, T be) {
    //        if (world.isClient && world.getTime()%20==0) {
    //            System.out.println("Tick...");
    //        }
            Direction direction = be.getCachedState().get(Properties.FACING);
            if (WorldReader.isInBlockLine(S0Blocks.DEBUG_MACHINE, world, pos, direction, 8)) {
                System.out.println("Found another debug machine!");
            }
        }

        @Override
        public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
            return null;
        }

    }

    public static class SH extends S0Machine.SH {

        public SH(int syncId, PlayerInventory playerInv, Inventory inv) {
            super(syncId, playerInv, inv);
        }

        public SH(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
            super(syncId, playerInventory, buf);
        }

        @Override
        public ScreenHandlerType<?> getScreenHandlerType() {
            return S0ScreenHandlerTypes.DEBUG_MACHINE;
        }

        @Override
        public Inventory getEmptyInventory() {
            return new SimpleInventory(9);
        }

    }

    public static class HS extends S0Machine.HS<SH> {

        public HS(SH handler, PlayerInventory inventory, Text title) {
            super(handler, inventory, title);
        }

        @Override
        public Identifier getTextureIdentifier() {
            return new Identifier("system0", "textures/item/indigonite_composite.png");
        }

    }
}
