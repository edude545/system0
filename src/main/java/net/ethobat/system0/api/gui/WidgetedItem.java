package net.ethobat.system0.api.gui;

import net.ethobat.system0.api.nbt.NBTHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.NameTagItem;
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

    // Server method.
    public static void openScreenFromItemStack(World world, ItemStackProxy proxy, PlayerEntity player) {
        player.openHandledScreen(proxy);
    }

    // WidgetedItems should extend this class.
    public abstract static class ItemStackProxy implements ExtendedScreenHandlerFactory {

        public ServerPlayerEntity player;
        public ItemStack stack;
        public Item item;

        public ItemStackProxy(ServerPlayerEntity player, ItemStack stack) {
            this.player = player;
            this.stack = stack;
            this.item = this.stack.getItem();
        }

        // Server method.
        public <T extends ItemStackProxy> void syncScreenData(World world) {
            GUINetworkingHandler.sendWidgetPacket(player, this.createWidgetNBT(player, new NbtCompound()));
        }

        @Override
        public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
            NbtCompound nbt = new NbtCompound();
            this.writeScreenOpeningNBT(player, nbt);
            buf.writeNbt(nbt);
        }

        public void writeScreenOpeningNBT(ServerPlayerEntity player, NbtCompound nbt) {
            nbt.put(NBTHandler.WIDGET_NBT_KEY, this.createWidgetNBT(player, new NbtCompound()));
        }

        // Read the ItemStack's NBT and convert into widget data, to be loaded by the server & client.
        public abstract NbtCompound createWidgetNBT(ServerPlayerEntity player, NbtCompound nbt);

        @Override
        public Text getDisplayName() {
            return Text.translatable(this.stack.getTranslationKey());
        }

    }

}
