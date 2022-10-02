package net.ethobat.system0.api.gui;

import net.ethobat.system0.auxiliary.S0Block;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;

public abstract class WidgetedBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory {

    // Maps users to the ScreenHandlers opened by this BlockEntity.
    // When the user's currentScreenHandler no longer matches this record, the player is removed from the map.
    public HashMap<ServerPlayerEntity,ScreenHandler> userSHRecord = new HashMap<>();

    public <T extends S0Block> WidgetedBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    // This method is for Blocks that have a WidgetedBlockEntity associated with them.
    // Example use in a Block class:
    // @Override
    // public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
    //     ...
    //     WidgetedBlockEntity.openScreenFromBlock(state, world, pos, player);
    //     ...
    //     return ActionResult.SUCCESS;
    // }
    // Server method.
    public static void openScreenFromBlock(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);
        if (screenHandlerFactory != null) {
            WidgetedBlockEntity be = ((WidgetedBlockEntity)world.getBlockEntity(pos));
            assert be != null;
            player.openHandledScreen(screenHandlerFactory);
            be.userSHRecord.put((ServerPlayerEntity) player, player.currentScreenHandler);
        }
    }

    // Server method.
    public <T extends BlockEntity> void syncScreenData() {
        for (ServerPlayerEntity user : this.userSHRecord.keySet()) {
            if (user.currentScreenHandler == this.userSHRecord.get(user)) {
                GUINetworkingHandler.sendWidgetPacket(user, this.createWidgetNBT(user, new NbtCompound()));
            } else {
                this.userSHRecord.remove(user);
            }
        }
    }

    public Text getDisplayName() {
        return Text.translatable(this.getCachedState().getBlock().getTranslationKey());
    }

    public abstract NbtCompound createWidgetNBT(ServerPlayerEntity player, NbtCompound nbt);

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeNbt(this.createWidgetNBT(player, new NbtCompound()));
    }

}
