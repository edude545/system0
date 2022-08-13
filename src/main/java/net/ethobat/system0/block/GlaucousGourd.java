package net.ethobat.system0.block;

import net.ethobat.system0.auxiliary.S0GourdBlock;
import net.ethobat.system0.registry.S0Blocks;
import net.minecraft.block.AttachedStemBlock;
import net.minecraft.block.StemBlock;

public class GlaucousGourd extends S0GourdBlock {

    public GlaucousGourd() {
        super("glaucous_gourd");
    }

    @Override
    public StemBlock getStem() {
        return S0Blocks.GLAUCOUS_GOURD_STEM;
    }

    @Override
    public AttachedStemBlock getAttachedStem() {
        return S0Blocks.ATTACHED_GLAUCOUS_GOURD_STEM;
    }

}
