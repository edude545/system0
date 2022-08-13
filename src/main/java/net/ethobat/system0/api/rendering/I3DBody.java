package net.ethobat.system0.api.rendering;

import net.minecraft.client.render.BufferBuilder;
import net.minecraft.util.math.Vec3d;

public interface I3DBody {

    BufferBuilder.BuiltBuffer buildBuffer(BufferBuilder bb);

}
