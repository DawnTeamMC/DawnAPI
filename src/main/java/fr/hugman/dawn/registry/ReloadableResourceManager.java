package fr.hugman.dawn.registry;

import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Class used to register a reloadable resource (resource/data pack).
 * It provides some utility methods that mimic the ones from {@link net.minecraft.registry.Registry the vanilla registry class}.
 *
 * @param <R> the resource
 *
 * @author Hugman
 * @since 4.1.0
 */
public class ReloadableResourceManager<R> {
	private final Logger LOGGER = LogUtils.getLogger();
	private final Codec<R> codec;
	private final Codec<R> entryCodec;
	private final ResourceType type;
	private final String folderPath;
	private Identifier id;
	private Map<Identifier, R> map = ImmutableMap.of();

	private ReloadableResourceManager(Codec<R> codec, ResourceType type, String folderPath) {
		this.codec = codec;
		this.type = type;
		this.folderPath = folderPath;

		this.entryCodec = Codec.either(codec, Identifier.CODEC).xmap(
				(either) -> either.map(r -> r, id -> this.map.get(id)),
				r -> (this.map.containsValue(r)) ? Either.right(getId(r)) : Either.left(r));
	}

	/**
	 * Factory method to create a new {@link ReloadableResourceManager}.
	 *
	 * @param codec      the codec used to deserialize the resource
	 * @param type       the {@link ResourceType type} of the resource
	 * @param folderPath the path to the folder containing the resource (must not end with a slash)
	 * @param <R>        the resource
	 */
	public static <R> ReloadableResourceManager<R> of(@NotNull Codec<R> codec, @NotNull ResourceType type, @NotNull String folderPath) {
		Objects.requireNonNull(codec);
		Objects.requireNonNull(type);
		Objects.requireNonNull(folderPath);
		if(folderPath.endsWith("/")) {
			throw new IllegalArgumentException("The folder path must not end with a slash.");
		}
		return new ReloadableResourceManager<>(codec, type, folderPath);
	}

	@Nullable
	public R get(Identifier id) {
		return this.map.get(id);
	}

	public R getOrThrow(Identifier id) {
		R object = this.get(id);
		if(object == null) {
			throw new IllegalStateException("Missing key in " + this.id + ": " + id);
		}
		return object;
	}

	public Identifier getId() {
		return id;
	}

	public Set<Identifier> getIds() {
		return this.map.keySet();
	}

	public boolean contains(R resource) {
		return this.map.containsValue(resource);
	}

	public boolean containsId(Identifier id) {
		return this.map.containsKey(id);
	}

	/**
	 * Returns the {@link Codec} that can encode/decode a resource of type {@code R} or a {@link Identifier} to a resource of type {@code R}.
	 */
	public Codec<R> getEntryCodec() {
		return entryCodec;
	}

	/**
	 * Returns the key of the given value in the manager.
	 */
	public Identifier getId(R value) {
		var opt = this.map.entrySet().stream().filter(entry -> Objects.equals(entry.getValue(), value)).findFirst();
		if(opt.isPresent()) {
			return opt.get().getKey();
		}
		throw new IllegalArgumentException("The given value does not have a key.");
	}

	/**
	 * Registers a {@link SimpleSynchronousResourceReloadListener} to the {@link ResourceManagerHelper}. Must be called once during {@link net.fabricmc.api.ModInitializer#onInitialize()}.
	 *
	 * @param id the id of the resource manager. It is usually very similar to the folder path.
	 */
	public void register(@NotNull Identifier id) {
		this.id = id;
		ResourceManagerHelper.get(type).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
			@Override
			public Identifier getFabricId() {
				return id;
			}

			@Override
			public void reload(ResourceManager manager) {
				var resources = manager.findResources(folderPath, path -> path.getPath().endsWith(".json"));

				ImmutableMap.Builder<Identifier, R> builder = ImmutableMap.builder();
				for(var path : resources.entrySet()) {
					try {
						try(Reader reader = new BufferedReader(new InputStreamReader(path.getValue().getInputStream()))) {
							JsonElement json = new JsonParser().parse(reader);
							String idPath = path.getKey().getPath();
							idPath = idPath.substring(folderPath.length() + 1, idPath.length() - ".json".length());
							Identifier resourceId = new Identifier(path.getKey().getNamespace(), idPath);
							DataResult<R> result = codec.decode(JsonOps.INSTANCE, json).map(Pair::getFirst);
							result.resultOrPartial(error -> LOGGER.error("Error while decoding resource of type {} at {}: {}", id, resourceId, error))
									.ifPresent(resource -> builder.put(resourceId, resource));
						}
					} catch(IOException e) {
						LOGGER.error("Failed to decode resource of type {} at {}: {}", id, path, e);
					}
				}
				map = builder.build();
			}
		});
	}
}
