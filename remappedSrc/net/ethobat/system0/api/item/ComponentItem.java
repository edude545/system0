package net.ethobat.system0.api.item;

import net.minecraft.item.Item;

public class ComponentItem extends Item {

    public enum ComponentTypes {
        ARCOPLEXER,
        CAPACITOR,
        HARDLIGHT_PROJECTOR,
        IMMATERIALIZER,
        IMPLEMENTATION_SIGIL,
        PROGRESSIVE_CPU,
        SYNTHBOARD,
    }

    public ComponentItem(Settings settings) {
        super(settings);
    }

}
