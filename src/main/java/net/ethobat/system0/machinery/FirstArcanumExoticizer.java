package net.ethobat.system0.machinery;

import net.ethobat.system0.auxiliary.S0Block;
import net.ethobat.system0.auxiliary.S0Machine;
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
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
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
        return new BE(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return BE::tick;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);
            if (player.isSneaking()) {
                ((BE) world.getBlockEntity(pos)).toggleMachine();
                return ActionResult.SUCCESS;
            }
            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }

        }
        return ActionResult.SUCCESS;
    }

    public static class BE extends S0Machine.BE {

        // 0: input
        // 1: output
        public DefaultedList<ItemStack> inv = DefaultedList.ofSize(9, ItemStack.EMPTY);

        @Override
        public DefaultedList<ItemStack> getItems() {
            return inv;
        }

        @Override
        public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
            //We provide *this* to the screenHandler as our class Implements Inventory
            //Only the Server has the Inventory at the start, this will be synced to the client in the ScreenHandler
            return new SH(syncId, playerInventory, this);
        }

        public <T extends S0Block> BE(BlockPos pos, BlockState state) {
            super(S0BlockEntityTypes.FIRST_ARCANUM_EXOTICIZER, pos, state);
        }

        public static <T extends BlockEntity> void tick(World world, BlockPos pos, BlockState state, T be) {
        }

        @Override
        public void readNbt(NbtCompound nbt) {
            super.readNbt(nbt);
            Inventories.readNbt(nbt, this.getItems());
        }

        @Override
        public NbtCompound writeNbt(NbtCompound nbt) {
            super.writeNbt(nbt);
            Inventories.writeNbt(nbt, this.getItems());
            return nbt;
        }

    }

    public static class SH extends S0Machine.SH {

//        // SIMPLE screen handler constructor! extremely fucking cursed
//        public SH(int syncID, PlayerInventory playerInv) {
//            this(syncID, playerInv, new SimpleInventory(9)); // calling the server constructor
//        }

        public SH(int syncID, PlayerInventory playerInv, PacketByteBuf buf) {
            super(syncID, playerInv, buf);
        }

        // Server constructor; server knows container's inventory and can directly provide it as an argument.
        // This inventory will then be synced to the client.
        public SH(int syncID, PlayerInventory playerInv, Inventory inv) {
            super(syncID, playerInv, inv);
            checkSize(inv, 9);
            this.place3x3GridSlots(inv, 0, 0);
            this.placePlayerInvSlots(playerInv, 0, 0);
            this.placePlayerHotbarSlots(playerInv, 0, 0);
        }

        @Override
        public ScreenHandlerType<?> getScreenHandlerType() {
            return S0ScreenHandlerTypes.FIRST_ARCANUM_EXOTICIZER;
        }

        @Override
        public Inventory getEmptyInventory() {
            return new SimpleInventory(9);
        }

    }

    public static class HS extends S0Machine.HS<SH> {

        public HS(ScreenHandler handler, PlayerInventory inventory, Text title) {
            //super((SH) handler, inventory, new LiteralText(String.valueOf(((BE) ((SH) handler).getBlockEntity()).on)));
            super((SH) handler, inventory, new LiteralText(((SH) handler).pos.toShortString()));
        }

        @Override
        public Identifier getTextureIdentifier() {
            return new Identifier("minecraft", "textures/gui/container/dispenser.png");
        }

    }

}
