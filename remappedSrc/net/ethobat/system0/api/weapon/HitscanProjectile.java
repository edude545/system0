package net.ethobat.system0.api.weapon;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class HitscanProjectile { // TODO

    public final BlockPos startPos;
    public final Vec3d direction;

    public HitscanProjectile(BlockPos startPos, Vec3d direction) {
        this.startPos = startPos;
        this.direction = direction;
    }

    public static HitscanProjectile fromEntity(Entity entity, float tickDelta) {
        return new HitscanProjectile(entity.getBlockPos(), entity.getCameraPosVec(tickDelta));
    }

}
