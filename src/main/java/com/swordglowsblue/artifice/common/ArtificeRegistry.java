package com.swordglowsblue.artifice.common;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;

public class ArtificeRegistry {
    /**
     * The {@link Registry} for client-side resource packs.
     */
    @Environment(EnvType.CLIENT)
    public static final MutableRegistry<ClientResourcePackProfileLike> RESOURCE_PACKS = FabricRegistryBuilder.createSimple(
            ClientResourcePackProfileLike.class,
            new Identifier("artifice", "resource_packs")
    ).buildAndRegister();

    /**
     * The {@link Registry} for server-side resource packs.
     */
    @Environment(EnvType.SERVER)
    public static final MutableRegistry<ServerResourcePackProfileLike> DATA_PACKS = FabricRegistryBuilder.createSimple(
            ServerResourcePackProfileLike.class,
            new Identifier("artifice", "data_packs")
    ).buildAndRegister();
}
