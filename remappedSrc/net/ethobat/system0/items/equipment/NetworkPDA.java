package net.ethobat.system0.items.equipment;

import net.ethobat.system0.System0;
import net.ethobat.system0.api.nbt.NBTHandler;
import net.ethobat.system0.api.network.abstracted.Network;
import net.ethobat.system0.api.util.MessageHandler;
import net.ethobat.system0.auxiliary.S0Item;
import net.ethobat.system0.api.network.WorldNetworks;
import net.ethobat.system0.registry.S0Items;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.UUID;

public class NetworkPDA extends S0Item {

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
            return WorldNetworks.getNetwork(NBTHandler.getUUID(stack.getNbt(), "network"));
        } else {
            return null;
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (user.isSneaking()) {
            UUID uuid = UUID.randomUUID();
            NBTHandler.genericPut(stack.getOrCreateNbt(), "network", uuid);
            MessageHandler.displayActionBarMessage(uuid.toString(), user);
        }
        return TypedActionResult.pass(stack);
    }

}
