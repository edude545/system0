package net.ethobat.system0.block;

import net.ethobat.system0.auxiliary.S0GourdBlock;
import net.minecraft.block.AttachedStemBlock;
import net.minecraft.block.StemBlock;

public class GlaucousGourd extends S0GourdBlock {


    public GlaucousGourd() {
        super("glaucous_gourd");
    }

    @Override
    public StemBlock getStem() {
        return null;
    }

    @Override
    public AttachedStemBlock getAttachedStem() {
        return null;
    }

}
