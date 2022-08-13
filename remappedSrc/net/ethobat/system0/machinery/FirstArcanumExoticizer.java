package net.ethobat.system0.machinery;

import net.ethobat.system0.auxiliary.S0Block;
import net.ethobat.system0.registry.S0BlockEntityTypes;
import net.ethobat.system0.registry.S0ScreenHandlerTypes;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
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
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class FirstArcanumExoticizer extends S0Machine {

    public static final DirectionProperty FACING = DirectionProperty.of("south");

    public FirstArcanumExoticizer() {
        super(FabricBlockSettings.copy(Blocks.IRON_BLOCK).nonOpaque(), "first_arcanum_exoticizer");
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
        return new net.ethobat.system0.machinery.FirstArcanumExoticizer.BE(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return net.ethobat.system0.machinery.FirstArcanumExoticizer.BE::tick;
    }

    public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }

    public static class BE extends S0Machine.BE {

        // 0: input
        // 1: output
        public DefaultedList<ItemStack> inv = DefaultedList.ofSize(9, ItemStack.EMPTY);

        @Override
        public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInv, PlayerEntity player) {
            //We provide *this* to the screenHandler as our class Implements Inventory
            //Only the Server has the Inventory at the start, this will be synced to the client in the ScreenHandler
            return new net.ethobat.system0.machinery.FirstArcanumExoticizer.SH(syncId, playerInv, this);
        }

        public <T extends S0Block> BE(BlockPos pos, BlockState state) {
            super(S0BlockEntityTypes.FIRST_ARCANUM_EXOTICIZER, pos, state);
        }

        @Override
        public DefaultedList<ItemStack> getItems() {
            return this.inv;
        }

        public static <T extends BlockEntity> void tick(World world, BlockPos pos, BlockState state, T be) {
        }



        @Override
        public NbtCompound createWidgetNBT(ServerPlayerEntity player, NbtCompound nbt) {
            return null;
        }

    }

    public static class SH extends S0Machine.SH {

//        // SIMPLE screen handler constructor! extremely fucking cursed
//        public SH(int syncID, PlayerInventory playerInv) {
//            this(syncID, playerInv, new SimpleInventory(9)); // calling the server constructor
//        }

        // Server constructor; server knows container's inventory and can directly provide it as an argument.
        // This inventory will then be synced to the client.
        public SH(int syncID, PlayerInventory playerInv, Inventory inv) {
            super(syncID, playerInv, inv);
            checkSize(inv, 9);
            //this.place3x3GridSlots(inv, 62, 17);
            //this.placePlayerInvSlots(playerInv, 8, 84);
            //this.placePlayerHotbarSlots(playerInv, 8, 142);
        }

        // Client constructor
        public SH(int syncID, PlayerInventory playerInv, PacketByteBuf buf) {
            this(syncID, playerInv, new SimpleInventory(9));
            checkSize(inv, 9);
            //this.updateWidgetsFromNBT(buf.readNbt());
        }

        @Override
        public ScreenHandlerType<?> getScreenHandlerType() {
            return S0ScreenHandlerTypes.FIRST_ARCANUM_EXOTICIZER;
        }

    }

    public static class HS extends S0Machine.HS<net.ethobat.system0.machinery.FirstArcanumExoticizer.SH> {

        public HS(ScreenHandler handler, PlayerInventory inventory, Text title) {
            //super((SH) handler, inventory, new LiteralText(String.valueOf(((BE) ((SH) handler).getBlockEntity()).on)));
            super((net.ethobat.system0.machinery.FirstArcanumExoticizer.SH) handler, inventory, Text.literal(((net.ethobat.system0.machinery.FirstArcanumExoticizer.SH) handler).pos.toShortString()));
        }

        @Override
        public Identifier getTextureIdentifier() {
            return new Identifier("minecraft", "textures/gui/container/dispenser.png");
        }

    }

}
