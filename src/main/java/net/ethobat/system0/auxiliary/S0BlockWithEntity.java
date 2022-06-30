package net.ethobat.system0.auxiliary;

import net.ethobat.system0.registry.S0Registrar;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemConvertible;

public abstract class S0BlockWithEntity extends BlockWithEntity implements IS0Block,ItemConvertible {

    private final S0BlockItem ITEM;

    public S0BlockWithEntity(Settings settings, String registryName) {
        super(settings);
        this.ITEM = new S0BlockItem(this, registryName);
        S0Registrar.register(this, registryName);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

}
