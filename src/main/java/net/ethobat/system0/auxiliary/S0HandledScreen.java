package net.ethobat.system0.auxiliary;

import net.ethobat.system0.api.gui.GUIBackground;
import net.ethobat.system0.api.gui.WidgetedScreen;
import net.ethobat.system0.api.rendering.S0Drawing;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

// HandledScreens are client-side only and are responsible for displaying the screen. Visuals, etc.
public abstract class S0HandledScreen<SH extends S0ScreenHandler> extends WidgetedScreen<SH> {

    public static int SCREEN_WIDTH = 640;
    public static int SCREEN_HEIGHT = 330;

    public static final GUIBackground bg = new GUIBackground(new Identifier("system0", "textures/gui/gui_bg_marked.png"));

    public S0HandledScreen(SH handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundHeight = getBGHeight() * GUIBackground.TILE_SIZE;
        this.backgroundWidth = getBGWidth() * GUIBackground.TILE_SIZE;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(matrices, mouseX, mouseY);
        this.drawWidgetTooltips(matrices, mouseX, mouseY);
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        bg.drawCenter(this, matrices, this.getBGWidth(), this.getBGHeight());
        this.drawWidgets(matrices, mouseX, mouseY);
    }

    @Override
    protected void drawForeground(MatrixStack matrixStack, int i, int j) {
//        this.textRenderer.draw(matrixStack, this.title, this.titleX, this.titleY, 4210752);
        this.drawTitle(matrixStack);
    }

    protected void drawTitle(MatrixStack matrixStack) {
        S0Drawing.drawText(matrixStack, this.title, this.titleX, this.titleY);
    }

    @Override
    protected void init() {
        super.init();
        // Center the title
        this.titleX = (this.backgroundWidth - this.textRenderer.getWidth(this.title)) / 2 - 5;
    }

    public abstract int getBGWidth();
    public abstract int getBGHeight();

}
