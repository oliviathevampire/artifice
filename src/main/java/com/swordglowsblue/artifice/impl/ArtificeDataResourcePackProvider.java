package com.swordglowsblue.artifice.impl;

import java.util.Map;

import com.swordglowsblue.artifice.api.Artifice;
import com.swordglowsblue.artifice.common.ArtificeRegistry;

import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.ResourcePackProvider;
import net.minecraft.util.Identifier;

public final class ArtificeDataResourcePackProvider implements ResourcePackProvider {
    @Override
    @SuppressWarnings( {"ConstantConditions", "unchecked"})
    public <T extends ResourcePackProfile> void register(Map<String, T> packs, ResourcePackProfile.Factory<T> factory) {
        for (Identifier id : ArtificeRegistry.DATA.getIds()) {
            packs.put(id.toString(), (T) ArtificeRegistry.DATA.get(id).toServerResourcePackProfile(factory));
        }
    }

}
