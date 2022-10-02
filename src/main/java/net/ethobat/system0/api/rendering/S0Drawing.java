package net.ethobat.system0.api.rendering;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;

public class S0Drawing extends DrawableHelper {

    static MinecraftClient client = MinecraftClient.getInstance();

    public static void prepareTexture(Identifier textureID) {
        //RenderSystem.setShader(GameRenderer::getPositionTexShader); // This is already present in DrawableHelper#drawTexturedQuad
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, textureID);
    }

    public static void fillRectangle(MatrixStack matrices, int x, int y, int width, int height, int color) {
//        RenderSystem.disableDepthTest();
//        RenderSystem.colorMask(true, true, true, false);
        DrawableHelper.fill(matrices, x, y, x + width, y + height, color);
//        RenderSystem.colorMask(true, true, true, true);
//        RenderSystem.enableDepthTest();
    }

    public static void drawText(MatrixStack matrices, Text text, int x, int y) {
        client.textRenderer.draw(matrices, text, x, y, 4210752);
    }

    public static void drawText(MatrixStack matrices, String string, int x, int y) {
        drawText(matrices, Text.literal(string), x, y);
    }

    // TODO
    // This code is copied from InventoryScreen#drawEntity - in the process of deconstructing it
    public static void drawEntity(int x, int y, int size, float mouseX, float mouseY, LivingEntity livingEntity) {
        mouseX = (float)Math.atan(((x + 51) - mouseX) / 40.0f);
        mouseY = (float)Math.atan(((y + 25) - mouseY) / 40.0f);

        MatrixStack matrixStack = RenderSystem.getModelViewStack();
        matrixStack.push();
        matrixStack.translate(x, y, 1050.0);
        matrixStack.scale(1.0f, 1.0f, -1.0f);
        RenderSystem.applyModelViewMatrix();
        MatrixStack matrixStack2 = new MatrixStack();

        S03DMath.translateOut(matrixStack2, 1000);

        S03DMath.scale(matrixStack2, size);

        Quaternion quaternion = Vec3f.POSITIVE_Z.getDegreesQuaternion(180.0f);
        Quaternion quaternion2 = Vec3f.POSITIVE_X.getDegreesQuaternion(mouseY * 20.0f);
        quaternion.hamiltonProduct(quaternion2);
        matrixStack2.multiply(quaternion);

//        S03DMath.rotate(matrixStack2, mouseY*20f, 0f, 180f);

        float cachedBodyYaw = livingEntity.bodyYaw;
        float cachedLookYaw = livingEntity.getYaw();
        float cachedLookPitch = livingEntity.getPitch();
        float cachedPrevHeadYaw = livingEntity.prevHeadYaw;
        float cachedHeadYaw = livingEntity.headYaw;

        livingEntity.bodyYaw = 180.0f + mouseX * 20.0f;
        livingEntity.setYaw(180.0f + mouseX * 40.0f);
        livingEntity.setPitch(-mouseY * 20.0f);
        livingEntity.headYaw = livingEntity.getYaw();
        livingEntity.prevHeadYaw = livingEntity.getYaw();

        DiffuseLighting.method_34742();
        EntityRenderDispatcher entityRenderDispatcher = MinecraftClient.getInstance().getEntityRenderDispatcher();

        quaternion2.conjugate();
        entityRenderDispatcher.setRotation(quaternion2);

        entityRenderDispatcher.setRenderShadows(false);
        VertexConsumerProvider.Immediate immediate = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();
        RenderSystem.runAsFancy(() -> entityRenderDispatcher.render(livingEntity, 0.0, 0.0, 0.0, 0.0f, 1.0f, matrixStack2, immediate, 0xF000F0));
        immediate.draw();
        entityRenderDispatcher.setRenderShadows(true);

        livingEntity.bodyYaw = cachedBodyYaw;
        livingEntity.setYaw(cachedLookYaw);
        livingEntity.setPitch(cachedLookPitch);
        livingEntity.prevHeadYaw = cachedPrevHeadYaw;
        livingEntity.headYaw = cachedHeadYaw;

        matrixStack.pop();
        RenderSystem.applyModelViewMatrix();
        DiffuseLighting.enableGuiDepthLighting();
    }

    public static void init() {

    }

}
