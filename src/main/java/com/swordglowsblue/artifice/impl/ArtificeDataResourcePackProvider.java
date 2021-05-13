package com.swordglowsblue.artifice.impl;

import java.util.function.Consumer;

import com.swordglowsblue.artifice.common.ArtificeRegistry;

import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.ResourcePackProvider;
import net.minecraft.util.Identifier;

public final class ArtificeDataResourcePackProvider implements ResourcePackProvider {

    @Override
    public void register(Consumer<ResourcePackProfile> consumer, ResourcePackProfile.Factory factory) {
        for (Identifier id : ArtificeRegistry.DATA_PACKS.getIds()) {
            consumer.accept(ArtificeRegistry.DATA_PACKS.get(id).toServerResourcePackProfile(factory));
        }
    }
}
