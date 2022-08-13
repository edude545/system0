package net.ethobat.system0.block;

import net.ethobat.system0.auxiliary.S0Block;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;

public class StorageBlock extends S0Block {

    public String type;

    public StorageBlock(String registryName) {
        super(FabricBlockSettings.copy(Blocks.IRON_BLOCK), registryName);
    }

}