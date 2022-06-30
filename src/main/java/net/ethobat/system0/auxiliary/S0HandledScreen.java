package net.ethobat.system0.auxiliary;

import com.mojang.blaze3d.systems.RenderSystem;
import net.ethobat.system0.api.gui.GUIBackground;
import net.ethobat.system0.api.gui.WidgetedScreen;
import net.minecraft.client.render.GameRenderer;
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
    protected void init() {
        super.init();
        // Center the title
        this.titleX = (this.backgroundWidth - this.textRenderer.getWidth(this.title)) / 2 - 5;
    }

    public abstract Identifier getTextureIdentifier();

    public abstract int getBGWidth();
    public abstract int getBGHeight();

}
