package net.ethobat.system0.registry;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.ethobat.system0.api.gui.GUIBackground;
import net.ethobat.system0.api.psionics.PsionicProfile;
import net.ethobat.system0.api.util.MessageHandler;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;


public final class S0Commands implements Command<Object> {

    public static void init() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
                dispatcher.register(literal("s0")
                        .then(
                                literal("debug")
                                        .executes(S0Commands::executeDebug)
                        )
                        .then(
                                literal("test").then (
                                    argument("dbgtestarg", IntegerArgumentType.integer())
                                        .executes(S0Commands::dbgtest)
                                )
                        )
                        .then(
                                literal("hello")
                                        .executes(ctx -> executeSay(ctx, "hello world!"))
                        )
                        .then(
                                literal("player").then (
                                    argument("player", EntityArgumentType.players())
                                    .then(
                                        literal("psi")
                                            .executes(S0Commands::executeShowPsionics)
                                    )
                                    .executes(ctx -> executeSay(ctx, "[/s0 player ... ] No subcommand recognized."))
                                )
                        )
                        .executes(ctx -> print(ctx, "[/s0 ...] No subcommand recognized."))
                )
        );
    }

    @Override
    public int run(CommandContext<Object> context) throws CommandSyntaxException {
        return 0;
    }

    public static int print(CommandContext<ServerCommandSource> ctx, String str) {
        System.out.println(str);
        return 1;
    }

    public static int executeDebug(CommandContext<ServerCommandSource> ctx) {
        return 1;
    }

    public static int executeSay(CommandContext<ServerCommandSource> ctx, String str) throws CommandSyntaxException {
        MessageHandler.say(str, ctx.getSource().getPlayer());
        return 1;
    }

    public static int executeShowPsionics(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
        PlayerEntity player = EntityArgumentType.getPlayer(ctx, "player");
        MessageHandler.say(PsionicProfile.ofPlayer(player).toString(), player);
        return 1;
    }

    private static int dbgtest(CommandContext<ServerCommandSource> ctx) {
        GUIBackground.TILE_SIZE = ctx.getArgument("dbgtestarg", Integer.class);
        return 1;
    }

}
