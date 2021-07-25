package net.ethobat.system0.auxiliary;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public interface IS0Inventory extends Inventory {

    DefaultedList<ItemStack> getItems();

    @Override
    default int size() {
        return this.getItems().size();
    }

    @Override
    default boolean isEmpty() {
        return getItems().isEmpty();
    }

    @Override
    default ItemStack getStack(int slot) {
        return getItems().get(slot);
    }

    @Override
    default ItemStack removeStack(int slot, int amount) {
        ItemStack result = Inventories.splitStack(getItems(), slot, amount);
        if (!result.isEmpty()) {
            markDirty();
        }
        return result;
    }

    @Override
    default ItemStack removeStack(int slot) {
        return Inventories.removeStack(getItems(), slot);
    }

    // Overwrites the existing stack.
    @Override
    default void setStack(int slot, ItemStack stack) {
        getItems().set(slot, stack);
//        if (stack.getCount() > this.getMaxCountPerStack()) {
//            stack.setCount(this.getMaxCountPerStack());
//        }
    }

    // Tells the game the inventory has been updated.
    @Override
    default void markDirty() {
        // No special behaviour has to be implemented for this, the method just has to exist.
    }

    @Override
    default boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    @Override
    default void clear() {
        getItems().clear();
    }

}
