package net.ethobat.system0.api.gui;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.HashMap;

public abstract class WidgetedItem extends Item {

    public WidgetedItem(Settings settings) {
        super(settings);
    }

    // This method is for Blocks that have a WidgetedBlockEntity associated with them.
    // Safe to call on both sides.
    public static void openScreenFromItemStack(World world, ItemStack stack, PlayerEntity player) {
        if (!world.isClient) {
            WidgetedItem item = (WidgetedItem) stack.getItem();
            ItemStackProxy proxy = item.createItemStackProxy(stack);
            player.openHandledScreen(proxy);
            proxy.userSHRecord.put((ServerPlayerEntity) player, player.currentScreenHandler);
        }
    }

    // Safe to call on both sides.
    public <T extends ItemStackProxy> void syncScreenData(World world, ItemStackProxy proxy) {
        assert !world.isClient();
        for (ServerPlayerEntity user : proxy.userSHRecord.keySet()) {
            if (user.currentScreenHandler == proxy.userSHRecord.get(user)) {
                GUINetworkingHandler.sendWidgetPacket(user, proxy.createWidgetNBT(user, new NbtCompound()));
            } else {
                proxy.userSHRecord.remove(user);
            }

        }
    }

    public abstract ItemStackProxy createItemStackProxy(ItemStack stack);

    public abstract static class ItemStackProxy implements ExtendedScreenHandlerFactory {

        public ItemStack stack;
        public Item item;

        // Maps users to the ScreenHandlers opened by this ItemStackProxy.
        // When the user's currentScreenHandler no longer matches this record, the player is removed from the map.
        public HashMap<ServerPlayerEntity,ScreenHandler> userSHRecord = new HashMap<>();

        public ItemStackProxy(ItemStack stack) {
            this.stack = stack;
            this.item = this.stack.getItem();
        }

        @Override
        public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
            buf.writeNbt(this.createWidgetNBT(player, new NbtCompound()));
        }

        public abstract NbtCompound createWidgetNBT(ServerPlayerEntity player, NbtCompound nbt);

        @Override
        public Text getDisplayName() {
            return Text.translatable(this.stack.getTranslationKey());
        }

    }

}
