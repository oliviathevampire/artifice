package com.swordglowsblue.artifice.api.builder.data.worldgen.biome;

import com.google.gson.JsonObject;
import com.swordglowsblue.artifice.api.builder.TypedJsonBuilder;
import com.swordglowsblue.artifice.api.util.Processor;

public class BiomeEffectsBuilder extends TypedJsonBuilder<JsonObject> {
    public BiomeEffectsBuilder() {
        super(new JsonObject(), j->j);
    }


    /**
     * @param fogColor RGB value.
     * @return BiomeBuilder
     */
    public BiomeEffectsBuilder fogColor(int fogColor) {
        this.root.addProperty("fog_color", fogColor);
        return this;
    }


    /**
     * @param waterColor RGB value.
     * @return BiomeBuilder
     */
    public BiomeEffectsBuilder waterColor(int waterColor) {
        this.root.addProperty("water_color", waterColor);
        return this;
    }


    /**
     * @param waterFogColor RGB value.
     * @return BiomeBuilder
     */
    public BiomeEffectsBuilder waterFogColor(int waterFogColor) {
        this.root.addProperty("water_fog_color", waterFogColor);
        return this;
    }

    public BiomeEffectsBuilder ambientSound(String soundID) {
        this.root.addProperty("ambient_sound", soundID);
        return this;
    }

    public BiomeEffectsBuilder moodSound(Processor<BiomeMoodSoundBuilder> biomeMoodSoundBuilder) {
        with("mood_sound", JsonObject::new, biomeMoodSound -> biomeMoodSoundBuilder.process(new BiomeMoodSoundBuilder()).buildTo(biomeMoodSound));
        return this;
    }

    public BiomeEffectsBuilder additionsSound(Processor<BiomeAdditionsSoundBuilder> biomeAdditionsSoundBuilder) {
        with("additions_sound", JsonObject::new, biomeAdditionsSound -> biomeAdditionsSoundBuilder.process(new BiomeAdditionsSoundBuilder()).buildTo(biomeAdditionsSound));
        return this;
    }

    public BiomeEffectsBuilder music(Processor<BiomeMusicSoundBuilder> biomeMusicSoundBuilder) {
        with("music", JsonObject::new, biomeMusicSound -> biomeMusicSoundBuilder.process(new BiomeMusicSoundBuilder()).buildTo(biomeMusicSound));
        return this;
    }

    public BiomeEffectsBuilder particle(Processor<BiomeParticleConfigBuilder> biomeParticleConfigBuilder) {
        with("particle", JsonObject::new, biomeParticleConfig -> biomeParticleConfigBuilder.process(new BiomeParticleConfigBuilder()).buildTo(biomeParticleConfig));
        return this;
    }

    public static class BiomeMoodSoundBuilder extends TypedJsonBuilder<JsonObject> {

        public BiomeMoodSoundBuilder() {
            super(new JsonObject(), j->j);
        }

        public BiomeMoodSoundBuilder tickDelay(int tick_delay) {
            this.root.addProperty("tick_delay", tick_delay);
            return this;
        }

        public BiomeMoodSoundBuilder blockSearchExtent(int block_search_extent) {
            this.root.addProperty("block_search_extent", block_search_extent);
            return this;
        }

        public BiomeMoodSoundBuilder offset(double offset) {
            this.root.addProperty("offset", offset);
            return this;
        }

        public BiomeMoodSoundBuilder soundID(String soundID) {
            this.root.addProperty("sound", soundID);
            return this;
        }
    }

    public static class BiomeMusicSoundBuilder extends TypedJsonBuilder<JsonObject> {

        public BiomeMusicSoundBuilder() {
            super(new JsonObject(), j->j);
        }

        public BiomeMusicSoundBuilder minDelay(int min_delay) {
            this.root.addProperty("min_delay", min_delay);
            return this;
        }

        public BiomeMusicSoundBuilder maxDelay(int max_delay) {
            this.root.addProperty("max_delay", max_delay);
            return this;
        }

        public BiomeMusicSoundBuilder replaceCurrentMusic(boolean replace_current_music) {
            this.root.addProperty("replace_current_music", replace_current_music);
            return this;
        }

        public BiomeMusicSoundBuilder soundID(String soundID) {
            this.root.addProperty("sound", soundID);
            return this;
        }
    }

    public static class BiomeAdditionsSoundBuilder extends TypedJsonBuilder<JsonObject> {

        public BiomeAdditionsSoundBuilder() {
            super(new JsonObject(), j->j);
        }

        public BiomeAdditionsSoundBuilder tickChance(double tick_chance) {
            this.root.addProperty("tick_chance", tick_chance);
            return this;
        }

        public BiomeAdditionsSoundBuilder soundID(String soundID) {
            this.root.addProperty("sound", soundID);
            return this;
        }
    }

    public static class BiomeParticleConfigBuilder extends TypedJsonBuilder<JsonObject> {

        public BiomeParticleConfigBuilder() {
            super(new JsonObject(), j->j);
        }

        public BiomeParticleConfigBuilder probability(float probability) {
            this.root.addProperty("probability", probability);
            return this;
        }

        public BiomeParticleConfigBuilder particleID(String id) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("type", id);
            this.root.add("options", jsonObject);
            return this;
        }

    }
}