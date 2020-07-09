package com.swordglowsblue.artifice.api.builder.data.worldgen;

import com.google.gson.JsonObject;
import com.swordglowsblue.artifice.api.builder.TypedJsonBuilder;
import com.swordglowsblue.artifice.api.resource.JsonResource;

public class ProcessorListBuilder extends TypedJsonBuilder<JsonResource<JsonObject>> {
    public ProcessorListBuilder() {
        super(new JsonObject(), JsonResource::new);
    }
}
