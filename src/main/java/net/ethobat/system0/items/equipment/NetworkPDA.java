package net.ethobat.system0.items.equipment;

import net.ethobat.system0.System0;
import net.ethobat.system0.api.gui.WidgetedItem;
import net.ethobat.system0.api.gui.WidgetedScreen;
import net.ethobat.system0.api.gui.WidgetedScreenHandler;
import net.ethobat.system0.api.gui.widgets.WNetworkViewport;
import net.ethobat.system0.api.nbt.NBTHandler;
import net.ethobat.system0.api.network.abstracted.Network;
import net.ethobat.system0.api.util.MessageHandler;
import net.ethobat.system0.api.savedata.Networks;
import net.ethobat.system0.auxiliary.S0HandledScreen;
import net.ethobat.system0.auxiliary.S0ScreenHandler;
import net.ethobat.system0.items.S0WidgetedItem;
import net.ethobat.system0.registry.S0Items;
import net.ethobat.system0.registry.S0ScreenHandlerTypes;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
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
    public static Network getPlayersDefaultNetwork(PlayerEntity user) {
        ItemStack pda = null;
        for (ItemStack stack : user.getInventory().main) {
            if (stack.getItem() == S0Items.NETWORK_PDA) {
                if (pda != null) { // return null if there are duplicate valid PDAs
                    return null;
                }
                if (getNetwork(stack) != null) { // if this pda itemstack has a valid network, keep track of it
                    pda = stack;
                }
            }
        }
        if (pda == null) {
            return null;
        }
        return getNetwork(pda);
    }

    public static Network getNetwork(ItemStack stack) {
        if (stack.hasNbt()) {
            assert stack.getNbt() != null;
            return Networks.getNetwork(NBTHandler.getUUID(stack.getNbt(), "network"));
        } else {
            return null;
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        super.use(world, user, hand);
        ItemStack stack = user.getStackInHand(hand);
        if (!user.isSneaking()) {
            WidgetedItem.openScreenFromItemStack(world, stack, user);
        } else { // Sneak + right click: Show PDA's network
            UUID uuid = UUID.randomUUID();
            NBTHandler.genericPut(stack.getOrCreateNbt(), "network", uuid);
            MessageHandler.displayActionBarMessage(uuid.toString(), user);
        }
        return TypedActionResult.pass(stack);
    }

    @Override
    public ItemStackProxy createItemStackProxy(ItemStack stack) {
        return new ISP(stack);
    }

    public static class ISP extends ItemStackProxy {

        public ISP(ItemStack stack) {
            super(stack);
        }

        @Override
        public NbtCompound createWidgetNBT(ServerPlayerEntity player, NbtCompound nbt) {
            return nbt;
        }

        @Nullable
        @Override
        public ScreenHandler createMenu(int syncID, PlayerInventory playerInventory, PlayerEntity playerEntity) {
            return new SH(syncID, playerInventory, this);
        }
    }

    public static class SH extends S0WidgetedItem.SH {

        public WNetworkViewport viewport;

        public SH(int syncID, PlayerInventory playerInv, ItemStackProxy proxy) {
            super(syncID, playerInv, proxy);

            this.viewport = new WNetworkViewport("viewport", 8, 8, 156, 136);

            this.addWidget(this.viewport);
        }

        // Client constructor
        public SH(int syncId, PlayerInventory playerInv, PacketByteBuf buf) {
            this(syncId, playerInv, new ISP(ItemStack.EMPTY));
            NbtCompound nbt = buf.readNbt();
            this.updateWidgetsFromNBT(nbt);
        }

        @Override
        public Inventory getInventory() {
            return null;
        }

        @Override
        public ScreenHandlerType<?> getType() {
            return S0ScreenHandlerTypes.NETWORK_PDA;
        }

        @Override
        public void close(PlayerEntity playerEntity) {
            super.close(playerEntity);

        }

    }

    public static class HS extends S0HandledScreen<SH> {

        public HS(SH handler, PlayerInventory inventory, Text title) {
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

}
