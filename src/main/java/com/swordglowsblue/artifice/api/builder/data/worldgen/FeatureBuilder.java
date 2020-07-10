package com.swordglowsblue.artifice.api.builder.data.worldgen;

import com.google.gson.JsonObject;
import com.swordglowsblue.artifice.api.builder.TypedJsonBuilder;
import com.swordglowsblue.artifice.api.resource.JsonResource;

public class FeatureBuilder extends TypedJsonBuilder<JsonResource<JsonObject>> {
    public FeatureBuilder() {
        super(new JsonObject(), JsonResource::new);
        this.root.add("config", new JsonObject());
    }

    /**
     * @param id ID of an existing feature.
     * @return this
     */
    public FeatureBuilder name(String id) {
        this.root.addProperty("name", id);
        return this;
    }

}
