package com.swordglowsblue.artifice.api.builder.data.worldgen;

import com.google.gson.JsonObject;
import com.swordglowsblue.artifice.api.builder.TypedJsonBuilder;
import com.swordglowsblue.artifice.api.resource.JsonResource;

public class StructureFeatureBuilder extends TypedJsonBuilder<JsonResource<JsonObject>> {
    public StructureFeatureBuilder() {
        super(new JsonObject(), JsonResource::new);
    }
}
