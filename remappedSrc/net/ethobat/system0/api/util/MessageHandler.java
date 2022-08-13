package net.ethobat.system0.api.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class MessageHandler {

    public static void say(Text text, PlayerEntity player) {
        player.sendMessage(text, false);
    }

    public static void say(String str, PlayerEntity player) {
        say(Text.literal(str), player);
    }

    public static void displayActionBarMessage(Text text, PlayerEntity player) {
        player.sendMessage(text, true);
    }

    public static void displayActionBarMessage(String str, PlayerEntity playerEntity) {
        displayActionBarMessage(Text.literal(str), playerEntity);
    }

}
