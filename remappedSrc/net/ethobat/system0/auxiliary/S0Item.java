package net.ethobat.system0.auxiliary;

import net.ethobat.system0.System0;
import net.ethobat.system0.registry.S0Registrar;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class S0Item extends Item implements IS0Item {

    public final String NAME;
    public boolean HAS_TOOLTIP = false;

    public S0Item(Settings settings, String registryName) {
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
}