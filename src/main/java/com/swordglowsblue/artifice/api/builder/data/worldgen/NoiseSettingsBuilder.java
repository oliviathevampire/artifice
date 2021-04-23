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
     * Set the bedrock roof position.
     * @param aquifersEnabled
     * @return
     */
    public NoiseSettingsBuilder aquifersEnabled(boolean aquifersEnabled) {
        this.root.addProperty("aquifers_enabled", aquifersEnabled);
        return this;
    }

    /**
     * Set the bedrock roof position.
     * @param noiseCavesEnabled
     * @return
     */
    public NoiseSettingsBuilder noiseCavesEnabled(boolean noiseCavesEnabled) {
        this.root.addProperty("noise_caves_enabled", noiseCavesEnabled);
        return this;
    }

    /**
     * Set the bedrock roof position.
     * @param deepslateEnabled
     * @return
     */
    public NoiseSettingsBuilder deepslateEnabled(boolean deepslateEnabled) {
        this.root.addProperty("deepslate_enabled", deepslateEnabled);
        return this;
    }

    /**
     * Set the bedrock roof position.
     * @param oreVeinsEnabled
     * @return
     */
    public NoiseSettingsBuilder oreVeinsEnabled(boolean oreVeinsEnabled) {
        this.root.addProperty("ore_veins_enabled", oreVeinsEnabled);
        return this;
    }

    /**
     * Set the bedrock roof position.
     * @param minSurfaceLevel
     * @return
     */
    public NoiseSettingsBuilder minSurfaceLevel(int minSurfaceLevel) {
        this.root.addProperty("min_surface_level", minSurfaceLevel);
        return this;
    }

    /**
     * Set the bedrock roof position.
     * @param bedrockRoofPosition
     * @return
     */
    public NoiseSettingsBuilder bedrockRoofPosition(int bedrockRoofPosition) {
        this.root.addProperty("bedrock_roof_position", bedrockRoofPosition);
        return this;
    }

    /**
     * Set the bedrock floor position.
     * @param bedrockFloorPosition
     * @return
     */
    public NoiseSettingsBuilder bedrockFloorPosition(int bedrockFloorPosition) {
        this.root.addProperty("bedrock_floor_position", bedrockFloorPosition);
        return this;
    }

    /**
     * Set the sea level.
     * @param seaLevel
     * @return
     */
    public NoiseSettingsBuilder seaLevel(int seaLevel) {
        this.root.addProperty("sea_level", seaLevel);
        return this;
    }

    /**
     * Build noise config.
     */
    public NoiseSettingsBuilder noiseConfig(Processor<NoiseConfigBuilder> noiseConfigBuilder) {
        with("noise", JsonObject::new, jsonObject -> noiseConfigBuilder.process(new NoiseConfigBuilder()).buildTo(jsonObject));
        return this;
    }

    public NoiseSettingsBuilder disableMobGeneration(boolean disableMobGeneration) {
        this.root.addProperty("disable_mob_generation", disableMobGeneration);
        return this;
    }

    /**
     * Set a block state.
     * @param id
     * @param blockStateBuilderProcessor
     * @return
     */
    public NoiseSettingsBuilder setBlockState(String id, Processor<StateDataBuilder> blockStateBuilderProcessor) {
        with(id, JsonObject::new, jsonObject -> blockStateBuilderProcessor.process(new StateDataBuilder()).buildTo(jsonObject));
        return this;
    }

    /**
     * Build default block.
     * @param blockStateBuilderProcessor
     * @return
     */
    public NoiseSettingsBuilder defaultBlock(Processor<StateDataBuilder> blockStateBuilderProcessor) {
        return this.setBlockState("default_block", blockStateBuilderProcessor);
    }

    /**
     * Build default fluid.
     * @param blockStateBuilderProcessor
     * @return
     */
    public NoiseSettingsBuilder defaultFluid(Processor<StateDataBuilder> blockStateBuilderProcessor) {
        return this.setBlockState("default_fluid", blockStateBuilderProcessor);
    }

    /**
     * Build structure manager.
     * @param structureManagerBuilder
     * @return
     */
    public NoiseSettingsBuilder structureManager(Processor<StructureManagerBuilder> structureManagerBuilder) {
        with("structures", JsonObject::new, jsonObject -> structureManagerBuilder.process(new StructureManagerBuilder()).buildTo(jsonObject));
        return this;
    }
}
