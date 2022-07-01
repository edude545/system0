package net.ethobat.system0.api.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.ethobat.system0.api.visuals.S0Renderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class GUIBackground extends DrawableHelper {

    public static int SCREEN_WIDTH = 640;
    public static int SCREEN_HEIGHT = 330;

    public static int TILE_SIZE = 4;
    public static int TEXTURE_SIZE = 16;

    protected enum Parts {
        TOP_LEFT(0,0),
        TOP_RIGHT(8,0),
        BOTTOM_LEFT(0,8),
        BOTTOM_RIGHT(8,8),
        LEFT(0,4),
        RIGHT(8,4),
        TOP(4,0),
        BOTTOM(4,8),
        MIDDLE(4,4);
        public final int u;
        public final int v;
        Parts(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }
    public final Identifier TEXTURE_ID;

    public GUIBackground(Identifier textureID) {
        this.TEXTURE_ID = textureID;
    }

    public void draw(MatrixStack matrices, int x, int y, int tileWidth, int tileHeight) {
        S0Renderer.prepareTexture(this.TEXTURE_ID);
        boolean isNearX;
        boolean isNearY;
        boolean isFarX;
        boolean isFarY;
        Parts part;
        for (int tileY = 0; tileY < tileHeight; tileY++) {
            for (int tileX = 0; tileX < tileWidth; tileX++) {
                isNearX = (tileX == 0); isFarX = (tileX == tileWidth - 1);
                isNearY = (tileY == 0); isFarY = (tileY == tileHeight - 1);
                     if (isNearX && isNearY) {part = Parts.TOP_LEFT;}
                else if (isFarX  && isFarY ) {part = Parts.BOTTOM_RIGHT;}
                else if (isFarX  && isNearY) {part = Parts.TOP_RIGHT;}
                else if (isNearX && isFarY ) {part = Parts.BOTTOM_LEFT;}
                else if (isNearX           ) {part = Parts.LEFT;}
                else if (isFarX            ) {part = Parts.RIGHT;}
                else if (           isNearY) {part = Parts.TOP;}
                else if (            isFarY) {part = Parts.BOTTOM;}
                else                         {part = Parts.MIDDLE;}
                DrawableHelper.drawTexture(matrices, (tileX*TILE_SIZE)+x, (tileY*TILE_SIZE)+y, part.u, part.v, TILE_SIZE, TILE_SIZE, TEXTURE_SIZE, TEXTURE_SIZE);
            }
        }
    }

    public void drawCenter(Screen screen, MatrixStack matrices, int tileWidth, int tileHeight) {
        this.draw(matrices, (screen.width/2)-(tileWidth*TILE_SIZE/2), (screen.height/2)-(tileHeight*TILE_SIZE/2), tileWidth, tileHeight);
    }

}
