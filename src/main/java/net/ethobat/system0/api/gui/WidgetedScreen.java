package net.ethobat.system0.api.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.ethobat.system0.api.gui.widgets.GUIWidget;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.HashSet;

public abstract class WidgetedScreen<SH extends ScreenHandler> extends HandledScreen<SH> {

    public final HashSet<GUIWidget> widgets;

    public WidgetedScreen(SH handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.widgets = new HashSet<>();
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        //int x = (this.width - this.backgroundWidth) / 2;
        //int y = (this.height - this.backgroundHeight) / 2;
        //prepareTexture(this.getTextureIdentifier()); // TODO
        //this.drawTexture(matrices, x, y, 0, 0, this.backgroundWidth, this.backgroundHeight);
    }

    public static void prepareTexture(Identifier id) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, id);
    }

}
