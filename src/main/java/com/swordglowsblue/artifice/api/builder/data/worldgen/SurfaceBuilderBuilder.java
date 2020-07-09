package com.swordglowsblue.artifice.api.builder.data.worldgen;

import com.google.gson.JsonObject;
import com.swordglowsblue.artifice.api.builder.TypedJsonBuilder;
import com.swordglowsblue.artifice.api.resource.JsonResource;

public class SurfaceBuilderBuilder extends TypedJsonBuilder<JsonResource<JsonObject>> {
    public SurfaceBuilderBuilder() {
        super(new JsonObject(), JsonResource::new);
    }
}
