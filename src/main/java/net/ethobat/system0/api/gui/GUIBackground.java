package net.ethobat.system0.api.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class GUIBackground extends DrawableHelper {

    protected static final int PIXELS_PER_PIXEL = 16; // ??? wtf

    protected enum Parts { // TODO something wrong with the UVs; parts being drawn right but with the wrong sprites
        TOP_LEFT(0,0),
        TOP_RIGHT(128,0),
        BOTTOM_LEFT(0,128),
        BOTTOM_RIGHT(128,128),
        LEFT(0,64),
        RIGHT(128,64),
        TOP(64,0),
        BOTTOM(64,128),
        MIDDLE(64,64);

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

    // TODO need to test this and make sure there are no fencepost errors in the loops
    public void draw(MatrixStack matrices, int x, int y, int tileWidth, int tileHeight) {
        this.prepareTexture();
        boolean isNearX;
        boolean isNearY;
        boolean isFarX;
        boolean isFarY;
        Parts part;
        for (int tileY = 0; tileY < tileHeight; tileY++) {
            for (int tileX = 0; tileX < tileWidth; tileX++) {
                isNearX = (tileX == 0); isFarX = (tileX == tileWidth - 1);
                isNearY = (tileY == 0); isFarY = (tileX == tileHeight - 1);
                     if (isNearX && isNearY) {part = Parts.TOP_LEFT;}
                else if (isFarX  && isFarY ) {part = Parts.BOTTOM_RIGHT;}
                else if (isFarX  && isNearY) {part = Parts.TOP_RIGHT;}
                else if (isNearX && isFarY ) {part = Parts.BOTTOM_LEFT;}
                else if (isNearX           ) {part = Parts.LEFT;}
                else if (isFarX            ) {part = Parts.RIGHT;}
                else if (           isNearY) {part = Parts.TOP;}
                else if (            isFarY) {part = Parts.BOTTOM;}
                else                         {part = Parts.MIDDLE;}
                System.out.println("PARTTYPE:"+part.name());
                this.drawTexture(matrices, x+tileX*128*2, y+tileY*128*2, part.u, part.v, 128, 128);
            }
        }
    }

    public void drawCenter(MatrixStack matrices, int tileWidth, int tileHeight) {
        this.draw(matrices, -tileWidth*4, -tileHeight*4, tileWidth, tileHeight);
    }

    protected void prepareTexture() {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, this.TEXTURE_ID);
    }

}
