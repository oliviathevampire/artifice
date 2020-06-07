package com.swordglowsblue.artifice.common;

import net.minecraft.client.resource.ClientResourcePackProfile;
import net.minecraft.resource.ResourcePackProfile;

public interface ClientResourcePackProfileLike {
    // Supplier to avoid loading ClientResourcePackProfile on the server
    <T extends ResourcePackProfile> ClientOnly<ClientResourcePackProfile> toClientResourcePackProfile(ResourcePackProfile.class_5351<T> factory);
}
