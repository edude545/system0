package net.ethobat.system0.auxiliary;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
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

    public abstract static class BE extends S0BlockEntity implements IS0Inventory,ExtendedScreenHandlerFactory {

        public boolean on;
        public DefaultedList<ItemStack> inv;

        public <T extends S0Block> BE(BlockEntityType<?> type, BlockPos pos, BlockState state) {
            super(type, pos, state);
        }

        @Override
        public Text getDisplayName() {
            return new TranslatableText(this.getCachedState().getBlock().getTranslationKey());
        }

        @Override
        public DefaultedList<ItemStack> getItems() {
            return this.inv;
        }

        @Override
        public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
            buf.writeBlockPos(this.getPos());
            buf.writeBoolean(this.on);
        }

        public boolean toggleMachine() {
            return this.on = !this.on;
        }

    }

    public abstract static class SH extends S0ScreenHandler {

        public BlockEntity blockEntity;
        public BlockPos pos;
        public boolean on;
        public Inventory inv;

        // Server constructor; the server knows the inventory of the container and can directly provide it as an argument.
        // This inventory will then be synced to the client.
        public SH(int syncId, PlayerInventory playerInv, Inventory inv) {
            super(null, syncId); // type constructor
            this.inv = inv;
            this.pos = BlockPos.ORIGIN;
            this.on = false;
            inv.onOpen(playerInv.player);
        }

        // Mystery constructor
        public SH(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
            this(syncId, playerInventory, new SimpleInventory(1));
            this.pos = buf.readBlockPos();
            this.on = buf.readBoolean();
        }

        @Override
        public ScreenHandlerType<?> getType() {
            return this.getScreenHandlerType();
        }

        public abstract ScreenHandlerType<?> getScreenHandlerType();

        public BlockEntity getBlockEntity() {
            System.out.println(this.blockEntity.toString());
            return this.blockEntity;
        }

        public void setBlockEntity(BlockEntity be) {
            this.blockEntity = be;
        }

        @Override
        public Inventory getInventory() {
            return this.inv;
        }

        public abstract Inventory getEmptyInventory();

        @Override
        public boolean canUse(PlayerEntity player) {
            return true;
        }

    }

    public abstract static class HS<T extends SH> extends S0HandledScreen<T> {

        public HS(T handler, PlayerInventory inventory, Text title) {
            super(handler, inventory, title);
        }

    }

}
