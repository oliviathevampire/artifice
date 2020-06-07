package com.swordglowsblue.artifice.mixin;

import com.swordglowsblue.artifice.impl.ArtificeDataResourcePackProvider;
import net.minecraft.resource.ResourcePackProvider;
import net.minecraft.server.MinecraftServer;
import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(MinecraftServer.class)
public abstract class MixinMinecraftServer {

    @ModifyArg(method = "method_29736", at = @At(value = "INVOKE", target = "Lnet/minecraft/resource/ResourcePackManager;<init>(Lnet/minecraft/resource/ResourcePackProfile$class_5351;[Lnet/minecraft/resource/ResourcePackProvider;)V"), index = 1)
    private static ResourcePackProvider[] appendArtificeDataPacks(ResourcePackProvider[] vanillaProviders) {
        return ArrayUtils.add(vanillaProviders, new ArtificeDataResourcePackProvider());
    }

}
