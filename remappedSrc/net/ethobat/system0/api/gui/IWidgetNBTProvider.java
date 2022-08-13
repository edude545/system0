package net.ethobat.system0.api.gui;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;

public interface IWidgetNBTProvider {

    NbtCompound createWidgetNBT(ServerPlayerEntity player, NbtCompound nbt);

}
