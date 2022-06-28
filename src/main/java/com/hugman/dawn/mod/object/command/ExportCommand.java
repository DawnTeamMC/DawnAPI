package com.hugman.dawn.mod.object.command;

import com.google.gson.JsonElement;
import com.hugman.dawn.mod.util.data.BlockData;
import com.hugman.dawn.mod.util.data.DataList;
import com.hugman.dawn.mod.util.data.DataSerialization;
import com.hugman.dawn.mod.util.data.EnchantmentData;
import com.hugman.dawn.mod.util.data.EntityTypeData;
import com.hugman.dawn.mod.util.data.ItemData;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.JsonOps;
import net.minecraft.block.Block;
import net.minecraft.data.DataCache;
import net.minecraft.data.report.WorldgenProvider;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.RegistryOps;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.GeneratorOptions;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import net.minecraft.world.level.LevelProperties;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ExportCommand
{
	public static final int PERMISSION_LEVEL = 3;
	public static final String NAME = "export";
	public static final String REGISTRIES_ARG = "registries";
	public static final String EXPANDED_ARG = "expanded";
	public static final String WORLDGEN_ARG = "worldgen";
	public static final String BUILTIN_ARG = "builtin";

	public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
		dispatcher.register(CommandManager.literal(NAME)
				.requires(sc -> sc.hasPermissionLevel(PERMISSION_LEVEL))
				.then(CommandManager.literal(REGISTRIES_ARG)
						.executes(cc -> exportRegistries(cc.getSource(), false))
						.then(CommandManager.argument(EXPANDED_ARG, BoolArgumentType.bool())
								.executes(cc -> exportRegistries(cc.getSource(), BoolArgumentType.getBool(cc, EXPANDED_ARG)))))
				.then(CommandManager.literal(WORLDGEN_ARG)
						.executes(cc -> exportWorldGen(cc.getSource(), false))
						.then(CommandManager.argument(BUILTIN_ARG, BoolArgumentType.bool())
								.executes(cc -> exportWorldGen(cc.getSource(), BoolArgumentType.getBool(cc, BUILTIN_ARG))))));
	}

	public static int exportRegistries(ServerCommandSource source, boolean expanded) {
		ArrayList<Registry<?>> registries = new ArrayList<>();
		registries.addAll(Registry.REGISTRIES.stream().toList());
		registries.addAll(BuiltinRegistries.REGISTRIES.stream().toList());

		source.sendFeedback(new TranslatableText("commands.export.start"), true);
		try {
			for(Registry<?> registry : registries) {
				exportRegistry(registry, expanded);
			}
		} catch(IOException e) {
			source.sendError(new TranslatableText("commands.export.fail.unknown"));
			e.printStackTrace();
			return 0;
		}

		Path finalExportPath = Paths.get("debug").resolve("export").resolve("registry_entries");
		Text exportFileComponent = new LiteralText(finalExportPath.toString()).formatted(Formatting.BLUE, Formatting.UNDERLINE).styled(text -> text.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, finalExportPath.toString())).withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslatableText("chat.fileExplorer.click"))));
		source.sendFeedback(new TranslatableText("commands.export.success", exportFileComponent), true);
		return 1;
	}

	public static <T> void exportRegistry(Registry<T> registry, boolean expanded) throws IOException {
		Path entriesPath = Paths.get("debug").resolve("export").resolve("registry_entries");

		String[] modIds = registry.getIds().stream().map(Identifier::getNamespace).distinct().toArray(String[]::new);
		for(String modId : modIds) {
			Path path = entriesPath
					.resolve(modId)
					.resolve(registry.getKey().getValue().getNamespace())
					.resolve(registry.getKey().getValue().getPath() + ".json");
			Set<Map.Entry<RegistryKey<T>, T>> entries = registry.getEntrySet().stream().filter(entry -> entry.getKey().getValue().getNamespace().equals(modId)).collect(Collectors.toSet());

			DataList<?> dataList = null;
			if(expanded) {
				if(registry == Registry.BLOCK) {
					dataList = new DataList<>(entries.stream().map(entry -> new BlockData(entry.getKey().getValue(), (Block) entry.getValue())).toList());
				}
				else if(registry == Registry.ITEM) {
					dataList = new DataList<>(entries.stream().map(entry -> new ItemData(entry.getKey().getValue(), (Item) entry.getValue())).toList());
				}
				else if(registry == Registry.ENCHANTMENT) {
					dataList = new DataList<>(entries.stream().map(entry -> new EnchantmentData(entry.getKey().getValue(), (Enchantment) entry.getValue())).toList());
				}
				else if(registry == Registry.ENTITY_TYPE) {
					dataList = new DataList<>(entries.stream().map(entry -> new EntityTypeData(entry.getKey().getValue(), (EntityType<?>) entry.getValue())).toList());
				}
			}

			if(dataList == null) {
				dataList = new DataList<>(entries.stream().map(e -> e.getKey().getValue()).toList());
			}
			Files.createDirectories(path.getParent());
			DataSerialization.saveToFile(path.toFile(), dataList.getClass(), dataList);
		}
	}

	public static int exportWorldGen(ServerCommandSource source, boolean builtin) {
		if(!source.getServer().isSingleplayer() && source.getServer().getCurrentPlayerCount() > 1) {
			source.sendError(new TranslatableText("commands.export.worldgen.fail.multiplayer"));
			return 0;
		}

		Path finalExportPath = Paths.get("debug").resolve("export").resolve("world_gen");
		Path exportPath = finalExportPath.resolve("cache");
		Text exportFileComponent = new LiteralText(finalExportPath.toString()).formatted(Formatting.BLUE, Formatting.UNDERLINE).styled(text -> text.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, finalExportPath.toString())).withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslatableText("chat.fileExplorer.click"))));

		if(exportPath.toFile().exists()) {
			source.sendError(new TranslatableText("commands.export.worldgen.fail.already_exists", exportFileComponent));
			return 0;
		}
		source.sendFeedback(new TranslatableText("commands.export.start"), true);

		try {
			DataCache cache = new DataCache(exportPath, "cache");

			DynamicRegistryManager registry = builtin ? DynamicRegistryManager.createAndLoad() : source.getWorld().getRegistryManager();

			DynamicOps<JsonElement> ops = RegistryOps.of(JsonOps.INSTANCE, registry);
			DynamicRegistryManager.getInfos().forEach(info -> WorldgenProvider.writeRegistryEntries(cache, exportPath, registry, ops, info));

			Registry<DimensionOptions> worldSettings;
			if(builtin) {
				Registry<DimensionOptions> defaultDimensions = DimensionType.createDefaultDimensionOptions(registry, 0L, false);
				NoiseChunkGenerator chunkGenerator = GeneratorOptions.createOverworldGenerator(registry, 0L, false);
				worldSettings = GeneratorOptions.getRegistryWithReplacedOverworldGenerator(registry.getManaged(Registry.DIMENSION_TYPE_KEY), defaultDimensions, chunkGenerator);
			}
			else {
				worldSettings = ((LevelProperties) source.getServer().getOverworld().getLevelProperties()).getGeneratorOptions().getDimensions();
			}
			WorldgenProvider.writeRegistryEntries(exportPath, cache, ops, Registry.DIMENSION_KEY, worldSettings, DimensionOptions.CODEC);

			// Move reports to final export path
			Path result = exportPath.resolve("reports").resolve("worldgen");
			Files.walk(result).sorted(Comparator.reverseOrder()).forEach(path -> {
				try {
					if(path.getFileName().toString().endsWith(".json")) {
						String newTarget = result.relativize(path).toString();
						Path newPath = finalExportPath.resolve(newTarget);
						Files.createDirectories(newPath.getParent());
						byte[] bytes = Files.readAllBytes(path);
						Files.write(newPath, bytes);
					}

					Files.delete(path);
				} catch(IOException e) {
					e.printStackTrace();
				}
			});

			// Delete the cache
			Files.walk(exportPath).sorted(Comparator.reverseOrder()).forEach(path -> {
				try {
					Files.delete(path);
				} catch(IOException e) {
					e.printStackTrace();
				}
			});

			source.sendFeedback(new TranslatableText("commands.export.success", exportFileComponent), true);
			return 1;
		} catch(IOException e) {
			source.sendError(new TranslatableText("commands.export.fail.unknown"));
			e.printStackTrace();
			return 0;
		}
	}
}