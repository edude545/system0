package net.ethobat.system0.api.gui.widgets;

import com.mojang.blaze3d.systems.RenderSystem;
import net.ethobat.system0.api.registry.S0Registry;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtInt;
import net.minecraft.util.Identifier;

public abstract class GUIWidget extends DrawableHelper {

    public int screenX;
    public int screenY;

    public GUIWidget(int x, int y) {
        this.screenX = x;
        this.screenY = y;
    }

    public GUIWidget(NbtCompound nbt) {
        this.screenX = nbt.getInt("screenX");
        this.screenY = nbt.getInt("screenY");
    }

    public abstract void draw(MatrixStack matrices);

    public static void prepareTexture(Identifier id) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, id);
    }

    public NbtCompound writeToNBT(NbtCompound nbt) {
        nbt.put("x", NbtInt.of(this.screenX));
        nbt.put("y", NbtInt.of(this.screenY));
        return nbt;
    }

    public void updateFromNBT(NbtCompound nbt) {
        this.screenX = nbt.getInt("x");
        this.screenY = nbt.getInt("y");
    }

}