package net.ethobat.system0;

import me.x150.renderer.event.Events;
import net.ethobat.system0.api.rendering.EventHandler;
import net.ethobat.system0.api.rendering.S0Drawing;
import net.ethobat.system0.registry.S0Blocks;
import net.ethobat.system0.registry.S0Registrar;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class System0Client implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        S0Registrar.initClient();
        S0Drawing.init();

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), S0Blocks.GLAUCOUS_GOURD_STEM);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), S0Blocks.ATTACHED_GLAUCOUS_GOURD_STEM);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), S0Blocks.FIRST_ARCANUM_EXOTICIZER);

        // For 0x3C50 rendering
        Events.registerEventHandlerClass(new EventHandler());

        System.out.println("System0Client initialized");
    }

}
