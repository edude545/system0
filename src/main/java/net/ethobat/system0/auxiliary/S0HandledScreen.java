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

    public static final GUIBackground bg = new GUIBackground(new Identifier("system0", "textures/gui/gui_bg_vanilla.png"));

    public S0HandledScreen(SH handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
//        int x = (this.width - this.backgroundWidth) / 2;
//        int y = (this.height - this.backgroundHeight) / 2;
//        prepareTexture(this.getTextureIdentifier());
//        this.drawTexture(matrices, x, y, 0, 0, this.backgroundWidth, this.backgroundHeight);
//        prepareTexture(new Identifier("system0", "textures/gui/gui_bg_vanilla.png"));
//        this.drawTexture(matrices, x, y, 0, 0, 128, 128);
        //matrices.scale(0.0625f, 0.0625f, 0.0625f); // this shrinks the text too...
        bg.drawCenter(matrices, 4, 4);
        //matrices.scale(16f, 16f, 16f); // ...need to scale it back up after
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
        // Center the title
        this.titleX = (this.backgroundWidth - this.textRenderer.getWidth(this.title)) / 2;
    }

    public abstract Identifier getTextureIdentifier();

}
