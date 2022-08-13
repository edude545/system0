package net.ethobat.system0.api.rendering;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3f;

public class S03DMath {

    // angle e.g. 180.0f
    public static void rotateX(MatrixStack matrixStack, float angle) {
        matrixStack.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(angle));
    }
    public static void rotateY(MatrixStack matrixStack, float angle) {
        matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(angle));
    }
    public static void rotateZ(MatrixStack matrixStack, float angle) {
        matrixStack.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(angle));
    }
    public static void rotate(MatrixStack matrixStack, float angleX, float angleY, float angleZ) {
        rotateX(matrixStack, angleX);
        rotateY(matrixStack, angleY);
        rotateZ(matrixStack, angleZ);
    }

    public static void translateRight(MatrixStack matrixStack, float dist) {
        matrixStack.translate(dist, 0, 0);
    }
    public static void translateDown(MatrixStack matrixStack, float dist) {
        matrixStack.translate(0, dist, 0);
    }
    public static void translateOut(MatrixStack matrixStack, float dist) {
        matrixStack.translate(0, 0, dist);
    }

    public static void scale(MatrixStack matrixStack, float factor) {
        matrixStack.scale(factor, factor, factor);
    }

}
