package com.swordglowsblue.artifice.api.builder.data.worldgen.features.trees;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.swordglowsblue.artifice.api.builder.TypedJsonBuilder;
import com.swordglowsblue.artifice.api.resource.JsonResource;
import com.swordglowsblue.artifice.api.util.Processor;

public class TreeFeatureBuilder extends TypedJsonBuilder<JsonResource<JsonObject>> {
    public TreeFeatureBuilder() {
        super(new JsonObject(), JsonResource::new);
        this.root.add("config", new JsonObject());
        this.root.get("config").getAsJsonObject().add("decorators", new JsonArray());
    }

    /**
     * @param id ID of an existing feature.
     * @return this
     */
    public TreeFeatureBuilder name(String id) {
        this.root.addProperty("name", id);
        return this;
    }

    public TreeFeatureBuilder maxWaterDepth(int max_water_depth) {
        this.root.get("config").getAsJsonObject().addProperty("max_water_depth", max_water_depth);
        return this;
    }

    public TreeFeatureBuilder ignoreVines(boolean ignore_vines) {
        this.root.get("config").getAsJsonObject().addProperty("ignore_vines", ignore_vines);
        return this;
    }

    public TreeFeatureBuilder heightmap(String heightmap) {
        this.root.get("config").getAsJsonObject().addProperty("heightmap", heightmap);
        return this;
    }

    public TreeFeatureBuilder minimumSize(Processor<MinimumSizeBuilder> minimumSizeBuilderProcessor) {
        with(this.root.get("config").getAsJsonObject(), "minimum_size", JsonObject::new, minimumSizeBuilder -> minimumSizeBuilderProcessor.process(new MinimumSizeBuilder()).buildTo(minimumSizeBuilder));
        return this;
    }

    public TreeFeatureBuilder trunkProvider(Processor<TrunkProviderBuilder> minimumSizeBuilderProcessor) {
        with(this.root.get("config").getAsJsonObject(), "trunk_provider", JsonObject::new, minimumSizeBuilder -> minimumSizeBuilderProcessor.process(new TrunkProviderBuilder()).buildTo(minimumSizeBuilder));
        return this;
    }

    public TreeFeatureBuilder leavesProvider(Processor<LeavesProviderBuilder> minimumSizeBuilderProcessor) {
        with(this.root.get("config").getAsJsonObject(), "leaves_provider", JsonObject::new, minimumSizeBuilder -> minimumSizeBuilderProcessor.process(new LeavesProviderBuilder()).buildTo(minimumSizeBuilder));
        return this;
    }

    public TreeFeatureBuilder foliagePlacer(Processor<FoliagePlacerBuilder> minimumSizeBuilderProcessor) {
        with(this.root.get("config").getAsJsonObject(), "foliage_placer", JsonObject::new, minimumSizeBuilder -> minimumSizeBuilderProcessor.process(new FoliagePlacerBuilder()).buildTo(minimumSizeBuilder));
        return this;
    }

    public TreeFeatureBuilder trunkPlacer(Processor<TrunkPlacerBuilder> minimumSizeBuilderProcessor) {
        with(this.root.get("config").getAsJsonObject(), "trunk_placer", JsonObject::new, minimumSizeBuilder -> minimumSizeBuilderProcessor.process(new TrunkPlacerBuilder()).buildTo(minimumSizeBuilder));
        return this;
    }

    public static class MinimumSizeBuilder extends TypedJsonBuilder<JsonObject> {

        public MinimumSizeBuilder() {
            super(new JsonObject(), j->j);
        }

        public MinimumSizeBuilder limit(int limit) {
            this.root.addProperty("limit", limit);
            return this;
        }

        public MinimumSizeBuilder lowerSize(int lower_size) {
            this.root.addProperty("lower_size", lower_size);
            return this;
        }

        public MinimumSizeBuilder upperSize(int upper_size) {
            this.root.addProperty("upper_size", upper_size);
            return this;
        }

        public MinimumSizeBuilder type(String type) {
            this.root.addProperty("type", type);
            return this;
        }
    }

    public static class TrunkProviderBuilder extends TypedJsonBuilder<JsonObject> {

        public TrunkProviderBuilder() {
            super(new JsonObject(), j->j);
        }

        public TrunkProviderBuilder state(Processor<StateBuilder> processor) {
            with("state", JsonObject::new, minimumSizeBuilder -> processor.process(new StateBuilder()).buildTo(minimumSizeBuilder));
            return this;
        }

        public TrunkProviderBuilder type(String type) {
            this.root.addProperty("type", type);
            return this;
        }

        public static class StateBuilder extends TypedJsonBuilder<JsonObject> {

            public StateBuilder() {
                super(new JsonObject(), j->j);
                this.root.add("Properties", new JsonObject());
            }

            public StateBuilder axis(String axis) {
                this.root.get("Properties").getAsJsonObject().addProperty("axis", axis);
                return this;
            }

            public StateBuilder name(String name) {
                this.root.addProperty("Name", name);
                return this;
            }

        }

    }

    public static class LeavesProviderBuilder extends TypedJsonBuilder<JsonObject> {

        public LeavesProviderBuilder() {
            super(new JsonObject(), j->j);
//            this.root.add("Properties", new JsonObject());
        }

        /*public LeavesProviderBuilder axis(String axis) {
            this.root.get("Properties").getAsJsonObject().addProperty("axis", axis);
            return this;
        }*/

        public LeavesProviderBuilder state(Processor<StateBuilder> processor) {
            with("state", JsonObject::new, minimumSizeBuilder -> processor.process(new StateBuilder()).buildTo(minimumSizeBuilder));
            return this;
        }

        public LeavesProviderBuilder type(String type) {
            this.root.addProperty("type", type);
            return this;
        }

        public static class StateBuilder extends TypedJsonBuilder<JsonObject> {

            public StateBuilder() {
                super(new JsonObject(), j->j);
//                this.root.add("Properties", new JsonObject());
            }

            /*public TrunkProviderBuilder.StateBuilder axis(String axis) {
                this.root.get("Properties").getAsJsonObject().addProperty("axis", axis);
                return this;
            }*/

            public StateBuilder name(String name) {
                this.root.addProperty("Name", name);
                return this;
            }

        }
    }

    public static class FoliagePlacerBuilder extends TypedJsonBuilder<JsonObject> {

        public FoliagePlacerBuilder() {
            super(new JsonObject(), j->j);
        }

        public FoliagePlacerBuilder radius(int radius) {
            this.root.addProperty("radius", radius);
            return this;
        }

        public FoliagePlacerBuilder offset(int offset) {
            this.root.addProperty("offset", offset);
            return this;
        }

        public FoliagePlacerBuilder height(int height) {
            this.root.addProperty("height", height);
            return this;
        }

        public FoliagePlacerBuilder type(String type) {
            this.root.addProperty("type", type);
            return this;
        }
    }

    public static class TrunkPlacerBuilder extends TypedJsonBuilder<JsonObject> {

        public TrunkPlacerBuilder() {
            super(new JsonObject(), j->j);
        }

        public TrunkPlacerBuilder baseHeight(int base_height) {
            this.root.addProperty("base_height", base_height);
            return this;
        }

        public TrunkPlacerBuilder heightRandA(int height_rand_a) {
            this.root.addProperty("height_rand_a", height_rand_a);
            return this;
        }

        public TrunkPlacerBuilder heightRandB(int height_rand_b) {
            this.root.addProperty("height_rand_b", height_rand_b);
            return this;
        }

        public TrunkPlacerBuilder type(String type) {
            this.root.addProperty("type", type);
            return this;
        }
    }

}
