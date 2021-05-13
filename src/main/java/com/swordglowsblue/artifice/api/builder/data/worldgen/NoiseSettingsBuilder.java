package com.swordglowsblue.artifice.api.builder.data.worldgen;

import com.google.gson.JsonObject;
import com.swordglowsblue.artifice.api.builder.TypedJsonBuilder;
import com.swordglowsblue.artifice.api.builder.data.StateDataBuilder;
import com.swordglowsblue.artifice.api.builder.data.dimension.NoiseConfigBuilder;
import com.swordglowsblue.artifice.api.builder.data.dimension.StructureManagerBuilder;
import com.swordglowsblue.artifice.api.resource.JsonResource;
import com.swordglowsblue.artifice.api.util.Processor;

public class NoiseSettingsBuilder extends TypedJsonBuilder<JsonResource<JsonObject>> {
    public NoiseSettingsBuilder() {
        super(new JsonObject(), JsonResource::new);
    }

    /**
     * Chooses if aquifers should be enabled or not
     * @return this
     */
    public NoiseSettingsBuilder aquifersEnabled(boolean aquifersEnabled) {
        this.root.addProperty("aquifers_enabled", aquifersEnabled);
        return this;
    }

    /**
     * Chooses if the new noise caves should be enabled or not
     * @return this
     */
    public NoiseSettingsBuilder noiseCavesEnabled(boolean noiseCavesEnabled) {
        this.root.addProperty("noise_caves_enabled", noiseCavesEnabled);
        return this;
    }

    /**
     * Chooses if the deepslate layer should be enabled or not
     * @return this
     */
    public NoiseSettingsBuilder deepslateEnabled(boolean deepslateEnabled) {
        this.root.addProperty("deepslate_enabled", deepslateEnabled);
        return this;
    }

    /**
     * Chooses if the ore veins should be enabled or not
     * @return this
     */
    public NoiseSettingsBuilder oreVeinsEnabled(boolean oreVeinsEnabled) {
        this.root.addProperty("ore_veins_enabled", oreVeinsEnabled);
        return this;
    }

    /**
     * Chooses if the noodle caves should be enabled or not
     * @return this
     */
    public NoiseSettingsBuilder noodleCavesEnabled(boolean noodleCavesEnabled) {
        this.root.addProperty("noodle_caves_enabled", noodleCavesEnabled);
        return this;
    }

    /**
     * Set the minimum y-pos where surface builders can generate
     * @return this
     */
    public NoiseSettingsBuilder minSurfaceLevel(int minSurfaceLevel) {
        this.root.addProperty("min_surface_level", minSurfaceLevel);
        return this;
    }

    /**
     * Set the bedrock roof position.
     * @return this
     */
    public NoiseSettingsBuilder bedrockRoofPosition(int bedrockRoofPosition) {
        this.root.addProperty("bedrock_roof_position", bedrockRoofPosition);
        return this;
    }

    /**
     * Set the bedrock floor position.
     * @return this
     */
    public NoiseSettingsBuilder bedrockFloorPosition(int bedrockFloorPosition) {
        this.root.addProperty("bedrock_floor_position", bedrockFloorPosition);
        return this;
    }

    /**
     * Set the sea level which is where the surface will be around.
     * @return this
     */
    public NoiseSettingsBuilder seaLevel(int seaLevel) {
        this.root.addProperty("sea_level", seaLevel);
        return this;
    }


    /**
     * Builds the noise configurations for the dimension
     * @return this
     */
    public NoiseSettingsBuilder noiseConfig(Processor<NoiseConfigBuilder> noiseConfigBuilder) {
        with("noise", JsonObject::new, jsonObject -> noiseConfigBuilder.process(new NoiseConfigBuilder()).buildTo(jsonObject));
        return this;
    }

    /**
     * Chooses if mob generation should be disabled or not
     * @return this
     */
    public NoiseSettingsBuilder disableMobGeneration(boolean disableMobGeneration) {
        this.root.addProperty("disable_mob_generation", disableMobGeneration);
        return this;
    }

    /**
     * Sets a blockstate with the defined id and a StateDataBuilder
     * @return this
     */
    public NoiseSettingsBuilder setBlockState(String id, Processor<StateDataBuilder> blockStateBuilderProcessor) {
        with(id, JsonObject::new, jsonObject -> blockStateBuilderProcessor.process(new StateDataBuilder()).buildTo(jsonObject));
        return this;
    }

    /**
     * Sets the default block for the dimension to the defined blockstate
     * @return this
     */
    public NoiseSettingsBuilder defaultBlock(Processor<StateDataBuilder> blockStateBuilderProcessor) {
        return this.setBlockState("default_block", blockStateBuilderProcessor);
    }

    /**
     * Sets the default fluid for the dimension to the defined fluidstate
     * @return this
     */
    public NoiseSettingsBuilder defaultFluid(Processor<StateDataBuilder> blockStateBuilderProcessor) {
        return this.setBlockState("default_fluid", blockStateBuilderProcessor);
    }

    /**
     * Build structure manager.
     * @return this
     */
    public NoiseSettingsBuilder structureManager(Processor<StructureManagerBuilder> structureManagerBuilder) {
        with("structures", JsonObject::new, jsonObject -> structureManagerBuilder.process(new StructureManagerBuilder()).buildTo(jsonObject));
        return this;
    }
}
