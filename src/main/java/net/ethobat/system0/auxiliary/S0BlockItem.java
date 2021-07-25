package net.ethobat.system0.auxiliary;

import net.ethobat.system0.System0;
import net.ethobat.system0.registry.S0Registrar;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class S0BlockItem extends BlockItem implements IS0Item {

    private final Block BLOCK;
    public boolean HAS_TOOLTIP = false;

    public S0BlockItem(Block block, String registryName) {
        super(block, new Settings().group(System0.ITEM_GROUP_DECORATION));
        this.BLOCK = block;
        S0Registrar.register(this, registryName);
    }

    @Override
    public void giveTooltip() {
        this.HAS_TOOLTIP = true;
    }

    public Block getBlock() {
        return this.BLOCK;
    }

}
