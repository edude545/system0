package net.ethobat.system0.mixin;

import com.google.common.collect.Maps;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.HashSet;
import java.util.Map;


// Not currently working.
@Mixin(ModelLoader.class)
public class ModelLoaderMixin {

    private static boolean currentlyMissingModel = false;
    private static Identifier identifier2Cache;

    @Shadow private Map<Identifier, UnbakedModel> unbakedModels;

    // This code runs after ModelLoader#getOrLoadModel calls Logger#warn with multiple arguments.
    // This only happens when a model is missing, just before it defaults to setting a missing model.
    @Inject(method = "getOrLoadModel", at=@At(value="INVOKE", target="Lorg/slf4j/Logger;warn(Ljava/lang/String;[Ljava/lang/Object;)V"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void beforeLogMissingModel(Identifier identifier, CallbackInfoReturnable<UnbakedModel> cir, UnbakedModel unbakedModel, Identifier identifier2, Exception exception) {
        currentlyMissingModel = true;
        identifier2Cache = identifier2;
    }

    // This should override all missing models with the model for a stone block.
    @Inject(method = "getOrLoadModel", at=@At(value="RETURN"), cancellable = true)
    private void afterReturn(Identifier identifier, CallbackInfoReturnable<UnbakedModel> cir) {
        if (currentlyMissingModel) {
            UnbakedModel model = this.unbakedModels.get(new Identifier("minecraft", "stone"));
            this.unbakedModels.put(identifier2Cache, model);
            cir.setReturnValue(model);
            currentlyMissingModel = false;
        }
    }

}
