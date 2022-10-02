package net.ethobat.system0.api.gui;

import net.ethobat.system0.System0;
import net.ethobat.system0.api.util.MouseButton;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public final class GUINetworkingHandler {

    public static Identifier WIDGET_CHANNEL_ID = new Identifier(System0.MOD_ID, "widget_data");
    public static Identifier MOUSE_INTERACT_CHANNEL_ID = new Identifier(System0.MOD_ID, "screen_mouse_interact_data");
    public static Identifier MOUSE_SCROLL_CHANNEL = new Identifier(System0.MOD_ID, "screen_mouse_scroll_data");

    public static void init() {
        ClientPlayNetworking.registerGlobalReceiver(WIDGET_CHANNEL_ID, GUINetworkingHandler::receiveWidgetPacket);
        ServerPlayNetworking.registerGlobalReceiver(MOUSE_INTERACT_CHANNEL_ID, GUINetworkingHandler::receiveMouseInteractPacket);
        ServerPlayNetworking.registerGlobalReceiver(MOUSE_SCROLL_CHANNEL, GUINetworkingHandler::receiveMouseScrollPacket);
    }

    // Sent by server. NBT here contains all widget data of the server ScreenHandler, and is received by the client ScreenHandler.
    public static void sendWidgetPacket(ServerPlayerEntity user, NbtCompound nbt) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeNbt(nbt);
        ServerPlayNetworking.send(user, WIDGET_CHANNEL_ID, buf);
    }

    // Received by client.
    public static void receiveWidgetPacket(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        WidgetedScreen<?> screen = (WidgetedScreen<?>) client.currentScreen;
        // Workaround - Although widget packets should never be received without a screen open, there is technically no screen open on the tick it's closed.
        if (screen != null) {
            NbtCompound nbt = buf.readNbt();
            screen.getScreenHandler().updateWidgetsFromNBT(nbt);
        }
    }

    // Sent by client.
    public static void sendMouseInteractPacket(int screenPosX, int screenPosY, double mouseX, double mouseY, int mouseButton, boolean released) {
        PacketByteBuf buf = PacketByteBufs.create();
        NbtCompound nbt = new NbtCompound();
        nbt.putInt("screenPosX", screenPosX); nbt.putInt("screenPosY", screenPosY);
        nbt.putDouble("mouseX", mouseX); nbt.putDouble("mouseY", mouseY);
        nbt.putInt("mouseButton", mouseButton); nbt.putBoolean("released", released);
        buf.writeNbt(nbt);
        ClientPlayNetworking.send(MOUSE_INTERACT_CHANNEL_ID, buf);
    }

    // Received by server.
    public static void receiveMouseInteractPacket(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        WidgetedScreenHandler screenHandler = (WidgetedScreenHandler) player.currentScreenHandler;
        NbtCompound nbt = buf.readNbt(); assert nbt != null;
        screenHandler.mouseInteract(
                nbt.getInt("screenPosX"),
                nbt.getInt("screenPosY"),
                nbt.getDouble("mouseX"),
                nbt.getDouble("mouseY"),
                MouseButton.fromInt(nbt.getInt("mouseButton")),
                nbt.getBoolean("released")
        );
    }

    // Sent by client.
    public static void sendMouseScrollPacket(int screenPosX, int screenPosY, double mouseX, double mouseY, double direction) {
        PacketByteBuf buf = PacketByteBufs.create();
        NbtCompound nbt = new NbtCompound();
        nbt.putInt("screenPosX", screenPosX); nbt.putInt("screenPosY", screenPosY);
        nbt.putDouble("mouseX", mouseX); nbt.putDouble("mouseY", mouseY);
        nbt.putDouble("direction", direction);
        buf.writeNbt(nbt);
        ClientPlayNetworking.send(MOUSE_SCROLL_CHANNEL, buf);
    }

    // Received by server.
    public static void receiveMouseScrollPacket(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        WidgetedScreenHandler screenHandler = (WidgetedScreenHandler) player.currentScreenHandler;
        NbtCompound nbt = buf.readNbt(); assert nbt != null;
        screenHandler.mouseScroll(
                nbt.getInt("screenPosX"),
                nbt.getInt("screenPosY"),
                nbt.getDouble("mouseX"),
                nbt.getDouble("mouseY"),
                nbt.getDouble("direction")
        );
    }

}
