package net.ethobat.system0.api;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

//
// NOT CURRENTLY USED
//

public class BlockItemPair {

    private final Block block;
    private final BlockItem blockItem;

    BlockItemPair(Block block) {
        this.block = block;
        blockItem = new BlockItem(block, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    }

    public Block getBlock() {
        return this.block;
    }

    public BlockItem getBlockItem() {
        return this.blockItem;
    }

}
