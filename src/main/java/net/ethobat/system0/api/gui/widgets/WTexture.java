package net.ethobat.system0.api.gui.widgets;

import net.ethobat.system0.api.gui.WidgetedScreen;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

// Basic widget. Draws a texture on the GUI, with no interactivity.
// Draws from a given point in a spritesheet. WTexture#single is probably more useful.
public class WTexture extends GUIWidget {

    public final Identifier ID;

    public final int U;
    public final int V;
    public final int WIDTH;
    public final int HEIGHT;
    public final int TX_WIDTH;
    public final int TX_HEIGHT;

    // Draws a part of the texture.
    public WTexture(String name, int x, int y, Identifier id, int u, int v, int width, int height, int txWidth, int txHeight) {
        super(name, x, y);
        this.ID = id;
        this.U = u;
        this.V = v;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.TX_WIDTH = txWidth;
        this.TX_HEIGHT = txHeight;
    }

    public static WTexture single(String name, int x, int y, Identifier id, int width, int height) {
        return new WTexture(name, x, y, id, 0, 0, width, height, width, height);
    }

//    // Draws the entire texture.
//    public WTexture(String name, int x, int y, Identifier id) {
//        this(name, x, y, id, u, v, width, height);
//    }

    @Override
    public void draw(WidgetedScreen<?> screen, MatrixStack matrices, int mouseX, int mouseY) {
        DrawableHelper.drawTexture(matrices, this.screenX, this.screenY, this.U, this.V, this.WIDTH, this.HEIGHT, this.TX_WIDTH, this.TX_HEIGHT);
    }

    @Override
    public void updateFromNBT(NbtCompound nbt) {

    }

    @Override
    public int getWidth() {
        return this.WIDTH;
    }

    @Override
    public int getHeight() {
        return this.HEIGHT;
    }

}
