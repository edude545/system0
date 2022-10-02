package net.ethobat.system0.mixin;

import net.ethobat.system0.auxiliary.S0BlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.UpgradeData;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraft.world.gen.chunk.BlendingData;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Map;

@Mixin(WorldChunk.class)
public abstract class WorldChunkMixin extends Chunk {

    public WorldChunkMixin(ChunkPos chunkPos, UpgradeData upgradeData, HeightLimitView heightLimitView, Registry<Biome> registry, long l, @Nullable ChunkSection[] chunkSections, @Nullable BlendingData blendingData) {
        super(chunkPos, upgradeData, heightLimitView, registry, l, chunkSections, blendingData);
    }

    @Inject(method = "removeBlockEntity", at=@At("HEAD"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    public void removeBlockEntity(BlockPos blockPos, CallbackInfo ci) {
        BlockEntity be = this.blockEntities.get(blockPos);
        if (be instanceof S0BlockEntity) {
            ((S0BlockEntity) be).onRemoved();
        }
    }

}
