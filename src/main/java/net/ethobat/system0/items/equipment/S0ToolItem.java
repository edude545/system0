package net.ethobat.system0.items.equipment;

import net.ethobat.system0.System0;
import net.ethobat.system0.auxiliary.IS0Item;
import net.ethobat.system0.registry.S0Registrar;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class S0ToolItem extends ToolItem implements IS0Item {

    public final String NAME;
    public boolean HAS_TOOLTIP = false;

    public S0ToolItem( Settings settings, String registryName, ToolMaterial material) {
        super(new ToolMaterial() {
            @Override
            public int getDurability() {
                return 0;
            }

            @Override
            public float getMiningSpeedMultiplier() {
                return 0;
            }

            @Override
            public float getAttackDamage() {
                return 0;
            }

            @Override
            public int getMiningLevel() {
                return 0;
            }

            @Override
            public int getEnchantability() {
                return 0;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return null;
            }
        }, settings.maxCount(1).group(System0.ITEM_GROUP_TOOLS));
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
            tooltip.add(new TranslatableText("item." + System0.MOD_ID + "." + this.NAME + ".tooltip").formatted(Formatting.DARK_GRAY));
        }
    }

}
