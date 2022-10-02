package net.ethobat.system0.items.equipment;

import net.ethobat.system0.System0;
import net.ethobat.system0.api.gui.WidgetedItem;
import net.ethobat.system0.api.gui.widgets.WNetworkViewport;
import net.ethobat.system0.api.nbt.NBTHandler;
import net.ethobat.system0.api.util.MessageHandler;
import net.ethobat.system0.items.S0WidgetedItem;
import net.ethobat.system0.registry.S0Items;
import net.ethobat.system0.registry.S0ScreenHandlerTypes;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.apache.logging.log4j.core.jmx.Server;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class NetworkPDA extends S0WidgetedItem {

    /*
        Item has the following NBT:
        {
            "network" : NET-UUID
        }
    */

    public NetworkPDA() {
        super(new Settings().group(System0.ITEM_GROUP_TOOLS).maxCount(1), "network_pda");
    }

    // Players can keep a connected PDA in their network to have that network act as their "default".
    // If players have exactly one connected PDA in their inventory, placed devices will automatically subscribe to that network.
    public static UUID getPlayersDefaultNetwork(PlayerEntity user) {
        ItemStack pda = null;
        for (ItemStack stack : user.getInventory().main) {
            if (stack.getItem() == S0Items.NETWORK_PDA) {
                if (pda != null) { // Return null if there are duplicate valid PDAs.
                    return null;
                }
                if (getNetworkID(stack) != null) { // If this pda ItemStack has a valid network, keep track of it,
                    pda = stack;
                }
            }
        }
        if (pda == null) {
            return null;
        }
        return getNetworkID(pda);
    }

    public static UUID getNetworkID(ItemStack stack) {
        if (stack.hasNbt()) {
            assert stack.getNbt() != null;
            return NBTHandler.getUUID(stack.getNbt(), "network");
        } else {
            return null;
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        super.use(world, user, hand);
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient()) {
            ServerPlayerEntity player = (ServerPlayerEntity) user;
            if (!user.isSneaking()) { // Right-click: Open network viewport
                ISPViewport proxy = new ISPViewport(player, stack);
                WidgetedItem.openScreenFromItemStack(world, proxy, user);
                ((SHViewport) player.currentScreenHandler).viewport.graph.setYaw(player.getYaw());
                proxy.syncScreenData(world);
            } else { // Sneak + right-click: Open network selector
                WidgetedItem.openScreenFromItemStack(world, new ISPSelector(player, stack), user);
                NBTHandler.genericPut(stack.getOrCreateNbt(), "network", UUID.fromString("9bbea3cb-2474-4b64-a441-f4be23bb3a51"));
            }
        }
        return TypedActionResult.pass(stack);
    }

    public static class ISPViewport extends ItemStackProxy {

        public ISPViewport(ServerPlayerEntity player, ItemStack stack) {
            super(player, stack);
        }

        @Override
        public NbtCompound createWidgetNBT(ServerPlayerEntity player, NbtCompound nbt) {
            nbt.put("viewport",
                    NBTHandler.filter(this.stack.getOrCreateNbt(), "network"));
            return nbt;
        }

        @Override
        public void writeScreenOpeningNBT(ServerPlayerEntity player, NbtCompound nbt) {
            super.writeScreenOpeningNBT(player, nbt);
            nbt.putFloat("playerLookYaw", player.getYaw());
        }

        @Nullable
        @Override
        public ScreenHandler createMenu(int syncID, PlayerInventory playerInventory, PlayerEntity playerEntity) {
            return new SHViewport(syncID, false, playerInventory, this);
        }
    }

    public static class SHViewport extends S0WidgetedItem.SH {

        public WNetworkViewport viewport;

        public SHViewport(int syncID, boolean isClient, PlayerInventory playerInv, ItemStackProxy proxy) {
            super(syncID, isClient, playerInv, proxy);

            this.viewport = new WNetworkViewport("viewport", playerInv.player.getBlockPos(), 8, 8, 156, 136);

            this.addWidget(this.viewport);
        }

        // Client constructor
        public SHViewport(int syncId, PlayerInventory playerInv, PacketByteBuf buf) {
            this(syncId, true, playerInv, new ISPViewport(null,ItemStack.EMPTY));
            NbtCompound nbt = buf.readNbt();
            this.updateWidgetsFromNBT(nbt);
            this.viewport.graph.setCameraRotation(this.viewport.graph.camera.getPitch(), nbt.getFloat("playerLookYaw"));
        }



        @Override
        public Inventory getInventory() {
            return null;
        }

        @Override
        public ScreenHandlerType<?> getType() {
            return S0ScreenHandlerTypes.NETWORK_PDA_VIEWPORT;
        }

    }

    public static class HSViewport extends S0WidgetedItem.HS<SHViewport> {

        public HSViewport(SHViewport handler, PlayerInventory inventory, Text title) {
            super(handler, inventory, title);
        }

        @Override
        protected void drawTitle(MatrixStack matrixStack) {
        }

        @Override
        public int getBGWidth() {
            return 72;
        }

        @Override
        public int getBGHeight() {
            return 38;
        }

    }

    public static class ISPSelector extends ItemStackProxy {

        public ISPSelector(ServerPlayerEntity player, ItemStack stack) {
            super(player, stack);
        }

        @Override
        public NbtCompound createWidgetNBT(ServerPlayerEntity player, NbtCompound nbt) {
            return nbt;
        }

        @Nullable
        @Override
        public ScreenHandler createMenu(int syncID, PlayerInventory playerInventory, PlayerEntity playerEntity) {
            return new SHSelector(syncID, false, playerInventory, this);
        }

    }

    public static class SHSelector extends S0WidgetedItem.SH {

        public SHSelector(int syncID, boolean isClient, PlayerInventory playerInv, ItemStackProxy proxy) {
            super(syncID, isClient, playerInv, proxy);
            //this.addWidget(this.networkList);
        }

        // Client constructor
        public SHSelector(int syncId, PlayerInventory playerInv, PacketByteBuf buf) {
            this(syncId, true, playerInv, new ISPSelector(null,ItemStack.EMPTY));
            NbtCompound nbt = buf.readNbt();
            this.updateWidgetsFromNBT(nbt);
        }

        @Override
        public Inventory getInventory() {
            return null;
        }

        @Override
        public ScreenHandlerType<?> getType() {
            return S0ScreenHandlerTypes.NETWORK_PDA_SELECTOR;
        }

    }

    public static class HSSelector extends S0WidgetedItem.HS<SHSelector> {

        public HSSelector(SHSelector handler, PlayerInventory inventory, Text title) {
            super(handler, inventory, title);
        }

        @Override
        protected void drawTitle(MatrixStack matrixStack) {
        }

        @Override
        public int getBGWidth() {
            return 38;
        }

        @Override
        public int getBGHeight() {
            return 48;
        }

    }

}
