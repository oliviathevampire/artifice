package com.swordglowsblue.artifice.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.swordglowsblue.artifice.api.ArtificeResourcePack;
import com.swordglowsblue.artifice.api.builder.JsonObjectBuilder;
import com.swordglowsblue.artifice.api.builder.TypedJsonBuilder;
import com.swordglowsblue.artifice.api.builder.assets.*;
import com.swordglowsblue.artifice.api.builder.data.AdvancementBuilder;
import com.swordglowsblue.artifice.api.builder.data.LootTableBuilder;
import com.swordglowsblue.artifice.api.builder.data.TagBuilder;
import com.swordglowsblue.artifice.api.builder.data.recipe.*;
import com.swordglowsblue.artifice.api.resource.ArtificeResource;
import com.swordglowsblue.artifice.api.resource.JsonResource;
import com.swordglowsblue.artifice.api.util.IdUtils;
import com.swordglowsblue.artifice.api.util.Processor;
import com.swordglowsblue.artifice.api.virtualpack.ArtificeResourcePackContainer;
import com.swordglowsblue.artifice.common.ArtificeRegistry;
import com.swordglowsblue.artifice.common.ClientOnly;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvironmentInterface;
import net.minecraft.SharedConstants;
import net.minecraft.class_5352;
import net.minecraft.client.resource.ClientResourcePackProfile;
import net.minecraft.client.resource.language.LanguageDefinition;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.ResourceType;
import net.minecraft.resource.metadata.ResourceMetadataReader;
import net.minecraft.util.Identifier;
import org.apache.commons.io.input.NullInputStream;
import org.apache.logging.log4j.LogManager;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
@Environment(EnvType.CLIENT)
public class ArtificeResourcePackImpl implements ArtificeResourcePack {
    private final ResourceType type;
    private final Set<String> namespaces = new HashSet<>();
    private final Map<Identifier, ArtificeResource> resources = new HashMap<>();
    private final Set<LanguageDefinition> languages = new HashSet<>();
    private final JsonResource<JsonObject> metadata;

    private String description;
    private String displayName;
    private boolean optional;
    private boolean visible;

    @SuppressWarnings("unchecked")
    public <T extends ResourcePackBuilder> ArtificeResourcePackImpl(ResourceType type, Consumer<T> registerResources) {
        this.type = type;
        registerResources.accept((T) new ArtificeResourcePackBuilder());

        JsonObject packMeta = new JsonObjectBuilder()
                        .add("pack_format", SharedConstants.getGameVersion().getPackVersion())
                        .add("description", description != null ? description : "In-memory resource pack created with Artifice")
                        .build();

        JsonObject languageMeta = new JsonObject();
        for (LanguageDefinition def : languages) {
            languageMeta.add(def.getCode(), new JsonObjectBuilder()
                            .add("name", def.getName())
                            .add("region", def.getRegion())
                            .add("bidirectional", def.isRightToLeft())
                            .build());
        }

        JsonObjectBuilder builder = new JsonObjectBuilder();
        builder.add("pack", packMeta);
        if (languages.size() > 0) builder.add("language", languageMeta);
        this.metadata = new JsonResource<>(builder.build());
    }

    @EnvironmentInterface(value = EnvType.CLIENT, itf = ClientResourcePackBuilder.class)
    private final class ArtificeResourcePackBuilder implements ClientResourcePackBuilder, ServerResourcePackBuilder {
        private ArtificeResourcePackBuilder() {
        }

        public void setDisplayName(String name) {
            ArtificeResourcePackImpl.this.displayName = name;
        }

        public void setDescription(String desc) {
            ArtificeResourcePackImpl.this.description = desc;
        }

        public void setVisible() {
            ArtificeResourcePackImpl.this.visible = true;
        }

        public void setOptional() {
            ArtificeResourcePackImpl.this.optional = true;
            ArtificeResourcePackImpl.this.visible = true;
        }

        public void add(Identifier id, ArtificeResource resource) {
            ArtificeResourcePackImpl.this.resources.put(id, resource);
            ArtificeResourcePackImpl.this.namespaces.add(id.getNamespace());
        }

        public void addItemModel(Identifier id, Processor<ModelBuilder> f) {
            this.add("models/item/", id, ".json", f, ModelBuilder::new);
        }

        public void addBlockModel(Identifier id, Processor<ModelBuilder> f) {
            this.add("models/block/", id, ".json", f, ModelBuilder::new);
        }

        public void addBlockState(Identifier id, Processor<BlockStateBuilder> f) {
            this.add("blockstates/", id, ".json", f, BlockStateBuilder::new);
        }

        public void addTranslations(Identifier id, Processor<TranslationBuilder> f) {
            this.add("lang/", id, ".json", f, TranslationBuilder::new);
        }

        public void addParticle(Identifier id, Processor<ParticleBuilder> f) {
            this.add("particles/", id, ".json", f, ParticleBuilder::new);
        }

        public void addItemAnimation(Identifier id, Processor<AnimationBuilder> f) {
            this.add("textures/item/", id, ".mcmeta", f, AnimationBuilder::new);
        }

        public void addBlockAnimation(Identifier id, Processor<AnimationBuilder> f) {
            this.add("textures/block/", id, ".mcmeta", f, AnimationBuilder::new);
        }

        public void addLanguage(LanguageDefinition def) {
            ArtificeResourcePackImpl.this.languages.add(def);
        }

        public void addLanguage(String code, String region, String name, boolean rtl) {
            this.addLanguage(new LanguageDefinition(code, region, name, rtl));
        }

        public void addAdvancement(Identifier id, Processor<AdvancementBuilder> f) {
            this.add("advancements/", id, ".json", f, AdvancementBuilder::new);
        }

        public void addLootTable(Identifier id, Processor<LootTableBuilder> f) {
            this.add("loot_tables/", id, ".json", f, LootTableBuilder::new);
        }

        public void addItemTag(Identifier id, Processor<TagBuilder> f) {
            this.add("tags/items/", id, ".json", f, TagBuilder::new);
        }

        public void addBlockTag(Identifier id, Processor<TagBuilder> f) {
            this.add("tags/blocks/", id, ".json", f, TagBuilder::new);
        }

        public void addEntityTypeTag(Identifier id, Processor<TagBuilder> f) {
            this.add("tags/entity_types/", id, ".json", f, TagBuilder::new);
        }

        public void addFluidTag(Identifier id, Processor<TagBuilder> f) {
            this.add("tags/fluids/", id, ".json", f, TagBuilder::new);
        }

        public void addFunctionTag(Identifier id, Processor<TagBuilder> f) {
            this.add("tags/functions/", id, ".json", f, TagBuilder::new);
        }

        public void addGenericRecipe(Identifier id, Processor<GenericRecipeBuilder> f) {
            this.add("recipes/", id, ".json", f, GenericRecipeBuilder::new);
        }

        public void addShapedRecipe(Identifier id, Processor<ShapedRecipeBuilder> f) {
            this.add("recipes/", id, ".json", f, ShapedRecipeBuilder::new);
        }

        public void addShapelessRecipe(Identifier id, Processor<ShapelessRecipeBuilder> f) {
            this.add("recipes/", id, ".json", f, ShapelessRecipeBuilder::new);
        }

        public void addStonecuttingRecipe(Identifier id, Processor<StonecuttingRecipeBuilder> f) {
            this.add("recipes/", id, ".json", f, StonecuttingRecipeBuilder::new);
        }

        public void addSmeltingRecipe(Identifier id, Processor<CookingRecipeBuilder> f) {
            this.add("recipes/", id, ".json", r -> f.process(r.type(new Identifier("smelting"))), CookingRecipeBuilder::new);
        }

        public void addBlastingRecipe(Identifier id, Processor<CookingRecipeBuilder> f) {
            this.add("recipes/", id, ".json", r -> f.process(r.type(new Identifier("blasting"))), CookingRecipeBuilder::new);
        }

        public void addSmokingRecipe(Identifier id, Processor<CookingRecipeBuilder> f) {
            this.add("recipes/", id, ".json", r -> f.process(r.type(new Identifier("smoking"))), CookingRecipeBuilder::new);
        }

        public void addCampfireRecipe(Identifier id, Processor<CookingRecipeBuilder> f) {
            this.add("recipes/", id, ".json", r -> f.process(r.type(new Identifier("campfire_cooking"))), CookingRecipeBuilder::new);
        }

        private <T extends TypedJsonBuilder<? extends JsonResource>> void add(String path, Identifier id, String ext, Processor<T> f, Supplier<T> ctor) {
            this.add(IdUtils.wrapPath(path, id, ext), f.process(ctor.get()).build());
        }
    }

    public InputStream openRoot(String fname) {
        if (fname.equals("pack.mcmeta")) return metadata.toInputStream();
        return new NullInputStream(0);
    }

    public InputStream open(ResourceType type, Identifier id) throws IOException {
        if (!contains(type, id)) throw new FileNotFoundException(id.getPath());
        return this.resources.get(id).toInputStream();
    }

    @Override
    public Collection<Identifier> findResources(ResourceType type, String namespace, String prefix, int maxDepth, Predicate<String> pathFilter) {
        if (type != this.type) return new HashSet<>();
        Set<Identifier> keys = new HashSet<>(this.resources.keySet());
        keys.removeIf(id -> !id.getPath().startsWith(prefix) || !pathFilter.test(id.getPath()));
        return keys;
    }

    public boolean contains(ResourceType type, Identifier id) {
        return type == this.type && this.resources.containsKey(id);
    }

    public <T> T parseMetadata(ResourceMetadataReader<T> reader) {
        return metadata.getData().has(reader.getKey())
                        ? reader.fromJson(metadata.getData().getAsJsonObject(reader.getKey()))
                        : null;
    }

    public Set<String> getNamespaces(ResourceType type) {
        return new HashSet<>(this.namespaces);
    }

    public ResourceType getType() {
        return this.type;
    }

    public boolean isOptional() {
        return this.optional;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public void close() {
    }

    public String getName() {
        if (displayName == null) {
            switch (this.type) {
            case CLIENT_RESOURCES:
                Identifier aid = ArtificeRegistry.ASSETS.getId(this);
                return displayName = aid != null ? aid.toString() : "Generated Resources";
            case SERVER_DATA:
                Identifier did = ArtificeRegistry.DATA.getId(this);
                return displayName = did != null ? did.toString() : "Generated Data";
            }
        }
        return displayName;
    }

    @Override
    public <T extends ResourcePackProfile> ClientOnly<ClientResourcePackProfile> toClientResourcePackProfile(ResourcePackProfile.class_5351<T> factory) {
        Identifier id = ArtificeRegistry.ASSETS.getId(this);
        assert id != null;
        ClientResourcePackProfile profile = new ArtificeResourcePackContainer(this.optional, this.visible, Objects.requireNonNull(ResourcePackProfile.of(
                id.toString(),
                false, () -> this, factory,
                this.optional ? ResourcePackProfile.InsertionPosition.TOP : ResourcePackProfile.InsertionPosition.BOTTOM, class_5352.field_25347
        )));

        return new ClientOnly<>(profile);
    }

    public ArtificeResourcePackContainer getAssetsContainer(ResourcePackProfile.class_5351<?> factory) {
        return (ArtificeResourcePackContainer) toClientResourcePackProfile(factory).get();
    }

    @Override
    public <T extends ResourcePackProfile> ResourcePackProfile toServerResourcePackProfile(ResourcePackProfile.class_5351<T> factory) {
        Identifier id = ArtificeRegistry.DATA.getId(this);
        assert id != null;
        return ResourcePackProfile.of(
                        id.toString(),
                        false, () -> this, factory,
                        ResourcePackProfile.InsertionPosition.BOTTOM, class_5352.field_25350
        );
    }

    public ResourcePackProfile getDataContainer(ResourcePackProfile.class_5351<?> factory) {
        return toServerResourcePackProfile(factory);
    }

    public void dumpResources(String folderPath) throws IOException, IllegalArgumentException {
        LogManager.getLogger().info("[Artifice] Dumping " + getName() + " " + type.getDirectory() + " to " + folderPath + ", this may take a while.");

        File dir = new File(folderPath);
        if (!dir.exists() && !dir.mkdirs()) {
            throw new IOException("Can't dump resources to " + folderPath + "; couldn't create parent directories");
        }
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("Can't dump resources to " + folderPath + " as it's not a directory");
        }
        if (!dir.canWrite()) {
            throw new IOException("Can't dump resources to " + folderPath + "; permission denied");
        }

        writeResourceFile(new File(folderPath + "/pack.mcmeta"), metadata);
        resources.forEach((id, resource) -> {
            String path = String.format("./%s/%s/%s/%s", folderPath, this.type.getDirectory(), id.getNamespace(), id.getPath());
            writeResourceFile(new File(path), resource);
        });

        LogManager.getLogger().info("[Artifice] Finished dumping " + getName() + " " + type.getDirectory() + ".");
    }

    private void writeResourceFile(File output, ArtificeResource resource) {
        try {
            if (output.getParentFile().exists() || output.getParentFile().mkdirs()) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(output));
                if (resource.getData() instanceof JsonElement) {
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    writer.write(gson.toJson(resource.getData()));
                } else {
                    writer.write(resource.getData().toString());
                }
                writer.close();
            } else {
                throw new IOException("Failed to dump resource file " + output.getPath() + "; couldn't create parent directories");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
