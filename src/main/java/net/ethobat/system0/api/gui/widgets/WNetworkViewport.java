package net.ethobat.system0.api.gui.widgets;

import me.x150.renderer.renderer.color.Color;
import me.x150.renderer.renderer.util.CameraContext3D;
import net.ethobat.system0.api.gui.WidgetedScreen;
import net.ethobat.system0.api.math.VecMath;
import net.ethobat.system0.api.nbt.NBTHandler;
import net.ethobat.system0.api.network.*;
import net.ethobat.system0.api.rendering.*;
import net.ethobat.system0.api.savedata.LevelNetworks;
import net.ethobat.system0.api.util.MouseButton;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

// TODO
public class WNetworkViewport extends GUIWidget implements IWClickable,IWScrollable {

    public final int WIDTH;
    public final int HEIGHT;

    public Network network;

    private final Vec3d startPos;
    public VirtualSpace graph;

    // Mouse interaction
    private int lastMouseX;
    private int lastMouseY;
    private boolean leftHeld;
    private boolean middleHeld;
    private boolean rightHeld;

    public WNetworkViewport(String name, BlockPos startPos, int x, int y, int width, int height) {
        super(name, x, y);
        this.startPos = S03DMath.blockPosToVec3dCenter(startPos);
        //this.graph = new VirtualSpace(this.startPos.add(-0.5,-0.5,-0.5));
        this.graph = new VirtualSpace(S03DMath.blockPosToVec3d(startPos));
        this.WIDTH = width;
        this.HEIGHT = height;
    }

    @Override
    public void draw(WidgetedScreen<?> screen, MatrixStack matrices, int mouseX, int mouseY) {
        this.drawInwardBevel(screen, matrices);
        S0Drawing.fillRectangle(matrices, this.screenX+screen.getX(), this.screenY+screen.getY(), this.getWidth(), this.getHeight(), 0xFF000000);

        if (this.network == null) {
            S0Drawing.drawText(matrices, "NO SIGNAL...", this.screenX+screen.getX()+16, this.screenY+screen.getY()+16);
        } else {
            if (!this.isPointWithinBounds(screen, mouseX, mouseY)) {
                this.leftHeld = false;
                this.middleHeld = false;
                this.rightHeld = false;
            } else {
                float dx = mouseX - lastMouseX;
                float dy = mouseY - lastMouseY;
                if (middleHeld) {
                    // Middle mouse rotates the camera around the current focus. Shift to scroll.
                    this.graph.rotateCamera(-dy * 0.125, dx * 0.125);
                }
                if (rightHeld) {
                    // Right mouse pans the camera over the horizontal plane. Shift to move on the Y axis.
                    if (!Screen.hasShiftDown()) {
                        // If panning horizontally, the mouse input should match up with the camera direction.
                        double yaw = this.graph.camera.getYaw() * Math.PI / 180;
//                        this.graph.nudgeCamera(new Vec3d(
//                                dx * Math.cos(yaw) - dy * Math.sin(yaw),
//                                0,
//                                dy * Math.cos(yaw) + dx * Math.sin(yaw)
//                        ).multiply(0.125));
                        this.graph.nudgeCamera(VecMath.rotateXZ(dx,dy,yaw).multiply(0.125));
                    } else {
                        this.graph.nudgeCamera(new Vec3d(0, dy, 0).multiply(0.125));
                    }
                }

            }
            this.lastMouseX = mouseX;
            this.lastMouseY = mouseY;

            this.graph.render(this.screenX + screen.getX(), this.screenY + screen.getY(), this.getWidth(), this.getHeight(), mouseX, mouseY);
        }
    }

    public void getClickPosition(int mouseX, int mouseY) {

    }

    // TODO probably useless
    public void highlightCubeIfLookingAt(int mouseX, int mouseY) {
        CameraContext3D camera = this.graph.camera;
        Vec3d unit = this.graph.getCameraLookDirection();
        Vec3d pos = camera.getPosition();
        boolean stopping = false;
        for (int i=0; i<16; i++) {
            for (I3DBody body : this.graph.bodies) {
                if (body instanceof Cuboid cuboid && cuboid.pos.add(0.5,0.5,0.5).distanceTo(pos) < 2) {
                    cuboid.color = Color.WHITE;
                    stopping = true;
                    break;
                }
            }
            if (stopping) {
                break;
            }
            pos = pos.add(unit);
        }
    }

    public void loadNetworkGraph() {
        this.graph.wipe();
        if (this.network != null) {
            for (NWConnection cxn : this.network.getConnections()) {
                this.graph.addObject(new Line(cxn.TRANSMITTER.getPos(), cxn.RECEIVER.getPos(), Color.GREEN));
            }
            for (NWNode node : this.network.getNodes()) {
                this.graph.addObject(new Cuboid(node.getPos(), Color.GREEN));
            }
            for (NWSource source : this.network.getSources()) {
                this.graph.addObject(new Cuboid(source.getPos(), Color.BLUE));
            }
            for (NWDrain drain : this.network.getDrains()) {
                this.graph.addObject(new Cuboid(drain.getPos(), Color.ORANGE));
            }
        }
        //this.graph.addObject(new Cuboid(this.startPos, new Vec3d(1,1,1), Color.GREEN));
    }

    public static void writeNBT(NbtCompound nbt, String name, ItemStack pdaStack) {
        NbtCompound widgetNBT = new NbtCompound();
        // ...
        NBTHandler.writeWidgetNBT(nbt, name, widgetNBT);
    }

    @Override
    public void updateFromNBT(NbtCompound nbt) {
        this.network = LevelNetworks.INSTANCE.getNetwork(NBTHandler.getUUID(nbt, "network"));
        this.loadNetworkGraph();
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
    public void onMouseInteract(double mouseX, double mouseY, MouseButton mouseButton, boolean released) {
        if (this.isClient) {
            switch(mouseButton) {
                case LEFT -> this.leftHeld = !released;
                case MIDDLE -> this.middleHeld = !released;
                case RIGHT -> this.rightHeld = !released;
            }
        }
    }

    @Override
    public void onMouseScroll(double mouseX, double mouseY, double direction) {
        if (this.isClient) {
            this.graph.zoomCamera((float) (-direction));
        }
    }
}
