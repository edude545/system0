package net.ethobat.system0.items;

import net.ethobat.system0.System0;
import net.ethobat.system0.api.gui.WidgetedItem;
import net.ethobat.system0.auxiliary.IS0Item;
import net.ethobat.system0.auxiliary.S0HandledScreen;
import net.ethobat.system0.auxiliary.S0ScreenHandler;
import net.ethobat.system0.registry.S0Registrar;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class S0WidgetedItem extends WidgetedItem implements IS0Item {

    public final String NAME;
    public boolean HAS_TOOLTIP = false;

    public S0WidgetedItem(Settings settings, String registryName) {
        super(settings);
        this.NAME = registryName;
        S0Registrar.register(this, registryName);
    }

    @Override
    public void giveTooltip() {
        this.HAS_TOOLTIP = true;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (this.HAS_TOOLTIP) {
            tooltip.add(Text.translatable("item." + System0.MOD_ID + "." + this.NAME + ".tooltip").formatted(Formatting.DARK_GRAY));
        }
    }

    public abstract static class SH extends S0ScreenHandler {

        public ItemStack stack;

        public SH(int syncID, PlayerInventory playerInv, ItemStackProxy proxy) {
            super(null, syncID, playerInv);
            this.stack = proxy.stack;
        }

        @Override
        public boolean canUse(PlayerEntity playerEntity) {
            return true;
        }

    }

    public abstract static class HS<T extends SH> extends S0HandledScreen<T> {

        public HS(T handler, PlayerInventory inventory, Text title) {
            super(handler, inventory, title);
        }

    }

}
