package net.ethobat.system0.api.gui.widgets;

import net.ethobat.system0.api.color.RGB;
import net.ethobat.system0.api.gui.WidgetedScreen;
import net.ethobat.system0.api.nbt.NBTHandler;
import net.ethobat.system0.api.network.abstracted.AbstractedConnection;
import net.ethobat.system0.api.network.abstracted.Network;
import net.ethobat.system0.api.rendering.Camera;
import net.ethobat.system0.api.rendering.Line;
import net.ethobat.system0.api.rendering.S0Drawing;
import net.ethobat.system0.api.rendering.VirtualSpace;
import net.ethobat.system0.api.util.MouseButton;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Vec3d;

// TODO
public class WNetworkViewport extends GUIWidget implements IWClickable {

    public final int WIDTH;
    public final int HEIGHT;

    public Network network;

    private final VirtualSpace graph;

    public WNetworkViewport(String name, int x, int y, int width, int height) {
        super(name, x, y);
        this.WIDTH = width;
        this.HEIGHT = height;
        this.graph = new VirtualSpace();
    }

    @Override
    public void draw(WidgetedScreen<?> screen, MatrixStack matrices, int mouseX, int mouseY) {
        this.drawInwardBevel(screen, matrices);
        S0Drawing.fillRectangle(matrices, this.screenX+screen.getX(), this.screenY+screen.getY(), this.getWidth(), this.getHeight(), 0xFF000000);
        //assert MinecraftClient.getInstance().player != null;
        //S0Drawing.drawEntity(this.screenX+screen.getX(), this.screenY+screen.getY(), 30, mouseX, mouseY, MinecraftClient.getInstance().player);
        this.graph.render();
    }

    public void loadNetworkGraph(Network network) {
        this.graph.wipe();
        for (AbstractedConnection cxn : network.getConnections()) {
            this.graph.addObject(new Line(cxn.TRANSMITTER.getPos(), cxn.RECEIVER.getPos(), RGB.GREEN.INT));
        }
    }

    public static void writeNBT(NbtCompound nbt, String name, ItemStack pdaStack) {
        NbtCompound widgetNBT = new NbtCompound();
        // ...
        NBTHandler.writeWidgetNBT(nbt, name, widgetNBT);
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

    @Override
    public void onMouseInteract(double widgetX, double widgetY, MouseButton mouseButton, boolean released) {

    }

    public static class NetworkVertex {

    }

}
