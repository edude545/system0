package net.ethobat.system0.auxiliary;

import net.ethobat.system0.registry.S0Registrar;
import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;

public class S0Block extends Block implements IS0Block,ItemConvertible {

    private final S0BlockItem ITEM;

    public S0Block(Settings settings, String registryName) {
        super(settings);
        this.ITEM = new S0BlockItem(this, registryName);
        S0Registrar.register(this, registryName);
    }

//    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
//        dropStack((World) world, pos, new ItemStack(this.asItem()));
//        System.out.println("Block broken");
//    }

//    public static List<ItemStack> getDroppedStacks(BlockState state, ServerWorld world, BlockPos pos, @Nullable BlockEntity blockEntity, @Nullable Entity entity, ItemStack stack) {
//        return Block.getDroppedStacks(state, world, pos, blockEntity, entity, new ItemStack(this.ITEM));
//    }

}
