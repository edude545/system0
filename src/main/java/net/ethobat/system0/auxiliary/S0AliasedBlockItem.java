package net.ethobat.system0.auxiliary;

import net.ethobat.system0.registry.S0Registrar;
import net.minecraft.block.Block;
import net.minecraft.item.AliasedBlockItem;

public class S0AliasedBlockItem extends AliasedBlockItem implements IS0Item {

    public final String NAME;
    public boolean HAS_TOOLTIP = false;

    private final Block BLOCK;

    public S0AliasedBlockItem(String registryName, Block block, Settings settings) {
        super(block, settings);
        this.NAME = registryName;
        this.BLOCK = block;
        S0Registrar.register(this, registryName);
    }

    @Override
    public void giveTooltip() {
        this.HAS_TOOLTIP = true;
    }

    @Override
    public Block getBlock() {
        return this.BLOCK;
    }
}
