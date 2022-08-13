package net.ethobat.system0.api.geometry;

import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class Shapes {

    public static final VoxelShape TALL_CUBOID = VoxelShapes.cuboid(0,0,0,1,2,1);

    // east, south, up
    public static VoxelShape cuboid(float widthZ, float widthX, float height) {
        return VoxelShapes.cuboid(0, 0, 0, widthZ, widthX, height);
    }

}
