package net.ethobat.system0.machinery;

import net.ethobat.system0.api.gui.WidgetedBlockEntity;
import net.ethobat.system0.api.gui.widgets.WPlayerInventory;
import net.ethobat.system0.auxiliary.*;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class S0Machine extends S0BlockWithEntity {

    public S0Machine(Settings settings, String registryName) {
        super(settings, registryName);
    }

    // Do stuff when the machine is broken/replaced.
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof BE) {
                ItemScatterer.spawn(world, pos, (BE) be);
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        WidgetedBlockEntity.openScreenFromBlock(state, world, pos, player);
        return ActionResult.SUCCESS;
    }

//    // reflection sorcery to make things clean (ﾉ◕ヮ◕)ﾉ*:･ﾟ✧.:*･゜ﾟ･*☆
//    // note to self: i wrote this a while ago and im not sure if its actually useful... DebugMachine just overrides this method with "return BE::tick"
//    @Override
//    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
//        return (world_, pos_, state_, be_) -> {
//            try {
//                System.out.println(this.getClass().getName());
//                Object j = Class.forName("net.ethobat.system0.machinery.DebugMachine$BE");
//                j = Class.forName("java.lang.ClassLoader");
//                Class.forName(this.getClass().toString() + "$BE")
//                        .getMethod("tick", World.class, BlockPos.class, BlockState.class, BlockEntity.class)
//                            .invoke(null, world_, pos_, state_, be_);
//            } catch (NoSuchMethodException | ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
//                e.printStackTrace(); // net.ethobat.system0.machinery.DebugMachine$BE
//            }
//        };
//    }

    public abstract static class BE extends WidgetedBlockEntity implements IS0Inventory {

        public boolean on;
        public DefaultedList<ItemStack> inv;

        public <T extends S0Block> BE(BlockEntityType<?> type, BlockPos pos, BlockState state) {
            super(type, pos, state);
        }

        @Override
        public void readNbt(NbtCompound nbt) {
            super.readNbt(nbt);
            Inventories.readNbt(nbt, this.getItems());
        }

        @Override
        public void writeNbt(NbtCompound nbt) {
            super.writeNbt(nbt);
            Inventories.writeNbt(nbt, this.getItems());
        }

        public boolean toggleMachine() {
            return this.on = !this.on;
        }

    }

    public abstract static class SH extends S0ScreenHandler {

        public WPlayerInventory playerInventory;

        public BlockPos pos;
        public boolean on;

        public Inventory inv;

        // Server constructor; the server knows the inventory of the container and can directly provide it as an argument.
        // This inventory will then be synced to the client.
        public SH(int syncId, PlayerInventory playerInv, Inventory inv) {
            super(null, syncId, playerInv); // type constructor

            this.playerInventory = new WPlayerInventory("playerInventory", 7, 70, playerInv);
            this.addWidget(this.playerInventory);

            this.inv = inv;
            this.pos = BlockPos.ORIGIN;
            this.on = false;

            inv.onOpen(playerInv.player);
        }

//        // Client constructor; server sends PacketByteBuf to client. PlayerInventory is already present on the client, and doesn't need to be synced here.
//        public SH(int syncId, PlayerInventory playerInv, PacketByteBuf buf) {
//            this(syncId, playerInv, new SimpleInventory(1)); // Dummy placeholder inv argument.
//        }

        @Override
        public Inventory getInventory() {
            return this.inv;
        }

        @Override
        public boolean canUse(PlayerEntity player) {
            return true;
        }

    }

    public abstract static class HS<T extends SH> extends S0HandledScreen<T> {

        public HS(T handler, PlayerInventory inventory, Text title) {
            super(handler, inventory, title);
        }

        @Override
        public int getBGHeight() {
            return 41;
        }

        @Override
        public int getBGWidth() {
            return 44;
        }

    }

}
