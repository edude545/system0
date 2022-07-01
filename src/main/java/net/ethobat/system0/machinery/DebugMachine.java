package net.ethobat.system0.machinery;

import net.ethobat.system0.System0;
import net.ethobat.system0.api.color.RGB;
import net.ethobat.system0.api.energy.SimpleEnergyContainer;
import net.ethobat.system0.api.gui.widgets.PowerBar;
import net.ethobat.system0.api.gui.widgets.WPowerBarMediumVert;
import net.ethobat.system0.api.gui.widgets.WSlot;
import net.ethobat.system0.api.util.MessageHandler;
import net.ethobat.system0.registry.S0BlockEntityTypes;
import net.ethobat.system0.registry.S0EnergyTypes;
import net.ethobat.system0.registry.S0ScreenHandlerTypes;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
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
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DebugMachine extends S0Machine {

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

        public WSlot input;

        public SimpleEnergyContainer skeinPower = new SimpleEnergyContainer(1000, S0EnergyTypes.SKEINTILLATING);

        public int n;

        public DefaultedList<ItemStack> inv = DefaultedList.ofSize(2, ItemStack.EMPTY);

        public BE(BlockPos pos, BlockState state) {
            super(S0BlockEntityTypes.DEBUG_MACHINE, pos, state);
        }

        @Override
        public void readNbt(NbtCompound nbt) {
            super.readNbt(nbt);
            this.skeinPower.amt = nbt.getLong("skeinPower");
        }

        @Override
        public NbtCompound writeNbt(NbtCompound nbt) {
            super.writeNbt(nbt);
            nbt.putLong("skeinPower", this.skeinPower.amt);
            return nbt;
        }

        @Override
        public DefaultedList<ItemStack> getItems() {
            return this.inv;
        }

        public static <T extends BlockEntity> void tick(World world, BlockPos pos, BlockState state, T be) {
    //        if (world.isClient && world.getTime()%20==0) {
    //            System.out.println("Tick...");
    //        }
            BE be_ = (BE) be;
            be_.syncScreenData();
            if (!world.isClient()) {
//                System.out.println(be_.getClass().toString());
                if (be_.skeinPower.amt >= 1000) {
                    be_.skeinPower.amt = 0;
                } else {
                    be_.skeinPower.put(5);
                }
//                Direction direction = be.getCachedState().get(Properties.FACING);
//                if (WorldReader.isInBlockLine(S0Blocks.DEBUG_MACHINE, world, pos, direction, 8)) {
//                    System.out.println("Found another debug machine!");
//                }
            }
        }

        @Override
        public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInv, PlayerEntity player) {
            return new SH(syncId, playerInv, this);
        }

        @Override
        public NbtCompound createWidgetNBT(ServerPlayerEntity player, NbtCompound nbt) {
            PowerBar.writeNBT(nbt, "mains", this.skeinPower);
            return nbt;
        }

    }

    public static class SH extends S0Machine.SH {

        public WSlot input;
        public WSlot output;
        public PowerBar skeinPowerBar;

        // Server constructor
        public SH(int syncId, PlayerInventory playerInv, Inventory inv) {
            super(syncId, playerInv, inv);

            //checkSize(inv, 2);

            this.input = new WSlot("input", 43, 34, this.getInventory(), 0);
            this.output = new WSlot("output", 115, 34, this.getInventory(), 1).setOutput();
            this.skeinPowerBar = new WPowerBarMediumVert("mains", 21, 26);

            skeinPowerBar.energyType = S0EnergyTypes.SKEINTILLATING;

            this.addWidget(this.input);
            this.addWidget(this.output);
            this.addWidget(this.skeinPowerBar);
        }

        // Client constructor
        public SH(int syncId, PlayerInventory playerInv, PacketByteBuf buf) {
            this(syncId, playerInv, new SimpleInventory(2));
            //checkSize(inv, 2);
            NbtCompound nbt = buf.readNbt();
            this.updateWidgetsFromNBT(nbt);
        }

        @Override
        public ScreenHandlerType<?> getScreenHandlerType() {
            return S0ScreenHandlerTypes.DEBUG_MACHINE;
        }

//        public long getEnergy() {
//            return 3000;
//        }

    }

    public static class HS extends S0Machine.HS<SH> {

        public HS(SH handler, PlayerInventory inventory, Text title) {
            super(handler, inventory, title);
        }

        @Override
        public Identifier getTextureIdentifier() {
            return new Identifier("system0", "textures/gui/simple_machine.png");
        }

        @Override
        public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
            super.render(matrices, mouseX, mouseY, delta);
            //textRenderer.draw(matrices, String.valueOf(handler.getEnergy()), 0, 0, new RGB(0,255,0).asInt());dr
        }

    }

}
