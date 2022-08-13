package net.ethobat.system0.auxiliary;

import net.ethobat.system0.registry.S0Registrar;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.ItemConvertible;

public class S0GourdBlock extends GourdBlock implements IS0Block,ItemConvertible {

    private final S0BlockItem ITEM;

    public S0GourdBlock(String registryName) {
        super(FabricBlockSettings.copy(Blocks.PUMPKIN));
        this.ITEM = new S0BlockItem(this, registryName);
        S0Registrar.register(this, registryName);
    }

    public S0BlockItem getItem() {
        return this.ITEM;
    }

//    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
//        dropStack((World) world, pos, new ItemStack(this.asItem()));
//        System.out.println("Block broken");
//    }

    @Override
    public StemBlock getStem() {
        return null;
    }

    @Override
    public AttachedStemBlock getAttachedStem() {
        return null;
    }

}
