package com.swordglowsblue.artifice.impl;

import com.swordglowsblue.artifice.common.ArtificeRegistry;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.ResourcePackProvider;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public final class ArtificeDataResourcePackProvider implements ResourcePackProvider {
    @Override
    @SuppressWarnings( {"ConstantConditions", "unchecked"})
    public <T extends ResourcePackProfile> void register(Consumer<T> packs, ResourcePackProfile.class_5351<T> factory) {
        for (Identifier id : ArtificeRegistry.DATA.getIds()) {
            packs.accept((T) ArtificeRegistry.DATA.get(id).toServerResourcePackProfile(factory));
        }
    }

}
