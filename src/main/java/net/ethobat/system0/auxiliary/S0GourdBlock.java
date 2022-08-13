package net.ethobat.system0.auxiliary;

import net.ethobat.system0.registry.S0Blocks;
import net.ethobat.system0.registry.S0Registrar;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.sound.BlockSoundGroup;

import java.util.function.Supplier;

public abstract class S0GourdBlock extends GourdBlock implements IS0Block,ItemConvertible {

    private final S0BlockItem ITEM;

    public S0GourdBlock(String registryName) {
        super(FabricBlockSettings.copyOf(Blocks.MELON));
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

    public static class Stem extends StemBlock implements IS0Block {

        public Stem(GourdBlock gourdBlock, Supplier<Item> seed, String registryName) {
            super(gourdBlock, seed, FabricBlockSettings.copyOf(Blocks.MELON_STEM));
            S0Registrar.register(this, registryName);
        }

    }

    public static class AttachedStem extends AttachedStemBlock implements IS0Block {

        public AttachedStem(GourdBlock gourdBlock, Supplier<Item> seed, String registryName) {
            super(gourdBlock, seed, FabricBlockSettings.copyOf(Blocks.ATTACHED_MELON_STEM));
            S0Registrar.register(this, registryName);
        }

    }

}
