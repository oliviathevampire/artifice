package com.swordglowsblue.artifice.common;

import com.mojang.serialization.Lifecycle;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SimpleRegistry;

public class ArtificeRegistry {
    /**
     * The {@link Registry} for client-side resource packs.
     */
    @Environment(EnvType.CLIENT)
    public static final SimpleRegistry<ClientResourcePackProfileLike> RESOURCE_PACKS = Registry.register((Registry) Registry.REGISTRIES,
            new Identifier("artifice", "resource_packs"),
            new SimpleRegistry<>(
                    RegistryKey.ofRegistry(new Identifier("artifice", "resource_packs")),
                    Lifecycle.stable()
            )
    );
    /**
     * The {@link Registry} for server-side resource packs.
     */
    public static final SimpleRegistry<ServerResourcePackProfileLike> DATA_PACKS = Registry.register((Registry) Registry.REGISTRIES,
            new Identifier("artifice", "data_packs"),
            new SimpleRegistry<>(
                RegistryKey.ofRegistry(new Identifier("artifice", "data_packs")),
                Lifecycle.stable()
            )
    );
}
