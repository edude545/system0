package net.ethobat.system0.registry;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.ethobat.system0.api.network.NWDrain;
import net.ethobat.system0.api.network.NWNode;
import net.ethobat.system0.api.network.NWSource;
import net.ethobat.system0.api.network.Network;
import net.ethobat.system0.api.psionics.PsionicProfile;
import net.ethobat.system0.api.savedata.INetworksComponent;
import net.ethobat.system0.api.savedata.LevelNetworks;
import net.ethobat.system0.api.util.MessageHandler;
import net.ethobat.system0.items.equipment.NetworkPDA;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;

import java.util.UUID;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;


public final class S0Commands implements Command<Object> {

    public static void init() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
                dispatcher.register(literal("s0")
                        .then(
                                literal("network")
                                        .then(
                                                literal("create")
                                                        .executes(S0Commands::executeCreateNetwork)
                                        )
                                        .then (
                                                literal("list")
                                                        .executes(S0Commands::executeListNetworks)
                                        )
                                        .then (
                                                literal("debugclear")
                                                        .executes(S0Commands::executeDebugClearNetworks)
                                        )
                                        .then (
                                                literal("createDebugNetwork")
                                                        .executes(S0Commands::executeCreateDebugNetwork)
                                        )
                        )
                        .then(
                                literal("debug")
                                        .executes(S0Commands::executeDebug)
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

    public static int executeCreateNetwork(CommandContext<ServerCommandSource> ctx) {
        S0Components.LEVEL_NETWORKS.get(ctx.getSource().getWorld().getLevelProperties())
                        .createNetwork();
        return 1;
    }

    public static int executeListNetworks(CommandContext<ServerCommandSource> ctx) {
        StringBuilder msg = new StringBuilder();
        INetworksComponent networks = S0Components.LEVEL_NETWORKS.get(ctx.getSource().getWorld().getLevelProperties());
        for (Network network : networks.getNetworkMap().values()) {
            msg.append("\n- ").append(network.toString());
        }
        MessageHandler.say(msg.toString(), ctx.getSource().getPlayer());
        return 1;
    }

    public static int executeDebugClearNetworks(CommandContext<ServerCommandSource> ctx) {
        try {
            INetworksComponent networks = S0Components.LEVEL_NETWORKS.get(ctx.getSource().getWorld().getLevelProperties());
            for (UUID uuid : networks.getNetworkMap().keySet()) {
                Network net = networks.getNetwork(uuid);
                for (NWNode node : net.nodes.values()) {
                    net.deleteNode(node);
                }
                for (NWSource source : net.sources.values()) {
                    net.deleteSource(source);
                }
                for (NWDrain drain : net.drains.values()) {
                    net.deleteDrain(drain);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    public static int executeCreateDebugNetwork(CommandContext<ServerCommandSource> ctx) {
        S0Components.LEVEL_NETWORKS.get(ctx.getSource().getWorld().getLevelProperties())
                .createNetwork(UUID.fromString("9bbea3cb-2474-4b64-a441-f4be23bb3a51"));
        return 1;
    }

    public static int executeDebug(CommandContext<ServerCommandSource> ctx) {
        MessageHandler.say(NetworkPDA.getPlayersDefaultNetwork(ctx.getSource().getPlayer()).toString(), ctx.getSource().getPlayer());
        return 1;
    }

    public static int executeSay(CommandContext<ServerCommandSource> ctx, String str) {
        MessageHandler.say(str, ctx.getSource().getPlayer());
        return 1;
    }

    public static int executeShowPsionics(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
        PlayerEntity player = EntityArgumentType.getPlayer(ctx, "player");
        MessageHandler.say(PsionicProfile.ofPlayer(player).toString(), player);
        return 1;
    }

}
