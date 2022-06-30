package net.ethobat.system0.api.gui.widgets;

import net.minecraft.util.Identifier;

public class WSingleTexture extends WTexture {

    public WSingleTexture(String name, int x, int y, Identifier id,int width, int height) {
        super(name, x, y, id, 0, 0, width, height, width, height);
    }

}
