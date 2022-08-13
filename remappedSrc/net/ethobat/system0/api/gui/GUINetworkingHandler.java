package net.ethobat.system0.api.gui;

import net.ethobat.system0.System0;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public final class GUINetworkingHandler {

    public static Identifier WIDGET_CHANNEL_ID = new Identifier(System0.MOD_ID, "widget_data");

    public static void sendWidgetData(ServerPlayerEntity user, NbtCompound nbt) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeNbt(nbt);
        ServerPlayNetworking.send(user, WIDGET_CHANNEL_ID, buf);
    }

    public static void init() {
        ClientPlayNetworking.registerGlobalReceiver(WIDGET_CHANNEL_ID, GUINetworkingHandler::receive);
    }

    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        WidgetedScreen<?> screen = (WidgetedScreen<?>) client.currentScreen;
        // This should never be called without a screen open, but there technically is no screen open on the tick it's closed.
        if (screen != null) {
            NbtCompound nbt = buf.readNbt();
            screen.getScreenHandler().updateWidgetsFromNBT(nbt);
        }
    }

}
